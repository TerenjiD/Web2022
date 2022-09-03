package controller;

import DTO.*;
import beans.*;
import com.google.gson.Gson;
import services.FacilityService;
import services.TestService;
import spark.Request;
import spark.Session;
import storages.TrainingHistoryStorage;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static spark.Spark.get;
import static spark.Spark.post;

public class TestController {

    private static String flagFacilityName;

    private static Gson g = new Gson();
    private static TestService testService;

    private static Boolean boolIfFirstTime=false;

    private static FacilityService facilityService;

    private static String facilityForCustomer;

    static {
        try {
            facilityService = new FacilityService();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static Content ContentToChange ;

    private static Content TrainingToShow ;

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

    public static void addCustomer(){

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
                    ContentDTO flag = g.fromJson(req.body(),ContentDTO.class);
                    //String flagName = flag.getContentType();
                    String flagUsername = userSession(req).getUsername();
                    Manager flagManager = testService.GetByIdManager(flagUsername);
                    String flagContentName = flag.getName();
                    String flagFacility = flagManager.getFacility();
                    String flagNameID = flagContentName + flagFacility;
                    //ContentDTO.setNameID(flagNameID);
                    //testService.EditCustomer(flagUser,flagUsername);
                    //testService.addContent(flagFacility,flagName);
                    Content flagContent = testService.CheckContent(flagNameID);
                    if(flagContent==null){
                        testService.addContent(flagFacility,flagNameID,flagManager,flag);
                        return "success";
                    }else{
                        return  null;
                    }

                }
        );
    }

    public static void getContent(){
        get(
                "rest/managerHomePage/getContent/",(req,res)->{
                    res.type("application/json");
                    String flagUsername = userSession(req).getUsername();
                    Manager flagManager = testService.GetByIdManager(flagUsername);
                    String flagFacility = flagManager.getFacility();
                    return g.toJson(testService.GetContent(flagFacility));
                }
        );
    }

    public static void setContent(){
        post(
                "rest/managerHomePage/setContent/",(req,res)->{
                    res.type("application/json");
                    Content flag = g.fromJson(req.body(),Content.class);
                    String nameID = flag.getNameID();
                    ContentToChange(nameID);
                    return "Success";
                }
        );
    }

    public static void contentToChangeFunction(){
        get(
                "rest/managerHomePage/setContent/",(req,res)->{
                    res.type("application/json");
                    return g.toJson(ContentToChange);
                }
        );
    }

    public static void getCoaches(){
        get(
                "rest/managerHomePage/getCoaches/",(req,res)->{
                    res.type("application/json");
                    return g.toJson(testService.GetCoaches());
                }
        );
    }

    public static void addCoachToContent(){
        post(
                "rest/managerHomePage/addCoach/",(req,res)->{
                    res.type("application/json");
                    Coach flag = g.fromJson(req.body(),Coach.class);
                    testService.ChangeCoach(flag,ContentToChange);
                    return "SUCCESS";
                }
        );
    }

    public static void changeContent(){
        post(
                "rest/managerHomePage/changeContent/",(req,res)->{
                    res.type("application/json");
                    Content flag = g.fromJson(req.body(),Content.class);
                    testService.ChangeContent(flag);
                    return "SUCCESS";
                }
        );
    }

    public static void buyMembership(){
        post("/rest/customerHomePage/buyMembership",(req,res)->{
            res.type("application/json");
            MembershipDTO membershipDTO = g.fromJson(req.body(),MembershipDTO.class);
            LocalDateTime currentDate = LocalDateTime.now();
            String flagMemType = membershipDTO.getType();
            //String flagStatus = membershipDTO.getStatus();
            String customerUsername = userSession(req).getUsername();
            String appNum = membershipDTO.getAppointmentNumber();
            int price = membershipDTO.getPrice();
            LocalDateTime expirationDate;
            MembershipType memType;
            if(flagMemType.equals("Mesecno")){
                memType = MembershipType.MONTHLY;
                //expirationDate = currentDate.plusMonths(1);
                expirationDate = currentDate.plusDays(1);
            }else if(flagMemType.equals("Godisnje")){
                memType = MembershipType.YEARLY;
                //expirationDate = currentDate.plusMonths(12);
                expirationDate = currentDate.plusMinutes(1);
            }else{
                memType = MembershipType.SIXMONTHLY;
                //expirationDate = currentDate.plusMonths(6);
                expirationDate = currentDate.plusMinutes(1);
            }
            String idFlag = Integer.toString(facilityService.GetMembershipIDCount()+1);
            String usernameFlag = userSession(req).getUsername();
            String facility = membershipDTO.getFacility();
            Membership membership = new Membership(idFlag,facility,memType,currentDate,
                    expirationDate,price,customerUsername,MembershipStatus.ACTIVE,appNum,appNum);
            facilityService.createMembership(membership,usernameFlag);
            return "SUCCESS";
        });
    }

    public static void CheckExpirationDate(){
        get("/rest/customerHomePage/getDate",(req,res)->{
            res.type("application/json");
            String username = userSession(req).getUsername();
            Customer userFlag = facilityService.GetByUsernameCustomer(username);
            String membershipID = userFlag.getMembership();
            boolean check = facilityService.CheckIfExpired(membershipID,username);
            if(check==true){
                return "SUCCESS";
            }else{
                return null;
            }

        });
    }

