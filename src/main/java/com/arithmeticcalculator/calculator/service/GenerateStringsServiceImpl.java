package com.arithmeticcalculator.calculator.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.arithmeticcalculator.calculator.generator.GenerateRequestData;
import com.arithmeticcalculator.calculator.generator.GenerateResponse;
import com.arithmeticcalculator.calculator.generator.GenerateStringsParams;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class GenerateStringsServiceImpl implements GenerateStringsService{
    
    @Value("${app.external.generator.strings.url}")
    private String uri;

    @Value("${app.external.generator.strings.jsonrpc}")
    private String jsonrpc;

    @Value("${app.external.generator.strings.method}")
    private String method;

    @Value("${app.external.generator.strings.id}")
    private Integer id;

    @Value("${app.external.generator.strings.apikey}")
    private String apikey; ;

    @Value("${app.external.generator.strings.char}")
    private String chars; 

    
    @Override
    public List<String> generateStrings(Integer n, Integer length) {

        GenerateRequestData data = new GenerateRequestData(id, jsonrpc, method, new GenerateStringsParams(apikey, n, length, chars, false));
        try {
            HttpClient client = HttpClient.newBuilder().build();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            var json = objectMapper.writeValueAsString(data);
            var request = HttpRequest
                            .newBuilder(new URI(uri))
                            .header("content-type", "application/json")
                            .POST(HttpRequest.BodyPublishers.ofString(json))
                            .build();
            String response = client.send(request,HttpResponse.BodyHandlers.ofString()).body();
            GenerateResponse generateResponse = objectMapper.readValue(response, GenerateResponse.class);
            return Arrays.asList(generateResponse.getResult().getRandom().getData());
            
        }catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(); 
        }
    }

}
