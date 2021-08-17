package com.example.SafetyProject.repository;
import com.example.SafetyProject.model.FireStation;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;

@Repository

public class FireStationRepository {


    private final DataHandler dataHandler;


    public FireStationRepository(DataHandler dataHandler) {

        this.dataHandler = dataHandler;
    }





    public List<FireStation> allFireStation() {

        return dataHandler.getData().getFirestations();
    }

    public List<FireStation> findAllFireStationsAddressByNumber(int stationNumber) {

        return dataHandler.getData().getFirestations().stream()
                .filter(p -> p.getStationNumber().equals(stationNumber))
                .collect(Collectors.toList());
    }}