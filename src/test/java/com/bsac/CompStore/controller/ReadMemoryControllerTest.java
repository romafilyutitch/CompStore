package com.bsac.CompStore.controller;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.ReadMemory;
import com.bsac.CompStore.model.ReadMemoryType;
import com.bsac.CompStore.service.ReadMemoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReadMemoryController.class)
public class ReadMemoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReadMemoryService readMemoryService;

    private ReadMemory readMemory1 = new ReadMemory();
    private ReadMemory readMemory2 = new ReadMemory();
    private ReadMemory readMemory3 = new ReadMemory();

    @BeforeEach
    private void setUp() {
        readMemory1.setId(1);
        readMemory1.setPrice(1000);
        readMemory1.setType(ReadMemoryType.HDD);
        readMemory1.setVolume(500);

        readMemory2.setId(2);
        readMemory2.setPrice(2000);
        readMemory2.setType(ReadMemoryType.SSD);
        readMemory2.setVolume(1000);

        readMemory3.setId(3);
        readMemory3.setPrice(3000);
        readMemory3.setType(ReadMemoryType.SSD);
        readMemory3.setVolume(500);
    }

    @Test
    public void findAll_shouldReturnAllRecords() throws Exception {
        List<ReadMemory> readMemoryList = Arrays.asList(readMemory1, readMemory2, readMemory3);

        when(readMemoryService.findAll()).thenReturn(readMemoryList);

        mockMvc.perform(MockMvcRequestBuilders.get("/readMemories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[1].price", is(readMemory2.getPrice())));
    }

    @Test
    public void findById_shouldReturnRecordWithId() throws Exception {
        when(readMemoryService.findById(readMemory1.getId())).thenReturn(readMemory1);

        mockMvc.perform(MockMvcRequestBuilders.get("/readMemories/" + readMemory1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(readMemory1.getId())));
    }

    @Test
    public void findById_shouldReturn404() throws Exception {
        when(readMemoryService.findById(readMemory1.getId())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/readMemories/" + readMemory1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void save_shouldSaveRecord() throws Exception {
        when(readMemoryService.save(readMemory1)).thenReturn(readMemory1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/readMemories")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(readMemory1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(readMemory1.getId())));
    }

    @Test
    public void update_shouldUpdateRecord() throws Exception {
        readMemory1.setPrice(1);
        when(readMemoryService.update(readMemory1.getId(), readMemory1)).thenReturn(readMemory1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/readMemories/" + readMemory1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(readMemory1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(readMemory1.getId())))
                .andExpect(jsonPath("$.price", is(readMemory1.getPrice())));
    }

    @Test
    public void delete_shouldReturnNoContent() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/readMemories/" + readMemory1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
