package controller;

import DTO.UserDTO;
import beans.Gender;
import beans.Role;
import beans.User;
import com.google.gson.Gson;
import services.TestService;

import static spark.Spark.post;

public class TestController {

    private static Gson g = new Gson();
    private static TestService testService = new TestService();

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
}
