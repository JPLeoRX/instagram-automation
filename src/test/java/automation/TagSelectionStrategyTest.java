package automation;

import automation.selection.tag_selection.RandomTagStrategy;
import automation.selection.TagSelectionStrategy;
import instagram.BaseTest;
import org.junit.Test;

public class TagSelectionStrategyTest extends BaseTest {
    @Test
    public void t() {
        TagSelectionStrategy t = new RandomTagStrategy(instagram, "jdm", "honda", "acura", "mazda", "toyota");

        for (int i = 0; i < 100; i++)
            System.out.println(t.select());
    }

}