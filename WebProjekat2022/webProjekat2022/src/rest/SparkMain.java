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

        FacilityController.getFacilities();
        FacilityController.searchFacilities();
    }
}
