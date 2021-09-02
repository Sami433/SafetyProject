package com.example.SafetyProject.service.dto;

import java.util.List;
import lombok.Builder;

@Builder
public class FloodDto {

    String address;
    List<PersonFloodDto> people;

    public FloodDto(String address, List<PersonFloodDto> people) {
        this.address = address;
        this.people = people;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<PersonFloodDto> getPeople() {
        return people;
    }

    public void setPeople(List<PersonFloodDto> people) {
        this.people = people;
    }




    }






