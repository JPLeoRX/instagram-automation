package instagram.core_objects;

import instagram.BaseTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest extends BaseTest {
    @Test
    public void getFollowers() {
        assertEqualsDelta(jpleorx.getNumberOfFollowers(), jpleorx.getFollowers().size());
        assertEqualsDelta(antonovka98.getNumberOfFollowers(), antonovka98.getFollowers().size());
        assertEqualsDelta(catsu07.getNumberOfFollowers(), catsu07.getFollowers().size());
        assertEqualsDelta(el_ohyel.getNumberOfFollowers(), el_ohyel.getFollowers().size());
        assertEqualsDelta(dvayanu.getNumberOfFollowers(), dvayanu.getFollowers().size());
    }

    @Test
    public void getFollowing() {
        assertEqualsDelta(jpleorx.getNumberOfFollowings(), jpleorx.getFollowing().size());
        assertEqualsDelta(antonovka98.getNumberOfFollowings(), antonovka98.getFollowing().size());
        assertEqualsDelta(catsu07.getNumberOfFollowings(), catsu07.getFollowing().size());
        assertEqualsDelta(el_ohyel.getNumberOfFollowings(), el_ohyel.getFollowing().size());
        assertEqualsDelta(dvayanu.getNumberOfFollowings(), dvayanu.getFollowing().size());
    }

    @Test
    public void hasInFollowers() {
        assertEquals(true, jpleorx.hasInFollowers(antonovka98));
        assertEquals(true, jpleorx.hasInFollowers(catsu07));
        assertEquals(false, jpleorx.hasInFollowers(el_ohyel));
        assertEquals(false, jpleorx.hasInFollowers(dvayanu));

        assertEquals(true, antonovka98.hasInFollowers(jpleorx));
        assertEquals(false, antonovka98.hasInFollowers(catsu07));
        assertEquals(false, antonovka98.hasInFollowers(el_ohyel));
        assertEquals(false, antonovka98.hasInFollowers(dvayanu));

        assertEquals(true, catsu07.hasInFollowers(jpleorx));
        assertEquals(false, catsu07.hasInFollowers(antonovka98));
        assertEquals(false, catsu07.hasInFollowers(el_ohyel));
        assertEquals(false, catsu07.hasInFollowers(dvayanu));

        assertEquals(true, el_ohyel.hasInFollowers(jpleorx));
        assertEquals(false, el_ohyel.hasInFollowers(antonovka98));
        assertEquals(false, el_ohyel.hasInFollowers(catsu07));
        assertEquals(false, el_ohyel.hasInFollowers(dvayanu));

        assertEquals(true, dvayanu.hasInFollowers(jpleorx));
        assertEquals(false, dvayanu.hasInFollowers(antonovka98));
        assertEquals(false, dvayanu.hasInFollowers(catsu07));
        assertEquals(false, dvayanu.hasInFollowers(el_ohyel));
    }

    @Test
    public void hasInFollowings() {
        assertEquals(true, jpleorx.hasInFollowings(antonovka98));
        assertEquals(true, jpleorx.hasInFollowings(catsu07));
        assertEquals(true, jpleorx.hasInFollowings(el_ohyel));
        assertEquals(true, jpleorx.hasInFollowings(dvayanu));

        assertEquals(true, antonovka98.hasInFollowings(jpleorx));
        assertEquals(false, antonovka98.hasInFollowings(catsu07));
        assertEquals(false, antonovka98.hasInFollowings(el_ohyel));
        assertEquals(false, antonovka98.hasInFollowings(dvayanu));

        assertEquals(true, catsu07.hasInFollowings(jpleorx));
        assertEquals(false, catsu07.hasInFollowings(antonovka98));
        assertEquals(false, catsu07.hasInFollowings(el_ohyel));
        assertEquals(false, catsu07.hasInFollowings(dvayanu));

        assertEquals(false, el_ohyel.hasInFollowings(jpleorx));
        assertEquals(false, el_ohyel.hasInFollowings(antonovka98));
        assertEquals(false, el_ohyel.hasInFollowings(catsu07));
        assertEquals(false, el_ohyel.hasInFollowings(dvayanu));

        assertEquals(false, dvayanu.hasInFollowings(jpleorx));
        assertEquals(false, dvayanu.hasInFollowings(antonovka98));
        assertEquals(false, dvayanu.hasInFollowings(catsu07));
        assertEquals(false, dvayanu.hasInFollowings(el_ohyel));
    }

    @Test
    public void getMedia() {
        assertEqualsDelta(jpleorx.getNumberOfMedia(), jpleorx.getMedia().size());
        assertEqualsDelta(antonovka98.getNumberOfMedia(), antonovka98.getMedia().size());
        assertEqualsDelta(catsu07.getNumberOfMedia(), catsu07.getMedia().size());
        assertEqualsDelta(el_ohyel.getNumberOfMedia(), el_ohyel.getMedia().size());
        assertEqualsDelta(dvayanu.getNumberOfMedia(), dvayanu.getMedia().size());
    }
}