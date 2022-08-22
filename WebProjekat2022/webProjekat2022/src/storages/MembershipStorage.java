package storages;

import beans.CustomerType;
import beans.Membership;
import beans.MembershipStatus;
import beans.MembershipType;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class MembershipStorage {

    private HashMap<String, Membership> memberships = new HashMap<String, Membership>();

    private static MembershipStorage instance = null;
    public static MembershipStorage getInstance() throws FileNotFoundException {
        if(instance==null){
            instance = new MembershipStorage();
        }
        return instance;
    }

    private MembershipStorage() throws FileNotFoundException {
        BufferedReader in = null;
        try {
            File file = new File(  "./static/memberships.txt");
            System.out.println(file.getCanonicalPath());
            in = new BufferedReader(new FileReader(file));
            readMemberships(in);
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

    private void readMemberships(BufferedReader in)  {
        String line, id="", type="", paymentDate="",expirationDate="",price="",customer="",status="",appointmentNumber="";
        StringTokenizer st;
        try {
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.equals("") || line.indexOf('#') == 0)
                    continue;
                st = new StringTokenizer(line, ";");
                while (st.hasMoreTokens()) {
                    id = st.nextToken().trim();
                    type = st.nextToken().trim();
                    paymentDate = st.nextToken().trim();
                    expirationDate = st.nextToken().trim();
                    price = st.nextToken().trim();
                    customer = st.nextToken().trim();
                    status = st.nextToken().trim();
                    appointmentNumber = st.nextToken().trim();
                }
                MembershipType memFlagType;
                if(type.equals("YEARLY")){
                    memFlagType = MembershipType.YEARLY;
                }else if(type.equals("SIXMONTHLY")){
                    memFlagType = MembershipType.SIXMONTHLY;
                }else if(type.equals("TWOMONTHLY")){
                    memFlagType = MembershipType.TWOMONTHLY;
                }else if(type.equals("MONTHLY")){
                    memFlagType = MembershipType.MONTHLY;
                }else{
                    memFlagType = MembershipType.YEARLY;
                }
                MembershipStatus memFlagStatus;
                if(status.equals("ACTIVE")){
                    memFlagStatus = MembershipStatus.ACTIVE;
                }else{
                    memFlagStatus = MembershipStatus.INACTIVE;
                }
                Date flagPaymentDate = new Date(paymentDate);
                Date flagExpirationDate = new Date(expirationDate);
                Membership membership = new Membership(id,memFlagType,flagPaymentDate,flagExpirationDate,Integer.parseInt(price)
                        ,customer, memFlagStatus,appointmentNumber);
                memberships.put(id, membership);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void addMembership(Membership membership){

        File file = new File("./static/memberships.txt");
        Scanner sc = new Scanner(System.in);
        try{
            FileWriter outputfile = new FileWriter(file,true);

            CSVWriter writer = new CSVWriter(outputfile, ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            Membership tempUser = memberships.get(membership.getId());
            if(tempUser==null){
                String[] data1 = {membership.getId(), membership.getType().toString(),
                        membership.getPaymentDate().toString(),membership.getExpirationDate().toString(),
                        String.valueOf(membership.getPrice()),membership.getCustomer(),membership.getStatus().toString(),
                        membership.getAppointmentNumber()};
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
        this.memberships.put(membership.getId(),membership);
    }

    public Membership FindById(String id){
        return memberships.get(id);
    }
}
