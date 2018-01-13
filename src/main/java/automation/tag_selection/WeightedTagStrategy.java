package automation.tag_selection;

import instagram.RandomWeightedCollection;
import instagram.core_objects.HashTag;
import org.brunocvcunha.instagram4j.Instagram4j;

import java.util.List;

public class WeightedTagStrategy extends TagSelectionStrategy {
    public WeightedTagStrategy(Instagram4j instagram4j, String... hashTags) {
        super(instagram4j, hashTags);
    }

    @Override
    public void setOriginalList(List<HashTag> originalList) {
        this.originalList = originalList;

        RandomWeightedCollection<HashTag> weightedCollection = new RandomWeightedCollection<>();
        for (HashTag tag : originalList)
            weightedCollection.addWeighted(tag.getMediaCount(), tag);

        this.randomCollection = weightedCollection;
    }

    @Override
    public HashTag select() {
        return getRandomCollection().getRandom();
    }
}
