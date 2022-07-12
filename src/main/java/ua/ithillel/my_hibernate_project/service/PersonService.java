package ua.ithillel.my_hibernate_project.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ithillel.my_hibernate_project.model.Person;
import ua.ithillel.my_hibernate_project.repository.PersonRepository;

import javax.persistence.NoResultException;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonService(PersonRepository personRepository, SessionFactory sessionFactory) {
        this.personRepository = personRepository;
        this.sessionFactory = sessionFactory;
    }

    public Optional<Person> getPerson (Long id) {
        return personRepository.findById(id);
    }

    public Optional<Person> addPerson (Person person) {
        return !existByContentOfPerson(person) ? putPerson(person) : Optional.empty();
    }

    public Optional<Person> updatePerson (Person person) {
        return !existByContentOfPerson(person) && personRepository.existsById(person.getPersonID()) ?
                postPerson(person) : Optional.empty();
    }

    public Optional<Person> removePerson (Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) personRepository.deleteById(id);
        return optionalPerson;
    }

    public Optional<Person> findPerson (Person person) {
        String fromPerson = "SELECT * FROM person WHERE person_name ILIKE :personName " +
                "AND person_surname ILIKE :personSurname " +
                "AND person_age = :personAge " +
                "AND person_gender = CAST(:personGender AS gender)";
        Session session = this.sessionFactory.openSession();
        NativeQuery query = session.createSQLQuery(fromPerson);
        query.addEntity(Person.class);
        query.setParameter("personName", person.getPersonName());
        query.setParameter("personSurname", person.getPersonSurname());
        query.setParameter("personAge", person.getPersonAge());
        query.setParameter("personGender", person.getGender().var);
        Optional<Person> result;
        try {
            result = Optional.of((Person) query.getSingleResult());
        }
        catch (NoResultException | NullPointerException e) {
            result = Optional.empty();
        }
        session.close();
        return result;
    }
    public boolean existByContentOfPerson (Person person) {
        return findPerson(person).orElse(null) != null;
    }

    public Optional<Person> putPerson (Person person) {
        String insertPerson = "INSERT INTO person(person_name, person_surname, person_age, person_gender) " +
                "VALUES (:personName, :personSurname, :personAge, CAST(:personGender AS gender))";
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery(insertPerson);
        query.setParameter("personName", person.getPersonName());
        query.setParameter("personSurname", person.getPersonSurname());
        query.setParameter("personAge", person.getPersonAge());
        query.setParameter("personGender", person.getGender().var);
        query.executeUpdate();
        transaction.commit();
        session.close();
        return findPerson(person);
    }

    public Optional<Person> postPerson (Person person) {
        String updatePerson = "UPDATE person SET person_name = :personName, " +
                "person_surname = :personSurname, " +
                "person_age = :personAge, " +
                "person_gender = CAST(:personGender AS gender) WHERE person_id = :personID";
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery(updatePerson);
        query.setParameter("personName", person.getPersonName());
        query.setParameter("personSurname", person.getPersonSurname());
        query.setParameter("personAge", person.getPersonAge());
        query.setParameter("personGender", person.getGender().var);
        query.setParameter("personID", person.getPersonID());
        query.executeUpdate();
        transaction.commit();
        session.close();
        return personRepository.findById(person.getPersonID());
    }
}
