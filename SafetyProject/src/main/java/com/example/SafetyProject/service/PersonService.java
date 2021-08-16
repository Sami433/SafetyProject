package com.example.SafetyProject.service;

import com.example.SafetyProject.repository.DataHandler;
import com.example.SafetyProject.repository.PersonRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PersonService {
    private final PersonRepository personRepository;
    private  final DataHandler dataHandler;

    public PersonService(PersonRepository personRepository, DataHandler dataHandler) {
        this.personRepository = personRepository;
        this.dataHandler = dataHandler;
    }



    public List<String> findAllEmailsByCity(String city) {

        return this.personRepository.findAllPersons().stream()
                .filter(p -> p.getCity().equals(city))
                .map(p -> p.getEmail())
                .collect(Collectors.toList());
    }







}
