package com.bsac.CompStore.service;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.GraphicsUnit;
import com.bsac.CompStore.model.GraphicsUnitType;
import com.bsac.CompStore.repository.GraphicsUnitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class GraphicsServiceUnitTest {

    @Autowired
    private GraphicsUnitService graphicsUnitService;

    @MockBean
    private GraphicsUnitRepository graphicsUnitRepository;

    private GraphicsUnit graphicsUnit1 = new GraphicsUnit();
    private GraphicsUnit graphicsUnit2 = new GraphicsUnit();

    @BeforeEach
    public void setUp() {
        graphicsUnit1.setId(1);
        graphicsUnit1.setPrice(1000);
        graphicsUnit1.setType(GraphicsUnitType.EMBEDDED);
        graphicsUnit1.setModel("NVIDIA GFORCE 3060");
        graphicsUnit1.setBrand("NVIDIA");

        graphicsUnit2.setId(2);
        graphicsUnit2.setPrice(2000);
        graphicsUnit2.setType(GraphicsUnitType.DISCRETE);
        graphicsUnit2.setModel("NVIDIA GFORCE 3070");
        graphicsUnit2.setPrice(5000);
    }

    @Test
    public void findAll_shouldReturnAllRecords() {
        List<GraphicsUnit> graphicsUnitList = Arrays.asList(graphicsUnit1, graphicsUnit2);
        when(graphicsUnitRepository.findAll()).thenReturn(graphicsUnitList);

        List<GraphicsUnit> allRecords = graphicsUnitService.findAll();

        assertEquals(graphicsUnitList, allRecords);
    }

    @Test
    public void findById_shouldReturnRecordWithId() {
        when(graphicsUnitRepository.findById(graphicsUnit1.getId())).thenReturn(Optional.of(graphicsUnit1));

        GraphicsUnit foundRecord = graphicsUnitService.findById(graphicsUnit1.getId());

        assertEquals(graphicsUnit1, foundRecord);
    }

    @Test
    public void findById_shouldThrowException_whenThereIsNoId() {
        when(graphicsUnitRepository.findById(graphicsUnit1.getId())).thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, () -> graphicsUnitService.findById(graphicsUnit1.getId()));
    }

    @Test
    public void save_shouldReturnSavedRecord() {
        when(graphicsUnitRepository.save(graphicsUnit1)).thenReturn(graphicsUnit1);

        GraphicsUnit savedRecord = graphicsUnitService.save(graphicsUnit1);

        assertEquals(graphicsUnit1, savedRecord);
    }

    @Test
    public void update_shouldUpdateRecord() {
        graphicsUnit1.setPrice(1);
        when(graphicsUnitRepository.findById(graphicsUnit1.getId())).thenReturn(Optional.of(graphicsUnit1));

        GraphicsUnit updatedRecord = graphicsUnitService.update(graphicsUnit1.getId(), graphicsUnit1);

        assertEquals(1, updatedRecord.getPrice());
    }

}
