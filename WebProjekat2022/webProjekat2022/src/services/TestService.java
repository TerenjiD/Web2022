package services;

import DTO.LoginDTO;
import beans.User;
import com.google.gson.JsonSyntaxException;
import storages.UserStorage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TestService {
    private UserStorage users = UserStorage.getInstance();

    public TestService() throws FileNotFoundException {
    }

    public void addUser(User user){
        this.users.addUser(user);
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
