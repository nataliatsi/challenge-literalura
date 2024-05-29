package com.nataliatsi.literalura.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

public class ApiService {
    public <T> T obterDados(String url, Class<T> classe){
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("A URL não pode ser nula");
        }
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<T> response = restTemplate.getForEntity(url, classe);
            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw new RuntimeException("HTTP error: " + e.getStatusCode(), e);
        } catch (RuntimeException e) {
            throw new RuntimeException("Ocorreu um erro ao buscar dados", e);
        }
    }

}
