package storages;

import beans.Gender;
import beans.Role;
import beans.User;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class UserStorage {
    private HashMap<String, User> users = new HashMap<String, User>();

    public UserStorage() {
        this(".");
    }

    public UserStorage(String path) {
        BufferedReader in = null;
        try {
            File file = new File(path + "/static/users.txt");
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

    private void readUsers(BufferedReader in){
        String line, username = "", password = "", name = "", lastName="", gender="", stateOfBirth="";
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
                    stateOfBirth = st.nextToken().trim();
                }
                Gender gen;
                if(gender.equals("Male")){
                    gen = Gender.MALE;
                }else{
                    gen = Gender.FEMALE;
                }

                User user = new User(username, password, name, lastName, gen, stateOfBirth, Role.CUSTOMER);
                users.put(username, user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addUser(User user){

        File file = new File("./static/users.txt");
        Scanner sc = new Scanner(System.in);
        try{
            FileWriter outputfile = new FileWriter(file,true);



            CSVWriter writer = new CSVWriter(outputfile, ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);


            String[] data1 = {user.getUsername(),user.getPassword(),user.getName(),user.getLastName()
            ,user.getGender().toString(),user.getDateOfBirth(),user.getRole().toString()};
            String[] data2 = {"m","m","m","m"
                    ,Gender.MALE.toString(),"m",Gender.FEMALE.toString()};
            List<String[]> userList = new ArrayList<>();
            userList.add(data1);
            //userList.add(data2);
            writer.writeAll(userList);

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.users.put(user.getUsername(),user);
    }
}
