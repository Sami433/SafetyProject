package com.example.SafetyProject.service.dto;

public class PersonInfoDto {


    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getMedications() {
        return medications;
    }

    public void setMedications(String[] medications) {
        this.medications = medications;
    }

    public String[] getAllergies() {
        return allergies;
    }

    public void setAllergies(String[] allergies) {
        this.allergies = allergies;
    }

    private String lastName;
    private String address;

    public PersonInfoDto(String firstName, String lastName, String address, String email, String[] medications, String[] allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.medications = medications;
        this.allergies = allergies;
    }

    private String email;
    private String[] medications;
    private String[] allergies;
}
