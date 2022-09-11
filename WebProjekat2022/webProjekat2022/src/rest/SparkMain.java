package rest;

import controller.FacilityController;
import controller.TestController;

import static spark.Spark.port;
import java.io.File;
import static spark.Spark.staticFiles;

public class SparkMain {
    public static void main(String[] args) throws Exception {
        port(8080);

        staticFiles.externalLocation(new File("./static").getCanonicalPath());


        TestController.addUser();
        TestController.loginUser();
        TestController.addManager();
        TestController.addCoach();
        TestController.loggedUserManager();
        TestController.changeManagerInfo();
        TestController.editManager();
        TestController.loggedUserCustomer();
        TestController.changeCustomerInfo();
        TestController.editCustomer();
        TestController.loggedUserCoach();
        TestController.changeCoachInfo();
        TestController.editCoach();
        TestController.loggedUserAdmin();
        TestController.changeAdminInfo();
        TestController.editAdmin();
        TestController.getUsers();
        TestController.getManagersWithoutFacility();
        TestController.createFacility();
        TestController.addManagerForFacility();
        TestController.createFacilitySpecial();
        TestController.getFacility();
        TestController.addContent();
        TestController.getContent();
        TestController.setContent();
        TestController.contentToChangeFunction();
        TestController.getCoaches();
        TestController.addCoachToContent();
        TestController.changeContent();

        FacilityController.getFacilities();
        FacilityController.searchFacilities();

        TestController.buyMembership();
        TestController.CheckExpirationDate();
        TestController.OpenFacility();
        TestController.getContentCustomer();
        TestController.BeginTraining();
        TestController.GetTrainingsForCustomer();

        TestController.GetTrainingsForCoach();
        TestController.SetTrainingToShow();
        TestController.ShowTraining();
        TestController.CancelPersonalTraining();
        TestController.GetCustomers();
        TestController.GetCoachesForSpecificFacility();

        TestController.PutComment();
        TestController.searchUsers();
        TestController.searchTrainingsCustomer();
        TestController.searchTrainingsCoach();
    }
}
