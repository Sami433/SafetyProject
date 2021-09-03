package com.example.SafetyProject.controller;

import com.example.SafetyProject.model.FireStation;
import com.example.SafetyProject.model.Person;
import com.example.SafetyProject.service.FireStationService;;
import com.example.SafetyProject.service.dto.FireStationDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FireStationController {
    private final FireStationService fireStationService;

    public FireStationController(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }

    @RequestMapping(value = "phoneAlert", method = RequestMethod.GET)
    public List<String> phoneNumberList(@RequestParam(name = "fireStation") int station) {

        return this.fireStationService.findPhoneNumbersByStationNumber(station);

    }

    @RequestMapping(value = "firestation", method = RequestMethod.GET)
    FireStationDto findAllPersonsByStationNumber(@RequestParam(name = "stationNumber") int number) {
        return this.fireStationService.findAllPersonsByStationNumber(number);
    }

    @PostMapping(value = "firestation")
    public void addFireStation(@RequestBody FireStation firestation) {
        fireStationService.addFireStation(firestation);
    }

}
