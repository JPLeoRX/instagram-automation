package core.instagram_helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramRequest;

import java.io.IOException;

public class RequestHelper {
    private static final Logger log = LogManager.getLogger();

    private static void debug(String methodName, String message) {
        log.debug("Method " + methodName + "(): " + message);
    }

    public static <R> R sendRequest(String methodName, Instagram4j instagram, InstagramRequest<R> request) {
        try {
            debug(methodName, "Sending request");
            return instagram.sendRequest(request);
        }

        catch (IOException | NoClassDefFoundError e) {
            debug(methodName, "Exception when sending request");
            e.printStackTrace();
            return null;
        }
    }
}