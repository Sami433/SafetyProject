package com.example.SafetyProject.service;

import com.example.SafetyProject.model.*;
import com.example.SafetyProject.repository.*;
import com.example.SafetyProject.service.dto.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final DataHandler dataHandler;
    private final FireStationRepository fireStationRepository;
    private final MedicalRecordsRepository medicalRecordsRepository;

    public PersonService(PersonRepository personRepository, DataHandler dataHandler, FireStationRepository fireStationRepository, MedicalRecordsRepository medicalRecordsRepository) {
        this.personRepository = personRepository;
        this.dataHandler = dataHandler;
        this.fireStationRepository = fireStationRepository;
        this.medicalRecordsRepository = medicalRecordsRepository;
    }


    public List<String> findAllEmailsByCity(String city) {

        return this.personRepository.findAllPersons().stream()
                .filter(p -> p.getCity().equals(city))
                .map(p -> p.getEmail())
                .collect(Collectors.toList());
    }


    public List<ChildAlertDto> findAllchildsUnder18ByAddress(String address) {

        List<ChildAlertDto> result = new ArrayList<>();
// récuperer la liste des peronnes habitants à cette adresse

        List<Person> persons = personRepository.findAllpersonByAddress(address);
// recuperer la liste des medical records de - de 18 ans

        List<MedicalRecord> medicalRecords = medicalRecordsRepository.findAllMedicalRecordsUnder18();

// pour chaque élément de personne rechercher dans la liste des - 18 ans
        // je crée une troisieme liste et je fait rentrer les noms qui correspondent
        for (Person person : persons) {
            MedicalRecord medicalRecord = medicalRecordsContainsPerson(medicalRecords, person);
            if (medicalRecord != null) {
                ChildAlertDto dto = new ChildAlertDto();
                dto.setFirstName(person.getFirstName());
                dto.setLastName(person.getLastName());
                dto.setAge(String.valueOf(computeAge(medicalRecord.getBirthDate())));
                dto.setHouseholds(persons.stream().filter(p -> !p.getFirstName().equals(person.getFirstName())).collect(Collectors.toList()));
                result.add(dto);
            }
        }


        return result;
    }
private MedicalRecord medicalRecordsContainsPerson (List <MedicalRecord> medicalRecords, Person person) {
    for (MedicalRecord medicalRecord : medicalRecords) {

        if (medicalRecord.getFirstName().equals(person.getFirstName()) && medicalRecord.getLastName().equals(person.getLastName())){
            return medicalRecord;
        }
    }
    return null;

}




}