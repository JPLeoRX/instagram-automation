package bot;

import core.instagram_helpers.LoginHelper;
import core.instagram_helpers.MediaInteractionHelper;
import core.instagram_helpers.TagHelper;
import core.instagram_helpers.UserInteractionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramTagFeedRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;

import java.io.InterruptedIOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Robot {
    private static final Logger log = LogManager.getLogger();

    private Instagram4j instagram;

    private List<String> tags = new ArrayList<>();

    private int maxLikesPerHour = 60;
    private int maxFollowsPerHour = 60;
    private double intervalPeriod = (60.0 / maxLikesPerHour) * 60 * 1000;

    private String username;
    private String password;


    public Robot(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void addHashtags(String ... hashtags) {
        Collections.addAll(tags, hashtags);
    }

    public void run() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(new RobotRun(), 0, 1, TimeUnit.MINUTES);
    }

    private class RobotRun implements Runnable {
        @Override
        public void run() {
            // Chose random tag
            String tag = tags.get(new Random().nextInt(tags.size() - 1));
            log.debug("Tag chosen: " + tag);

            // Get tag feed
            InstagramFeedResult feed = TagHelper.getFeed(instagram, tag);
            List<InstagramFeedItem> feedItems = feed.getItems();

            // Select random photo from tag feed
            InstagramFeedItem feedItem = feedItems.get(new Random().nextInt(10));

            // Like it and follow its author
            MediaInteractionHelper.like(instagram, feedItem.getPk());
            UserInteractionHelper.follow(instagram, feedItem.getUser().getPk());
        }
    }

    private void login() {
        this.instagram = LoginHelper.login(username, password);
    }

    public static void main(String[] args) {
        Robot robot = new Robot("jpleorx1234", "admin1234");
        robot.addHashtags("jdm", "honda", "acura");
        robot.login();
        robot.run();
    }
}
