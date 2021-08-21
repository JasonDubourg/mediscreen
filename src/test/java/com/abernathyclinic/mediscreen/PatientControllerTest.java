package com.abernathyclinic.mediscreen;

import com.abernathyclinic.mediscreen.controller.PatientController;
import com.abernathyclinic.mediscreen.entity.Patient;
import com.abernathyclinic.mediscreen.service.PatientFeignService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.http.MediaType;
import java.util.ArrayList;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientFeignService patientFeignService;

    @DisplayName("GET : /patient-info/{id}")
    @Test
    void findPatientByIdTest() throws Exception{
        // ARRANGE
        when(patientFeignService.getPatientById(any(Integer.class))).thenReturn(new Patient());
        // ACT
        MvcResult mvcResult = this.mockMvc.perform(get("/patient-info/1")).andDo(print()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        // ASSERT
        assertEquals(status, 200);
        verify(patientFeignService, times(1)).getPatientById(any(Integer.class));
    }

    @DisplayName("GET : /patient/firstName&lastName")
    @Test
    void findPatientByFirstNameAndLastNameTest() throws Exception{
        // ARRANGE
        when(patientFeignService.getPatient(any(String.class), any(String.class))).thenReturn(new Patient());
        // ACT
        MvcResult mvcResult = this.mockMvc.perform(get("/patient?firstName=firstName&lastName=lastName")).andDo(print()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        // ASSERT
        assertEquals(status, 200);
        verify(patientFeignService, times(1)).getPatient(any(String.class), any(String.class));
    }

    @DisplayName("GET : /patients")
    @Test
    void findAllPatientsTest() throws Exception{
        // ARRANGE
        when(patientFeignService.findAllPatients()).thenReturn(new ArrayList<Patient>());
        // ACT
        MvcResult mvcResult = this.mockMvc.perform(get("/patients")).andDo(print()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        // ASSERT
        assertEquals(status, 200);
        verify(patientFeignService, times(1)).findAllPatients();
    }

    @DisplayName("POST : /patient")
    @Test
    void savePatientTest() throws Exception{
        // ARRANGE
        Patient patientToSave = new Patient("lastName", "firstName", "dateOfBirth", "gender", "homeAddress", "phoneNumber");
        when(patientFeignService.savePatient(patientToSave)).thenReturn(any(String.class));
        // ACT
        MvcResult mvcResult = mockMvc.perform(post("/patient").contentType(MediaType.APPLICATION_JSON).content("{\"lastName\": \"firstName\"}"))
                .andDo(print()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        // ASSERT
        assertEquals(200, response.getStatus());
    }

    @DisplayName("DELETE : /patient/{id}")
    @Test
    void deletePatient() throws Exception{
        // ARRANGE
        Integer id = 1;
        Patient patientToDelete = new Patient("lastName", "firstName", "dateOfBirth", "gender", "homeAddress", "phoneNumber");
        when(patientFeignService.deletePatient(id)).thenReturn(patientToDelete);
        // ACT
        MvcResult mvcResult = mockMvc.perform(delete("/patient/" + id).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        // ASSERT
        assertEquals(200, response.getStatus());
        verify(patientFeignService, times(1)).deletePatient(id);
    }

    @DisplayName("PUT : /patient/{id}")
    @Test
    void updatePatient() throws Exception{
        // ARRANGE
        int id = 1;
        Patient patientToUpdate = new Patient("lastName", "firstName", "dateOfBirth", "gender", "homeAddress", "phoneNumber");
        when(patientFeignService.updatePatient(id, patientToUpdate)).thenReturn(patientToUpdate);
        // ACT
        MvcResult mvcResult = mockMvc.perform(put("/patient/" + id).contentType(MediaType.APPLICATION_JSON).content("{\"lastName\": \"firstName\"}")).andDo(print()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        // ASSERT
        assertEquals(200, response.getStatus());
    }

}
