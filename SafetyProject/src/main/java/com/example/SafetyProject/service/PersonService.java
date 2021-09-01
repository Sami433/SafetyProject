package com.example.SafetyProject.service;


import com.example.SafetyProject.model.*;
import com.example.SafetyProject.repository.*;
import com.example.SafetyProject.service.dto.*;
import org.springframework.stereotype.Service;

import java.text.*;
import java.util.*;
import java.util.stream.Collectors;


@Service

public class PersonService extends CalculatorAge {

    private final PersonRepository personRepository;
    private final FireStationRepository fireStationRepository;
    private final MedicalRecordsRepository medicalRecordsRepository;

    public PersonService(PersonRepository personRepository, FireStationRepository fireStationRepository, MedicalRecordsRepository medicalRecordsRepository) {
        this.personRepository = personRepository;
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

        List<Person> persons = personRepository.findAllpersonByAddress(address);

        List<MedicalRecord> medicalRecords = medicalRecordsRepository.findAllMedicalRecordsUnder18();

        for (Person person : persons) {
            MedicalRecord medicalRecord = medicalRecordsContainsPerson(medicalRecords, person);
            if (medicalRecord != null) {
                ChildAlertDto dto = new ChildAlertDto();
                dto.setFirstName(person.getFirstName());
                dto.setLastName(person.getLastName());
                dto.setAge(String.valueOf(calculatorAge(medicalRecord.getBirthdate())));
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


    public List<PersonInfoDto> listPersonInfos(String firstName, String lastName) {
        List<Person> persons = personRepository.findAllpersonByName(firstName, lastName);
        List<PersonInfoDto> result = new ArrayList<>();
        List<MedicalRecord> medicalRecords = medicalRecordsRepository.findAllMedicalRecords();

        for (Person person : persons) {
            MedicalRecord medicalRecord = medicalRecordsContainsPerson(medicalRecords, person);
            if (medicalRecord != null) {
                PersonInfoDto dto = new PersonInfoDto();
                dto.setLastName(person.getLastName());
                dto.setFirstName(person.getFirstName());
                dto.setEmail(person.getEmail());
                dto.setAddress(person.getAddress());
                dto.setAge(String.valueOf(calculatorAge(medicalRecord.getBirthdate())));
                dto.setMedications(medicalRecord.getMedications());
                dto.setAllergies(medicalRecord.getAllergies());
                result.add(dto);
            }
        }
        return result;
    }


}