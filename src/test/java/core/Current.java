package core;

import core.instagram_helpers.LoginHelper;
import org.brunocvcunha.instagram4j.Instagram4j;

public class Current {
    public static Instagram4j instagram = null;

    public static void login() {
        if (instagram == null) {
            instagram = LoginHelper.login("jpleorx1234", "admin1234");
        }
    }
}
