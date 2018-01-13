package automation.media_selection;

import instagram.core_objects.Media;
import org.brunocvcunha.instagram4j.Instagram4j;

import java.util.List;

public class LastMediaStrategy extends MediaSelectionStrategy {
    public LastMediaStrategy(Instagram4j instagram4j) {
        super(instagram4j);
    }

    @Override
    public void setOriginalList(List<Media> originalList) {
        this.originalList = originalList;
    }

    @Override
    public Media select() {
        return getOriginalList().get(0);
    }
}