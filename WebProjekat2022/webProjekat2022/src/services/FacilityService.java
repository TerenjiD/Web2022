package services;

import DTO.TrainingHistoryDTO;
import beans.Customer;
import beans.Facility;
import beans.Membership;
import beans.TrainingHistory;
import storages.CustomerStorage;
import storages.FacilityStorage;
import storages.MembershipStorage;
import storages.TrainingHistoryStorage;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FacilityService {
    private FacilityStorage facilities= new FacilityStorage();
    private CustomerStorage customers = CustomerStorage.getInstance();

    private TrainingHistoryStorage trainings = TrainingHistoryStorage.getInstance();

    private MembershipStorage memberships = MembershipStorage.getInstance();

    public FacilityService() throws FileNotFoundException{

    }

    public Customer GetByUsernameCustomer(String username){
        return customers.GetByID(username);
    }


    public List<Facility> getFacilities() {
        List<Facility> list= facilities.sortCollection(this.facilities.getValues());
        return list;
    }

    public List<Facility> searchFacilities(String input) {
        List<Facility> list= facilities.sortCollection(this.facilities.getSearched(input));
        return list;
    }

    public int GetMembershipIDCount(){
        return memberships.CountID();
    }

    public void createMembership(Membership membership,String username){
        String id = membership.getId();
        Customer customer = customers.GetByID(username);
        customers.editMembership(customer,id);
        memberships.addMembership(membership);
    }

    public boolean CheckIfExpired(String id,String username){
        Membership membership = memberships.FindById(id);
        String oldID = membership.getId();
        LocalDateTime timeToWork = membership.getExpirationDate();
        int hour = timeToWork.getHour();
        int minute = timeToWork.getMinute();
        int seconds = timeToWork.getSecond();
        int day = timeToWork.getDayOfMonth();
        LocalDateTime currentTime = LocalDateTime.now();
        int price = membership.getPrice();
        int usage = Integer.parseInt(membership.getAppointmentNumber());
        long points =(price/1000)*(30-usage);
        if(points == 0){
            points = 1;
        }
        //if(currentTime.getHour()>=hour || currentTime.getMinute()>=minute || currentTime.getSecond()>=seconds){
        if(currentTime.getDayOfMonth()>=day){
            Customer customer = customers.GetByID(username);
            customers.editMembershipAndPoints(customer,oldID,"nista",points);
            return false;
        }else{
            return true;
        }
    }

    public Membership GetMembershipById(String id){
        return memberships.FindById(id);
    }

    public void EditMembership(LocalDate currentDate, String membershipID){
        memberships.editAppNumber(membershipID);
    }

    public int GetTrainingSize(){
        return trainings.IdSize();
    }

    public void AddTraining(TrainingHistory training){
        trainings.addTraining(training);
    }

    public List<TrainingHistoryDTO> GetTrainingsForCustomer(String customerUsername){
        List<TrainingHistory> listToCheck = trainings.GetTrainings();
        List<TrainingHistoryDTO> listToReturn = new ArrayList<>();
        for (TrainingHistory training:
                listToCheck) {
            String flag=training.getCustomer().getUsername();
            if(flag.equals(customerUsername)){
                TrainingHistoryDTO dtoToAdd = new TrainingHistoryDTO(training.getId() , training.getApplicationDate().toString(), training.getTraining().getNameID(),
                        training.getTraining().getFacilityName(),training.getTraining().getName(),training.getTraining().getType(),
                        training.getTraining().getLogo(),training.getTraining().getDescription(), training.getTraining().getDuration(),
                        training.getCustomer().getUsername(),training.getCoach());
                listToReturn.add(dtoToAdd);
            }
        }
        return listToReturn;
    }

}
