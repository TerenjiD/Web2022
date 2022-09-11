package storages;

import DTO.TrainingHistoryDTO;
import DTO.TrainingSearchDTO;
import beans.*;
import com.opencsv.CSVWriter;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
public class TrainingHistoryStorage {
    private HashMap<String, TrainingHistory> trainings = new HashMap<String, TrainingHistory>();

    private CoachStorage coaches = CoachStorage.getInstance();
    private CustomerStorage customers = CustomerStorage.getInstance();
    private ContentStorage trainingHistory = ContentStorage.getInstance();

    private FacilityStorage facilities = FacilityStorage.getInstance();
    private static TrainingHistoryStorage instance = null;
    public static TrainingHistoryStorage getInstance() throws FileNotFoundException {
        if (instance==null){
            instance = new TrainingHistoryStorage();
        }
        return instance;
    }

    public int IdSize(){
        return trainings.size();
    }

    public Collection<TrainingHistory> getValues() {return trainings.values();}

    public List<TrainingHistory> GetTrainings(){
        List<TrainingHistory> listToReturn = new ArrayList<>(getValues());
        return listToReturn;
    }

    private TrainingHistoryStorage() throws FileNotFoundException {
        BufferedReader in = null;
        try {
            File file = new File(  "./static/trainingHistory.txt");
            System.out.println(file.getCanonicalPath());
            in = new BufferedReader(new FileReader(file));
            readUsers(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if ( in != null ) {
                try {
                    in.close();
                }
                catch (Exception e) { }
            }
        }
    }

    private void readUsers(BufferedReader in)  {
        String line, id="",applicationDate="",training="",customer="",coach="";
        StringTokenizer st;
        try {
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.equals("") || line.indexOf('#') == 0)
                    continue;
                st = new StringTokenizer(line, ";");
                while (st.hasMoreTokens()) {
                    id = st.nextToken().trim();
                    applicationDate = st.nextToken().trim();
                    training = st.nextToken().trim();
                    customer = st.nextToken().trim();
                    coach = st.nextToken().trim();
                }
                LocalDate start = LocalDate.parse(applicationDate);
                TrainingHistory trainingToAddToHasMap = new TrainingHistory(id,start,
                        trainingHistory.CheckIfExist(training),
                        customers.GetByID(customer),coach);
                trainings.put(id, trainingToAddToHasMap);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void addTraining(TrainingHistory trainingToAdd){

        File file = new File("./static/trainingHistory.txt");
        Scanner sc = new Scanner(System.in);
        try{
            FileWriter outputfile = new FileWriter(file,true);

            CSVWriter writer = new CSVWriter(outputfile, ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            TrainingHistory tempUser = trainings.get(trainingToAdd.getId());
            if(tempUser==null){
                String[] data1 = {trainingToAdd.getId(), trainingToAdd.getApplicationDate().toString(),
                        trainingToAdd.getTraining().getNameID(), trainingToAdd.getCustomer().getUsername(),
                        trainingToAdd.getCoach()};
                List<String[]> userList = new ArrayList<>();
                userList.add(data1);
                //userList.add(data2);
                writer.writeAll(userList);

                writer.close();
            }else{
                writer.close();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.trainings.put(trainingToAdd.getId(), trainingToAdd);
    }

    public List<TrainingHistoryDTO> getSearchedTrainings(TrainingSearchDTO search, List<TrainingHistoryDTO> list) {
        Collection<TrainingHistoryDTO> temp = new ArrayList<TrainingHistoryDTO>();
        temp = searchByName(search.getName(),list);
        temp = searchByDate(search.getDate(),temp);
        if(search.getSortBy() != null && search.getSortType() != null){
            temp=sortList(search.getSortBy(),search.getSortType(),temp);
        }
        if(search.getFilterBy() != null){
            temp = filterList(search.getFilterBy(),temp);
        }
        List<TrainingHistoryDTO> listRet = new ArrayList<TrainingHistoryDTO>(temp);
        return listRet;
    }

    public Collection<TrainingHistoryDTO> searchByName(String name,List<TrainingHistoryDTO> list){
        Collection<TrainingHistoryDTO> temp = new ArrayList<TrainingHistoryDTO>();
        if (name == null || name == ""){
            return list;
        }else{
            for (TrainingHistoryDTO u : list) {
                if (u.getFacilityName().toUpperCase().contains(name.toUpperCase()))
                    temp.add(u);
            }
        }
        List<TrainingHistoryDTO> listRet = new ArrayList<TrainingHistoryDTO>(temp);
        return listRet;
    }

    private Collection<TrainingHistoryDTO> searchByDate(String date, Collection<TrainingHistoryDTO> starter) {
        Collection<TrainingHistoryDTO> temp = new ArrayList<TrainingHistoryDTO>();
        if (date == "" || date == null){
            temp = starter;
        }else{
            for (TrainingHistoryDTO u : starter) {
                if (u.getApplicationDate().toUpperCase().contains(date.toUpperCase()))
                    temp.add(u);
            }
        }
        List<TrainingHistoryDTO> list = new ArrayList<TrainingHistoryDTO>(temp);
        return list;
    }

    public Collection<TrainingHistoryDTO> sortList(String criterium,String type,Collection<TrainingHistoryDTO> starter){
        List<TrainingHistoryDTO> list = new ArrayList<TrainingHistoryDTO>(starter);
        List<TrainingHistoryDTO> temp = new ArrayList<TrainingHistoryDTO>();
        if(type.equals("1")){
            if (criterium.equals("1")) {
                Collections.sort(list,new TrainingComparatorByName());
            }else {
                Collections.sort(list, new TrainingComparatorByDate());
            }
        }else{
            if (criterium.equals("1")) {
                Collections.sort(list,new TrainingComparatorByName().reversed());
            }else {
                Collections.sort(list, new TrainingComparatorByDate().reversed());
            }
        }
        return list;
    }

    public Collection<TrainingHistoryDTO> filterList(String type,Collection<TrainingHistoryDTO> starter){
        Collection<TrainingHistoryDTO> temp = new ArrayList<TrainingHistoryDTO>();
        if(type.equals("1")){
            for (TrainingHistoryDTO f:starter){
                if(facilities.GetByIdFacility(f.getFacilityName()).getFacilityType().toString().contains("GYM")){
                    temp.add(f);
                }
            }
        }else if(type.equals("2")){
            for (TrainingHistoryDTO f:starter){
                if(facilities.GetByIdFacility(f.getFacilityName()).getFacilityType().toString().contains("POOL")){
                    temp.add(f);
                }
            }
        }else if(type.equals("3")){
            for (TrainingHistoryDTO f:starter){
                if(facilities.GetByIdFacility(f.getFacilityName()).getFacilityType().toString().contains("SPORT")){
                    temp.add(f);
                }
            }
        }else if(type.equals("4")){
            for (TrainingHistoryDTO f:starter){
                if(facilities.GetByIdFacility(f.getFacilityName()).getFacilityType().toString().contains("DANCE")){
                    temp.add(f);
                }
            }
        }else if(type.equals("5")){
            for (TrainingHistoryDTO f:starter){
                if(f.getType().toString().contains("GROUP")){
                    temp.add(f);
                }
            }
        }else if(type.equals("6")){
            for (TrainingHistoryDTO f:starter){
                if(f.getType().toString().contains("PERSONAL")){
                    temp.add(f);
                }
            }
        }else{
            for (TrainingHistoryDTO f:starter){
                if(f.getType().toString().contains("GYM")){
                    temp.add(f);
                }
            }
        }
        List<TrainingHistoryDTO> list = new ArrayList<TrainingHistoryDTO>(temp);
        return list;
    }
 }
