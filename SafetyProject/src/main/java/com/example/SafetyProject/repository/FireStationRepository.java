package com.example.SafetyProject.repository;

import com.example.SafetyProject.model.FireStation;
import com.example.SafetyProject.model.Person;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FireStationRepository {

    private final DataHandler dataHandler;

    public FireStationRepository(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }


    public List<FireStation> findAllFireStationsAddressByNumber(Integer station) {

        final List<FireStation> collect = dataHandler.getData().getFirestations().stream()
                .filter(p -> p.getStation().equals(station.toString()))
                .collect(Collectors.toList());
        return collect;
    }

    public FireStation findFireStationNumberByAddress(String address) {
        return dataHandler.getData().getFirestations().stream()
                .filter(p -> p.getAddress().equals(address))
                .findFirst()
                .orElseGet(() -> new FireStation());
    }

    public void saveFireStation(FireStation fireStation) {
        dataHandler.getData().getFirestations().add(fireStation);
        dataHandler.save();
    }
}