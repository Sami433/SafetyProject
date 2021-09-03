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


    public void deleteFireStation(String adress, String station) {
        List<FireStation> firestations = dataHandler.getData().getFirestations();
        List<FireStation> toRemove = dataHandler.getData().getFirestations().stream()
                .filter(fireStation -> fireStation.getAddress().equals(adress))
                .filter(fireStation-> fireStation.getStation().equals(station))
                .collect(Collectors.toList());
        firestations.removeAll(toRemove);
        dataHandler.getData().setFirestations(firestations);
        dataHandler.save();
    }

}