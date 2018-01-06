package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.*;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserFollowersResult;
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

        try {
            debug("follow", "Sending request");
            instagram.sendRequest(r);
        }

        catch (IOException e) {
            debug("follow", "Exception when sending request");
            e.printStackTrace();
            return;
        }

        debug("follow", "Followed successfully");
    }

    public static void unfollow(Instagram4j instagram, long userId) {
        debug("unfollow", "Started");

        InstagramUnfollowRequest r = new InstagramUnfollowRequest(userId);
        debug("unfollow", "Unfollow request created");

        try {
            debug("unfollow", "Sending request");
            instagram.sendRequest(r);
        }

        catch (IOException e) {
            debug("unfollow", "Exception when sending request");
            e.printStackTrace();
            return;
        }

        debug("unfollow", "Unfollowed successfully");
    }

    public static List<InstagramUserSummary> getFollowers(Instagram4j instagram, long userId) {
        debug("getFollowers", "Started");

        InstagramGetUserFollowersRequest r = new InstagramGetUserFollowersRequest(userId);
        debug("getFollowers", "Get User Followers request created");

        try {
            debug("getFollowers", "Sending request");
            return instagram.sendRequest(r).getUsers();
        }

        catch (IOException | NoClassDefFoundError e) {
            debug("getFollowers", "Exception when sending request");
            e.printStackTrace();
            return null;
        }
    }

    public static List<InstagramUserSummary> getFollowing(Instagram4j instagram, long userId) {
        debug("getFollowing", "Started");

        InstagramGetUserFollowingRequest r = new InstagramGetUserFollowingRequest(userId);
        debug("getFollowing", "Get User Following request created");

        try {
            debug("getFollowing", "Sending request");
            return instagram.sendRequest(r).getUsers();
        }

        catch (IOException | NoClassDefFoundError e) {
            debug("getFollowing", "Exception when sending request");
            e.printStackTrace();
            return null;
        }
    }

    public static InstagramUser findUser(Instagram4j instagram, String username) {
        debug("findUser", "Started");

        InstagramSearchUsernameRequest r = new InstagramSearchUsernameRequest(username);
        debug("findUser", "Find User request created");

        try {
            debug("findUser", "Sending request");
            return instagram.sendRequest(r).getUser();
        }

        catch (IOException | NoClassDefFoundError e) {
            debug("findUser", "Exception when sending request");
            e.printStackTrace();
            return null;
        }
    }
}