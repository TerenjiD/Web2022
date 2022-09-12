package beans;

import java.util.Comparator;

public class UserComparatorByPoints implements Comparator<Customer> {
@Override

public int compare(Customer o1, Customer o2) {
        return Double.compare(o1.getPoints(),o2.getPoints());
        }
}
