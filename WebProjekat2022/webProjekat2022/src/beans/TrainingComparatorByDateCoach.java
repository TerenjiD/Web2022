package beans;

import DTO.TrainingHistoryDTO;

import java.util.Comparator;

public class TrainingComparatorByDateCoach implements Comparator<Content> {
    @Override

    public int compare(Content o1, Content o2) {
        return o1.getDuration().toUpperCase().compareTo(o2.getDuration().toUpperCase());
    }
}
