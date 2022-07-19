package storages;

import beans.Coach;
import beans.Gender;
import beans.Manager;
import beans.Role;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class CoachStorage {
    private HashMap<String, Coach> coaches = new HashMap<String, Coach>();
    private static CoachStorage instance = null;

    public static CoachStorage getInstance() throws FileNotFoundException {
        if (instance == null) {
            instance = new CoachStorage();
        }
        return instance;
    }

    private CoachStorage() throws FileNotFoundException {
        BufferedReader in = null;
        try {
            File file = new File("./static/coaches.txt");
            System.out.println(file.getCanonicalPath());
            in = new BufferedReader(new FileReader(file));
            readCoaches(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                }
            }
        }
    }

    private void readCoaches(BufferedReader in) {
        String line, username = "", password = "", name = "", lastName = "", gender = "", dateOfBirth = "", role = "", trainingHistory = "";
        StringTokenizer st;
        try {
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.equals("") || line.indexOf('#') == 0)
                    continue;
                st = new StringTokenizer(line, ";");
                while (st.hasMoreTokens()) {
                    username = st.nextToken().trim();
                    password = st.nextToken().trim();
                    name = st.nextToken().trim();
                    lastName = st.nextToken().trim();
                    gender = st.nextToken().trim();
                    dateOfBirth = st.nextToken().trim();
                    role = st.nextToken().trim();
                    trainingHistory = st.nextToken().trim();
                }
                Gender gen;
                if (gender.equals("MALE")) {
                    gen = Gender.MALE;
                } else {
                    gen = Gender.FEMALE;
                }
                Coach coach = new Coach(username, password,
                        name, lastName, gen, dateOfBirth, Role.MANAGER, trainingHistory);
                coaches.put(username, coach);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCoach(Coach coach){
        File file = new File("./static/coaches.txt");
        Scanner sc = new Scanner(System.in);
        try{
            FileWriter outputfile = new FileWriter(file,true);
            CSVWriter writer = new CSVWriter(outputfile, ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            Coach tempCoach = coaches.get(coach.getUsername());
            if(tempCoach==null){
                String[] data1 = {coach.getUsername(),coach.getPassword(),coach.getName(),coach.getLastName()
                        ,coach.getGender().toString(),coach.getDateOfBirth(),coach.getRole().toString(),coach.getTrainingHistory()};
                List<String[]> coachList = new ArrayList<>();
                coachList.add(data1);
                //userList.add(data2);
                writer.writeAll(coachList);

                writer.close();
            }else{
                writer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
