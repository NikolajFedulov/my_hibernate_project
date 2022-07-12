package ua.ithillel.my_hibernate_project.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id", nullable = false, updatable = false)
    private long animalID;

    @Column(name = "species", nullable = false)
    @Enumerated(EnumType.STRING)
    private Species species;

    @Column(name = "animal_gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender animalGender;

    @Column(name = "animal_age", nullable = false)
    private int animalAge;

    @Column(name = "animal_name", nullable = false)
    private String animalName;


    public Animal(){
    }

    public Animal(long animalID, Species species, Gender animalGender, int animalAge, String animalName) {
        this.animalID = animalID;
        this.animalGender = animalGender;
        this.animalAge = animalAge;
        this.animalName = animalName;
        this.species = species;
    }

    public long getAnimalID() {
        return animalID;
    }

    public Gender getAnimalGender() {
        return animalGender;
    }

    public int getAnimalAge() {
        return animalAge;
    }

    public String getAnimalName() {
        return animalName;
    }

    public Species getSpecies() {
        return species;
    }

    public void setAnimalID(int animalID) {
        this.animalID = animalID;
    }

    public void setAnimalGender(Gender animalGender) {
        this.animalGender = animalGender;
    }

    public void setAnimalAge(int animalAge) {
        this.animalAge = animalAge;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return this.animalID == animal.animalID &&
                this.animalAge == animal.animalAge &&
                this.species == animal.species &&
                this.animalGender == animal.animalGender &&
                this.animalName.equalsIgnoreCase(animal.animalName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalID, species, animalGender, animalAge, animalName);
    }
}
