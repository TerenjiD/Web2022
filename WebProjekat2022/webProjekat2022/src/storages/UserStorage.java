package storages;

import beans.Gender;
import beans.Role;
import beans.User;
import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class UserStorage {
    private HashMap<String, User> users = new HashMap<String, User>();
    private HashMap<String, User> users1 = new HashMap<String, User>();
    private static Scanner x;
    private static UserStorage instance = null;
    public static UserStorage getInstance() throws FileNotFoundException {
        if (instance==null){
            instance = new UserStorage();
        }
        return instance;
    }


    private UserStorage() throws FileNotFoundException {
        BufferedReader in = null;
        try {
            File file = new File(  "./static/users.csv");
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
        String line, username="", password="", name="",lastName="",gender="",dateOfBirth="",role="";
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
                }
                Gender gen;
                if(gender.equals("MALE")){
                    gen = Gender.MALE;
                }else{
                    gen = Gender.FEMALE;
                }
                Role roleFlag;
                if(role.equals("CUSTOMER")){
                    roleFlag = Role.CUSTOMER;
                }else if(role.equals("COACH")){
                    roleFlag = Role.COACH;
                }
                else if(role.equals("MANAGER")){
                    roleFlag = Role.MANAGER;
                }
                else{
                    roleFlag = Role.ADMIN;
                }

                User user = new User(username,password,name,lastName,gen,dateOfBirth, roleFlag);
                users.put(username, user);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public User FindById(String username){

        return users.get(username);
    }

    public void addUser(User user){

        File file = new File("./static/users.csv");
        Scanner sc = new Scanner(System.in);
        try{
            FileWriter outputfile = new FileWriter(file,true);

            CSVWriter writer = new CSVWriter(outputfile, ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            User tempUser = users.get(user.getUsername());
            if(tempUser==null){
                String[] data1 = {user.getUsername(),user.getPassword(),user.getName(),user.getLastName()
                        ,user.getGender().toString(),user.getDateOfBirth(),user.getRole().toString()};
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
        this.users.put(user.getUsername(),user);
    }

    public void editUser(User user,String username){
        users.put(user.getUsername(),user);
        String usern=user.getUsername();
        String password=user.getPassword();
        String name=user.getName();
        String lastName=user.getLastName();
        String gender=user.getGender().toString();
        String dateOfBirth=user.getDateOfBirth();
        String role=user.getRole().toString();
        String file = "./static/users.csv";
        File oldFile = new File(file);
        File newFile = new File("./static/temp.csv");
        BufferedReader reader = null;
        String line = "";
        List<String[]> rows = new ArrayList<>();
        try{
            FileWriter outputfile = new FileWriter("./static/temp.csv",true);
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
            File dump = new File ("./static/users.csv");
            newFile.renameTo(dump);


        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public Collection<User> getValues() {return users.values();}

    public List<User> GetUsers(){
        List<User> listToReturn = new ArrayList<>(getValues());
        return listToReturn;
    }

}