    public static void OpenFacility(){
        post("rest/customerHomePage/FacilityOpen",(req,res)->{
            res.type("application/json");
            Facility facility = g.fromJson(req.body(),Facility.class);
            Customer customer = facilityService.GetByUsernameCustomer(userSession(req).getUsername());
            String membershipID = customer.getMembership();
            Membership membership = facilityService.GetMembershipById(membershipID);
            String facilityName = membership.getFacility();
            String facilityFlag = facility.getName();
            LocalDate currentDate = LocalDate.now();
            if(membershipID.equals("nista")){
                return null;
            }else{
                if(facilityName.equals(facilityFlag)){
                    facilityService.EditMembership(currentDate,membershipID);
                    return "Success";
                }else{
                    return null;
                }
            }


        });
    }

    public static void getContentCustomer(){
        get(
                "rest/customerHomePage/FacilityOpen/getContents",(req,res)->{
                    res.type("application/json");
                    Customer customer = facilityService.GetByUsernameCustomer(userSession(req).getUsername());
                    String memId = customer.getMembership();
                    Membership membership = facilityService.GetMembershipById(memId);
                    String flagFacility = membership.getFacility();
                    return g.toJson(testService.GetContent(flagFacility));
                }
        );
    }

    public static void BeginTraining(){
        post(
                "rest/customerHomePage/FacilityOpen/beginTraining",(req,res)->{
                    res.type("application/json");
                    Content content = g.fromJson(req.body(),Content.class);
                    Customer customer = facilityService.GetByUsernameCustomer(userSession(req).getUsername());
                    String coach = content.getCoachID();
                    LocalDate currentDate = LocalDate.now();
                    int idToParse = facilityService.GetTrainingSize()+1;
                    String id = Integer.toString(idToParse);
                    TrainingHistory training = new TrainingHistory(id,currentDate,content,customer,coach);
                    facilityService.AddTraining(training);
                    //ja
                    String customerUserName = userSession(req).getUsername();
                    String facilityName = content.getFacilityName();
                    facilityForCustomer = facilityName;
                    CheckIfCustomerIsFirstTimeInFacility(customerUserName,facilityName);
                    //ja
                    return "success";
                });
    }

    public static void CheckIfCustomerIsFirstTimeInFacility(String customerUsername,String facilityName){
        boolIfFirstTime = testService.CheckIfFirstTime(customerUsername,facilityName);
    }

    public static void RouteComment(){
        get(
                "rest/customerHomePage/checkComment",(req,res)->{
                    res.body("application/json");
                    if(boolIfFirstTime==true){
                        boolIfFirstTime=false;
                        return "Success";
                    }else{
                        return null;
                    }
                }
        );
    }

    public static void AddComment(){
        post(
                "rest/customerHomePage/putComment/add",(req,res)-> {
                    res.body("application/json");
                    CommentDTO comment = g.fromJson(req.body(), CommentDTO.class);
                    comment.setId(testService.GetSizeComments()+1);
                    comment.setFacilityID(facilityForCustomer);
                    comment.setCustomerID(userSession(req).getUsername());
                    comment.setAvailable(0);
                    comment.setIsDeleted(0);
                    testService.AddComment(comment);
                    return "Success";
                }
                );
    }

    public static void GetTrainingsForCustomer(){
        get(
                "/rest/customerHomePage/getTrainingHistory",(req,res)->{
                    Customer customer = facilityService.GetByUsernameCustomer(userSession(req).getUsername());
                    String username = customer.getUsername();
                    return g.toJson(facilityService.GetTrainingsForCustomer(username));
                }
        );
    }

    public static void GetTrainingsForCoach(){
        get(
                "rest/coachHomePage/getTrainings",(req,res)->{
                    res.type("application/json");
                    User user = userSession(req);
                    String username = user.getUsername();
                    return g.toJson(facilityService.getTrainings(username));
                }
        );
    }

    public static void SetTrainingToShow(){
        post(
                "rest/coachHomePage/viewTraining",(req,res)->{
                    res.type("application/json");
                    TrainingToShow  = g.fromJson(req.body(),Content.class);
                    return "SUCCESS";
                }
        );
    }

    public static void ShowTraining(){
        get(
                "rest/coachHomePage/view",(req,res)->{
                    res.type("application/json");
                    return g.toJson(TrainingToShow);
                }
        );
    }

    public static void CancelPersonalTraining(){
        post(
                "rest/coachHomePage/view/delete",(req,res)->{
                    res.type("application/json");
                    facilityService.CancelTraining(TrainingToShow);
                    return "SUCCESS";
                }
        );
    }

    public static void GetCustomers(){
        get(
                "rest/managerHomePage/GetCustomers",(req,res)->{
                    res.type("application/json");
                    String username = userSession(req).getUsername();
                    Manager manager = testService.GetByIdManager(username);
                    return g.toJson(facilityService.GetCustomersToShowForManager(manager));
                }
        );
    }

    public static void GetCoachesForSpecificFacility(){
        get(
                "rest/managerHomePage/GetCoachesForSpecificFacility",(req,res)->{
                    res.type("application/json");
                    String username = userSession(req).getUsername();
                    Manager manager = testService.GetByIdManager(username);
                    String facility = manager.getFacility();
                    return g.toJson(facilityService.GetCoachesToShowForManager(facility));
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

    public static void ContentToChange(String nameID){
        ContentToChange = testService.CheckContent(nameID);
    }
}
