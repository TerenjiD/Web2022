package services;

import DTO.LoginDTO;
import DTO.ManagerDTO;
import DTO.UserDTO;
import beans.*;
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
    public Manager GetByIdManager(String username){
        Manager manager = managers.GetByIdManager(username);
        return manager;
    }
    public User loginUser(LoginDTO loginDTO) throws JsonSyntaxException, IOException {
        User user = users.FindById(loginDTO.getUsername());

        if(loginDTO.getPassword().equals(user.getPassword())){
            return user;
        }

        return null;

    }

    public void EditManager(ManagerDTO manager){
        Gender gen;
        if(manager.getGender().equals("MALE")){
            gen = Gender.MALE;
        }else{
            gen = Gender.FEMALE;
        }
        Role roleFlag;
        if(manager.getRole().equals("CUSTOMER")){
            roleFlag = Role.CUSTOMER;
        }else if(manager.getRole().equals("COACH")){
            roleFlag = Role.COACH;
        }
        else if(manager.getRole().equals("MANAGER")){
            roleFlag = Role.MANAGER;
        }
        else{
            roleFlag = Role.ADMIN;
        }
        User flagUser = new User(manager.getUsername(), manager.getPassword(), manager.getName(),manager.getLastName(),
                gen,manager.getDateOfBirth(),roleFlag);
        Manager flagManager = new Manager(manager.getUsername(), manager.getPassword(), manager.getName(),manager.getLastName(),
                gen,manager.getDateOfBirth(),roleFlag,manager.getFacility());
        users.editUser(flagUser,flagUser.getUsername());
        managers.editManager(flagManager,flagManager.getUsername());
    }

    public void EditCustomer(UserDTO userDTO){
        Gender gen;
        if(userDTO.getGender().equals("MALE")){
            gen = Gender.MALE;
        }else{
            gen = Gender.FEMALE;
        }
        User flagUser = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getName(),userDTO.getLastName(),
                gen,userDTO.getDateOfBirth(),Role.CUSTOMER);
        users.editUser(flagUser,flagUser.getUsername());
    }
}
