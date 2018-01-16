package automation.selection;

import automation.selection.RandomizedSelectionStrategy;
import instagram.core_objects.Media;
import org.brunocvcunha.instagram4j.Instagram4j;

import java.util.List;

public abstract class MediaSelectionStrategy extends RandomizedSelectionStrategy<Media> {
    private Instagram4j instagram4j;

    public MediaSelectionStrategy(Instagram4j instagram4j) {
        this.instagram4j = instagram4j;
    }

    @Override
    public Media select(List<Media> originalList) {
        this.setOriginals(originalList);
        return this.select();
    }
}