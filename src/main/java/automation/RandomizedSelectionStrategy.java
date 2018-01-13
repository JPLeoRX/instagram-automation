package automation;

import instagram.RandomCollection;
import instagram.core_objects.HashTag;

import java.util.List;

public abstract class RandomizedSelectionStrategy<SelectedType> implements SelectionStrategy<SelectedType> {
    protected List<SelectedType> originalList;
    protected RandomCollection<SelectedType> randomCollection;

    public List<SelectedType> getOriginalList() {
        return originalList;
    }

    public RandomCollection<SelectedType> getRandomCollection() {
        return randomCollection;
    }


    public abstract void setOriginalList(List<SelectedType> originalList);
}
