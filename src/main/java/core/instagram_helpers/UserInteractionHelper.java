package core.instagram_helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.*;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserFollowersResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

import java.io.IOException;
import java.util.List;

public class UserInteractionHelper {
    private static final Logger log = LogManager.getLogger();

    private static void debug(String methodName, String message) {
        log.debug("Method " + methodName + "(): " + message);
    }

    public static void follow(Instagram4j instagram, long userId) {
        debug("follow", "Started");

        InstagramFollowRequest r = new InstagramFollowRequest(userId);
        debug("follow", "Follow request created");

        RequestHelper.sendRequest("follow", instagram, r);
        debug("follow", "Followed successfully");
    }

    public static void unfollow(Instagram4j instagram, long userId) {
        debug("unfollow", "Started");

        InstagramUnfollowRequest r = new InstagramUnfollowRequest(userId);
        debug("unfollow", "Unfollow request created");

        RequestHelper.sendRequest("unfollow", instagram, r);
        debug("unfollow", "Unfollowed successfully");
    }

    // Internal implementation
    //------------------------------------------------------------------------------------------------------------------
    private static InstagramSearchUsernameResult findUserResult(Instagram4j instagram, String username) {
        debug("findUser", "Started");

        InstagramSearchUsernameRequest r = new InstagramSearchUsernameRequest(username);
        debug("findUser", "Find User request created");

        return RequestHelper.sendRequest("findUser", instagram, r);
    }

    private static InstagramGetUserFollowersResult getFollowersResult(Instagram4j instagram, long userId) {
        debug("getFollowers", "Started");

        InstagramGetUserFollowersRequest r = new InstagramGetUserFollowersRequest(userId);
        debug("getFollowers", "Get User Followers request created");

        return RequestHelper.sendRequest("getFollowers", instagram, r);
    }

    private static InstagramGetUserFollowersResult getFollowingResult(Instagram4j instagram, long userId) {
        debug("getFollowing", "Started");

        InstagramGetUserFollowingRequest r = new InstagramGetUserFollowingRequest(userId);
        debug("getFollowing", "Get User Following request created");

        return RequestHelper.sendRequest("getFollowing", instagram, r);
    }
    //------------------------------------------------------------------------------------------------------------------



    // Public methods
    //------------------------------------------------------------------------------------------------------------------
    public static InstagramUser findUser(Instagram4j instagram, String username) {
        InstagramSearchUsernameResult r = findUserResult(instagram, username);
        return r == null ? null : r.getUser();
    }

    public static List<InstagramUserSummary> getFollowers(Instagram4j instagram, long userId) {
        InstagramGetUserFollowersResult r = getFollowersResult(instagram, userId);
        return r == null ? null : r.getUsers();
    }

    public static List<InstagramUserSummary> getFollowing(Instagram4j instagram, long userId) {
        InstagramGetUserFollowersResult r = getFollowingResult(instagram, userId);
        return r == null ? null : r.getUsers();
    }
    //------------------------------------------------------------------------------------------------------------------
}