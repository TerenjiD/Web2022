package services;

import beans.Facility;
import storages.FacilityStorage;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class FacilityService {

    private FacilityStorage facilities= new FacilityStorage();

    public List<Facility> getFacilities() {
        List<Facility> list= facilities.sortCollection(this.facilities.getValues());
        return list;
    }

    public List<Facility> searchFacilities(String input) {
        List<Facility> list= facilities.sortCollection(this.facilities.getSearched(input));
        return list;
    }
}
