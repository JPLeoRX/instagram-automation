package core.instagram_helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.brunocvcunha.instagram4j.Instagram4j;

import java.io.IOException;

public class LoginHelper {
    private static final Logger log = LogManager.getLogger();

    private static void debug(String methodName, String message) {
        log.debug("Method " + methodName + "(): " + message);
    }

    public static Instagram4j login(String username, String password) {
        debug("login", "Started");

        Instagram4j instagram = Instagram4j.builder().username(username).password(password).build();
        debug("login", "Instagram4j created");

        instagram.setup();
        debug("login", "Instagram4j setted-up");


        try {
            debug("login", "Logging-in");
            instagram.login();
        }

        catch (IOException e) {
            debug("login", "Exception when logging-in");
            e.printStackTrace();
            return null;
        }

        return instagram;
    }
}