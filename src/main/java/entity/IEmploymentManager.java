package entity;

import java.util.Collection;

public interface IEmploymentManager {
    boolean hasNoEmployees();
    Employee hireAsNewSubordinate(Person applicant, long bossId);
    Employee hireAsBoss(Person applicant);
    Employee findById(long employeeId);
    Collection<Employee> getAllEmployees();
    int getEmployeesCount();
}
