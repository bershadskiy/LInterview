package entity;

import java.util.HashSet;
import java.util.Random;

public final class Employee extends ReporterAbstract implements IManager {
    private final long employeeId;
    private long bossId = -1;
    private HashSet<Long> subordinatesIds = null;

    Employee(Person applicant) {
        super(applicant);
        this.employeeId = new Random().nextLong();
    }

    public long getMyEmployeeId() {
        return employeeId;
    }

    public long getMyBossId() {
        return bossId;
    }

    /**
     * @return self for chaining call purposes
     */
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
