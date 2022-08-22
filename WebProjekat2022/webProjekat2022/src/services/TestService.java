package services;

import DTO.*;
import beans.*;
import com.google.gson.JsonSyntaxException;
import storages.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class TestService {
    private UserStorage users = UserStorage.getInstance();
    private ManagerStorage managers = ManagerStorage.getInstance();
    private CoachStorage coaches = CoachStorage.getInstance();

    private FacilityStorage facilities = FacilityStorage.getInstance();

    private ContentStorage contents = ContentStorage.getInstance();

    private CustomerStorage customers = CustomerStorage.getInstance();

    public TestService() throws FileNotFoundException {
    }

    public void addFacility(FacilityDTO facility){
        this.facilities.addFacility(facility);
    }
    public void addUser(User user){
        this.users.addUser(user);
        Customer customer = new Customer(user.getUsername(),user.getPassword(),user.getName(),user.getLastName(),user.getGender(),
                user.getDateOfBirth(),user.getRole(),0,new CustomerType("nista"),new Membership("nista"));
        this.customers.addCustomer(customer);
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
    public Coach GetByIdCoach(String username){
        Coach coach = coaches.GetByIdCoach(username);
        return coach;
    }

    public List<User> GetUsers(){
        return users.GetUsers();
    }

    public List<Manager> GetManagersWithoutFacility(){
        return managers.GetManagersWithoutFacility();
    }

    public User loginUser(LoginDTO loginDTO) throws JsonSyntaxException, IOException {
        User user = users.FindById(loginDTO.getUsername());

        if(loginDTO.getPassword().equals(user.getPassword())){
            return user;
        }

        return null;

    }

    public void EditManager(ManagerDTO manager,String flagUsername){
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
        users.editUser(flagUser,flagUsername);
        managers.editManager(flagManager,flagUsername);
    }

    public void EditCustomer(UserDTO userDTO,String flagUsername){
        Gender gen;
        if(userDTO.getGender().equals("MALE")){
            gen = Gender.MALE;
        }else{
            gen = Gender.FEMALE;
        }
        User flagUser = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getName(),userDTO.getLastName(),
                gen,userDTO.getDateOfBirth(),Role.CUSTOMER);
        users.editUser(flagUser,flagUsername);
    }

    public void EditAdmin(UserDTO adminDTO, String flagUsername){
        Gender gen;
        if(adminDTO.getGender().equals("MALE")){
            gen = Gender.MALE;
        }else{
            gen = Gender.FEMALE;
        }
        User flagUser = new User(adminDTO.getUsername(), adminDTO.getPassword(), adminDTO.getName(),adminDTO.getLastName(),
                gen,adminDTO.getDateOfBirth(),Role.ADMIN);
        users.editUser(flagUser,flagUsername);
    }

    public void EditCoach(CoachDTO coachDTO,String flagUsername){
        Gender gen;
        if(coachDTO.getGender().equals("MALE")){
            gen = Gender.MALE;
        }else{
            gen = Gender.FEMALE;
        }
        User flagUser = new User(coachDTO.getUsername(), coachDTO.getPassword(), coachDTO.getName(),coachDTO.getLastName(),
                gen,coachDTO.getDateOfBirth(),Role.COACH);
        Coach flagCoach = new Coach(coachDTO.getUsername(),coachDTO.getPassword(),coachDTO.getName(),coachDTO.getLastName(),
                gen,coachDTO.getDateOfBirth(),Role.COACH,coachDTO.getTrainingHistory());
        users.editUser(flagUser,flagUsername);
        coaches.editCoach(flagCoach,flagUsername);
    }

    public Facility GetFacility(String name){
        return facilities.CheckIfExists(name);
    }

    public FacilityDTO GetFacilityDTO(String name){
        Facility flag = facilities.CheckIfExists(name);
        FacilityDTO flagFacility = new FacilityDTO(flag.getName(),flag.getFacilityType().toString(),flag.getContentType().toString(),
                flag.getStatus().toString(),flag.getLogo(),Float.toString(flag.getLocation().getLatitude())
                ,Float.toString(flag.getLocation().getLongitude()), flag.getLocation().getAddress().getStreet(),
                flag.getLocation().getAddress().getNumber(), flag.getLocation().getAddress().getCity(),
                flag.getLocation().getAddress().getCountry(), flag.getWorkingHours(),flag.getRating());
        return flagFacility;
    }

    public void addContent(String name,String content,Manager manager,ContentDTO contentDTO){
        facilities.addContent(name,content);
        String flagContentName = contentDTO.getName();
        String flagFacility = manager.getFacility();
        String flagNameID = flagContentName + flagFacility;
        ContentType flagType;
        String flagForCheck = contentDTO.getType();
        ContentType flagContent;
        if(flagForCheck.equals("GROUP_TRAINING")){
            flagContent = ContentType.GROUP_TRAINING;
        }else if(flagForCheck.equals("PERSONAL_TRAINING")){
            flagContent = ContentType.PERSONAL_TRAINING;
        }else{
            flagContent = ContentType.SAUNA;
        }
        Content contentToSend = new Content(flagNameID,flagFacility,contentDTO.getName(),flagContent,"nema",
                contentDTO.getLogo(),contentDTO.getDescription(),contentDTO.getDuration());
        contents.addContent(contentToSend);
    }

    public List<Content> GetContent(String facilityName){
        return contents.GetContents(facilityName);
    }

    public Content CheckContent(String nameID){
        return contents.CheckIfExist(nameID);
    }

    public List<Coach> GetCoaches(){
        return coaches.GetCoaches();
    }

    public void ChangeCoach(Coach coach,Content content){
        contents.ChangeCoach(coach,content);
    }

    public void ChangeContent(Content content){
        contents.ChangeContent(content);
    }

}
