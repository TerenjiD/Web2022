package controller;

import DTO.*;
import beans.Membership;
import beans.MembershipType;
import services.FacilityService;

import com.google.gson.Gson;
import beans.Facility;
import services.TestService;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.put;
import static spark.Spark.post;

public class FacilityController {
    private static FacilityService facilityService;

    private static Gson g = new Gson();

    static {
        try {
            facilityService = new FacilityService();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static Gson gson = new Gson();

    public static void getFacilities() {
        get("rest/facilities/", (req, res) -> {
            res.type("application/json");
            return gson.toJson(facilityService.getFacilities());
        });
    }

    public static void searchFacilities() {
        post("rest/facilities/search/", (req, res) -> {
            res.type("application/json");
            FacilitySearchDTO facilitySearchDTO = g.fromJson(req.body(), FacilitySearchDTO.class);
            return gson.toJson(facilityService.searchFacilities(facilitySearchDTO));
        });
    }

}
