package automation.selection.media_selection;

import automation.selection.MediaSelectionStrategy;
import helpers.RandomCollection;
import instagram.core_objects.Media;
import org.brunocvcunha.instagram4j.Instagram4j;

import java.util.List;

public class RandomMediaStrategy extends MediaSelectionStrategy {
    public RandomMediaStrategy(Instagram4j instagram4j) {
        super(instagram4j);
    }

    @Override
    public void setOriginals(List<Media> originalList) {
        this.originalList = originalList;
        this.randomCollection = new RandomCollection<>(originalList);
    }

    @Override
    public Media select() {
        return getRandomCollection().getRandom();
    }
}