package instagram.core_objects;

import org.brunocvcunha.instagram4j.Instagram4j;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class CurrentUser extends User {
    private Queue<UserForUnfollow> followedByRobotUsers = new ArrayDeque<>();

    public CurrentUser(Instagram4j instagram4j, String username) {
        super(instagram4j, username);
    }

    public void newUserFollowedByRobot(UserForUnfollow followedUser) {
        followedByRobotUsers.add(followedUser);
    }

    public UserForUnfollow nextUserToUnfollowPoll() {
        return followedByRobotUsers.poll();
    }

    public UserForUnfollow nextUserToUnfollowPeek() {
        return followedByRobotUsers.peek();
    }
}
