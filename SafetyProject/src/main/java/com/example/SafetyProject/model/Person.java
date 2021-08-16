package com.example.SafetyProject.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Person {

    String firstName;
    String lastName;
    String address;
    String city;
    String zip;
    String phone;
    String email;

    public Person(){};

    public Person(@JsonProperty("firstName") String firstName,
                  @JsonProperty("lastName") String lastName,
                  @JsonProperty("address") String address,
                  @JsonProperty("city") String city,
                  @JsonProperty("zip") String zip,
                  @JsonProperty("phone") String phone,
                  @JsonProperty("email") String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
