package beans;

import java.util.Comparator;

public class FacilityComparatorByGrade implements Comparator<Facility> {
    @Override

    public int compare(Facility o1, Facility o2) {
        return Double.compare(Double.parseDouble(o1.getRating()),Double.parseDouble(o2.getRating()));
    }
}
