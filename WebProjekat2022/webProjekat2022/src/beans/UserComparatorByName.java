package beans;

import java.util.Comparator;

public class UserComparatorByName implements Comparator<User> {

    @Override

    public int compare(User o1, User o2) {
        return o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase());
    }
}
