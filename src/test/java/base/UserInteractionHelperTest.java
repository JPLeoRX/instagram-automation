package base;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserInteractionHelperTest {
    private static Instagram4j instagram;
    private static InstagramUser jpleorx;
    private static InstagramUser evis_e;
    private static InstagramUser antonovka98;

    @BeforeClass
    public static void loginAndFindUsers() {
        Current.login();
        instagram = Current.instagram;
        jpleorx = UserInteractionHelper.findUser(instagram, "jpleorx");
        evis_e = UserInteractionHelper.findUser(instagram, "evis_e");
        antonovka98 = UserInteractionHelper.findUser(instagram, "antonovka98");
    }

    @Test
    public void findUser() {
        assertEquals(513, jpleorx.getMedia_count());
        assertEquals(6420, jpleorx.getFollowing_count());
        assertEquals("Leo", jpleorx.getFull_name());
        assertEquals("jpleorx", jpleorx.getUsername());
        assertEquals(false, jpleorx.is_private());

        assertEquals(18, evis_e.getMedia_count());
        assertEquals(67, evis_e.getFollowing_count());
        assertEquals(58, evis_e.getFollower_count());
        assertEquals("", evis_e.getFull_name());
        assertEquals("", evis_e.getBiography());
        assertEquals("evis_e", evis_e.getUsername());
        assertEquals(true, evis_e.is_private);

        assertEquals(2, antonovka98.getMedia_count());
        assertEquals(101, antonovka98.getFollowing_count());
        assertEquals(54, antonovka98.getFollower_count());
        assertEquals("Lidia Antonova", antonovka98.getFull_name());
        assertEquals("", antonovka98.getBiography());
        assertEquals("antonovka98", antonovka98.getUsername());
        assertEquals(false, antonovka98.is_private());
    }

    @Test
    public void getFollowers() {
        List<InstagramUserSummary> antonovka98Followers = UserInteractionHelper.getFollowers(instagram, antonovka98.getPk());
        assertEquals(54, antonovka98Followers.size());

        boolean hasJpleorx = false;
        boolean hasGamemem = false;
        for (InstagramUserSummary userSummary : antonovka98Followers) {
            if (userSummary.getUsername().equalsIgnoreCase("jpleorx"))
                hasJpleorx = true;
            else if (userSummary.getUsername().equalsIgnoreCase("game.mem"))
                hasGamemem = true;
        }

        assertEquals(true, hasJpleorx);
        assertEquals(true, hasGamemem);
    }

    @Test
    public void getFollowing() {
        List<InstagramUserSummary> antonovka98Following = UserInteractionHelper.getFollowing(instagram, antonovka98.getPk());
        assertEquals(101, antonovka98Following.size());

        boolean hasGreenday = false;
        boolean hasOnerepublic = false;
        for (InstagramUserSummary userSummary : antonovka98Following) {
            if (userSummary.getUsername().equalsIgnoreCase("greenday"))
                hasGreenday = true;
            else if (userSummary.getUsername().equalsIgnoreCase("onerepublic"))
                hasOnerepublic = true;
        }

        assertEquals(true, hasGreenday);
        assertEquals(true, hasOnerepublic);
    }
}