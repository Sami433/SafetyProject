package com.example.SafetyProject.service.dto;

import com.example.SafetyProject.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@AllArgsConstructor
@Getter
@Setter

public class ChildAlertDto {

    private String firstName;
    private String lastName;
    private String age;
    private List<Person> households;

    public ChildAlertDto() {

    }
}