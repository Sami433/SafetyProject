package com.example.SafetyProject.repository;

import com.example.SafetyProject.model.FireStation;
import org.springframework.stereotype.*;

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
    }}
