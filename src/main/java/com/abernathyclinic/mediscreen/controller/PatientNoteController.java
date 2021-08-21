package com.abernathyclinic.mediscreen.controller;

import com.abernathyclinic.mediscreen.entity.Patient;
import com.abernathyclinic.mediscreen.entity.PatientNote;
import com.abernathyclinic.mediscreen.service.NoteFeignService;
import com.abernathyclinic.mediscreen.service.PatientFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientNoteController {

    @Autowired
    NoteFeignService noteFeignService;

    @CrossOrigin("http://localhost:4200")
    @GetMapping(value = "/patient-note/{patientId}")
    public PatientNote findPatientNoteById(@PathVariable("patientId") int patientId){
        return noteFeignService.findPatientNoteById(patientId);
    };

    @CrossOrigin("http://localhost:4200")
    @GetMapping(value = "/patientsNotes")
    public List<PatientNote> findAllPatientsNotes(){
        return noteFeignService.findAllPatientsNotes();
    };

    @CrossOrigin("http://localhost:4200")
    @PostMapping(value = "/patient-note")
    public PatientNote savePatientNote(@RequestBody PatientNote patientNote){
        return noteFeignService.savePatientNote(patientNote);
    };

    @CrossOrigin("http://localhost:4200")
    @PutMapping(value = "/patient-note/{patientId}")
    public PatientNote updatePatientNote(@PathVariable("patientId") int patientId,  @RequestBody PatientNote patientNote){
        return noteFeignService.updatePatientNote(patientId, patientNote);
    };

    @CrossOrigin("http://localhost:4200")
    @DeleteMapping(value = "/patient-note/{patientId}")
    public String deletePatientNote(@PathVariable("patientId") int patientId){
        return noteFeignService.deletePatientNote(patientId);
    };
}
