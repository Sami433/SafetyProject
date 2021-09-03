package com.example.SafetyProject.service;


import com.example.SafetyProject.model.*;
import com.example.SafetyProject.repository.*;
import com.example.SafetyProject.service.dto.*;
import org.springframework.stereotype.Service;

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
                dto.setAddress(person.getAddress());
                dto.setEmail(person.getEmail());
                dto.setAge(String.valueOf(calculatorAge(medicalRecord.getBirthdate())));
                dto.setMedications(medicalRecord.getMedications());
                dto.setAllergies(medicalRecord.getAllergies());
                result.add(dto);
            }
        }
        return result;
    }


    private MedicalRecord medicalRecordsContainsPerson(List<MedicalRecord> medicalRecords, Person person) {
        for (MedicalRecord medicalRecord : medicalRecords) {

            if (medicalRecord.getFirstName().equals(person.getFirstName()) && medicalRecord.getLastName().equals(person.getLastName())) {
                return medicalRecord;
            }
        }
        return null;

    }

    public List<FloodDto> flood(List<Integer> stationsNumbers) {

        return stationsNumbers.stream().flatMap(n -> fireStationRepository.findAllFireStationsAddressByNumber(n)
                        .stream()).map(s -> FloodDto.builder()
                        .address(s.getAddress())
                        .people(getPeopleByAddress(s.getAddress())).build())
                .collect(Collectors.toList());
    }

    private List<PersonFloodDto> getPeopleByAddress(String address) {
        List<Person> persons = personRepository.findAllpersonByAddress(address);
        List<MedicalRecord> medicalRecords = medicalRecordsRepository.findAllMedicalRecords();
        List<PersonFloodDto> result = new ArrayList<>();


        for (Person person : persons) {
            MedicalRecord medicalRecord = medicalRecordsContainsPerson(medicalRecords, person);
            FireStation fireStation = fireStationRepository.findFireStationNumberByAddress(address);

            if (medicalRecord != null) {
                PersonFloodDto dto = new PersonFloodDto();
                dto.setLastName(person.getLastName());
                dto.setFirstName(person.getFirstName());
                dto.setPhone(person.getPhone());
                dto.setAge(String.valueOf(calculatorAge(medicalRecord.getBirthdate())));
                dto.setMedications(medicalRecord.getMedications());
                dto.setAllergies(medicalRecord.getAllergies());
                result.add(dto);
            }

        }
        return result;
    }

    public List<FireDto> findAllPersonsWithMedicalRecords(String adress) {
        List<FireDto> result = new ArrayList<>();
        List<Person> persons = personRepository.findAllpersonByAddress(adress);
        List<MedicalRecord> medicalRecords = medicalRecordsRepository.findAllMedicalRecords();

        for (Person person : persons) {
            MedicalRecord medicalRecord = medicalRecordsContainsPerson(medicalRecords, person);
            FireStation fireStation = fireStationRepository.findFireStationNumberByAddress(adress);

            if (medicalRecord != null) {
                FireDto dto = new FireDto();
                dto.setLastName(person.getLastName());
                dto.setFirstName(person.getFirstName());
                dto.setAddress(person.getAddress());
                dto.setPhone(person.getPhone());
                dto.setMedications(medicalRecord.getMedications());
                dto.setAllergies(medicalRecord.getAllergies());
                dto.setFireStation(fireStation.getStation());
                dto.setAge(String.valueOf(calculatorAge(medicalRecord.getBirthdate())));
                result.add(dto);
            }
        }

            return result;

        }


    public void addPerson(Person person) {

        personRepository.savePerson(person);
    }
    public void deletePerson(String firstName, String lastName) {
        personRepository.deletePerson(firstName, lastName);
    }
    public void updatePerson(Person person) {
        personRepository.updatePerson(person);
    }
    }

