package entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CorpTest {

    Corp corpInstance;

    @Before
    public void setUp() {
        assertNull("corp instance was not reset", corpInstance);
        corpInstance = Corp.getInstance();
    }

    @After
    public void tearDown() {
        assertNotNull("corp instance was not created", corpInstance);
        Corp.resetCorp();
        corpInstance = null;
    }

    @Test
    public void getInstance() {
        assertNotNull("Corp instance is not created", this.corpInstance);
        Corp corp = Corp.getInstance();
        assertNotNull("new instance is not created", corp);
        assertSame("Corp singleton is broken", this.corpInstance, corp);
    }

    @Test
    public void hasNoEmployees() {
        assertTrue("Corp created not empty", this.corpInstance.hasNoEmployees());
    }

    @Test
    public void hireAsBoss() {
        Person p = new Person(1, "address");
        Employee employee = this.corpInstance.hireAsBoss(p);
        assertNotNull("should be no restrictions to hire a person as boss", employee);
        assertEquals("C-level manager should have -1 as bossID", -1, employee.getMyBossId());
        assertFalse("Employee is not added to the office", this.corpInstance.hasNoEmployees());
        assertEquals("age is different", p.getAge(), employee.getAge());
        assertEquals("address is different", p.getAddress(), employee.getAddress());
    }

    /**
     * Also checking {@link IManager} subordinates related methods
     */
    @Test
    public void hireAsNewSubordinate() {
        long desiredBossId = 161412277L;
        Person pApplicant = new Person(24);
        Person pBoss = new Person(4);
        Employee rejectedEmployee = this.corpInstance.hireAsNewSubordinate(pApplicant, desiredBossId);
        assertNull("Employee was hired for unknown boss", rejectedEmployee);
        assertTrue("Employee was still added to corp", this.corpInstance.hasNoEmployees());
        Employee boss = this.corpInstance.hireAsBoss(pBoss);
        desiredBossId = boss.getMyEmployeeId();
        assertTrue("newly added boss has no subordinates", boss.hasNoSubordinates());
        Employee employee = this.corpInstance.hireAsNewSubordinate(pApplicant, desiredBossId);
        assertEquals("wrong bossID", desiredBossId, employee.getMyBossId());
        assertTrue("employee is not added to boss subordinates",
                boss.getSubordinatesIds().contains(employee.getMyEmployeeId()));
    }

    @Test
    public void findById() {
        Person p = new Person(2);
        Employee boss = this.corpInstance.hireAsBoss(p);
        Employee byId = this.corpInstance.findById(boss.getMyEmployeeId());
        assertSame("employed boss is different from founded one", boss, byId);
    }

    @Test
    public void getEmployeesCount() {
        assertEquals("empty corp should have 0 employees", 0, this.corpInstance.getEmployeesCount());
        Person p = new Person(1);
        Employee employee = this.corpInstance.hireAsBoss(p);
        assertEquals("we hired exact one person", 1, this.corpInstance.getEmployeesCount());
        Employee employee1 = this.corpInstance.hireAsBoss(p);
        assertEquals("we hired exact two persons", 2, this.corpInstance.getEmployeesCount());
        assertNotSame("Same person, taken as source should be processed as two different employees"
                , employee, employee1);
    }

    @Test
    public void getAllEmployees() {
        assertNull("empty corp should return null as employees list", this.corpInstance.getAllEmployees());
        Person p = new Person(1);
        Employee employee = this.corpInstance.hireAsBoss(p);
        Employee employee1 = this.corpInstance.hireAsBoss(p);
        final ArrayList<Employee> allEmployees = this.corpInstance.getAllEmployees();
        assertEquals("All employees collection not matching input size",
                2, allEmployees.size());
        final String collection_is_broken_msg = "Collection is broken";
        assertTrue(collection_is_broken_msg, allEmployees.contains(employee));
        assertTrue(collection_is_broken_msg, allEmployees.contains(employee1));
    }


}