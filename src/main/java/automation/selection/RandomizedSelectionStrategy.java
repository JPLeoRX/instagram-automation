package automation.selection;

import helpers.RandomCollection;

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


    public abstract void setOriginals(List<SelectedType> originalList);
}
