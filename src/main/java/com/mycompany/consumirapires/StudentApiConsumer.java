/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.consumirapires;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Kiwar
 */
public class StudentApiConsumer implements CrudRepository<Student> {

    private final String URL = "http://empresa.ec/controlador/apiRest.php";

    @Override
    public List<Student> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        Student[] students = restTemplate.getForObject(URL, Student[].class);
        return students != null ? Arrays.asList(students) : Collections.emptyList();
    }

    @Override
    public boolean create(Student entity) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Student> requestEntity = new HttpEntity<>(entity);
        ResponseEntity<Void> responseEntity = restTemplate.exchange(this.URL, HttpMethod.POST,
                requestEntity, Void.class);
        return responseEntity.getStatusCode() == HttpStatus.OK;
    }

    @Override
    public boolean update(Student entity) {

        RestTemplate restTemplate = new RestTemplate();

        // Configurar la URL y los parámetros de la solicitud
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("id", entity.getId());

        // Configurar el encabezado para enviar datos en formato JSON
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Configurar el cuerpo de la solicitud con el objeto Student convertido a JSON
        HttpEntity<Student> requestEntity = new HttpEntity<>(entity, headers);

        // Realizar la solicitud PUT
        ResponseEntity<Void> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.PUT,
                requestEntity,
                Void.class);

        // Verificar si la solicitud fue exitosa (código de estado 200)
        return responseEntity.getStatusCode() == HttpStatus.OK;
        /*
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("cedula", entity.getId())
                .queryParam("nombre", entity.getNombre())
                .queryParam("apellido", entity.getApellido())
                .queryParam("direccion", entity.getDireccion())
                .queryParam("telefono", entity.getTelefono());
        String updateUri = builder.toUriString();
        ResponseEntity<Void> responseEntity = restTemplate.exchange(updateUri, HttpMethod.PUT, 
                null, Void.class);
        return responseEntity.getStatusCode() == HttpStatus.OK;*/
    }

    @Override
    public boolean delete(String id) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL)
                .queryParam("cedula", id);

        String deleteUri = builder.toUriString();
        ResponseEntity<Void> responseEntity = restTemplate.exchange(deleteUri, HttpMethod.DELETE,
                null, Void.class);
        return responseEntity.getStatusCode() == HttpStatus.OK;
    }

    @Override
    public Student getStudent(String id) {
        RestTemplate restTemplate = new RestTemplate();
        Student[] students = restTemplate.getForObject(URL + "?cedula=" + id, Student[].class);
        return students[0];
    }

}
