package storages;

import beans.*;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class CustomerStorage {
    MembershipStorage memStor = MembershipStorage.getInstance();
    CustomerTypeStorage typeStor = CustomerTypeStorage.getInstance();
    private HashMap<String, Customer> customers = new HashMap<String, Customer>();
    private static CustomerStorage instance = null;
    public static CustomerStorage getInstance() throws FileNotFoundException {
        if(instance==null){
            instance = new CustomerStorage();
        }
        return instance;
    }

    private CustomerStorage() throws FileNotFoundException {
        BufferedReader in = null;
        try {
            File file = new File(  "./static/customers.txt");
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
        String line, username="", password="", name="",lastName="",gender="",dateOfBirth="",role="",points="",customerType=""
                ,membership="";
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
                    points = st.nextToken().trim();
                    customerType = st.nextToken().trim();
                    membership = st.nextToken().trim();
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
                Customer customer = new Customer(username,password,name,lastName,gen,dateOfBirth, roleFlag,Integer.parseInt(points),
                        typeStor.FindById(customerType),memStor.FindById(membership));
                customers.put(username, customer);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void addCustomer(Customer customer){

        File file = new File("./static/customers.txt");
        Scanner sc = new Scanner(System.in);
        try{
            FileWriter outputfile = new FileWriter(file,true);

            CSVWriter writer = new CSVWriter(outputfile, ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            Customer tempUser = customers.get(customer.getUsername());
            if(tempUser==null){
                String[] data1 = {customer.getUsername(),customer.getPassword(),customer.getName(),customer.getLastName()
                        ,customer.getGender().toString(),customer.getDateOfBirth(),customer.getRole().toString(),
                        "0", "nista", "nista"};
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
        this.customers.put(customer.getUsername(),customer);
    }

}
