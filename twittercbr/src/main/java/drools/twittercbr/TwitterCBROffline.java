package drools.twittercbr;

import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.WorkingMemoryEntryPoint;
import org.drools.time.SessionPseudoClock;
import twitter4j.Status;
import twitter4j.StatusListener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.TimeUnit;

/**
 * @author Yoshimasa Tanabe
 */
public class TwitterCBROffline extends TwitterCBRTemplate {

  public static void main(String[] args) throws Exception {
    new TwitterCBROffline().invoke(args);
  }

  @Override
  protected void feedEvents(StatefulKnowledgeSession ksession, WorkingMemoryEntryPoint ep) {
    new Thread(() -> {
      ksession.fireUntilHalt();
    }).start();

    doFeedEvents(ksession, ep);
    ksession.halt();
  }

  private void doFeedEvents(StatefulKnowledgeSession ksession, WorkingMemoryEntryPoint ep) {
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/drools/twittercbr/twitterstream.dump"))) {
      StatusListener listener = new TwitterStatusListener(ep);
      SessionPseudoClock clock = ksession.getSessionClock();
      while (true) {
        try {
          Status status = (Status) in.readObject();
          clock.advanceTime(status.getCreatedAt().getTime() - clock.getCurrentTime(), TimeUnit.MILLISECONDS);
          listener.onStatus(status);
        } catch (IOException ioe) {
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
