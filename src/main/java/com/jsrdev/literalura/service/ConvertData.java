package com.jsrdev.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData implements IConvertData{

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> genericClass) {
        try {
            return objectMapper.readValue(json, genericClass);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage() + " no he podido deserealizar el json");
            throw new RuntimeException(e);
        }
    }
}
