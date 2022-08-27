package controller;

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
        get("rest/facilities/search/:input", (req, res) -> {
            res.type("application/json");
            String input = req.params("input");
            return gson.toJson(facilityService.searchFacilities(input));
        });
    }


    public static void buyMembership(){
        post("/rest/customerHomePage/buyMembership",(req,res)->{
           res.type("application/json");
           Membership membership = gson.fromJson(req.body(),Membership.class);
           LocalDate currentDate = LocalDate.now();
           MembershipType memType = membership.getType();
           LocalDate expirationDate;
           if(memType == MembershipType.MONTHLY){
               expirationDate = currentDate.plusMonths(1);
           }else if(memType == MembershipType.YEARLY){
               expirationDate = currentDate.plusMonths(12);
           }else{
               expirationDate = currentDate.plusMonths(6);
           }
           String idFlag = Integer.toString(facilityService.GetMembershipIDCount()+1);
           membership.setId(idFlag);
           membership.setPaymentDate(currentDate);
           membership.setExpirationDate(expirationDate);
           facilityService.createMembership(membership);
           return "SUCCESS";
        });
    }
}
