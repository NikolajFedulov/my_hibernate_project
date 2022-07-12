package ua.ithillel.my_hibernate_project.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ua.ithillel.my_hibernate_project.model.Animal;
import ua.ithillel.my_hibernate_project.model.Gender;
import ua.ithillel.my_hibernate_project.model.Species;
import ua.ithillel.my_hibernate_project.repository.AnimalRepository;

import java.util.Optional;


class AnimalServiceTest {


    private final SessionFactory sessionFactory = Mockito.mock(SessionFactory.class);
    private final AnimalRepository animalRepository = Mockito.mock(AnimalRepository.class);
    private final AnimalService animalService = new AnimalService(animalRepository, sessionFactory);

    private final Animal zoe = new Animal(
            1,
            Species.DOG,
            Gender.FEMALE,
            13,
            "Zoe"
    );
    private final Animal fredricka = new Animal(
            2,
            Species.DOG,
            Gender.FEMALE,
            0,
            "Fredricka"
    );

    @Test
    void getAnimal() {
        Mockito.when(animalRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(zoe));
        Assertions.assertEquals(zoe, animalService.getAnimal(100000000l).orElse(null));

        Mockito.when(animalRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Assertions.assertNull(animalService.getAnimal(100000000l).orElse(null));
    }

    @Test
    void addAnimal() {
        Session session = Mockito.mock(Session.class);
        Mockito.when(sessionFactory.openSession()).thenReturn(session);
        NativeQuery query = Mockito.mock(NativeQuery.class);
        Mockito.when(session.createSQLQuery(Mockito.anyString())).thenReturn(query);
        Transaction transaction = Mockito.mock(Transaction.class);
        Mockito.when(session.beginTransaction()).thenReturn(transaction);

        Mockito.when(query.getSingleResult()).thenReturn(null).thenReturn(zoe);
        Assertions.assertEquals(zoe, animalService.addAnimal(zoe).orElse(null));

        Mockito.when(query.getSingleResult()).thenReturn(zoe);
        Assertions.assertNull(animalService.addAnimal(zoe).orElse(null));
    }

    @Test
    void updateAnimal() {
        Session session = Mockito.mock(Session.class);
        Mockito.when(sessionFactory.openSession()).thenReturn(session);
        NativeQuery query = Mockito.mock(NativeQuery.class);
        Mockito.when(session.createSQLQuery(Mockito.anyString())).thenReturn(query);
        Transaction transaction = Mockito.mock(Transaction.class);
        Mockito.when(session.beginTransaction()).thenReturn(transaction);

        Mockito.when(animalRepository.existsById(Mockito.anyLong())).thenReturn(true);
        Mockito.when(query.getSingleResult()).thenReturn(null);
        Mockito.when(animalRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(zoe));
        Assertions.assertEquals(zoe, animalService.updateAnimal(fredricka).orElse(null));

        Mockito.when(animalRepository.existsById(Mockito.anyLong())).thenReturn(false);
        Mockito.when(query.getSingleResult()).thenReturn(null);
        Assertions.assertNull(animalService.updateAnimal(zoe).orElse(null));

        Mockito.when(animalRepository.existsById(Mockito.anyLong())).thenReturn(true);
        Mockito.when(query.getSingleResult()).thenReturn(fredricka);
        Assertions.assertNull(animalService.updateAnimal(zoe).orElse(null));
    }

    @Test
    void removeAnimal() {
        Mockito.when(animalRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(zoe));
        Assertions.assertEquals(zoe, animalService.removeAnimal(10000000L).orElse(null));

        Mockito.when(animalRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Assertions.assertNull(animalService.removeAnimal(10000000L).orElse(null));
    }

    @Test
    void findAnimal() {
        Session session = Mockito.mock(Session.class);
        Mockito.when(sessionFactory.openSession()).thenReturn(session);
        NativeQuery query = Mockito.mock(NativeQuery.class);
        Mockito.when(session.createSQLQuery(Mockito.anyString())).thenReturn(query);

        Mockito.when(query.getSingleResult()).thenReturn(zoe);
        Assertions.assertEquals(zoe, animalService.findAnimal(fredricka).orElse(null));

        Mockito.when(query.getSingleResult()).thenReturn(null);
        Assertions.assertNull(animalService.findAnimal(zoe).orElse(null));
    }

    @Test
    void existByContentOfAnimal() {
        Session session = Mockito.mock(Session.class);
        Mockito.when(sessionFactory.openSession()).thenReturn(session);
        NativeQuery query = Mockito.mock(NativeQuery.class);
        Mockito.when(session.createSQLQuery(Mockito.anyString())).thenReturn(query);

        Mockito.when(query.getSingleResult()).thenReturn(zoe);
        Assertions.assertTrue(animalService.existByContentOfAnimal(fredricka));

        Mockito.when(query.getSingleResult()).thenReturn(null);
        Assertions.assertFalse(animalService.existByContentOfAnimal(zoe));
    }

    @Test
    void putAnimal() {
        Session session = Mockito.mock(Session.class);
        Mockito.when(sessionFactory.openSession()).thenReturn(session);
        NativeQuery query = Mockito.mock(NativeQuery.class);
        Mockito.when(session.createSQLQuery(Mockito.anyString())).thenReturn(query);
        Transaction transaction = Mockito.mock(Transaction.class);
        Mockito.when(session.beginTransaction()).thenReturn(transaction);

        Mockito.when(query.getSingleResult()).thenReturn(zoe);
        Assertions.assertEquals(zoe, animalService.putAnimal(fredricka).orElse(null));

        Mockito.when(query.getSingleResult()).thenReturn(null);
        Assertions.assertNull(animalService.putAnimal(zoe).orElse(null));
    }

    @Test
    void postAnimal() {
        Session session = Mockito.mock(Session.class);
        Mockito.when(sessionFactory.openSession()).thenReturn(session);
        NativeQuery query = Mockito.mock(NativeQuery.class);
        Mockito.when(session.createSQLQuery(Mockito.anyString())).thenReturn(query);
        Transaction transaction = Mockito.mock(Transaction.class);
        Mockito.when(session.beginTransaction()).thenReturn(transaction);

        Mockito.when(animalRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(zoe));
        Assertions.assertEquals(zoe, animalService.postAnimal(fredricka).orElse(null));

        Mockito.when(animalRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Assertions.assertNull(animalService.postAnimal(zoe).orElse(null));
    }
}