package com.rocketseat.createUrlShortner;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main implements RequestHandler<Map<String, Object>, Map<String, String>> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Map<String, String> handleRequest(Map<String, Object> input, Context context) {
        String body = (String) input.get("body");

        Map<String, String> bodyMap;

        try{
            bodyMap = objectMapper.readValue(body, Map.class);

        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String originalUrl = bodyMap.get("originalUrl");
        String expirationTime = bodyMap.get("expirationTime");

        String shortUrlCode = java.util.UUID.randomUUID().toString().substring(0, 8);
        Map<String, String> response = new HashMap<>();
        response.put("code", shortUrlCode);
        return response;
    }
}














