package beans;

import java.util.Comparator;

public class FacilityComparatorByLocation implements Comparator<Facility> {

    @Override

    public int compare(Facility o1, Facility o2) {
        return o1.getLocation().getAddress().getCity().toUpperCase().compareTo(o2.getLocation().getAddress().getCity().toUpperCase());
    }
}
