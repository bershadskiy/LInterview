package entity;

import java.util.ArrayList;
import java.util.HashMap;

public class Corp implements IEmploymentManager {
    private static Corp corpInstance = null;
    private HashMap<Long, Employee> employees = null;

    public static Corp getInstance() {
        if (null == corpInstance)
            corpInstance = new Corp();
        return corpInstance;
    }

    private Corp() {
    }

    static void resetCorp() {
        if (null == corpInstance)
            return;

        if (null != corpInstance.employees) {
            corpInstance.employees.clear();
            corpInstance.employees = null;
        }
        corpInstance = null;
    }

    @Override
    public boolean hasNoEmployees() {
        return null == this.employees || this.employees.isEmpty();
    }

    /**
     * Any person who comes can become a new employee
     *
     * @param applicant {@link Person} who applies
     * @param bossId    pass -1 to hire person as C-level manager
     * @return {@code null} if something goes wrong, employed person otherwise
     */
    @Override
    public Employee hireAsNewSubordinate(Person applicant, long bossId) {
        if (null == this.employees)
            this.employees = new HashMap<>(1);

        Employee desiredBoss = null;
        if (-1 != bossId) {
            desiredBoss = this.findById(bossId);
            if (null == desiredBoss)
                return null;
        }

        Employee hiring = Employee.fromPerson(applicant)
                .setBossId(bossId);
        long employeeId = hiring.getMyEmployeeId();

        if (-1 == bossId || desiredBoss.addSubordinate(employeeId))
            this.employees.put(employeeId, hiring);
        else
            hiring = null;

        return hiring;
    }

    /**
     * Calls {@link Corp#hireAsNewSubordinate(Person, long)} with -1 as bossID
     *
     * @param applicant {@link Person} who applies
     */
    @Override
    public Employee hireAsBoss(Person applicant) {
        return this.hireAsNewSubordinate(applicant, -1);
    }

    @Override
    public Employee findById(long employeeId) {
        if (hasNoEmployees())
            return null;

        return this.employees.get(employeeId);
    }

    @Override
    public ArrayList<Employee> getAllEmployees() {
        if (this.hasNoEmployees())
            return null;

        return new ArrayList<>(this.employees.values());
    }

    @Override
    public int getEmployeesCount() {
        return hasNoEmployees() ? 0 : this.employees.size();
    }
}
