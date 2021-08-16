package com.example.SafetyProject.repository;

import com.example.SafetyProject.model.Data;
import com.jsoniter.JsonIterator;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;


@Component
public class DataHandler {

    private final ResourceLoader loader;
    private final Data data;

    public DataHandler(ResourceLoader loader) throws IOException {
        this.loader = loader;
        File file = loader.getResource("classpath:/json/data.json").getFile();
        String data = FileUtils.readFileToString(file, "UTF-8");
        this.data = JsonIterator.deserialize(data, Data.class);
    }

    public Data getData (){
        return data;
    }

/*
    CommandLineRunner runner (PersonService personService){
       return args -> {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Person>> typeReference = new TypeReference<List<Person>>(){};
            TypeReference<List<FireStation>> typeReference2 = new TypeReference<List<FireStation>>(){};
            TypeReference<List<MedicalRecord>> typeReference3 = new TypeReference<List<MedicalRecord>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/data.json");
            try {
                List<Person> persons = mapper.readValue(inputStream, typeReference);
                List<FireStation> fireStations = mapper.readValue(inputStream, typeReference2);
                List<MedicalRecord> medicalRecords = mapper.readValue(inputStream, typeReference3);

            } catch (IOException e) {
                e.printStackTrace();
            }
        };
   }*/
}
