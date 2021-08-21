package com.abernathyclinic.mediscreen.service;

import com.abernathyclinic.mediscreen.entity.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "patient-database", url = "localhost:8081")
public interface PatientFeignService {

    @GetMapping("/")
    String index();

    @GetMapping(value = "/patient-info/{id}")
    Patient getPatientById(@PathVariable("id") int id);

    @GetMapping(value = "/patient")
    Patient getPatient(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName);

    @GetMapping(value = "/patients")
    List<Patient> findAllPatients();

    @PostMapping(value = "/patient")
    String savePatient(@RequestBody Patient patient);

    @DeleteMapping(value = "/patient/{id}")
    Patient deletePatient(@PathVariable("id") int id);

    @PutMapping(value = "/patient/{id}")
    Patient updatePatient(@PathVariable("id") int id,  @RequestBody Patient patient);

}
