package core;

import core.instagram_helpers.UserInteractionHelper;
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
    public void findUser_Invalid() {
        // Invalid - mixed string
        InstagramUser invalidUser1 = UserInteractionHelper.findUser(instagram, "2276defwssaf2e98fgfss4351");
        assertEquals(null, invalidUser1);

        // Invalid - digits only
        InstagramUser invalidUser2 = UserInteractionHelper.findUser(instagram, "12788187847473886278343162641");
        assertEquals(null, invalidUser2);

        // Invalid - letters only
        InstagramUser invalidUser3 = UserInteractionHelper.findUser(instagram, "shsdhajpjpiadpjdsjpdajhfishf");
        assertEquals(null, invalidUser3);

        // Invalid - null
        InstagramUser invalidUser4 = UserInteractionHelper.findUser(instagram, null);
        assertEquals(null, invalidUser4);

        // Invalid - empty
        InstagramUser invalidUser5 = UserInteractionHelper.findUser(instagram, "");
        assertEquals(null, invalidUser5);

        // Invalid - very long string
        InstagramUser invalidUser6 = UserInteractionHelper.findUser(instagram, "4364398438032932983jhkfdjhkfsdhgfgsgsfjjkgfsjgfsfsgsjhogiouogiipgwugpiuhjlgdjhlgdadagdgapiguiupgigsigsig437643543795478547842");
        assertEquals(null, invalidUser6);
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
    public void getFollowers_Invalid() {
        List<InstagramUserSummary> invalidFollowers1 = UserInteractionHelper.getFollowers(instagram, 0);
        assertEquals(null, invalidFollowers1);

        List<InstagramUserSummary> invalidFollowers2 = UserInteractionHelper.getFollowers(instagram, 1111111111);
        assertEquals(null, invalidFollowers2);

        List<InstagramUserSummary> invalidFollowers3 = UserInteractionHelper.getFollowers(instagram, 999999999);
        assertEquals(null, invalidFollowers3);
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

    @Test
    public void getFollowing_Invalid() {
        List<InstagramUserSummary> invalidFollowers1 = UserInteractionHelper.getFollowing(instagram, 0);
        assertEquals(null, invalidFollowers1);

        List<InstagramUserSummary> invalidFollowers2 = UserInteractionHelper.getFollowing(instagram, 1111111111);
        assertEquals(null, invalidFollowers2);

        List<InstagramUserSummary> invalidFollowers3 = UserInteractionHelper.getFollowing(instagram, 999999999);
        assertEquals(null, invalidFollowers3);
    }
}