package com.example.SafetyProject.repository;

import com.example.SafetyProject.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public class PersonRepository {

    private final DataHandler dataHandler;

    public PersonRepository(DataHandler dataHandler) {this.dataHandler = dataHandler;}

    public List< Person > findAllPersons() {

        return this.dataHandler.getData().getPersons();
    }

}