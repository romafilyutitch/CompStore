package com.bsac.CompStore.controller;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.GraphicsUnit;
import com.bsac.CompStore.model.GraphicsUnitType;
import com.bsac.CompStore.service.GraphicsUnitService;
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

@WebMvcTest(GraphicsUnitController.class)
public class GraphicsUnitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GraphicsUnitService graphicsUnitService;

    private GraphicsUnit graphicsUnit1 = new GraphicsUnit();
    private GraphicsUnit graphicsUnit2 = new GraphicsUnit();
    private GraphicsUnit graphicsUnit3 = new GraphicsUnit();

    @BeforeEach
    public void setUp() {
        graphicsUnit1.setId(1);
        graphicsUnit1.setBrand("NVIDIA");
        graphicsUnit1.setModel("NVIDIA GFORCE 1020");
        graphicsUnit1.setType(GraphicsUnitType.DISCRETE);

        graphicsUnit2.setId(2);
        graphicsUnit2.setBrand("NVIDIA");
        graphicsUnit2.setModel("NVIDIA GFORCE 2020");
        graphicsUnit2.setType(GraphicsUnitType.DISCRETE);

        graphicsUnit3.setId(3);
        graphicsUnit3.setBrand("NVIDIA");
        graphicsUnit3.setModel("NVIDIA GFORCE 3020");
        graphicsUnit3.setType(GraphicsUnitType.DISCRETE);
    }

    @Test
    public void findAll_shouldFindAllRecords() throws Exception {
        List<GraphicsUnit> graphicsUnitList = Arrays.asList(graphicsUnit1, graphicsUnit2, graphicsUnit3);

        when(graphicsUnitService.findAll()).thenReturn(graphicsUnitList);

        mockMvc.perform(MockMvcRequestBuilders.get("/graphicsUnits").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[1].model", is(graphicsUnit2.getModel())));
    }

    @Test
    public void findById_shouldFindRecordWithId() throws Exception {
        when(graphicsUnitService.findById(graphicsUnit1.getId())).thenReturn(graphicsUnit1);

        mockMvc.perform(MockMvcRequestBuilders.get("/graphicsUnits/" + graphicsUnit1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(graphicsUnit1.getId())));
    }

    @Test
    public void findById_shouldGet404_whenThereIsNoId() throws Exception {
        final int notExistingId = -1;
        when(graphicsUnitService.findById(notExistingId)).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/graphicsUnits/" + notExistingId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void save_shouldSaveRecord() throws Exception {
        when(graphicsUnitService.save(graphicsUnit1)).thenReturn(graphicsUnit1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/graphicsUnits")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(graphicsUnit1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(graphicsUnit1.getId())));
    }

    @Test
    public void update_shouldUpdateRecord() throws Exception {
        graphicsUnit1.setBrand("Intel");
        when(graphicsUnitService.update(graphicsUnit1.getId(), graphicsUnit1)).thenReturn(graphicsUnit1);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/graphicsUnits/ " + graphicsUnit1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(graphicsUnit1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(graphicsUnit1.getId())))
                .andExpect(jsonPath("$.brand", is(graphicsUnit1.getBrand())));
    }

    @Test
    public void should_deleteRecord() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/graphicsUnits/" + graphicsUnit1.getId()))
                .andExpect(status().isNoContent());
    }
}
