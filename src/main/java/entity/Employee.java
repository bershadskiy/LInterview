package entity;

import java.util.HashSet;
import java.util.Random;

public class Employee extends ReporterAbstract implements IManager {
    private final long employeeId;
    private long bossId;
    private HashSet<Long> subordinatesIds;

    public Employee(Person applicant) {
        super(applicant);
        this.employeeId = new Random().nextLong();
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public long getBossId() {
        return bossId;
    }

    public Employee setBossId(long bossId) {
        this.bossId = bossId;
        return this;
    }

    @Override
    public boolean addSubordinate(long subordinateId) {
        if(null == this.subordinatesIds)
            this.subordinatesIds = new HashSet<>();
        return subordinatesIds.add(subordinateId);
    }

    @Override
    public boolean hasNoSubordinates() {
        return null == this.subordinatesIds || subordinatesIds.isEmpty();
    }

    @Override
    public HashSet<Long> getSubordinatesIds() {
        return this.subordinatesIds;
    }
}
