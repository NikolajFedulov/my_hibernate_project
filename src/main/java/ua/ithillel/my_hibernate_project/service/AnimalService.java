package ua.ithillel.my_hibernate_project.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ithillel.my_hibernate_project.model.Animal;
import ua.ithillel.my_hibernate_project.repository.AnimalRepository;

import javax.persistence.NoResultException;
import java.util.Optional;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final SessionFactory sessionFactory;

    @Autowired
    public AnimalService(AnimalRepository animalRepository, SessionFactory sessionFactory) {
        this.animalRepository = animalRepository;
        this.sessionFactory = sessionFactory;
    }

    public Optional<Animal> getAnimal (Long id) {
        return animalRepository.findById(id);
    }

    public Optional<Animal> addAnimal (Animal animal) {
        return !existByContentOfAnimal(animal) ? putAnimal(animal) : Optional.empty();
    }

    public Optional<Animal> updateAnimal (Animal animal) {
        return !existByContentOfAnimal(animal) && animalRepository.existsById(animal.getAnimalID()) ?
                postAnimal(animal) : Optional.empty();
    }

    public Optional<Animal> removeAnimal (Long id) {
        Optional<Animal> optionalAnimal = animalRepository.findById(id);
        if (optionalAnimal.isPresent()) animalRepository.deleteById(id);
        return optionalAnimal;
    }

    public Optional<Animal> findAnimal (Animal animal) {
        String fromAnimal = "SELECT * FROM animal WHERE species = CAST(:species AS species) " +
                "AND animal_gender = CAST(:animalGender AS gender) " +
                "AND animal_age = :animalAge " +
                "AND animal_name ILIKE :animalName";
        Session session = this.sessionFactory.openSession();
        NativeQuery query = session.createSQLQuery(fromAnimal);
        query.addEntity(Animal.class);
        query.setParameter("species", animal.getSpecies().var);
        query.setParameter("animalGender", animal.getAnimalGender().var);
        query.setParameter("animalAge", animal.getAnimalAge());
        query.setParameter("animalName", animal.getAnimalName());
        Optional<Animal> result;
        try {
            result = Optional.of((Animal) query.getSingleResult());
        }
        catch (NoResultException | NullPointerException e) {
            result = Optional.empty();
        }
        session.close();
        return result;
    }
    public boolean existByContentOfAnimal (Animal animal) {
        return findAnimal(animal).orElse(null) != null;
    }

    public Optional<Animal> putAnimal (Animal animal) {
        String insertAnimal = "INSERT INTO animal(species, animal_gender, animal_age, animal_name) " +
                "VALUES (CAST(:species AS species), CAST(:animalGender AS gender), :animalAge, :animalName)";
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery(insertAnimal);
        query.setParameter("species", animal.getSpecies().var);
        query.setParameter("animalGender", animal.getAnimalGender().var);
        query.setParameter("animalAge", animal.getAnimalAge());
        query.setParameter("animalName", animal.getAnimalName());
        query.executeUpdate();
        transaction.commit();
        session.close();
        return findAnimal(animal);
    }

    public Optional<Animal> postAnimal (Animal animal) {
        String updateAnimal = "UPDATE animal SET species = CAST(:species AS species), " +
                "animal_gender = CAST(:animalGender AS gender), " +
                "animal_age = :animalAge, " +
                "animal_name = :animalName WHERE animal_id = :animalID";
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery(updateAnimal);
        query.setParameter("species", animal.getSpecies().var);
        query.setParameter("animalGender", animal.getAnimalGender().var);
        query.setParameter("animalAge", animal.getAnimalAge());
        query.setParameter("animalName", animal.getAnimalName());
        query.setParameter("animalID", animal.getAnimalID());
        query.executeUpdate();
        transaction.commit();
        session.close();
        return animalRepository.findById(animal.getAnimalID());
    }
}
