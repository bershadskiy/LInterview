package entity;

import java.util.LinkedList;
import java.util.Random;

public class Employee extends PersonAbstract implements IManager {
    public long getEmployeeId() {
        return employeeId;
    }

    private final long employeeId;

    public long getBossId() {
        return bossId;
    }

    public void setBossId(long bossId) {
        this.bossId = bossId;
    }

    private long bossId;
    private LinkedList<Long> subordinatesIds;

    public Employee() {
        this.employeeId = new Random().nextLong();
    }

    @Override
    public void addSubordinate(long subordinateId) {
        if(null == this.subordinatesIds)
            this.subordinatesIds = new LinkedList<>();
        subordinatesIds.addLast(subordinateId);
    }

    @Override
    public boolean hasNoSubordinates() {
        return null == this.subordinatesIds || subordinatesIds.isEmpty();
    }

    @Override
    public LinkedList<Long> getSubordinatesIds() {
        return this.subordinatesIds;
    }
}
