package automation.selection.tag_selection;

import automation.selection.TagSelectionStrategy;
import helpers.RandomUtils;
import instagram.core_objects.HashTag;
import org.brunocvcunha.instagram4j.Instagram4j;

import java.util.List;

public class WeightedMostPostsTagStrategy extends TagSelectionStrategy {
    public WeightedMostPostsTagStrategy(Instagram4j instagram4j, String... hashTags) {
        super(instagram4j, hashTags);
    }

    @Override
    public void setOriginals(List<HashTag> originalList) {
        this.originalList = originalList;
        this.randomCollection = RandomUtils.mostPosts(originalList);
    }

    @Override
    public HashTag select() {
        return getRandomCollection().getRandom().clone();
    }
}
