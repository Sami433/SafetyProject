package com.example.SafetyProject.repository;

import com.example.SafetyProject.model.FireStation;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;


@Component
public class FireStationRepository {

    private final DataHandler dataHandler;

    public FireStationRepository(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }



    public List<FireStation> findAllFireStationsAddressByNumber(String station) {

        final List<FireStation> collect = dataHandler.getData().getFirestations().stream()
                .filter(p -> p.getStation().equals(station))
                .collect(Collectors.toList());
        return collect;
    }}
