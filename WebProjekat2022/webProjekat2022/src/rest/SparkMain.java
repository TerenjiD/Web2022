package rest;

import controller.TestController;

import java.io.File;

import static spark.Spark.port;
import static spark.Spark.staticFiles;
import static spark.Spark.get;
public class SparkMain {
    public static void main(String[] args) throws Exception {
        port(8080);

        staticFiles.externalLocation(new File("./static").getCanonicalPath());
        get("/component/register",(req,res)->{
            return "Works";
        });
        TestController.addUser();
        TestController.loginUser();
    }
}
