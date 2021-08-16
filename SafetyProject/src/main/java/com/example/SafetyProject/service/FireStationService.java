package com.example.SafetyProject.service;
import com.example.SafetyProject.repository.DataHandler;
import com.example.SafetyProject.repository.FireStationRepository;
import com.example.SafetyProject.repository.PersonRepository;
import com.example.SafetyProject.model.FireStation;
import com.example.SafetyProject.model.Person;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class FireStationService {
    private FireStationRepository fireStationRepository;
    private PersonRepository personRepository;
    private DataHandler dataHandler;


    public List<String> findPhoneNumbersByStationNumber(int stationNumber) {

        List<String> result = new ArrayList<>();

        List<FireStation> fireStations = fireStationRepository.findAllFireStationsAddressByNumber(stationNumber);

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

    }}
