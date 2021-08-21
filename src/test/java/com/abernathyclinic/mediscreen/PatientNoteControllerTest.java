package com.abernathyclinic.mediscreen;

import com.abernathyclinic.mediscreen.controller.PatientNoteController;
import com.abernathyclinic.mediscreen.entity.PatientNote;
import com.abernathyclinic.mediscreen.service.NoteFeignService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(PatientNoteController.class)
public class PatientNoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteFeignService noteFeignService;

    @DisplayName("GET : /patient-note/{patientId}")
    @Test
    void findPatientNoteById() throws Exception{
        // ARRANGE
        when(noteFeignService.findPatientNoteById(any(Integer.class))).thenReturn(new PatientNote());
        // ACT
        MvcResult mvcResult = this.mockMvc.perform(get("/patient-note/1")).andDo(print()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        // ASSERT
        assertEquals(status, 200);
        verify(noteFeignService, times(1)).findPatientNoteById(any(Integer.class));
    }

    @DisplayName("GET : /patientsNotes")
    @Test
    void findAllPatientsNotes() throws Exception{
        // ARRANGE
        when(noteFeignService.findAllPatientsNotes()).thenReturn(new ArrayList<>());
        // ACT
        MvcResult mvcResult = this.mockMvc.perform(get("/patientsNotes")).andDo(print()).andReturn();
        int status = mvcResult.getResponse().getStatus();
        // ASSERT
        assertEquals(status, 200);
        verify(noteFeignService, times(1)).findAllPatientsNotes();
    }

    @DisplayName("POST : /patient-note")
    @Test
    void savePatientNote() throws Exception{
        // ARRANGE
        PatientNote patientNoteToSave = new PatientNote(1, "Note");
        when(noteFeignService.savePatientNote(patientNoteToSave)).thenReturn(patientNoteToSave);
        // ACT
        MvcResult mvcResult = mockMvc.perform(post("/patient-note").contentType(MediaType.APPLICATION_JSON).content("{\"note\": \"note\"}"))
                .andDo(print()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        // ASSERT
        assertEquals(200, response.getStatus());
    }

    @DisplayName("DELETE : /patient-note/{patientId}")
    @Test
    void deletePatient() throws Exception{
        // ARRANGE
        int id = 1;
        PatientNote patientNoteToDelete = new PatientNote(1, "Note");
        when(noteFeignService.deletePatientNote(id)).thenReturn("Patient deleted");
        // ACT
        MvcResult mvcResult = mockMvc.perform(delete("/patient-note/" + id).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        // ASSERT
        assertEquals(200, response.getStatus());
        verify(noteFeignService, times(1)).deletePatientNote(id);
    }

    @DisplayName("PUT : /patient-note/{patientId}")
    @Test
    void updatePatient() throws Exception{
        // ARRANGE
        int id = 1;
        PatientNote patientNoteToUpdate = new PatientNote(1, "Note");
        when(noteFeignService.updatePatientNote(id, patientNoteToUpdate)).thenReturn(patientNoteToUpdate);
        // ACT
        MvcResult mvcResult = mockMvc.perform(put("/patient-note/" + id).contentType(MediaType.APPLICATION_JSON).content("{\"note\": \"note\"}")).andDo(print()).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        // ASSERT
        assertEquals(200, response.getStatus());
    }
}
