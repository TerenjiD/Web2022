package controller;

import DTO.CoachDTO;
import DTO.LoginDTO;
import DTO.ManagerDTO;
import DTO.UserDTO;
import beans.*;
import com.google.gson.Gson;
import services.TestService;
import spark.Request;
import spark.Session;

import java.io.FileNotFoundException;

import static spark.Spark.get;
import static spark.Spark.post;


public class TestController {

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

    public static void editCustomer(){
        post(
                "/rest/customerHomePage/changeInfoCustomer/customer",(req,res)->{
                    res.type("application/json");
                    UserDTO flag = g.fromJson(req.body(),UserDTO.class);
                    testService.EditCustomer(flag);
                    return "Success";
                }
        );
    }

    public static void editManager(){
        post(
                "rest/managerHomePage/changeInfoManager/manager",(req,res)->{
                    res.type("application/json");
                    ManagerDTO flag = g.fromJson(req.body(),ManagerDTO.class);
                    testService.EditManager(flag);
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
