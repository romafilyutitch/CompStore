package com.bsac.CompStore.controller;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.Processor;
import com.bsac.CompStore.service.ProcessorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProcessorController.class)
public class ProcessorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProcessorService processorService;

    Processor processor1 = new Processor();
    Processor processor2 = new Processor();
    Processor processor3 = new Processor();

    @BeforeEach
    public void setUp() {
        processor1.setId(1);
        processor1.setBrand("Intel");
        processor1.setPrice(2000);
        processor1.setFrequency(2.1);
        processor1.setSeries("Intel Core");
        processor1.setCoresAmount(2);

        processor2.setId(2);
        processor2.setBrand("Intel");
        processor1.setPrice(3000);
        processor2.setFrequency(2.0);
        processor2.setSeries("Intel Core i5");
        processor2.setCoresAmount(4);

        processor3.setId(3);
        processor3.setBrand("AMD");
        processor3.setSeries("AMD Risen");
        processor3.setFrequency(2.6);
        processor3.setPrice(3000);
        processor3.setCoresAmount(8);
    }

    @Test
    public void findAll_shouldFindAllRecords() throws Exception {
        List<Processor> processors = Arrays.asList(processor1, processor2, processor3);

        Mockito.when(processorService.findAll()).thenReturn(processors);

        mockMvc.perform(MockMvcRequestBuilders.get("/processors").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[1].series", is("Intel Core i5")));

        Mockito.verify(processorService).findAll();
    }

    @Test
    public void findById_shouldFindRecordsWithPassedId() throws Exception {
        Mockito.when(processorService.findById(processor1.getId())).thenReturn(processor1);

        mockMvc.perform(MockMvcRequestBuilders.get("/processors/" + processor1.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(processor1.getId())))
                .andExpect(jsonPath("$.brand", is(processor1.getBrand())));

        Mockito.verify(processorService).findById(processor1.getId());
    }

    @Test
    public void findById_shouldReturn404() throws Exception {
        final int notExistingId = -1;
        Mockito.when(processorService.findById(notExistingId)).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/processors/" + notExistingId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createProcessor_shouldSaveProcessor() throws Exception {
        Mockito.when(processorService.save(processor1)).thenReturn(processor1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/processors")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(processor1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(processor1.getId())))
                .andExpect(jsonPath("$.series", is(processor1.getSeries())));
    }

    @Test
    public void delete_shouldDeleteProcessor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/processors/" + processor1.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void update_shouldUpdateProcessor() throws Exception {
        processor1.setPrice(10000.0);
        Mockito.when(processorService.update(processor1.getId(), processor1)).thenReturn(processor1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/processors/" + processor1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(processor1));

        mockMvc.perform(mockRequest)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(processor1.getId())))
                .andExpect(jsonPath("$.price", is(10000.0)));
    }


}
