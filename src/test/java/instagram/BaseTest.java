package instagram;

import instagram.core_objects.Login;
import instagram.core_objects.User;
import org.brunocvcunha.instagram4j.Instagram4j;

import org.junit.Test;

import static org.junit.Assert.*;

public class BaseTest {
    protected static Instagram4j instagram = new Login("jpleorx1234", "admin1234").send();

    protected static User currentUser = new User(instagram, instagram.getUsername());
    protected static User jpleorx = new User(instagram, "jpleorx");
    protected static User antonovka98 = new User(instagram, "antonovka98");
    protected static User catsu07 = new User(instagram, "catsu07");
    protected static User el_ohyel = new User(instagram, "el_ohyel");
    protected static User dvayanu = new User(instagram, "dvayanu");

    protected static void assertEqualsDelta(int expected, int actual) {
        assertEquals(expected, actual, (expected / 100.0 * 5.0));
    }
}