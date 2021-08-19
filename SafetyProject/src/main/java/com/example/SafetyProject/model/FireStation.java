package com.example.SafetyProject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FireStation {
    String address;
    int stationNumber;

    public FireStation(){};
    public FireStation(@JsonProperty("address")String address,
                       @JsonProperty("station") int stationNumber) {
        this.address = address;
        this.stationNumber = stationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }}