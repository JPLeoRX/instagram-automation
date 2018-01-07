package core.instagram_helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.*;
import org.brunocvcunha.instagram4j.requests.payload.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class UserInteractionHelper {
    private static final Logger log = LogManager.getLogger();

    private static void debug(String methodName, String message) {
        log.debug("Method " + methodName + "(): " + message);
    }

    // Internal implementation
    //------------------------------------------------------------------------------------------------------------------
    private static StatusResult  followResult(Instagram4j instagram, long userId) {
        debug("follow", "Started");
        InstagramFollowRequest r = new InstagramFollowRequest(userId);
        debug("follow", "Follow request created");
        return RequestHelper.sendRequest("follow", instagram, r);
    }

    private static StatusResult unfollowResult(Instagram4j instagram, long userId) {
        debug("unfollow", "Started");
        InstagramUnfollowRequest r = new InstagramUnfollowRequest(userId);
        debug("unfollow", "Unfollow request created");
        return RequestHelper.sendRequest("unfollow", instagram, r);
    }

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

    private static InstagramGetUserFollowersResult getFollowersResult(Instagram4j instagram, long userId, String maxId) {
        debug("getFollowers", "Started");
        InstagramGetUserFollowersRequest r  = new InstagramGetUserFollowersRequest(userId, maxId);
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
    public static void follow(Instagram4j instagram, long userId) {
        followResult(instagram, userId);
    }

    public static void unfollow(Instagram4j instagram, long userId) {
        unfollowResult(instagram, userId);
    }

    public static InstagramUser findUser(Instagram4j instagram, String username) {
        InstagramSearchUsernameResult r = findUserResult(instagram, username);
        return r == null ? null : r.getUser();
    }

    public static List<InstagramUserSummary> getFollowers(Instagram4j instagram, long userId) {
        // Create a new user list
        List<InstagramUserSummary> list = new LinkedList<>();

        // Create initial request
        InstagramGetUserFollowersResult r = getFollowersResult(instagram, userId);

        // If the initial response was valid
        if (r != null) {
            // Store this part of the list
            list.addAll(r.getUsers());

            // While the list has next part
            while ((r.getNext_max_id() != null) && (!r.getNext_max_id().equalsIgnoreCase(""))) {
                // Create new request
                r = getFollowersResult(instagram, userId, r.getNext_max_id());

                // If the response is valid
                if (r != null)
                    // Store this part of the list
                    list.addAll(r.getUsers());
            }
        }

        // Return the list
        return list.size() == 0 ? null : list;
    }

    public static List<InstagramUserSummary> getFollowing(Instagram4j instagram, long userId) {
        InstagramGetUserFollowersResult r = getFollowingResult(instagram, userId);
        return r == null ? null : r.getUsers();
    }

    public static boolean isFollowing(Instagram4j instagram4j, long currentUserId, long possibleFollowingUserId) {
        List<InstagramUserSummary> followings = getFollowing(instagram4j, currentUserId);
        for (InstagramUserSummary userSummary : followings)
            if (userSummary.getPk() == possibleFollowingUserId)
                return true;
        return false;
    }

    public static boolean hasInFollowers(Instagram4j instagram4j, long currentUserId, long possibleFollowerUserId) {
        List<InstagramUserSummary> followers = getFollowers(instagram4j, currentUserId);
        for (InstagramUserSummary userSummary : followers)
            if (userSummary.getPk() == possibleFollowerUserId)
                return true;
        return false;
    }
    //------------------------------------------------------------------------------------------------------------------
}