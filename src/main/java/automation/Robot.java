package automation;

import automation.bot.Bot;
import automation.bot.BotRunnable;
import automation.selection.media_selection.LastMediaStrategy;
import automation.selection.MediaSelectionStrategy;
import automation.selection.tag_selection.RandomTagStrategy;
import automation.selection.TagSelectionStrategy;
import helpers.ListUtils;
import instagram.core_objects.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.brunocvcunha.instagram4j.Instagram4j;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Robot implements Bot {
    private static final Logger log = LogManager.getLogger();

    private Instagram4j instagram4j;
    private CurrentUser currentUser;
    private TagSelectionStrategy tagStrategy;
    private MediaSelectionStrategy mediaStrategy;

    public Robot(Instagram4j instagram4j, String ... hashTags) {
        this.instagram4j = instagram4j;
        this.currentUser = new CurrentUser(instagram4j, instagram4j.getUsername());

        this.tagStrategy = new RandomTagStrategy(instagram4j, hashTags);
        this.mediaStrategy = new LastMediaStrategy(instagram4j);
    }

    @Override
    public void iteration() {
        // Select random tag
        HashTag tag = tagStrategy.select();
        log.debug("Hashtag Selected: " + tag);

        // If we already interacted with all media for given hashtag - select another one
        while (!ListUtils.hasUntouched(tag.getMedia(), currentUser))
            tag = tagStrategy.select();

        // Select random pic
        Media pic = mediaStrategy.select(tag.getMedia());
        log.debug("Media Selected: " + pic);

        // If we already interacted with this picture - select another one
        while (pic.hasInLikers(currentUser) || pic.getAuthor().hasInFollowers(currentUser))
            pic = mediaStrategy.select();

        // Like the picture
        pic.like();
        log.debug("Media Liked!");

        // Follow the author
        pic.getAuthor().follow();
        currentUser.newUserFollowedByRobot(new UserForUnfollow(pic.getAuthor(), LocalDateTime.now()));
        log.debug("Author Followed: " + pic.getAuthor());
    }

    @Override
    public void start() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(new BotRunnable(this), 0, 2, TimeUnit.MINUTES);
    }

    public static void main(String[] args) {
        Login login = new Login("jpleorx1234", "admin1234");
        Instagram4j instagram4j = login.send();

        // 16-5, 12:58
        Robot robot = new Robot(instagram4j, "jdm");
        robot.start();
    }
}