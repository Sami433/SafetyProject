package com.example.SafetyProject.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class PersonInfoDto {

    String firstName;
    String lastName;
    String address;
    String age;
    String email;
    String[] medications;
    String[] allergies;

    public PersonInfoDto() {

    }


}

