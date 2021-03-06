package automation.selection.tag_selection;

import automation.selection.TagSelectionStrategy;
import helpers.RandomCollection;
import instagram.core_objects.HashTag;
import org.brunocvcunha.instagram4j.Instagram4j;

import java.util.List;

public class RandomTagStrategy extends TagSelectionStrategy {
    public RandomTagStrategy(Instagram4j instagram4j, String... hashTags) {
        super(instagram4j, hashTags);
    }

    @Override
    public void setOriginals(List<HashTag> originalList) {
        this.originalList = originalList;
        this.randomCollection = new RandomCollection<>(originalList);
    }

    @Override
    public HashTag select() {
        return getRandomCollection().getRandom().clone();
    }
}