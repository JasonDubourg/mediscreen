package com.abernathyclinic.mediscreen.service;

import com.abernathyclinic.mediscreen.entity.PatientDiabetesReport;
import com.abernathyclinic.mediscreen.entity.PatientWithNote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "patient-rapport", url = "localhost:8083")
public interface PatientReportFeignService {

    @PostMapping(value = "/patient-report")
    PatientDiabetesReport getPatientDiabetesReportById(@RequestBody PatientWithNote patientWithNote);
}
