package entity;

public class Person {

    public Person(int age){
        this(age,null);
    }

    public Person(int age, String address) {
        this.age = age;
        this.address = address;
    }

    Person(Person applicant) {
        this.address = applicant.address;
        this.age = applicant.age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private int age;
    private String address;
}
