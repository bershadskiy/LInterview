package entity;

import java.util.List;

public interface IManager {
    void addSubordinate(long subordinateId);
    boolean hasNoSubordinates();
    List<Long> getSubordinatesIds();
}
