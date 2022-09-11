package beans;

import java.util.Comparator;

public class UserComparatorByUsername implements Comparator<User> {
    @Override

    public int compare(User o1, User o2) {
        return o1.getUsername().toUpperCase().compareTo(o2.getUsername().toUpperCase());
    }
}
