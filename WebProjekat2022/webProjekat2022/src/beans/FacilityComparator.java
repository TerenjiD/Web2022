package beans;

import java.util.Comparator;

public class FacilityComparator implements Comparator<Facility> {
    @Override

    public int compare(Facility o1, Facility o2) {
        return o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase());
    }
}
