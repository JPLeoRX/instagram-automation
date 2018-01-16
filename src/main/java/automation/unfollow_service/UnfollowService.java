package automation.unfollow_service;

import automation.bot.BotRunnable;
import instagram.core_objects.CurrentUser;
import instagram.core_objects.UserForUnfollow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UnfollowService {
    private static final Logger log = LogManager.getLogger();

    private CurrentUser currentUser;
    private int delayForUnfollowHours;

    public UnfollowService(CurrentUser currentUser, int delayForUnfollowHours) {
        this.currentUser = currentUser;
        this.delayForUnfollowHours = delayForUnfollowHours;
    }

    public void iteration() {
        // Get the next user for unfollow
        UserForUnfollow userForUnfollow = currentUser.nextUserToUnfollowPeek();

        // Get it's followed time
        LocalDateTime followTime = userForUnfollow.getFollowedTime();

        // Store the duration
        Duration timePassed = Duration.between(followTime, LocalDateTime.now());

        // If enough time has passed
        if (timePassed.toHours() >= delayForUnfollowHours) {
            // Unfollow
            userForUnfollow = currentUser.nextUserToUnfollowPoll();
            userForUnfollow.getUser().unfollow();
            log.debug("Unfollowed user: " + userForUnfollow.getUser() + " after " + timePassed.toHours() + " hours after follow");
        }
    }

    public void start() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(new UnfollowServiceRunnable(this), 0, 2, TimeUnit.MINUTES);
    }
}
