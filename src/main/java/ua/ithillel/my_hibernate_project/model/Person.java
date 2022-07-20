package ua.ithillel.my_hibernate_project.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "person")
public class Person {

    @Id
    @Column(name = "person_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long personID;

    @Column(name = "person_name", nullable = false)
    private String personName;

    @Column(name = "person_surname", nullable = false)
    private String personSurname;

    @Column(name = "person_age", nullable = false)
    private int personAge;

    @Column(name = "person_gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Person() {
    }

    public Person(long personID, String personName, String personSurname, int personAge, Gender gender) {
        this.personID = personID;
        this.personName = personName;
        this.personSurname = personSurname;
        this.personAge = personAge;
        this.gender = gender;


    }

    public long getPersonID() {
        return personID;
    }

    public String getPersonName() {
        return personName;
    }

    public String getPersonSurname() {
        return personSurname;
    }

    public int getPersonAge() {
        return personAge;
    }

    public Gender getGender() {
        return gender;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public void setPersonSurname(String personSurname) {
        this.personSurname = personSurname;
    }

    public void setPersonAge(int personAge) {
        this.personAge = personAge;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return this.personID == person.personID &&
                this.personAge == person.personAge &&
                this.personName.equalsIgnoreCase(person.personName) &&
                this.personSurname.equalsIgnoreCase(person.personSurname) &&
                gender == person.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(personID, personName, personSurname, personAge, gender);
    }
}
