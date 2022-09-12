package storages;

import beans.Gender;
import beans.Manager;
import beans.Role;
import beans.User;
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

    public Collection<Manager> getValues() {
        return managers.values();
    }

    public List<Manager> getAll(){
        List<Manager> listToReturn = new ArrayList<>(getValues());
        return listToReturn;
    }

    public List<Manager> GetManagersWithoutFacility(){
        List<Manager> flagList = new ArrayList<>(getValues());
        List<Manager> listToReturn = new ArrayList<>();
        for (Manager flag:
                flagList) {
            String flagFacility = flag.getFacility();
            if(flagFacility.equals("nista")){
                listToReturn.add(flag);
            }
        }
        return  listToReturn;
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
            String flagForFacility = "";

            flagForFacility = "nista";
            //manager.setFacility("nista");
            if(tempManager==null){

                String[] data1 = {manager.getUsername(),manager.getPassword(),manager.getName(),manager.getLastName()
                        ,manager.getGender().toString(),manager.getDateOfBirth(),manager.getRole().toString(),manager.getFacility()};
                List<String[]> managerList = new ArrayList<>();
                managerList.add(data1);
                //userList.add(data2);
                writer.writeAll(managerList);

                writer.close();
                this.managers.put(manager.getUsername(),manager);
            }else{
                writer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Manager GetByIdManager(String username){
        Manager manager = managers.get(username);
        return manager;
    }


    public void editManager(Manager manager,String username){
        managers.put(manager.getUsername(),manager);
        String usern=manager.getUsername();
        String password=manager.getPassword();
        String name=manager.getName();
        String lastName=manager.getLastName();
        String gender=manager.getGender().toString();
        String dateOfBirth=manager.getDateOfBirth();
        String role=manager.getRole().toString();
        String facility = manager.getFacility();
        String file = "./static/managers.txt";
        File oldFile = new File(file);
        File newFile = new File("./static/temp.txt");
        BufferedReader reader = null;
        String line = "";
        List<String[]> rows = new ArrayList<>();
        try{
            FileWriter outputfile = new FileWriter("./static/temp.txt",true);
            reader = new BufferedReader(new FileReader(file));
            while((line=reader.readLine()) != null){
                String[] row = line.split(";");
                if(row[0].equals(username)){
                    row[0] = usern;
                    row[1] = password;
                    row[2] = name;
                    row[3] = lastName;
                    row[4] = gender;
                    row[5] = dateOfBirth;
                    row[6] = role;
                    row[7] = facility;
                }
                rows.add(row);

            }
            reader.close();

            CSVWriter writer = new CSVWriter(outputfile, ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);

            writer.writeAll(rows);
            writer.close();
            oldFile.delete();
            File dump = new File ("./static/managers.txt");
            newFile.renameTo(dump);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
