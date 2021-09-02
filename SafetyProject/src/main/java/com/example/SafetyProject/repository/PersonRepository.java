package com.example.SafetyProject.repository;

import com.example.SafetyProject.model.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.text.*;
import java.util.*;
import java.util.stream.*;

@Repository

public class PersonRepository {

    private final DataHandler dataHandler;

    public PersonRepository(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    public List<Person> findAllPersons() {

        return this.dataHandler.getData().getPersons();
    }

    public List<Person> findAllpersonByAddress(String aress) {

        return dataHandler.getData().getPersons().stream().filter(p -> p.getAddress().equals(aress)).collect(Collectors.toList());
    }

    public List<Person> findAllpersonByName(String firstName, String lastName) {
        return this.dataHandler.getData().getPersons().stream()
                .filter(person -> person.getFirstName().equals(firstName))
                .filter(person -> person.getLastName().equals(lastName))
                .collect(Collectors.toList());
    }

    public void savePerson(Person person) {
        dataHandler.getData().getPersons().add(person);
        dataHandler.save();
    }

    public void deletePerson(String firstName, String lastName) {
        List<Person> persons = dataHandler.getData().getPersons();
        List<Person> toRemove = dataHandler.getData().getPersons().stream()
                .filter(person -> person.getFirstName().equals(firstName))
                .filter(person -> person.getLastName().equals(lastName))
                .collect(Collectors.toList());
        persons.removeAll(toRemove);
        dataHandler.getData().setPersons(persons);
        dataHandler.save();
    }

}
