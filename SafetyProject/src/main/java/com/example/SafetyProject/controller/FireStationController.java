package com.example.SafetyProject.controller;

import com.example.SafetyProject.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class FireStationController {
    private  final FireStationService fireStationService;

    public FireStationController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }
    @RequestMapping(value = "phoneAlert", method = RequestMethod.GET)
    public List <String> phoneNumberList(@RequestParam(name = "fireStation") String station) {

        return this.fireStationService.findPhoneNumbersByStationNumber(station);

    }
}
