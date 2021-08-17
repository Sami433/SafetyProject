package com.example.SafetyProject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FireStation {
    String address;
    String stationNumber;

    public FireStation(){};
    public FireStation(@JsonProperty("address")String address,  @JsonProperty("station")String stationNumber) {
        this.address = address;
        this.stationNumber = stationNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }


}
