package com.bsac.CompStore.controller;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.RandomAccessMemory;
import com.bsac.CompStore.model.RandomAccessMemoryType;
import com.bsac.CompStore.service.RandomAccessMemoryService;
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

@WebMvcTest(RandomAccessMemoryController.class)
public class RandomAccessMemoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RandomAccessMemoryService randomAccessMemoryService;

    private RandomAccessMemory randomAccessMemory1 = new RandomAccessMemory();
    private RandomAccessMemory randomAccessMemory2 = new RandomAccessMemory();
    private RandomAccessMemory randomAccessMemory3 = new RandomAccessMemory();

    @BeforeEach
    public void setUp() {
        randomAccessMemory1.setId(1);
        randomAccessMemory1.setFrequency(144);
        randomAccessMemory1.setPrice(1000);
        randomAccessMemory1.setType(RandomAccessMemoryType.DDR1);
        randomAccessMemory1.setVolume(4);

        randomAccessMemory2.setId(2);
        randomAccessMemory2.setFrequency(244);
        randomAccessMemory2.setPrice(2000);
        randomAccessMemory2.setType(RandomAccessMemoryType.DDR2);
        randomAccessMemory2.setVolume(8);

        randomAccessMemory3.setId(3);
        randomAccessMemory3.setFrequency(144);
        randomAccessMemory3.setPrice(2000);
        randomAccessMemory3.setType(RandomAccessMemoryType.DDR4);
        randomAccessMemory3.setVolume(16);
    }

    @Test
    public void findAll_shouldFindAllRecords() throws Exception {
        List<RandomAccessMemory> randomAccessMemoryList = Arrays.asList(randomAccessMemory1, randomAccessMemory2, randomAccessMemory3);

        when(randomAccessMemoryService.findAll()).thenReturn(randomAccessMemoryList);

        mockMvc.perform(MockMvcRequestBuilders.get("/randomAccessMemories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[1].volume", is(randomAccessMemory2.getVolume())));
    }

    @Test
    public void findById_shouldFindARecordWithId() throws Exception {
        when(randomAccessMemoryService.findById(randomAccessMemory1.getId())).thenReturn(randomAccessMemory1);

        mockMvc.perform(MockMvcRequestBuilders.get("/randomAccessMemories/" + randomAccessMemory1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(randomAccessMemory1.getId())));
    }

    @Test
    public void findById_shouldReturn404_whenThereIsNoId() throws Exception {
        when(randomAccessMemoryService.findById(randomAccessMemory1.getId())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/randomAccessMemories/" + randomAccessMemory1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void save_shouldSaveRecord() throws Exception {
        when(randomAccessMemoryService.save(randomAccessMemory1)).thenReturn(randomAccessMemory1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/randomAccessMemories")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(randomAccessMemory1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(randomAccessMemory1.getId())));
    }

    @Test
    public void update_shouldUpdateRecord() throws Exception {
        when(randomAccessMemoryService.update(randomAccessMemory1.getId(), randomAccessMemory1))
                .thenReturn(randomAccessMemory1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/randomAccessMemories/" + randomAccessMemory1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(randomAccessMemory1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(randomAccessMemory1.getId())));
    }

    @Test
    public void delete_shouldReturnNotContent() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/randomAccessMemories/" + randomAccessMemory1.getId()))
                .andExpect(status().isNoContent());
    }
}
