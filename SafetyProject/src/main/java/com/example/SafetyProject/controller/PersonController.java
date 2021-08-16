package com.example.SafetyProject.controller;

import com.example.SafetyProject.service.PersonService;
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



}

