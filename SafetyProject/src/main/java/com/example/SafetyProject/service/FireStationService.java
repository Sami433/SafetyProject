package com.example.SafetyProject.service;
import com.example.SafetyProject.model.MedicalRecord;
import com.example.SafetyProject.repository.MedicalRecordsRepository;
import com.example.SafetyProject.repository.DataHandler;
import com.example.SafetyProject.repository.FireStationRepository;
import com.example.SafetyProject.repository.PersonRepository;
import com.example.SafetyProject.model.FireStation;
import com.example.SafetyProject.model.Person;
import com.example.SafetyProject.service.dto.FireStationDto;
import com.example.SafetyProject.service.dto.FireStationPersonDto;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class FireStationService extends CalculatorAge {
    private final FireStationRepository fireStationRepository;
    private final PersonRepository personRepository;
    public final DataHandler dataHandler;
    private final MedicalRecordsRepository medicalRecordsRepository;

    public FireStationService(FireStationRepository fireStationRepository, PersonRepository personRepository, DataHandler dataHandler, MedicalRecordsRepository medicalRecordsRepository) {
        this.fireStationRepository = fireStationRepository;
        this.personRepository = personRepository;
        this.dataHandler = dataHandler;
        this.medicalRecordsRepository = medicalRecordsRepository;
    }





    public List<String> findPhoneNumbersByStationNumber(int station) {
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


    private MedicalRecord medicalRecordsContainsPerson (List <MedicalRecord> medicalRecords, Person person) {
        for (MedicalRecord medicalRecord : medicalRecords) {

            if (medicalRecord.getFirstName().equals(person.getFirstName()) && medicalRecord.getLastName().equals(person.getLastName())){
                return medicalRecord;
            }
        }
        return null;

    }

    private FireStation FireStationContainPersons(List<FireStation> fireStations, Person person) {

        for (FireStation fireStation : fireStations) {
            if (fireStation.getAddress().equals(person.getAddress())) {
                return fireStation;
            }
        }
        return null;
    }


    public FireStationDto findAllPersonsByStationNumber(int number) {

        FireStationDto result = new FireStationDto();
        List<FireStationPersonDto> people = new ArrayList<>();
        result.setPeople(people);
        // get all stations by number
        List<FireStation> fireStations = fireStationRepository.findAllFireStationsAddressByNumber(number);
        List<MedicalRecord> medicalRecords = medicalRecordsRepository.findAllMedicalRecords();
        // get all people
        List<Person> persons = personRepository.findAllPersons();
        // compare addresses and add the results in FireStationDto

        for (Person person : persons) {
            FireStation fireStation = FireStationContainPersons(fireStations, person);
            if (fireStation != null) {
                FireStationPersonDto fireStationPersonDto = new FireStationPersonDto();
                fireStationPersonDto.setFirstName(person.getFirstName());
                fireStationPersonDto.setLastName(person.getLastName());
                fireStationPersonDto.setAddress(person.getAddress());
                fireStationPersonDto.setPhone(person.getPhone());


                Integer childsCount = 0;
                Integer adultsCount = 0;
                for (Person person2 : persons) {

                    MedicalRecord medicalRecord = medicalRecordsContainsPerson(medicalRecords, person2);
                    if (medicalRecord != null) {
                        if ((calculatorAge(medicalRecord.getBirthdate()) < 18)) {
                            result.setChildsCount(childsCount + 1);
                            childsCount++;
                        } else
                            result.setAdultsCount(adultsCount + 1);
                        adultsCount++;


                    }
                }
                result.getPeople().add(fireStationPersonDto);
            }


        }
        return result;
    }

    public void addFireStation(FireStation fireStation) {

        fireStationRepository.saveFireStation(fireStation);
    }



    public void deleteFireStation(String adress, String station) {
        fireStationRepository.deleteFireStation(adress, station);
    }

}

