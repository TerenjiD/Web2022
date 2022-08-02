package controller;

import DTO.*;
import beans.*;
import com.google.gson.Gson;
import services.FacilityService;
import services.TestService;
import spark.Request;
import spark.Session;

import java.io.FileNotFoundException;

import static spark.Spark.get;
import static spark.Spark.post;


public class TestController {

    private static String flagFacilityName;

    private static Gson g = new Gson();
    private static TestService testService;

    static {
        try {
            testService = new TestService();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addManager(){
        post(
                "rest/adminHomePage/registerManager/", (req,res) -> {
                    res.type("application/json");
                    ManagerDTO managerDTO = g.fromJson(req.body(),ManagerDTO.class);
                    User flag = testService.GetById(managerDTO.getUsername());
                    Gender gen;
                    if (managerDTO.getGender().equals("Male")){
                        gen = Gender.MALE;
                    }else{
                        gen = Gender.FEMALE;
                    }
                    Manager manager = new Manager(managerDTO.getUsername(),managerDTO.getPassword(),managerDTO.getName(),
                            managerDTO.getLastName(),gen,managerDTO.getDateOfBirth(),Role.MANAGER,managerDTO.getFacility());
                    User user = new User(managerDTO.getUsername(),managerDTO.getPassword(),managerDTO.getName(),
                            managerDTO.getLastName(),gen,managerDTO.getDateOfBirth(),Role.MANAGER);
                    testService.addUser(user);


                    if(flag == null) {
                        testService.addManager(manager);
                        return "success";
                    }
                    else
                        return null;
                }
        );
    }

    public static void addManagerForFacility(){
        post(
                "/rest/adminHomePage/createFacility/managerForFacility/create", (req,res) -> {
                    res.type("application/json");
                    ManagerDTO managerDTO = g.fromJson(req.body(),ManagerDTO.class);
                    User flag = testService.GetById(managerDTO.getUsername());
                    Gender gen;
                    if (managerDTO.getGender().equals("Male")){
                        gen = Gender.MALE;
                    }else{
                        gen = Gender.FEMALE;
                    }
                    Manager manager = new Manager(managerDTO.getUsername(),managerDTO.getPassword(),managerDTO.getName(),
                            managerDTO.getLastName(),gen,managerDTO.getDateOfBirth(),Role.MANAGER,flagFacilityName);
                    User user = new User(managerDTO.getUsername(),managerDTO.getPassword(),managerDTO.getName(),
                            managerDTO.getLastName(),gen,managerDTO.getDateOfBirth(),Role.MANAGER);
                    testService.addUser(user);


                    if(flag == null) {
                        testService.addManager(manager);
                        return "success";
                    }
                    else
                        return null;
                }
        );
    }

    public static void addCoach(){
        post(
                "rest/adminHomePage/registerCoach/",(req,res) -> {
                    CoachDTO coachDTO = g.fromJson(req.body(), CoachDTO.class);
                    User flag = testService.GetById(coachDTO.getUsername());
                    Gender gen;
                    if (coachDTO.getGender().equals("MALE")){
                        gen = Gender.MALE;
                    }else{
                        gen = Gender.FEMALE;
                    }
                    Coach coach = new Coach(coachDTO.getUsername(),coachDTO.getPassword(),coachDTO.getName(),
                            coachDTO.getLastName(),gen,coachDTO.getDateOfBirth(),Role.COACH,coachDTO.getTrainingHistory());
                    User user = new User(coachDTO.getUsername(),coachDTO.getPassword(),coachDTO.getName(),
                            coachDTO.getLastName(),gen,coachDTO.getDateOfBirth(),Role.COACH);
                    testService.addUser(user);


                    if(flag == null) {
                        testService.addCoach(coach);
                        return "success";
                    }
                    else
                        return null;
                }
        );
    }

    public static void addUser() {
        post(
                "rest/", (req,res) -> {
                    res.type("application/json");

                    UserDTO userDTO = g.fromJson(req.body(), UserDTO.class);
                    User flag = testService.GetById(userDTO.getUsername());
                    Gender gen = Gender.MALE;
                    if (userDTO.getGender().equals("MALE")) {
                        gen = Gender.MALE;
                    } else {
                        gen = Gender.FEMALE;
                    }
                    User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getName(), userDTO.getLastName(),
                            gen, userDTO.getDateOfBirth(), Role.CUSTOMER);
                    testService.addUser(user);

                    if(flag == null)
                        return "success";
                    else
                        return null;
                }
        );
    }

    public static void loginUser(){
        post(
            "/rest/customerHomePage",( req , res) -> {
                    res.type("application/json");
                    try{
                        LoginDTO loginDTO = g.fromJson(req.body(),LoginDTO.class);
                        testService.loginUser(loginDTO);
                        userSession(req).setUsername(loginDTO.getUsername());
                        return g.toJson(testService.GetById(loginDTO.getUsername()));
                    }catch (Exception e){
                        e.printStackTrace();
                        return null;
                    }
                }
        );
    }

    public static void getUsers(){
        get(
                "/rest/adminHomePage/showUsers",(req,res)->{
                    res.type("application/json");
                    return g.toJson(testService.GetUsers());
                }
        );
    }

    public static void getManagersWithoutFacility(){
        get(
                "/rest/adminHomePage/createFacility/getManagers",(req,res)->{
                    res.type("application/json");
                    return g.toJson(testService.GetManagersWithoutFacility());
                }
        );
    }

    public static void createFacility(){
        post(
                "/rest/adminHomePage/createFacility/create",(req,res)->{
                    res.type("application/json");
                    FacilityDTO facilityDTO = g.fromJson(req.body(), FacilityDTO.class);
                    flagFacilityName = facilityDTO.getName();
                    Manager manager = testService.GetByIdManager(facilityDTO.getManager());
                    ManagerDTO managerDTO = new ManagerDTO(manager.getUsername(), manager.getPassword(),manager.getName(),
                            manager.getLastName(), manager.getGender().toString(),manager.getDateOfBirth(),
                            manager.getRole().toString(),facilityDTO.getName());

                    testService.addFacility(facilityDTO);
                    Facility flagCheck = testService.GetFacility(facilityDTO.getName());
                    if(flagCheck==null){
                        testService.EditManager(managerDTO,managerDTO.getUsername());
                        return "Success";
                    }else{
                        return null;
                    }

                }
        );
    }

    public static void createFacilitySpecial(){
        post(
                "/rest/adminHomePage/createFacility/createSpecial",(req,res)->{
                    res.type("application/json");
                    FacilityDTO facilityDTO = g.fromJson(req.body(), FacilityDTO.class);
                    flagFacilityName = facilityDTO.getName();
                    testService.addFacility(facilityDTO);
                    Facility flagCheck = testService.GetFacility(facilityDTO.getName());
                    if(flagCheck==null){
                        return "Success";
                    }else{
                        return null;
                    }

                }
        );
    }

    public static void getFacility(){
        get(
                "/rest/managerHomePage/getFacility",(req,res)->{
                    res.type("application/json");
                    Manager flag = testService.GetByIdManager(userSession(req).getUsername());
                    FacilityDTO flagFacility = testService.GetFacilityDTO(flag.getFacility());
                    if(flagFacility == null){
                        FacilityDTO fac = new FacilityDTO("nista","nista","nista","nista"
                                ,"nista", "nista","nista","nista","nista","nista","nista","nista",
                                "nista","nista");
                        return g.toJson(fac);
                    }else{
                        return g.toJson(flagFacility);
                    }

                }
        );
    }

    public static void loggedUserCustomer(){
        get(
                "/rest/customerHomePage/customer",(req,res)->{
                    res.type("application/json");
                    return g.toJson(testService.GetById(userSession(req).getUsername()));
                }
        );
    }

    public static void loggedUserManager(){
        get(
                "/rest/managerHomePage/manager",(req,res)->{
                    res.type("application/json");
                    return g.toJson(testService.GetById(userSession(req).getUsername()));
                }
        );
    }

    public static void loggedUserCoach(){
        get(
                "/rest/coachHomePage/coach",(req,res)->{
                    res.type("application/json");
                    return g.toJson(testService.GetById(userSession(req).getUsername()));
                }
        );
    }

    public static void loggedUserAdmin(){
        get(
                "/rest/adminHomePage/admin",(req,res)->{
                    res.type("application/json");
                    return g.toJson(testService.GetById(userSession(req).getUsername()));
                }
        );
    }

    public static void changeManagerInfo(){
        get(
                "rest/managerHomePage/changeInfoManager",(req,res)->{
                    res.type("application/json");
                    return g.toJson(testService.GetByIdManager(userSession(req).getUsername()));
                }
        );
    }
    public static void changeCustomerInfo(){
        get(
                "/rest/customerHomePage/changeInfoCustomer/",(req,res)->{
                    res.type("application/json");
                    return g.toJson(testService.GetById(userSession(req).getUsername()));
                }
        );
    }
    public static void changeCoachInfo(){
        get(
                "/rest/coachHomePage/changeInfoCoach/",(req,res)->{
                    res.type("application/json");
                    return g.toJson(testService.GetByIdCoach(userSession(req).getUsername()));
                }
        );
    }
    public static void changeAdminInfo(){
        get(
                "/rest/adminHomePage/changeInfoAdmin/",(req,res)->{
                    res.type("application/json");
                    return g.toJson(testService.GetById(userSession(req).getUsername()));
                }
        );
    }

    public static void editCustomer(){
        post(
                "/rest/customerHomePage/changeInfoCustomer/customer",(req,res)->{
                    res.type("application/json");
                    UserDTO flag = g.fromJson(req.body(),UserDTO.class);
                    String flagUser = userSession(req).getUsername();
                    testService.EditCustomer(flag,flagUser);
                    return "Success";
                }
        );
    }

    public static void editManager(){
        post(
                    "rest/managerHomePage/changeInfoManager/manager",(req,res)->{
                    res.type("application/json");
                    ManagerDTO flag = g.fromJson(req.body(),ManagerDTO.class);
                    UserDTO flagUser = new UserDTO(flag.getUsername(),flag.getPassword(),flag.getName(),flag.getLastName(),
                            flag.getGender(),flag.getDateOfBirth());
                    String flagUsername = userSession(req).getUsername();
                    //testService.EditCustomer(flagUser,flagUsername);
                    testService.EditManager(flag,flagUsername);
                    return "success";
                }
        );
    }
    public static void editCoach(){
        post(
                "rest/coachHomePage/changeInfoCoach/coach",(req,res)->{
                    res.type("application/json");
                    CoachDTO flag = g.fromJson(req.body(),CoachDTO.class);
                    UserDTO flagUser = new UserDTO(flag.getUsername(),flag.getPassword(),flag.getName(),flag.getLastName(),
                            flag.getGender(),flag.getDateOfBirth());
                    String flagUsername = userSession(req).getUsername();
                    //testService.EditCustomer(flagUser,flagUsername);
                    testService.EditCoach(flag,flagUsername);
                    return "success";
                }
        );
    }
    public static void editAdmin(){
        post(
                "rest/adminHomePage/changeInfoAdmin/admin",(req,res)->{
                    res.type("application/json");
                    UserDTO flag = g.fromJson(req.body(),UserDTO.class);
                    String flagUsername = userSession(req).getUsername();
                    //testService.EditCustomer(flagUser,flagUsername);
                    testService.EditAdmin(flag,flagUsername);
                    return "success";
                }
        );
    }

    public static void addContent(){
        post(
                "rest/managerHomePage/addContent/",(req,res)->{
                    res.type("application/json");
                    FacilityDTO flag = g.fromJson(req.body(),FacilityDTO.class);
                    String flagName = flag.getContentType();
                    String flagUsername = userSession(req).getUsername();
                    Manager flagManager = testService.GetByIdManager(flagUsername);
                    String flagFacility = flagManager.getFacility();
                    //testService.EditCustomer(flagUser,flagUsername);
                    testService.addContent(flagFacility,flagName);
                    return "success";
                }
        );
    }

    public static User userSession(Request req){

        Session ss = req.session(true);
        User loggedUser = ss.attribute("loggedUser");
        if(loggedUser == null){
            loggedUser = new User();
            ss.attribute("loggedUser",loggedUser);
        }
        return loggedUser;
    }


}
