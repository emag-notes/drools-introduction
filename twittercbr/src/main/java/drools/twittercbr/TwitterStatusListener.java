package drools.twittercbr;

import org.kie.api.runtime.rule.EntryPoint;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

/**
 * @author Yoshimasa Tanabe
 */
public class TwitterStatusListener implements StatusListener {

  private EntryPoint ep;

  public TwitterStatusListener(EntryPoint ep) {
    this.ep = ep;
  }

  @Override
  public void onStatus(Status status) {
    ep.insert(status);
  }

  @Override public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
  @Override public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
  @Override public void onScrubGeo(long userId, long upToStatusId) {}
  @Override public void onStallWarning(StallWarning warning) {}
  @Override public void onException(Exception ex) {}

}
