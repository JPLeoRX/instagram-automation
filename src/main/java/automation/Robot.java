package automation;

import instagram.ListUtils;
import instagram.RandomCollection;
import instagram.core_objects.HashTag;
import instagram.core_objects.Login;
import instagram.core_objects.Media;
import instagram.core_objects.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.brunocvcunha.instagram4j.Instagram4j;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Robot {
    private Instagram4j instagram4j;
    private User currentUser;
    private TagSelectionStrategy tagStrategy;

    private Queue<User> followedUser = new ArrayDeque<>();

    public Robot(Instagram4j instagram4j, String ... hashTags) {
        this.instagram4j = instagram4j;
        this.currentUser = new User(instagram4j, instagram4j.getUsername());
        this.tagStrategy = new SimpleTagSelection(instagram4j, hashTags);
    }

    public void iteration() {
        // Select random tag
        HashTag tag = tagStrategy.randomTag();

        // If we already interacted with all media for given hashtag - select another one
        while (!ListUtils.hasUntouched(tag.getMedia(), currentUser))
            tag = tagStrategy.randomTag();

        // Create a randomized collection of media
        RandomCollection<Media> randomTagFeed = new RandomCollection<>(tag.getMedia());

        // Select random pic
        Media pic = randomTagFeed.getRandom();

        // If we already interacted with this picture - select another one
        while (pic.hasInLikers(currentUser) || pic.getAuthor().hasInFollowers(currentUser))
            pic = randomTagFeed.getRandom();

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