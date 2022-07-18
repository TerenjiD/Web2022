package controller;

import DTO.LoginDTO;
import DTO.UserDTO;
import beans.Gender;
import beans.Role;
import beans.User;
import com.google.gson.Gson;
import services.TestService;
import spark.Session;

import java.io.FileNotFoundException;

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

    public static void addUser() {
        post(
                "rest/", (req,res) -> {
                    res.type("application/json");
                    UserDTO userDTO = g.fromJson(req.body(),UserDTO.class);
                    Gender gen = Gender.MALE;
                    if (userDTO.getGender().equals("Male")){
                        gen = Gender.MALE;
                    }else{
                        gen = Gender.FEMALE;
                    }
                    User user = new User(userDTO.getUsername(),userDTO.getPassword(),userDTO.getName(),userDTO.getLastName(),
                            gen,userDTO.getDateOfBirth(), Role.CUSTOMER);
                    testService.addUser(user);
                    return "SUCCESS";
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

                        Session session = req.session(true);
                        User loggedUser = session.attribute("user");
                        if(loggedUser == null){
                            session.attribute("user",testService.GetById(loginDTO.getUsername()));
                        }
                        return g.toJson(testService.GetById(loginDTO.getUsername()));
                    }catch (Exception e){
                        e.printStackTrace();
                        return null;
                    }
                }
        );
    }


}
