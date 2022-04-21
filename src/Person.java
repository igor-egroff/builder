import java.util.*;

public class Person {

    protected final String name;
    protected final String surname;
    protected int age;
    protected String city;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;

    }

    public Person(String name, String surname, int age, String city) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.city = city;
    }

    //Наполним класс Person методами, нужными для реализации поведения объектов этого класса как описано выше
    // в условии.


    public boolean hasAge() {
        if (!(age == 0)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasAddress() {
        if (city == null) {
            return true;
        } else {
            return false;
        }
    }


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return city;
    }

    public void setAddress(String address) {
        this.city = city;
    }

    public void happyBirthday(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name + surname + age + city;
        //return name;
    }

    @Override
    public boolean equals(Object obj) {
        // Сравним с собой
        if (obj == this) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(Person.class)) {
            return false;
        }

        Person altPerson = (Person) obj;

        return this.name.equals(altPerson.name) && this.surname.equals(altPerson.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    public PersonBuilder newChildBuilder() {
        return new PersonBuilder();
    }
}
