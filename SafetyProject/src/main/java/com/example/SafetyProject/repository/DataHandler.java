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

    public Data getData() {
        return data;
    }

}