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

    public List<Person> findAllpersonByAddress(String address) {

        return dataHandler.getData().getPersons().stream().filter(p -> p.getAddress().equals(address)).collect(Collectors.toList());
    }
ab
}