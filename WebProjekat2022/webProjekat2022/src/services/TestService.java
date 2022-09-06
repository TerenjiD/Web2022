package services;

import DTO.*;
import beans.*;
import com.google.gson.JsonSyntaxException;
import storages.*;

import javax.xml.stream.events.Comment;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestService {
    private UserStorage users = UserStorage.getInstance();
    private ManagerStorage managers = ManagerStorage.getInstance();
    private CoachStorage coaches = CoachStorage.getInstance();

    private FacilityStorage facilities = FacilityStorage.getInstance();

    private ContentStorage contents = ContentStorage.getInstance();

    private CustomerStorage customers = CustomerStorage.getInstance();

    //ja
    private CommentStorage comments = CommentStorage.getInstance();
    //ja

    public TestService() throws FileNotFoundException {
    }

    public void addFacility(FacilityDTO facility){
        this.facilities.addFacility(facility);
    }
    public void addUser(User user){
        this.users.addUser(user);
        Customer customer = new Customer(user.getUsername(),user.getPassword(),user.getName(),user.getLastName(),user.getGender(),
                user.getDateOfBirth(),user.getRole(),0,"nista","nista");
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
                contentDTO.getLogo(),contentDTO.getDescription(),contentDTO.getDuration(),contentDTO.getStartTime(),
                contentDTO.getEndTime(),0);
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

    public Boolean CheckIfFirstTime(String customerUsername,String facilityName){
        Boolean boolToReturn = true;
        List<CommentDTO> commentList = comments.GetComments();
        for (CommentDTO comment: commentList) {
            String facilityToCheck = comment.getFacilityID();
            String customerToCheck = comment.getCustomerID();
            if(facilityToCheck.equals(facilityName) && customerToCheck.equals(customerUsername)){
                boolToReturn = false;
                break;
            }
        }
        return boolToReturn;
    }

    public int GetSizeComments(){
        return comments.GetSize();
    }

    public void AddComment(CommentDTO comment){
        comments.AddComment(comment);
    }

    public List<CommentDTO> getComments(){
        List<CommentDTO> listToIterate = comments.GetComments();
        List<CommentDTO> listToReturn = new ArrayList<>();
        for (CommentDTO comment:listToIterate) {
            if (comment.getIsDeleted() == 0 && comment.getAvailable() == 0){
                listToReturn.add(comment);
            }
        }
        return listToReturn;
    }

    public void acceptComment(CommentDTO comment){
        comments.EditComment(comment);
    }

    public List<CommentDTO> getAllComments(){
        List<CommentDTO> listToReturn = comments.GetComments();
        return listToReturn;
    }
    public List<CommentDTO> getAllCommentsForManager(String facility){
        List<CommentDTO> listToIterate = comments.GetComments();
        List<CommentDTO> listToReturn = new ArrayList<>();
        for (CommentDTO comment:listToIterate) {
            String facilityToCheck = comment.getFacilityID();
            if (facilityToCheck.equals(facility) && comment.getIsDeleted() == 0){
                listToReturn.add(comment);
            }
        }
        return listToReturn;
    }

    public List<CommentDTO> getCommentsForFacility(String facility){
        List<CommentDTO> listToIterate = comments.GetComments();
        List<CommentDTO> listToReturn = new ArrayList<>();
        for (CommentDTO comment:listToIterate) {
            String facilityToCheck = comment.getFacilityID();
            if (facilityToCheck.equals(facility) && comment.getAvailable() == 1){
                listToReturn.add(comment);
            }
        }
        return listToReturn;
    }

}
