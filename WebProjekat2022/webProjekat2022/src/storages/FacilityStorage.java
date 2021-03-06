package storages;

import beans.*;

import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class FacilityStorage {

    private HashMap<String, Facility> facilities = new HashMap<String, Facility>();

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
                Facility facility = new Facility(name, getFacilityType(facilityType), getContentType(contentType),
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
            return ContentType.SAUNA;
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

    public List<Facility> getSearched(String search) {
        List<Facility> temp = new ArrayList<Facility>();
            for (Facility f : getValues()) {
                if (f.getName().toUpperCase().contains(search.toUpperCase()) || f.getFacilityType().toString().contains(search.toUpperCase()) ||
                        f.getLocation().getAddress().getCountry().toUpperCase().contains(search.toUpperCase()) ||
                        f.getLocation().getAddress().getCity().toUpperCase().contains(search.toUpperCase()) ||
                        f.getRating().toUpperCase().contains(search.toUpperCase()))
                    temp.add(f);
            }
        List<Facility> list = new ArrayList<Facility>(temp);
        Collections.sort(list);
        return list;
    }

}
