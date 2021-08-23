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
    private final FireStationRepository fireStationRepository;
    private final PersonRepository personRepository;
    public final DataHandler dataHandler;

    public FireStationService(FireStationRepository fireStationRepository, PersonRepository personRepository, DataHandler dataHandler) {
        this.fireStationRepository = fireStationRepository;
        this.personRepository = personRepository;
        this.dataHandler = dataHandler;
    }


    public List<String> findPhoneNumbersByStationNumber(String station) {
        List<String> result = new ArrayList<>();
        List<FireStation> fireStations = fireStationRepository.findAllFireStationsAddressByNumber(station);
        List<Person> persons = personRepository.findAllPersons();

        for (Person person : persons) {
            if (personsContainsFirestationAddress(fireStations, person)) {
                result.add(person.getPhone());
            }
        }

        return result;

    }

    private boolean personsContainsFirestationAddress(List<FireStation> fireStations, Person person) {
        for (FireStation fireStation:fireStations){
            if (fireStation.getAddress().equals(person.getAddress())){
                return  true;
            }
        }
        return false;
    }
}