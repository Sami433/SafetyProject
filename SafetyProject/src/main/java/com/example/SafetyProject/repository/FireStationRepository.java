package com.example.SafetyProject.repository;
import com.example.SafetyProject.model.FireStation;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Objects;
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

    public List<FireStation> findAllFireStationsAddressByNumber(Integer number) {

        return dataHandler.getData().getFirestations().stream().filter(p -> Objects.equals(p.getStationNumber(), number.toString())).collect(Collectors.toList());
    }
}