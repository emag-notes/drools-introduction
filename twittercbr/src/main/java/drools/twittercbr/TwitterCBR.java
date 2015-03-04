package drools.twittercbr;

import org.drools.core.time.SessionPseudoClock;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;
import twitter4j.Status;
import twitter4j.StatusListener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author Yoshimasa Tanabe
 */
public class TwitterCBR {

  public static void main(String[] args) {

    KieServices services = KieServices.Factory.get();
    KieFileSystem fileSystem = services.newKieFileSystem();
    fileSystem.write(services.getResources().newFileSystemResource("src/main/resources/drools/twittercbr/twitterRules1.drl"));
    services.newKieBuilder(fileSystem).buildAll();
    KieContainer container = services.newKieContainer(services.getRepository().getDefaultReleaseId());
    KieSession session = container.newKieSession();

    EntryPoint ep = session.getEntryPoint("twitter");

    new Thread(() -> {
      session.fireUntilHalt();
    }).start();

    feedEvents(session, ep);
    session.halt();
  }

  private static void feedEvents(KieSession session, EntryPoint ep) {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/drools/twittercbr/twitterstream.dump"))){
      StatusListener listener = new TwitterStatusListener(ep);
      SessionPseudoClock clock = session.getSessionClock();

      while (true) {
        Status status = (Status) in.readObject();
        clock.advanceTime(status.getCreatedAt().getTime() - clock.getCurrentTime(), TimeUnit.MILLISECONDS);
        listener.onStatus(status);
      }

    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

//  private static KnowledgeBase createKnowledgeBase(String ruleFile) {
//    KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
//    kbuilder.add(ResourceFactory.newClassPathResource(ruleFile), ResourceType.DRL);
//
//    KieBaseConfiguration kbconf = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();
//    kbconf.setOption(EventProcessingOption.STREAM);
//
//    KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(kbconf);
//    kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
//    return kbase;
//  }
//  private static StatefulKnowledgeSession createKnowledgeSession(final KnowledgeBase kbase) {
//    KieSessionConfiguration ksconf = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();
//    ksconf.setOption(ClockTypeOption.get(ClockType.PSEUDO_CLOCK.getId()));
//    return kbase.newStatefulKnowledgeSession(ksconf, null);
//  }
}
