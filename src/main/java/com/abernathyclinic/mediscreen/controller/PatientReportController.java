package com.abernathyclinic.mediscreen.controller;

import com.abernathyclinic.mediscreen.entity.Patient;
import com.abernathyclinic.mediscreen.entity.PatientDiabetesReport;
import com.abernathyclinic.mediscreen.entity.PatientNote;
import com.abernathyclinic.mediscreen.entity.PatientWithNote;
import com.abernathyclinic.mediscreen.service.NoteFeignService;
import com.abernathyclinic.mediscreen.service.PatientFeignService;
import com.abernathyclinic.mediscreen.service.PatientReportFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientReportController {

    @Autowired
    private PatientFeignService patientFeignService;

    @Autowired
    private NoteFeignService noteFeignService;

    @Autowired
    private PatientReportFeignService patientReportFeignService;

    @CrossOrigin("http://localhost:4200")
    @GetMapping(value = "/patient-report/{id}")
    PatientDiabetesReport getPatientDiabetesReportById(@PathVariable("id") int id){
        Patient patient = patientFeignService.getPatientById(id);
        PatientNote patientNote = noteFeignService.findPatientNoteById(patient.getId());
        PatientWithNote patientWithNote = new PatientWithNote(patient, patientNote);
        return patientReportFeignService.getPatientDiabetesReportById(patientWithNote);
    };
}
