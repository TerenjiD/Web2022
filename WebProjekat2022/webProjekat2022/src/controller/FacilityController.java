package controller;

import services.FacilityService;

import com.google.gson.Gson;
import beans.Facility;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.put;
import static spark.Spark.post;

public class FacilityController {
    private static FacilityService facilityService=new FacilityService();
    private static Gson gson = new Gson();

    public static void getFacilities() {
        get("rest/facilities/", (req, res) -> {
            res.type("application/json");
            return gson.toJson(facilityService.getFacilities());
        });
    }

    public static void searchFacilities() {
        get("rest/facilities/search/:input", (req, res) -> {
            res.type("application/json");
            String input = req.params("input");
            return gson.toJson(facilityService.searchFacilities(input));
        });
    }
}
