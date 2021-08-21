package com.abernathyclinic.mediscreen.service;

import com.abernathyclinic.mediscreen.entity.PatientNote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "note-database", url = "localhost:8082")
public interface NoteFeignService {

    @GetMapping(value = "/patient-note/{patientId}")
    PatientNote findPatientNoteById(@PathVariable("patientId") int patientId);

    @GetMapping(value = "/patientsNotes")
    List<PatientNote> findAllPatientsNotes();

    @PostMapping(value = "/patient-note")
    PatientNote savePatientNote(@RequestBody PatientNote patientNote);

    @PutMapping(value = "/patient-note/{patientId}")
    PatientNote updatePatientNote(@PathVariable("patientId") int patientId,  @RequestBody PatientNote patientNote);

    @DeleteMapping(value = "/patient-note/{patientId}")
    String deletePatientNote(@PathVariable("patientId") int patientId);
}
