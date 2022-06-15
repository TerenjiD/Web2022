package controller;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.put;
import static spark.Spark.post;

public class TestController {

    public static void test() {
        get(
                "rest/users/", (req,res) -> {
                    return "hello world";
                }
        );
    }
}
