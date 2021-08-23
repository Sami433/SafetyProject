package com.example.SafetyProject.repository;

import com.example.SafetyProject.model.*;
import org.springframework.stereotype.*;

import java.text.*;
import java.util.*;
import java.util.stream.*;

@Component
public class MedicalRecordsRepository {


    private final DataHandler dataHandler;


    public MedicalRecordsRepository(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }



    private boolean isUnder18(String birthdate) {

        Date date = null;
        try {
            date = new SimpleDateFormat("DD/MM/YYYY").parse(birthdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 18);
        // a tester / 365.25 System.out.println(LocalDate.parse(birthdate).datesUntil(LocalDate.now()).count());
        return !calendar.getTime().after(date);

    }

    public List<MedicalRecord> findAllMedicalRecordsUnder18() {

        return dataHandler.getData().getMedicalrecords().stream().filter(m -> isUnder18(m.getBirthdate())).collect(Collectors.toList());

    }
    public MedicalRecord findMedicalWithFirstNameAndLastName(String firstName, String lastName) {
        return dataHandler.getData().getMedicalrecords().stream()
                .filter(p -> p.getFirstName().equals(firstName))
                .filter(p -> p.getLastName().equals(lastName))
                .findFirst()
                .orElseGet(() -> new MedicalRecord());

    }
    public List <MedicalRecord> findAllMedicalRecords() {
        return dataHandler.getData().getMedicalrecords();

    }

}
