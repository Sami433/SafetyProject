package com.example.SafetyProject.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class CalculatorAge {

    public static int calculatorAge(String birthDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        LocalDate birthDay = LocalDate.parse(birthDate, formatter);
        LocalDate currentDate = LocalDate.now();

        if ((birthDay != null) && (currentDate != null)) {
            return Period.between(birthDay, currentDate).getYears();
        } else {
            return 0;
        }

    }
}