package com.abernathyclinic.mediscreen.controller;

import com.abernathyclinic.mediscreen.entity.Patient;
import com.abernathyclinic.mediscreen.service.PatientFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

        @Autowired
        private PatientFeignService patientFeignService;

        @CrossOrigin("http://localhost:4200")
        @GetMapping("/")
        public String index(){
                return patientFeignService.index();
        };

        @CrossOrigin("http://localhost:4200")
        @GetMapping(value = "/patient-info/{id}")
        public Patient findPatientById(@PathVariable("id") int id){
                return patientFeignService.getPatientById(id);
        };

        @CrossOrigin("http://localhost:4200")
        @GetMapping(value = "/patient")
        public Patient findPatientByFirstNameAndLastName(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName){
                return patientFeignService.getPatient(firstName, lastName);
        };

        @CrossOrigin("http://localhost:4200")
        @GetMapping(value = "/patients")
        public List<Patient> findAllPatients(){
                return patientFeignService.findAllPatients();
        };

        @CrossOrigin("http://localhost:4200")
        @PostMapping(value = "/patient")
        public Patient savePatient(@RequestBody Patient patient){
                return patientFeignService.savePatient(patient);
        };

        @CrossOrigin("http://localhost:4200")
        @DeleteMapping(value = "/patient/{id}")
        public Patient deletePatient(@PathVariable("id") int id){
                return patientFeignService.deletePatient(id);
        };

        @CrossOrigin("http://localhost:4200")
        @PutMapping(value = "/patient/{id}")
        public Patient updatePatient(@PathVariable("id") int id,  @RequestBody Patient patient){
                return patientFeignService.updatePatient(id, patient);
        };
}
