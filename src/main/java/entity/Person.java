package entity;

public class Person {

    public Person(int age) {
        this(age, null);
    }

    public Person(int age, String address) {
        setAge(age);
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (0 >= age)
            throw new IllegalArgumentException("Age should be greater then 0");
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    private int age;
    private String address;
}
