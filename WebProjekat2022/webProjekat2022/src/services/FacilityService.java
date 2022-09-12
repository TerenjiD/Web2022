package services;

import DTO.FacilitySearchDTO;
import DTO.TrainingHistoryDTO;
import DTO.TrainingSearchDTO;
import beans.*;
import storages.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class FacilityService {
    private FacilityStorage facilities= new FacilityStorage();
    private CustomerStorage customers = CustomerStorage.getInstance();

    private TrainingHistoryStorage trainings = TrainingHistoryStorage.getInstance();

    private CoachStorage coaches = CoachStorage.getInstance();
    private ContentStorage contents = ContentStorage.getInstance();

    private MembershipStorage memberships = MembershipStorage.getInstance();

    private CustomerTypeStorage types = CustomerTypeStorage.getInstance();

    private static TestService testService;

    public FacilityService() throws FileNotFoundException{

    }

    public Customer GetByUsernameCustomer(String username){
        return customers.GetByID(username);
    }


    public List<Facility> getFacilities() {
        List<Facility> list= facilities.sortCollection(this.facilities.getValues());
        return list;
    }

    public List<Facility> searchFacilities(FacilitySearchDTO input) {
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

    public boolean CheckIfExpired(String id,String username) {
        Membership membership = memberships.FindById(id);
        String oldID = membership.getId();
        LocalDateTime timeToWork = membership.getExpirationDate();
        int hour = timeToWork.getHour();
        int minute = timeToWork.getMinute();
        int seconds = timeToWork.getSecond();
        int day = timeToWork.getDayOfMonth();
        LocalDateTime currentTime = LocalDateTime.now();
        double price = membership.getPrice();
        int usage = Integer.parseInt(membership.getAppointmentNumber());
        int maxApps = Integer.parseInt(membership.getAppointmentNumberMax());
        double points = (price / 1000) * (maxApps - usage);
        if (points == 0) {
            points = 1;
        }
            //if(currentTime.getHour()>=hour || currentTime.getMinute()>=minute || currentTime.getSecond()>=seconds){
            if (currentTime.getDayOfMonth() >= day) {
                Customer customer = customers.GetByID(username);
                double newPoints = customer.getPoints() + points;
                changeCustomerType(customer, newPoints);
                customers.editMembershipAndPoints(customer, oldID, "nista", points);
                return false;
            } else {
                return true;
            }
    }
    public void changeCustomerType(Customer customer,double newPoints){
        String type = customer.getCustomerType();
        double flagPointsNumber = 0;
        List<CustomerType> listToIterate = types.getTypes();
        for (CustomerType typeFlag:listToIterate
        ) {
            String flag = typeFlag.getName();//flag silver
            double flagNum = typeFlag.getRequiredPoints();
            if(flag.equals(type)){
                continue;
            }else{
                if(flagPointsNumber == 0 && flagNum<newPoints){
                    customers.editType(customer,flag);
                    flagPointsNumber = flagNum;
                }else if(newPoints<flagPointsNumber){
                    continue;
                }else if(flagNum<newPoints && flagPointsNumber<flagNum){
                    customers.editType(customer,flag);
                    flagPointsNumber = flagNum;
                }

            }
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

    public List<Content> getTrainings(String username){
        List<Content> listToReturn = contents.GetTrainingsForCoach(username);
        return listToReturn;
    }

    public void CancelTraining(Content training){
        String id = training.getNameID();
        contents.CancelContent(training);
    }

    public List<Customer> GetCustomersToShowForManager(Manager manager){
        List<Customer> listToIterate =  customers.GetCustomers();
        List<Customer> listToReturn = new ArrayList<>();
        String facilityFlag = manager.getFacility();
        Facility facility = facilities.getFacility(facilityFlag);
        //String memFlag = customer.getMembership();
        for (Customer customerToCheck: listToIterate
        ) {
            String membershipFlag = customerToCheck.getMembership();
            if(membershipFlag.equals("nista")){
                continue;
            }
            Membership membership = memberships.FindById(membershipFlag);
            String facilityFlag1 = membership.getFacility();

            int appNum = Integer.parseInt(membership.getAppointmentNumber());
            int appNumMax = Integer.parseInt(membership.getAppointmentNumberMax());
            int sum = appNumMax-appNum;
            if(facilityFlag1.equals(facilityFlag) && sum != 0){
                listToReturn.add(customerToCheck);
            }
        }
        return listToReturn;
    }

    public Customer GetCustomer(String username){
        return customers.FindById(username);
    }

    public List<Coach> GetCoachesToShowForManager(String facility){
        String username="",name="",lastName="";
        List<Coach> coachList = coaches.GetCoaches();
        List<Content> contentList = contents.GetContents(facility);
        List<Coach> listToReturn = new ArrayList<>();
        Coach coach;
        StringTokenizer st;
        for (Content content: contentList) {
            String coachToTrim = content.getCoachID();
            if(coachToTrim.equals("nema")){
                continue;
            }
            StringTokenizer str = new StringTokenizer(coachToTrim, " ");
            while (str.hasMoreTokens()) {
                username = str.nextToken().trim();
                name = str.nextToken().trim();
                lastName = str.nextToken().trim();
            }
            coach = coaches.GetByIdCoach(username);
            listToReturn.add(coach);
        }
        return listToReturn;
    }


    public List<TrainingHistoryDTO> searchTrainingsCustomer(TrainingSearchDTO input,List<TrainingHistoryDTO> list) {
        List<TrainingHistoryDTO> listReturn= this.trainings.getSearchedTrainings(input,list);
        return listReturn;
    }

    public List<Content> searchTrainingsCoach(TrainingSearchDTO input, List<Content> list) {
        List<Content> listReturn= this.contents.getSearchedTrainingsCoach(input,list);
        return listReturn;
    }

    public boolean checkDateForTrainingToCancel(Content training){
        //String flagNameID = training.getNameID();
        //contents.CheckIfExist(flagNameID);
        LocalDate dateFlag = LocalDate.parse(training.getDuration());
        LocalDate dateFlagNow = LocalDate.now();
        int daysBeetween =(int) DAYS.between(dateFlagNow,dateFlag);
        if(daysBeetween<3){
            return false;
        }else {
            return true;
        }

    }



}
