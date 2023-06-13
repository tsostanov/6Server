package labCollection;

import data.LabWork;

import java.util.Comparator;

/**
 * Compare LabWorks by their id number.
 */
public class IdComparator implements Comparator<LabWork> {
    @Override
    public int compare(LabWork o1, LabWork o2) {
        return o1.getId() - o2.getId();
    }
}
