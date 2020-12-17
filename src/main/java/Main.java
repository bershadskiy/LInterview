import entity.Corp;
import entity.Employee;
import entity.Person;

public class Main {

    private static void print(String s) {
        System.out.println(s);
    }

    private static void failed(Person person) {
        System.out.println(person.getName() + " was not hired");
    }

    private static void succeed(Person person, Employee boss) {
        System.out.println(person.getName() + " was hired as " + boss.getName() + "'s subordinate");
    }

    public static void main(String[] args) {
        Corp corp = Corp.getInstance();
        Person john = new Person(40, "John (CEO)");
        Person mary = new Person(24, "Mary");
        Person peter = new Person(42, "Peter");
        Person michael = new Person(38, "Michael");
        Person sam = new Person(38, "Sam");
        Person will = new Person(38, "Will");
        Person jackie = new Person(38, "Jackie");
        Person frank = new Person(38, "Frank");

        Employee eJohn = corp.hireAsBoss(john);
        if (null == eJohn) {
            failed(john);
            return;
        } else {
            print("John was hired as Corp CEO");
        }

        Employee eMary = corp.hireAsNewSubordinate(mary, eJohn.getMyEmployeeId());
        if (null == eMary) {
            failed(mary);
            return;
        } else {
            succeed(eMary, eJohn);
        }

        if (null == corp.hireAsNewSubordinate(peter, eJohn.getMyEmployeeId())) {
            failed(peter);
        } else {
            succeed(peter, eJohn);
        }

        if (null == corp.hireAsNewSubordinate(michael, eJohn.getMyEmployeeId())) {
            failed(michael);
        } else {
            succeed(michael, eJohn);
        }

        if (null == corp.hireAsNewSubordinate(sam, eMary.getMyEmployeeId())) {
            failed(sam);
        } else {
            succeed(sam, eMary);
        }

        Employee eWill = corp.hireAsNewSubordinate(will, eMary.getMyEmployeeId());
        if(null == eWill){
            failed(will);
            return;
        } else {
            succeed(eWill,eMary);
        }

        if (null == corp.hireAsNewSubordinate(jackie, eWill.getMyEmployeeId())) {
            failed(jackie);
        } else {
            succeed(jackie, eWill);
        }

        if (null == corp.hireAsNewSubordinate(frank, eWill.getMyEmployeeId())) {
            failed(frank);
        } else {
            succeed(sam, eWill);
        }
        print("Corp is not hiring anymore. Proper implementation for taking employee by name for read/write access " +
                "can be checked inside the test");
    }
}
