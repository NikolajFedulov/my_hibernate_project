package ua.ithillel.my_hibernate_project.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ua.ithillel.my_hibernate_project.model.Gender;
import ua.ithillel.my_hibernate_project.model.Person;
import ua.ithillel.my_hibernate_project.repository.PersonRepository;

import java.util.Optional;


class PersonServiceTest {

    private final SessionFactory sessionFactory = Mockito.mock(SessionFactory.class);
    private final PersonRepository personRepository = Mockito.mock(PersonRepository.class);
    private final PersonService personService = new PersonService(personRepository, sessionFactory);

    private final Person susan = new Person(
            1,
            "Susan",
            "Shelton",
            25,
            Gender.FEMALE
    );
    private final Person gloria = new Person(
            2,
            "Gloria",
            "Singleton",
            30,
            Gender.FEMALE
    );

    @Test
    void getPerson() {
        Mockito.when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(susan));
        Assertions.assertEquals(susan, personService.getPerson(48646215L).orElse(null));

        Mockito.when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Assertions.assertNull(personService.getPerson(151512572L).orElse(null));
    }

    @Test
    void addPerson() {
        Session session = Mockito.mock(Session.class);
        Mockito.when(sessionFactory.openSession()).thenReturn(session);
        NativeQuery query = Mockito.mock(NativeQuery.class);
        Mockito.when(session.createSQLQuery(Mockito.anyString())).thenReturn(query);
        Transaction transaction = Mockito.mock(Transaction.class);
        Mockito.when(session.beginTransaction()).thenReturn(transaction);

        Mockito.when(query.getSingleResult()).thenReturn(null).thenReturn(susan);
        Assertions.assertEquals(susan, personService.addPerson(gloria).orElse(null));

        Mockito.when(query.getSingleResult()).thenReturn(susan);
        Assertions.assertNull(personService.addPerson(susan).orElse(null));
    }

    @Test
    void updatePerson() {
        Session session = Mockito.mock(Session.class);
        Mockito.when(sessionFactory.openSession()).thenReturn(session);
        NativeQuery query = Mockito.mock(NativeQuery.class);
        Mockito.when(session.createSQLQuery(Mockito.anyString())).thenReturn(query);
        Transaction transaction = Mockito.mock(Transaction.class);
        Mockito.when(session.beginTransaction()).thenReturn(transaction);

        Mockito.when(personRepository.existsById(Mockito.anyLong())).thenReturn(true);
        Mockito.when(query.getSingleResult()).thenReturn(null);
        Mockito.when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(susan));
        Assertions.assertEquals(susan, personService.updatePerson(gloria).orElse(null));

        Mockito.when(personRepository.existsById(Mockito.anyLong())).thenReturn(false);
        Mockito.when(query.getSingleResult()).thenReturn(null);
        Assertions.assertNull(personService.updatePerson(gloria).orElse(null));

        Mockito.when(personRepository.existsById(Mockito.anyLong())).thenReturn(true);
        Mockito.when(query.getSingleResult()).thenReturn(gloria);
        Assertions.assertNull(personService.updatePerson(susan).orElse(null));
    }

    @Test
    void removePerson() {
        Mockito.when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(susan));
        Assertions.assertEquals(susan, personService.removePerson(48152185L).orElse(null));

        Mockito.when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Assertions.assertNull(personService.removePerson(51551L).orElse(null));
    }

    @Test
    void findPerson() {
        Session session = Mockito.mock(Session.class);
        Mockito.when(sessionFactory.openSession()).thenReturn(session);
        NativeQuery query = Mockito.mock(NativeQuery.class);
        Mockito.when(session.createSQLQuery(Mockito.anyString())).thenReturn(query);

        Mockito.when(query.getSingleResult()).thenReturn(susan);
        Assertions.assertEquals(susan, personService.findPerson(gloria).orElse(null));

        Mockito.when(query.getSingleResult()).thenReturn(null);
        Assertions.assertNull(personService.findPerson(susan).orElse(null));
    }

    @Test
    void existByContentOfPerson() {
        Session session = Mockito.mock(Session.class);
        Mockito.when(sessionFactory.openSession()).thenReturn(session);
        NativeQuery query = Mockito.mock(NativeQuery.class);
        Mockito.when(session.createSQLQuery(Mockito.anyString())).thenReturn(query);

        Mockito.when(query.getSingleResult()).thenReturn(susan);
        Assertions.assertTrue(personService.existByContentOfPerson(gloria));

        Mockito.when(query.getSingleResult()).thenReturn(null);
        Assertions.assertFalse(personService.existByContentOfPerson(susan));
    }

    @Test
    void putPerson() {
        Session session = Mockito.mock(Session.class);
        Mockito.when(sessionFactory.openSession()).thenReturn(session);
        NativeQuery query = Mockito.mock(NativeQuery.class);
        Mockito.when(session.createSQLQuery(Mockito.anyString())).thenReturn(query);
        Transaction transaction = Mockito.mock(Transaction.class);
        Mockito.when(session.beginTransaction()).thenReturn(transaction);

        Mockito.when(query.getSingleResult()).thenReturn(susan);
        Assertions.assertEquals(susan, personService.putPerson(gloria).orElse(null));

        Mockito.when(query.getSingleResult()).thenReturn(null);
        Assertions.assertNull(personService.putPerson(susan).orElse(null));
    }

    @Test
    void postPerson() {
        Session session = Mockito.mock(Session.class);
        Mockito.when(sessionFactory.openSession()).thenReturn(session);
        NativeQuery query = Mockito.mock(NativeQuery.class);
        Mockito.when(session.createSQLQuery(Mockito.anyString())).thenReturn(query);
        Transaction transaction = Mockito.mock(Transaction.class);
        Mockito.when(session.beginTransaction()).thenReturn(transaction);

        Mockito.when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(susan));
        Assertions.assertEquals(susan, personService.postPerson(gloria).orElse(null));

        Mockito.when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Assertions.assertNull(personService.postPerson(susan).orElse(null));
    }
}