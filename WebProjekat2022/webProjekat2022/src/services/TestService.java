package services;

import DTO.LoginDTO;
import beans.Coach;
import beans.Manager;
import beans.User;
import com.google.gson.JsonSyntaxException;
import storages.CoachStorage;
import storages.ManagerStorage;
import storages.UserStorage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestService {
    private UserStorage users = UserStorage.getInstance();
    private ManagerStorage managers = ManagerStorage.getInstance();
    private CoachStorage coaches = CoachStorage.getInstance();

    public TestService() throws FileNotFoundException {
    }

    public void addUser(User user){
        this.users.addUser(user);
    }

    public void addManager(Manager manager){
        this.managers.addManager(manager);
    }
    public void addCoach(Coach coach){
        this.coaches.addCoach(coach);
    }
    public User GetById(String username){
        User user = users.FindById(username);
        return user;
    }
    public User loginUser(LoginDTO loginDTO) throws JsonSyntaxException, IOException {
        User user = users.FindById(loginDTO.getUsername());

        if(loginDTO.getPassword().equals(user.getPassword())){
            return user;
        }

        return null;

    }
}
