package com.example.SafetyProject.service;

import com.example.SafetyProject.model.FireStation;
import com.example.SafetyProject.model.Person;
import com.example.SafetyProject.repository.DataHandler;
import com.example.SafetyProject.repository.FireStationRepository;
import com.example.SafetyProject.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final DataHandler dataHandler;
    private final FireStationRepository fireStationRepository;

    public PersonService(PersonRepository personRepository, DataHandler dataHandler, FireStationRepository fireStationRepository) {
        this.personRepository = personRepository;
        this.dataHandler = dataHandler;
        this.fireStationRepository = fireStationRepository;
    }





    public List<String> findAllEmailsByCity(String city) {

        return this.personRepository.findAllPersons().stream()
                .filter(p -> p.getCity().equals(city))
                .map(p -> p.getEmail())
                .collect(Collectors.toList());
    }

    public List<String> findPhoneNumbersByStationNumber(int number) {

        List<String> result = new ArrayList<>();

        List<FireStation> fireStations = fireStationRepository.findAllFireStationsAddressByNumber(number);


        List<Person> persons = personRepository.findAllPersons();


        for (Person person : persons) {
            if (personsContainsFirestationAddress(fireStations, person)) {
                result.add(person.getPhone());
            }
        }

        return result;

    }


    private boolean personsContainsFirestationAddress(List<FireStation> fireStations, Person person) {
        for (FireStation fireStation : fireStations) {
            if (fireStation.getAddress().equals(person.getAddress())) {
                return true;
            }
        }
        return false;
    }






}
