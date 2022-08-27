package storages;

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

}
