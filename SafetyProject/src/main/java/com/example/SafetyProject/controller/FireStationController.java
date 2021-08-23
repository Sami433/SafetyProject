package com.example.SafetyProject.controller;

import com.example.SafetyProject.service.FireStationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FireStationController {
    private  final FireStationService fireStationService;

    public FireStationController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }
    @RequestMapping(value = "phoneAlert", method = RequestMethod.GET)
    public List <String> phoneNumberList(@RequestParam(name = "fireStation") int station) {

        return this.fireStationService.findPhoneNumbersByStationNumber(station);

    }
}

