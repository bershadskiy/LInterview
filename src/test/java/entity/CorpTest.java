package entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

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
        assertEquals("address is different", p.getName(), employee.getName());
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
        final Collection<Employee> allEmployees = this.corpInstance.getAllEmployees();
        assertEquals("All employees collection not matching input size",
                2, allEmployees.size());
        final String collection_is_broken_msg = "Collection is broken";
        assertTrue(collection_is_broken_msg, allEmployees.contains(employee));
        assertTrue(collection_is_broken_msg, allEmployees.contains(employee1));
    }

    @Test
    public void findByName() {
        Person p = new Person(1, "John");
        Employee employee = this.corpInstance.hireAsBoss(p);
        assertNotNull(employee);
        ArrayList<Employee> johnList = new ArrayList<>(this.corpInstance.findByName("John"));
        assertNotNull(johnList);
        assertEquals(1, johnList.size());
        Employee actual = johnList.get(0);
        assertEquals(employee, actual);
        employee.setAge(11);
        assertEquals(11, actual.getAge());
    }

    @Test
    public void findByNameAndID() {
        Person p = new Person(1, "John");
        this.corpInstance.hireAsBoss(p);
        this.corpInstance.hireAsBoss(p);
        this.corpInstance.hireAsBoss(p);
        this.corpInstance.hireAsBoss(p);
        this.corpInstance.hireAsBoss(p);
        ArrayList<Employee> johnsList = new ArrayList<>(this.corpInstance.findByName("John"));
        assertNotNull(johnsList);
        assertEquals(5, johnsList.size());
        Employee actual = johnsList.get(0);
        Employee byId = this.corpInstance.findById(actual.getMyEmployeeId());
        assertEquals(byId, actual);
        byId.setAge(11);
        assertEquals(11, actual.getAge());
        johnsList.remove(0);
        for (Employee e : johnsList) {
            assertEquals(1, e.getAge());
        }
    }

    int count = 4 * 1000 * 1000;
    String[] names = new String[]{"John", "Tom", "Rebeca", "April"};

    @Test
    public void timeO() {
        HashMap<Long, Employee> s = new HashMap<>(1);
        long startAdd = System.currentTimeMillis();
        Employee e = null;
        for (int i = 0; i < count; i++) {
            e = Employee.fromPerson(new Person(i + 1, names[i % 4]));
            s.put(e.getMyEmployeeId(), e);
        }
        long addDoneMs = System.currentTimeMillis();
        System.out.println("HM time to add");
        System.out.println((addDoneMs - startAdd));

        Employee finalE = e;
        final List<Employee> collect = s.values().stream()
                .filter(em -> em.getName().equals(finalE.getName())).collect(Collectors.toList());
        long findDoneMs = System.currentTimeMillis();
        System.out.println("HM time to find");
        System.out.println((findDoneMs - addDoneMs));
    }

    @Test
    public void timeOA() {
        LinkedList<Employee> s = new LinkedList<>();
        long startAdd = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            Employee e = Employee.fromPerson(new Person(i + 1, names[i % 4]));
            s.add(e);
        }
        long addDoneMs = System.currentTimeMillis();

        System.out.println("LList time to add");
        System.out.println((addDoneMs - startAdd));

        Employee peek = s.peek();

        final List<Employee> collect = s.stream()
                .filter(e -> e.getName().equals(peek.getName())).collect(Collectors.toList());
        long findDoneMs = System.currentTimeMillis();
        System.out.println("LList time to find");
        System.out.println((findDoneMs - addDoneMs));
    }
}