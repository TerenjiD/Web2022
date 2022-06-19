package services;

import beans.User;
import storages.UserStorage;

public class TestService {
    private UserStorage users = new UserStorage();
    public void addUser(User user){
        this.users.addUser(user);
    }
}
