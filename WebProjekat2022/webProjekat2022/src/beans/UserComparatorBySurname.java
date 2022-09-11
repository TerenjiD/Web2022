package beans;

import java.util.Comparator;

public class UserComparatorBySurname implements Comparator<User> {
    @Override

    public int compare(User o1, User o2) {
        return o1.getLastName().toUpperCase().compareTo(o2.getLastName().toUpperCase());
    }
}
