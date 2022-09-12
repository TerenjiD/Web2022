package storages;

import DTO.FacilityDTO;
import DTO.FacilitySearchDTO;
import beans.*;
import com.opencsv.CSVWriter;

import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class FacilityStorage {

    private HashMap<String, Facility> facilities = new HashMap<String, Facility>();
    private  static FacilityStorage instance = null;
    public static FacilityStorage getInstance() throws FileNotFoundException {
        if (instance == null) {
            instance = new FacilityStorage();
        }
        return instance;
    }

    public Facility CheckIfExists(String name){
        return facilities.get(name);
    }

    public FacilityStorage() {
        this(".");
    }

    public FacilityStorage(String path) {
        BufferedReader in = null;
        try {
            File file = new File(path + "/static/facilities.txt");
            System.out.println(file.getCanonicalPath());
            in = new BufferedReader(new FileReader(file));
            readFacilities(in);
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

    /**
     * Cita proizvode iz datoteke i smesta ih u asocijativnu listu objekata.
     * Kljuc je ime objekta.
     */
    private void readFacilities(BufferedReader in) {
        String line, name = "", facilityType = "", contentType = "", status="", logo="", location="",
                workingHours="", rating="";
        StringTokenizer st;
        try {
            while ((line = in.readLine()) != null) {
                line = line.trim();
                if (line.equals("") || line.indexOf('#') == 0)
                    continue;
                st = new StringTokenizer(line, ";");
                while (st.hasMoreTokens()) {
                    name = st.nextToken().trim();
                    facilityType = st.nextToken().trim();
                    contentType = st.nextToken().trim();
                    status = st.nextToken().trim();
                    logo = st.nextToken().trim();
                    location = st.nextToken().trim();
                    workingHours = st.nextToken().trim();
                    rating = st.nextToken().trim();
                }
                Facility facility = new Facility(name, getFacilityType(facilityType), contentType,
                        getFacilityStatus(status), logo, getLocation(location),workingHours, rating);
                facilities.put(name, facility);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private FacilityType getFacilityType(String type){
        if(type.equals("POOL"))
            return FacilityType.POOL;
        else if (type.equals("DANCE_STUDIO")) {
            return FacilityType.DANCE_STUDIO;
        }else if (type.equals("SPORT_CENTER")) {
            return FacilityType.SPORT_CENTER;
        }else
            return FacilityType.GYM;
    }

    private ContentType getContentType(String type){
        if(type.equals("SAUNA"))
            return ContentType.GYM;
        else if (type.equals("GROUP_TRAINING")) {
            return ContentType.GROUP_TRAINING;
        }else
            return ContentType.PERSONAL_TRAINING;
    }

    private FacilityStatus getFacilityStatus(String type){
        if(type.equals("OPEN"))
            return FacilityStatus.OPEN;
        else
            return FacilityStatus.CLOSED;
    }



    private Location getLocation(String location){
        String latitude="", longitude="", address="";

        StringTokenizer str = new StringTokenizer(location, "|");
        while (str.hasMoreTokens()) {
            latitude = str.nextToken().trim();
            longitude = str.nextToken().trim();
            address = str.nextToken().trim();
        }

        String  street="", number="", city="", country="";
        StringTokenizer strn = new StringTokenizer(address, "'");
        while (strn.hasMoreTokens()) {
            street = strn.nextToken().trim();
            number = strn.nextToken().trim();
            city = strn.nextToken().trim();
            country = strn.nextToken().trim();
        }
        Address add= new Address(street,number,city,country);
        Location newLocation = new Location(Float.parseFloat(latitude), Float.parseFloat(longitude), add);
        return newLocation;
    }

    /** Vraca kolekciju objekata. */
    public Collection<Facility> getValues() {
        return facilities.values();
    }

    /** Vraca objekat na osnovu njegovog imena. */
    public Facility getFacility(String name) {
        return facilities.get(name);
    }

    public List<Facility> sortCollection(Collection<Facility> collection) {
        List<Facility> list= new ArrayList<Facility>(collection);
        Collections.sort(list);
        return list;
    }

    public List<Facility> getSearched(FacilitySearchDTO search) {
        Collection<Facility> temp = new ArrayList<Facility>();
        temp = searchByName(search.getName());
        temp = searchByType(search.getType(),temp);
        temp = searchByLocation(search.getLocation(),temp);
        temp = searchByRating(search.getRating(),temp);
        if(search.getSortBy() != null && search.getSortType() != null){
            temp=sortList(search.getSortBy(),search.getSortType(),temp);
        }
        if(search.getFilterBy() != null){
            temp = filterList(search.getFilterBy(),temp);
        }
        List<Facility> list = new ArrayList<Facility>(temp);
        return list;
    }

    public Collection<Facility> searchByName(String name){
        Collection<Facility> temp = new ArrayList<Facility>();
        if (name == null || name ==""){
             temp = getValues();
        }else{
            for (Facility f : getValues()) {
                if (f.getName().toUpperCase().contains(name.toUpperCase()))
                    temp.add(f);
                }
        }
        List<Facility> list = new ArrayList<Facility>(temp);
        return list;
    }

    public Collection<Facility> searchByType(String type,Collection<Facility> starter){
        Collection<Facility> temp = new ArrayList<Facility>();
        if (type == "" || type == null){
            temp = starter;
        }else{
            for (Facility f : starter) {
                if (f.getFacilityType().toString().contains(type.toUpperCase()))
                    temp.add(f);
            }
        }
        List<Facility> list = new ArrayList<Facility>(temp);
        return list;
    }

    public Collection<Facility> searchByLocation(String location,Collection<Facility> starter){
        Collection<Facility> temp = new ArrayList<Facility>();
        if (location == "" || location == null){
            temp = starter;
        }else{
            for (Facility f : starter) {
                if (f.getLocation().getAddress().getCountry().toUpperCase().contains(location.toUpperCase()) ||
                        f.getLocation().getAddress().getCity().toUpperCase().contains(location.toUpperCase()))
                    temp.add(f);
            }
        }
        List<Facility> list = new ArrayList<Facility>(temp);
        return list;
    }

    public Collection<Facility> searchByRating(String rating,Collection<Facility> starter){
        Collection<Facility> temp = new ArrayList<Facility>();
        if (rating == "" || rating == null){
            temp = starter;
        }else{
            for (Facility f : starter) {
                if ( f.getRating().toUpperCase().contains(rating.toUpperCase()))
                    temp.add(f);
            }
        }
        List<Facility> list = new ArrayList<Facility>(temp);
        return list;
    }

     public Collection<Facility> sortList(String criterium,String type,Collection<Facility> starter){
         List<Facility> list = new ArrayList<Facility>(starter);
         if(type.equals("1")){
             if (criterium.equals("1")) {
                 Collections.sort(list,new FacilityComparator());
             }else if(criterium.equals("2")){
                 Collections.sort(list,new FacilityComparatorByLocation());
             }else{
                 Collections.sort(list,new FacilityComparatorByGrade());
             }
         }else{
             if (criterium.equals("1")) {
                 Collections.sort(list,new FacilityComparator().reversed());
             }else if(criterium.equals("2")){
                 Collections.sort(list,new FacilityComparatorByLocation().reversed());
             }else{
                 Collections.sort(list,new FacilityComparatorByGrade().reversed());
             }
         }
         return list;
     }

    public Collection<Facility> filterList(String type,Collection<Facility> starter){
        Collection<Facility> temp = new ArrayList<Facility>();
        if(type.equals("1")){
            for (Facility f:starter){
                if(f.getFacilityType().toString().toUpperCase().contains("GYM")){
                    temp.add(f);
                }
            }
        }else if(type.equals("2")){
            for (Facility f:starter){
                if(f.getFacilityType().toString().toUpperCase().contains("POOL")){
                    temp.add(f);
                }
            }
        }else if(type.equals("3")){
            for (Facility f:starter){
                if(f.getFacilityType().toString().toUpperCase().contains("SPORT_CENTER")){
                    temp.add(f);
                }
            }
        }else if(type.equals("4")){
            for (Facility f:starter){
                if(f.getFacilityType().toString().toUpperCase().contains("DANCE_STUDIO")){
                    temp.add(f);
                }
            }
        }else if(type.equals("5")){
            for (Facility f:starter){
                if(f.getStatus().toString().toUpperCase().contains("OPEN")){
                    temp.add(f);
                }
            }
        }else{
            for (Facility f:starter){
                if(f.getStatus().toString().toUpperCase().contains("CLOSED")){
                    temp.add(f);
                }
            }
        }
        List<Facility> list = new ArrayList<Facility>(temp);
        return list;
    }

    public void addFacility(FacilityDTO facility){
        File file = new File("./static/facilities.txt");
        Scanner sc = new Scanner(System.in);
        try{
            FileWriter outputfile = new FileWriter(file,true);
            CSVWriter writer = new CSVWriter(outputfile, ';',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            Facility tempFacility = facilities.get(facility.getName());
            String flagLocation = facility.getLatitude()+"|"+facility.getLongitude()+"|"+facility.getStreet()+"'"+
                    facility.getNumber()+"'"+facility.getCity()+"'"+facility.getCountry();
            if(tempFacility==null){
                //String flagLocation = getLocation(facility.getLocation());
                String[] data1 = {facility.getName(),facility.getFacilityType().toString(),facility.getContentType().toString(),
                facility.getStatus().toString(),facility.getLogo(),flagLocation,facility.getWorkingHours(),
                facility.getRating()};
                List<String[]> facilityList = new ArrayList<>();
                facilityList.add(data1);
                //userList.add(data2);
                writer.writeAll(facilityList);

                writer.close();
            }else{
                writer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //this.facilities.put(facility.getName(),facility);
    }

    public void addContent(String name,String content){
        Facility flagFacility = facilities.get(name);
        String flagLocation = Float.toString(flagFacility.getLocation().getLatitude())+"|"+Float.toString(flagFacility.getLocation().getLongitude())
                +"|"+flagFacility.getLocation().getAddress().getStreet()+"'"+flagFacility.getLocation().getAddress().getNumber()+
                "'"+flagFacility.getLocation().getAddress().getCity() +"'"+flagFacility.getLocation().getAddress().getCountry();
        String flagOldContent =flagFacility.getContentType();
        String flagContent = "";
        if(flagOldContent.equals("")){
            flagContent = content;
        }else{
            flagContent = flagOldContent + "," + content;
        }
        String file = "./static/facilities.txt";
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
                if(row[0].equals(name)){
                    row[0] = flagFacility.getName();
                    row[1] = flagFacility.getFacilityType().toString();
                    row[2] = flagContent;
                    row[3] = flagFacility.getStatus().toString();
                    row[4] = flagFacility.getLogo();
                    row[5] = flagLocation;
                    row[6] = flagFacility.getRating();
                    row[7] = flagFacility.getWorkingHours();
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
            File dump = new File ("./static/facilities.txt");
            newFile.renameTo(dump);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Facility GetByIdFacility(String username){
        Facility facility = facilities.get(username);
        return facility;
    }

}
