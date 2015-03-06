package drools.twittercbr;

import org.drools.ClockType;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.conf.EventProcessingOption;
import org.drools.io.ResourceFactory;
import org.drools.runtime.KnowledgeSessionConfiguration;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.conf.ClockTypeOption;
import org.drools.runtime.rule.WorkingMemoryEntryPoint;

/**
 * @author tanabe
 */
abstract class TwitterCBRTemplate {

  static {
    // disable twitter4j log
    System.setProperty("twitter4j.loggerFactory", "twitter4j.internal.logging.NullLoggerFactory");
  }

  private StatefulKnowledgeSession ksession;

  void invoke(String... args) {
    if(args.length == 0) {
      System.err.println("Please provide the rules file name to load.");
      System.err.println("e.g. 'twitterRules1.drl'");
      System.exit( 0 );
    }

    String fileName = args[0];
    KnowledgeBase kbase = createKnowledgeBase("drools/twittercbr/" + fileName);
    this.ksession = createKnowledgeSession(kbase);
    WorkingMemoryEntryPoint ep = ksession.getWorkingMemoryEntryPoint("twitter");

    feedEvents(ksession, ep);
  }

  abstract protected void feedEvents(StatefulKnowledgeSession ksession, WorkingMemoryEntryPoint ep);

  private static KnowledgeBase createKnowledgeBase(String ruleFile) {
    KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
    kbuilder.add(ResourceFactory.newClassPathResource(ruleFile), ResourceType.DRL);

    KnowledgeBaseConfiguration kbconf = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
    kbconf.setOption(EventProcessingOption.STREAM);

    KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(kbconf);
    kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
    return kbase;
  }

  private static StatefulKnowledgeSession createKnowledgeSession(final KnowledgeBase kbase) {
    KnowledgeSessionConfiguration ksconf = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
    ksconf.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));
    return kbase.newStatefulKnowledgeSession(ksconf, null);
  }

}
