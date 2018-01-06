package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetMediaCommentsRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetMediaLikersRequest;
import org.brunocvcunha.instagram4j.requests.InstagramLikeRequest;
import org.brunocvcunha.instagram4j.requests.InstagramPostCommentRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetMediaCommentsResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetMediaLikersResult;

import java.io.IOException;

public class MediaInteractionHelper {
    private static final Logger log = LogManager.getLogger();

    private static void debug(String methodName, String message) {
        log.debug("Method " + methodName + "(): " + message);
    }

    public static void like(Instagram4j instagram, long mediaIa) {
        debug("like", "Started");

        InstagramLikeRequest r = new InstagramLikeRequest(mediaIa);
        debug("like", "Like request created");

        try {
            debug("like", "Sending request");
            instagram.sendRequest(r);
        }

        catch (IOException e) {
            debug("like", "Exception when sending request");
            e.printStackTrace();
            return;
        }

        debug("like", "Liked successfully");
    }

    public static void comment(Instagram4j instagram, long mediaId, String comment) {
        debug("comment", "Started");

        InstagramPostCommentRequest r = new InstagramPostCommentRequest(mediaId, comment);
        debug("comment", "Comment request created");

        try {
            debug("comment", "Sending request");
            instagram.sendRequest(r);
        }

        catch (IOException e) {
            debug("comment", "Exception when sending request");
            e.printStackTrace();
            return;
        }

        debug("comment", "Commented successfully");
    }

    public static InstagramGetMediaLikersResult getLikers(Instagram4j instagram, long mediaId) {
        debug("getLikers", "Started");

        InstagramGetMediaLikersRequest r = new InstagramGetMediaLikersRequest(mediaId);
        debug("getLikers", "Get Media Likers request created");

        try {
            debug("getLikers", "Sending request");
            return instagram.sendRequest(r);
        }

        catch (IOException e) {
            debug("getLikers", "Exception when sending request");
            e.printStackTrace();
            return null;
        }
    }

    public static InstagramGetMediaCommentsResult getComments(Instagram4j instagram, long mediaId) {
        debug("getComments", "Started");

        InstagramGetMediaCommentsRequest r = new InstagramGetMediaCommentsRequest(String.valueOf(mediaId), null);
        debug("getComments", "Get Media Comments request created");

        try {
            debug("getComments", "Sending request");
            return instagram.sendRequest(r);
        }

        catch (IOException e) {
            debug("getComments", "Exception when sending request");
            e.printStackTrace();
            return null;
        }
    }
}