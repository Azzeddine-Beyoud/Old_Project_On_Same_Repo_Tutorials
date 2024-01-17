package Modul;

public class Person {

    private  int id ,age ;
    private String name , lname , address;

    public Person() {
    }

    public Person(int age, String name, String lname, String address) {
        this.age = age;
        this.name = name;
        this.lname = lname;
        this.address = address;
    }

    public Person(int id, int age, String name, String lname, String address) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.lname = lname;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
