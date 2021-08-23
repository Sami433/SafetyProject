package com.example.SafetyProject.service;


import com.example.SafetyProject.model.*;
import com.example.SafetyProject.repository.*;
import com.example.SafetyProject.service.dto.*;
import org.springframework.stereotype.Service;

import java.text.*;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class PersonService {

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
                dto.setAge(String.valueOf(computeAge(medicalRecord.getBirthdate())));
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

    private int computeAge(String birthdateOfPerson) {
        Date date = null;
        Calendar now = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(birthdateOfPerson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        birthDate.setTime(date);
        if (birthDate.after(now)) {
            throw new IllegalArgumentException("Can't be born in the future");
        }
        int year1 = now.get(Calendar.YEAR);
        int year2 = birthDate.get(Calendar.YEAR);
        int age = year1 - year2;
        int month1 = now.get(Calendar.MONTH);
        int month2 = birthDate.get(Calendar.MONTH);
        if (month2 > month1) {
            age--;
        } else if (month1 == month2) {
            int day1 = now.get(Calendar.DAY_OF_MONTH);
            int day2 = birthDate.get(Calendar.DAY_OF_MONTH);
            if (day2 > day1) {
                age--;
            }
        }
        return age;
    }


}