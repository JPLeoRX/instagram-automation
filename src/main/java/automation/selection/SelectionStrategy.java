package automation.selection;

import java.util.List;

public interface SelectionStrategy<SelectedType> {
    void setOriginals(List<SelectedType> originalList);

    SelectedType select();

    SelectedType select(List<SelectedType> originalList);
}