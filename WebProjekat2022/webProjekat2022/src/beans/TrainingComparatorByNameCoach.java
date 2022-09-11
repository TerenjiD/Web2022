package beans;

import DTO.TrainingHistoryDTO;

import java.util.Comparator;

public class TrainingComparatorByNameCoach implements Comparator<Content> {
    @Override

    public int compare(Content o1, Content o2) {
        return o1.getFacilityName().toUpperCase().compareTo(o2.getFacilityName().toUpperCase());
    }
}
