package automation;

import automation.media_selection.LastMediaStrategy;
import automation.media_selection.MediaSelectionStrategy;
import automation.tag_selection.RandomTagStrategy;
import automation.tag_selection.TagSelectionStrategy;
import instagram.ListUtils;
import instagram.core_objects.HashTag;
import instagram.core_objects.Login;
import instagram.core_objects.Media;
import instagram.core_objects.User;
import org.brunocvcunha.instagram4j.Instagram4j;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Robot {
    private Instagram4j instagram4j;
    private User currentUser;
    private TagSelectionStrategy tagStrategy;
    private MediaSelectionStrategy mediaStrategy;

    private Queue<User> followedUser = new ArrayDeque<>();

    public Robot(Instagram4j instagram4j, String ... hashTags) {
        this.instagram4j = instagram4j;
        this.currentUser = new User(instagram4j, instagram4j.getUsername());

        this.tagStrategy = new RandomTagStrategy(instagram4j, hashTags);
        this.mediaStrategy = new LastMediaStrategy(instagram4j);
    }

    public void iteration() {
        // Select random tag
        HashTag tag = tagStrategy.select();

        // If we already interacted with all media for given hashtag - select another one
        while (!ListUtils.hasUntouched(tag.getMedia(), currentUser))
            tag = tagStrategy.select();

        // Select random pic
        mediaStrategy.setOriginalList(tag.getMedia());
        Media pic = mediaStrategy.select();

        // If we already interacted with this picture - select another one
        while (pic.hasInLikers(currentUser) || pic.getAuthor().hasInFollowers(currentUser))
            pic = mediaStrategy.select();

        // Like the picture
        pic.like();

        // Follow the author
        pic.getAuthor().follow();
        followedUser.add(pic.getAuthor());
    }

    public void unfollowService() {
        User unfollowedUser = followedUser.poll();
        if (unfollowedUser != null)
            unfollowedUser.unfollow();
    }

    public void run() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(new RobotRunnable(), 0, 1, TimeUnit.MINUTES);
        service.scheduleWithFixedDelay(new UnfollowRunnable(), 10, 1, TimeUnit.MINUTES);
    }

    private class RobotRunnable implements Runnable {
        @Override
        public void run() {
            try {
                iteration();
            }

            catch (Exception e) {
                e.printStackTrace();
                run();
            }
        }
    }

    private class UnfollowRunnable implements Runnable {
        @Override
        public void run() {
            try {
                unfollowService();
            }

            catch (Exception e) {
                e.printStackTrace();
                run();
            }
        }
    }

    public static void main(String[] args) {
        Login login = new Login("jpleorx1234", "admin1234");
        Instagram4j instagram4j = login.send();

        // 13-0, 17:47
        Robot robot = new Robot(instagram4j, "jdm", "honda", "acura", "mazda", "toyota");
        robot.run();
    }
}