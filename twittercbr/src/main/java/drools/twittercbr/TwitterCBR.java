package drools.twittercbr;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.WorkingMemoryEntryPoint;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

/**
 * @author Yoshimasa Tanabe
 */
public class TwitterCBR extends TwitterCBRTemplate {

  public static void main(String[] args) throws Exception {
    new TwitterCBR().invoke("twitterRules1.drl");
  }

  @Override
  protected void feedEvents(StatefulKnowledgeSession ksession, WorkingMemoryEntryPoint ep) {
    new Thread(() -> {
      StatusListener listener = new TwitterStatusListener( ep );
      TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
      twitterStream.addListener( listener );
      twitterStream.sample();
    }).start();

    ksession.fireUntilHalt();
  }

}
