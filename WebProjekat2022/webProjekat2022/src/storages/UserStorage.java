package storages;

import beans.Gender;
import beans.Role;
import beans.User;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class UserStorage {
    //private HashMap<String, User> users = new HashMap<String, User>();
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
            File file = new File(  "./static/users.txt");
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
    private List<String[]> readUsers1(BufferedReader in)  {
        List<String[]> userList = new ArrayList<>();
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
                String[] data1 = {user.getUsername(),user.getPassword(),user.getName(),user.getLastName()
                        ,user.getGender().toString(),user.getDateOfBirth(),user.getRole().toString()};
                userList.add(data1);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    public User FindById(String username){

        return users.get(username);
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
        String usern="";
        String password="";
        String name="";
        String lastname="";
        String gender="";
        String dateOfBirth="";
        String role="";
        String tempFile = "./static/temp.txt";
        File oldFile = new File("./static/users.txt");
        File newFile = new File(tempFile);
        try{
            FileWriter fw = new FileWriter(tempFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            x = new Scanner(new File("./static/users.txt"));
            x.useDelimiter("[;\n]");
            while(x.hasNext()){
                usern = x.next();
                password = x.next();
                name = x.next();
                lastname = x.next();
                gender = x.next();
                dateOfBirth = x.next();
                role = x.next();
                if(usern.equals(username)){
                    pw.println(user.getUsername()+";"+user.getPassword()+";"+user.getName()+";"+user.getLastName()+";"+
                            user.getGender().toString()+";"+user.getDateOfBirth()+";"+user.getRole().toString());
                }else{
                    pw.println(usern+";"+password+";"+name+";"+lastname+";"+
                            gender+";"+dateOfBirth+";"+role);
                }

            }
            x.close();
            pw.flush();
            pw.close();
            oldFile.delete();
            File dump = new File("./static/users.txt");
            newFile.renameTo(dump);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
