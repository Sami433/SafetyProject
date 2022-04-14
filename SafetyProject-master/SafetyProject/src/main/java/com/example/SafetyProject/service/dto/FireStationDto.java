package com.example.SafetyProject.service.dto;

import java.util.List;

public class FireStationDto {


    Integer adultsCount;
    Integer childsCount;
    List<FireStationPersonDto> people;



    public FireStationDto(Integer adultsCount, Integer childsCount, List<FireStationPersonDto> people) {
        this.adultsCount = adultsCount;
        this.childsCount = childsCount;
        this.people = people;
    }

    public FireStationDto() {

    }

    public Integer getAdultsCount() {
        return adultsCount;
    }

    public void setAdultsCount(Integer adultsCount) {
        this.adultsCount = adultsCount;
    }

    public Integer getChildsCount() {
        return childsCount;
    }

    public void setChildsCount(Integer childsCount) {
        this.childsCount = childsCount;
    }

    public List<FireStationPersonDto> getPeople() {
        return people;
    }

    public void setPeople(List<FireStationPersonDto> people) {
        this.people = people;
    }






}