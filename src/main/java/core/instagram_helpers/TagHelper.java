package core.instagram_helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramTagFeedRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;

import java.io.IOException;

public class TagHelper {
    private static final Logger log = LogManager.getLogger();

    private static void debug(String methodName, String message) {
        log.debug("Method " + methodName + "(): " + message);
    }

    public static InstagramFeedResult getFeed(Instagram4j instagram, String hashtag) {
        debug("getFeed", "Started");

        InstagramTagFeedRequest r = new InstagramTagFeedRequest(hashtag);
        debug("getFeed", "Tag Feed request created");

        try {
            debug("getFeed", "Sending request");
            return instagram.sendRequest(r);
        }

        catch (IOException e) {
            debug("getFeed", "Exception when sending request");
            e.printStackTrace();
            return null;
        }
    }
}
