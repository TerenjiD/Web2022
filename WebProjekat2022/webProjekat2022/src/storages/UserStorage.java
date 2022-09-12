package storages;

import DTO.UsersSearchDTO;
import beans.*;
import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class UserStorage {
    private HashMap<String, User> users = new HashMap<String, User>();
    private HashMap<String, User> users1 = new HashMap<String, User>();
    private static Scanner x;

    private CustomerStorage customers = CustomerStorage.getInstance();
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


    public List<User> getSearchedUsers(UsersSearchDTO search) {
        Collection<User> temp = new ArrayList<User>();
        temp = searchByName(search.getName());
        temp = searchBySurname(search.getSurname(),temp);
        temp = searchByUsername(search.getUsername(),temp);
        if(search.getSortBy() != null && search.getSortType() != null){
            temp=sortList(search.getSortBy(),search.getSortType(),temp);
        }
        if(search.getFilterBy() != null){
            temp = filterList(search.getFilterBy(),temp);
        }
        List<User> list = new ArrayList<User>(temp);
        return list;
    }

    public Collection<User> searchByName(String name){
        Collection<User> temp = new ArrayList<User>();
        if (name == null || name == ""){
            temp = getValues();
        }else{
            for (User u : getValues()) {
                if (u.getName().toUpperCase().contains(name.toUpperCase()))
                    temp.add(u);
            }
        }
        List<User> list = new ArrayList<User>(temp);
        return list;
    }

    public Collection<User> searchBySurname(String surname,Collection<User> starter){
        Collection<User> temp = new ArrayList<User>();
        if (surname == "" || surname == null){
            temp = starter;
        }else{
            for (User u : starter) {
                if (u.getLastName().toUpperCase().contains(surname.toUpperCase()))
                    temp.add(u);
            }
        }
        List<User> list = new ArrayList<User>(temp);
        return list;
    }

    public Collection<User> searchByUsername(String username,Collection<User> starter){
        Collection<User> temp = new ArrayList<User>();
        if (username == "" || username == null){
            temp = starter;
        }else{
            for (User u : starter) {
                if (u.getUsername().toUpperCase().contains(username.toUpperCase()))
                    temp.add(u);
            }
        }
        List<User> list = new ArrayList<User>(temp);
        return list;
    }

    public Collection<User> sortList(String criterium,String type,Collection<User> starter){
        List<User> list = new ArrayList<User>(starter);
        List<User> temp = new ArrayList<User>();
        List<Customer> customer = new ArrayList<Customer>();
        for( User u : starter){
            if(u.getRole().toString().toUpperCase().contains("CUSTOMER")){
                    customer.add(customers.FindById(u.getUsername()));
            }
        }
        if(type.equals("1")){
            if (criterium.equals("1")) {
                Collections.sort(list,new UserComparatorByName());
            }else if(criterium.equals("2")){
                Collections.sort(list,new UserComparatorBySurname());
            }else if(criterium.equals("3")){
                Collections.sort(list,new UserComparatorByUsername());
            }else{
                if(!customer.isEmpty())
                Collections.sort(customer,new UserComparatorByPoints());
                for(Customer c : customer){
                    temp.add(FindById(c.getUsername()));
                }
                list=temp;
            }
        }else{
            if (criterium.equals("1")) {
                Collections.sort(list,new UserComparatorByName().reversed());
            }else if(criterium.equals("2")){
                Collections.sort(list,new UserComparatorBySurname().reversed());
            }else if(criterium.equals("3")){
                Collections.sort(list,new UserComparatorByUsername().reversed());
            }else{
                if(!customer.isEmpty())
                    Collections.sort(customer,new UserComparatorByPoints().reversed());
                for(Customer c : customer){
                    temp.add(FindById(c.getUsername()));
                }
                list=temp;
            }
        }
        return list;
    }
    public Collection<User> filterList(String type,Collection<User> starter){
        Collection<User> temp = new ArrayList<User>();
        Collection<Customer> cust= new ArrayList<Customer>();
        for (User f :starter){
            if(f.getRole().toString().toUpperCase().contains("CUSTOMER")){
                try {
                    cust.add(CustomerStorage.getInstance().FindById(f.getUsername()));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if(type.equals("1")){
            for (User f:starter){
                if(f.getRole().toString().toUpperCase().contains("ADMIN")){
                    temp.add(f);
                }
            }
        }else if(type.equals("2")){
            for (User f:starter){
                if(f.getRole().toString().toUpperCase().contains("MANAGER")){
                    temp.add(f);
                }
            }
        }else if(type.equals("3")){
            for (User f:starter){
                if(f.getRole().toString().toUpperCase().contains("COACH")){
                    temp.add(f);
                }
            }
        }else if(type.equals("4")){
            for (User f:starter){
                if(f.getRole().toString().toUpperCase().contains("CUSTOMER")){
                    temp.add(f);
                }
            }
        }else if(type.equals("5")){
            for (User f : cust){
                if(f.getRole().toString().toUpperCase().contains("CUSTOMER")){
                    if(customers.GetByID(f.getUsername()).getCustomerType().toUpperCase().contains("NORMAL"))
                    temp.add(FindById(f.getUsername()));
                }
            }
        }else if(type.equals("6")){
            for (User f : cust){
                if(f.getRole().toString().toUpperCase().contains("CUSTOMER")){
                    if(customers.GetByID(f.getUsername()).getCustomerType().toUpperCase().contains("SILVER"))
                        temp.add(FindById(f.getUsername()));
                }
            }
        }else{
            for (User f : cust){
                if(f.getRole().toString().toUpperCase().contains("CUSTOMER")){
                    if(customers.GetByID(f.getUsername()).getCustomerType().toUpperCase().contains("GOLD"))
                        temp.add(FindById(f.getUsername()));
                }
            }
        }
        List<User> list = new ArrayList<User>(temp);
        return list;
    }

}
