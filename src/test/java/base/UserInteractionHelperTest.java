package base;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserInteractionHelperTest {
    private static Instagram4j instagram;
    @BeforeClass
    public static void login() {
        Current.login();
        instagram = Current.instagram;
    }

    @Test
    public void findUser() {
        InstagramUser jpleorx = UserInteractionHelper.findUser(instagram, "jpleorx");
        assertEquals(513, jpleorx.getMedia_count());
        assertEquals(6420, jpleorx.getFollowing_count());
        assertEquals("Leo", jpleorx.getFull_name());

        InstagramUser evis_e = UserInteractionHelper.findUser(instagram, "evis_e");
        assertEquals(18, evis_e.getMedia_count());
        assertEquals(67, evis_e.getFollowing_count());
        assertEquals(58, evis_e.getFollower_count());
        assertEquals("", evis_e.getFull_name());
        assertEquals("", evis_e.getBiography());
    }
}