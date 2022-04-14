package com.example.SafetyProject.controller;

import com.example.SafetyProject.model.Person;
import com.example.SafetyProject.service.PersonService;
import com.example.SafetyProject.service.dto.*;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping(value = "communityEmail", method = RequestMethod.GET)
    public List<String> listEmails(@RequestParam(name = "city") String city) {

        return this.personService.findAllEmailsByCity(city);

    }

    @RequestMapping(value = "childAlert", method = RequestMethod.GET)
    public List<ChildAlertDto> findAllchildsUnder18ByAddress(@RequestParam(name = "address") String address) {

        return this.personService.findAllchildsUnder18ByAddress(address);

    }


    @RequestMapping(value = "personInfo", method = RequestMethod.GET)
    public List<PersonInfoDto> listPersonInfos(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {

        return this.personService.listPersonInfos(firstName, lastName);

    }

    @RequestMapping(value = "flood/stations", method = RequestMethod.GET)
    public List<FloodDto> flood(@RequestParam(name = "stations") List<Integer> stationsNumbers) {

        return this.personService.flood(stationsNumbers);

    }

    @RequestMapping(value = "fire", method = RequestMethod.GET)
    public List<FireDto> findAllPersonsWithMedicalRecords(@RequestParam(name = "address") String address) {
        return this.personService.findAllPersonsWithMedicalRecords(address);
    }
    @PostMapping(value = "person")
    public void addPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }


    @DeleteMapping(value="person")
    public void deletePerson(@RequestParam(name = "firstName") String firstName, @RequestParam(name = "lastName") String lastName) {
        personService.deletePerson(firstName, lastName);
    }

    @PutMapping(value="person")
    public Person updatePerson(@NonNull @RequestBody Person person) {
        personService.updatePerson(person);
        return person;
    }

}