package instagram.core_objects;

import instagram.BaseTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class MediaTest extends BaseTest {
    private static Media pic1 = jpleorx.getMediaShort().get(0);
    private static Media pic2 = antonovka98.getMediaShort().get(0);
    private static Media pic3 = catsu07.getMediaShort().get(0);
    private static Media pic4 = el_ohyel.getMediaShort().get(0);
    private static Media pic5 = dvayanu.getMediaShort().get(0);

    @Test
    public void likeUnlike() {
        Media picTestLike = antonovka98.getMedia().get(0);

        // Make sure that this pic is not currently liked
        assertEquals(false, picTestLike.hasInLikers(currentUser));

        // Like the picture
        picTestLike.like();
        picTestLike.refresh();

        // Check if we are in it's likers now
        assertEquals(true, picTestLike.hasInLikers(currentUser));

        // Unlike the picture
        picTestLike.unlike();
        picTestLike.refresh();

        // Make sure we are not in it's likers
        assertEquals(false, picTestLike.hasInLikers(currentUser));

    }

    @Test
    public void getLikers() {
        assertEqualsDelta(pic1.getNumberOfLikes(), pic1.getLikers().size());
        assertEqualsDelta(pic2.getNumberOfLikes(), pic2.getLikers().size());
        assertEqualsDelta(pic3.getNumberOfLikes(), pic3.getLikers().size());
        assertEqualsDelta(pic4.getNumberOfLikes(), pic4.getLikers().size());
        assertEqualsDelta(pic5.getNumberOfLikes(), pic5.getLikers().size());
    }

    @Test
    public void getAuthor() {
        assertEquals(pic1.getAuthor(), jpleorx);
        assertEquals(pic2.getAuthor(), antonovka98);
        assertEquals(pic3.getAuthor(), catsu07);
        assertEquals(pic4.getAuthor(), el_ohyel);
        assertEquals(pic5.getAuthor(), dvayanu);
    }

    @Test
    public void hasInLikers() {
        assertEquals(false, pic1.hasInLikers(jpleorx));
        assertEquals(false, pic2.hasInLikers(jpleorx));
        assertEquals(true, pic3.hasInLikers(jpleorx));
        assertEquals(true, pic4.hasInLikers(jpleorx));
        assertEquals(true, pic5.hasInLikers(jpleorx));
    }
}