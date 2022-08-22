package storages;

import beans.*;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class CustomerTypeStorage {
    private HashMap<String, CustomerType> customerTypes = new HashMap<String, CustomerType>();

    private static CustomerTypeStorage instance = null;
    public static CustomerTypeStorage getInstance() throws FileNotFoundException {
        if(instance==null){
            instance = new CustomerTypeStorage();
        }
        return instance;
    }

    private CustomerTypeStorage() throws FileNotFoundException {
        BufferedReader in = null;
        try {
            File file = new File(  "./static/customerTypes.txt");
            System.out.println(file.getCanonicalPath());
            in = new BufferedReader(new FileReader(file));
            readTypes(in);
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

    private void readTypes(BufferedReader in)  {
        String line, name="", discount="", requiredPoints="";
        StringTokenizer st;
        try {
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.equals("") || line.indexOf('#') == 0)
                    continue;
                st = new StringTokenizer(line, ";");
                while (st.hasMoreTokens()) {
                    name = st.nextToken().trim();
                    discount = st.nextToken().trim();
                    requiredPoints = st.nextToken().trim();
                }
                CustomerType user = new CustomerType(name,Integer.parseInt(discount),Integer.parseInt(requiredPoints));
                customerTypes.put(name, user);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void addUser(CustomerType customer){

        File file = new File("./static/customerTypes.txt");
        Scanner sc = new Scanner(System.in);
        try{
            FileWriter outputfile = new FileWriter(file,true);

            CSVWriter writer = new CSVWriter(outputfile, ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            CustomerType tempUser = customerTypes.get(customer.getName());
            if(tempUser==null){
                String[] data1 = {customer.getName(),String.valueOf(customer.getDiscount()),
                        String.valueOf(customer.getRequiredPoints())};
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
        this.customerTypes.put(customer.getName(),customer);
    }

    public CustomerType FindById(String name){
        return customerTypes.get(name);
    }

}
