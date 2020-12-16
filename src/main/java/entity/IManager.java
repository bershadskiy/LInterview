package entity;

import java.util.HashSet;

public interface IManager {
    boolean addSubordinate(long subordinateId);
    boolean hasNoSubordinates();
    HashSet<Long> getSubordinatesIds();
}
