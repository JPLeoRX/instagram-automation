package automation.selection.media_selection;

import automation.selection.MediaSelectionStrategy;
import helpers.RandomUtils;
import instagram.core_objects.Media;
import org.brunocvcunha.instagram4j.Instagram4j;

import java.util.List;

public class WeightedMostLikesMediaStrategy extends MediaSelectionStrategy {
    public WeightedMostLikesMediaStrategy(Instagram4j instagram4j) {
        super(instagram4j);
    }

    @Override
    public void setOriginals(List<Media> originalList) {
        this.originalList = originalList;
        this.randomCollection = RandomUtils.mostLikes(originalList);
    }

    @Override
    public Media select() {
        return getRandomCollection().getRandom();
    }
}