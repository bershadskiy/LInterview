package entity;

import java.util.List;

public interface IEmploymentManager {
    boolean hasNoEmployees();
    Employee hireAsNewSubordinate(Person applicant, long bossId);
    Employee hireAsBoss(Person applicant);
    Employee findById(long employeeId);
    List<Employee> getAllEmployees();
    int getEmployeesCount();
}
