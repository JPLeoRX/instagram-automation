package automation.media_selection;

import automation.RandomizedSelectionStrategy;
import automation.SelectionStrategy;
import instagram.RandomCollection;
import instagram.core_objects.Media;
import org.brunocvcunha.instagram4j.Instagram4j;

import java.util.List;

public abstract class MediaSelectionStrategy extends RandomizedSelectionStrategy<Media> {
    private Instagram4j instagram4j;

    public MediaSelectionStrategy(Instagram4j instagram4j) {
        this.instagram4j = instagram4j;
    }
}