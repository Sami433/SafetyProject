package com.example.SafetyProject.service;
import com.example.SafetyProject.repository.DataHandler;
import com.example.SafetyProject.repository.FireStationRepository;
import com.example.SafetyProject.repository.PersonRepository;
import com.example.SafetyProject.model.FireStation;
import com.example.SafetyProject.model.Person;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class FireStationService {
    private FireStationRepository fireStationRepository;
    private PersonRepository personRepository;
    private DataHandler dataHandler;


}