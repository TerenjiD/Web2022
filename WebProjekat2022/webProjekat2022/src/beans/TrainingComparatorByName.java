package beans;

import DTO.TrainingHistoryDTO;

import java.util.Comparator;

public class TrainingComparatorByName implements Comparator<TrainingHistoryDTO> {
    @Override

    public int compare(TrainingHistoryDTO o1, TrainingHistoryDTO o2) {
        return o1.getFacilityName().toUpperCase().compareTo(o2.getFacilityName().toUpperCase());
    }
}
