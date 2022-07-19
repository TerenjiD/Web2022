package storages;

import beans.Gender;
import beans.Manager;
import beans.Role;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class ManagerStorage {
    private HashMap<String, Manager> managers = new HashMap<String,Manager>();
    private static ManagerStorage instance = null;
    public static ManagerStorage getInstance() throws FileNotFoundException{
        if(instance==null){
            instance = new ManagerStorage();
        }
        return instance;
    }

    private ManagerStorage() throws FileNotFoundException{
        BufferedReader in = null;
        try {
            File file = new File(  "./static/managers.txt");
            System.out.println(file.getCanonicalPath());
            in = new BufferedReader(new FileReader(file));
            readManagers(in);
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

    private void readManagers(BufferedReader in ){
        String line, username="", password="", name="",lastName="",gender="",dateOfBirth="",role="",facility="";
        StringTokenizer st;
        try{
            while ((line = in.readLine()) != null){
                line = line.trim();
                if (line.equals("") || line.indexOf('#') == 0)
                    continue;
                st = new StringTokenizer(line, ";");
                while(st.hasMoreTokens()){
                    username = st.nextToken().trim();
                    password = st.nextToken().trim();
                    name = st.nextToken().trim();
                    lastName = st.nextToken().trim();
                    gender = st.nextToken().trim();
                    dateOfBirth = st.nextToken().trim();
                    role = st.nextToken().trim();
                    facility = st.nextToken().trim();
                }
                Gender gen;
                if(gender.equals("MALE")){
                    gen = Gender.MALE;
                }else{
                    gen = Gender.FEMALE;
                }
                Manager manager = new Manager(username,password,
                        name,lastName,gen,dateOfBirth, Role.MANAGER,facility);
                managers.put(username,manager);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void addManager(Manager manager){
        File file = new File("./static/managers.txt");
        Scanner sc = new Scanner(System.in);
        try{
            FileWriter outputfile = new FileWriter(file,true);
            CSVWriter writer = new CSVWriter(outputfile, ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            Manager tempManager = managers.get(manager.getUsername());
            if(tempManager==null){
                String[] data1 = {manager.getUsername(),manager.getPassword(),manager.getName(),manager.getLastName()
                        ,manager.getGender().toString(),manager.getDateOfBirth(),manager.getRole().toString(),manager.getFacility()};
                List<String[]> managerList = new ArrayList<>();
                managerList.add(data1);
                //userList.add(data2);
                writer.writeAll(managerList);

                writer.close();
            }else{
                writer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
