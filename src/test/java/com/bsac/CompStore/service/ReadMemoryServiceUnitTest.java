package com.bsac.CompStore.service;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.ReadMemory;
import com.bsac.CompStore.model.ReadMemoryType;
import com.bsac.CompStore.repository.ReadMemoryRepository;
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
public class ReadMemoryServiceUnitTest {
    @Autowired
    private ReadMemoryService readMemoryService;

    @MockBean
    private ReadMemoryRepository readMemoryRepository;

    private ReadMemory readMemory1 = new ReadMemory();
    private ReadMemory readMemory2 = new ReadMemory();

    @BeforeEach
    public void setUp() {
        readMemory1.setId(1);
        readMemory1.setType(ReadMemoryType.SSD);
        readMemory1.setVolume(500);

        readMemory2.setId(2);
        readMemory2.setType(ReadMemoryType.SSD);
        readMemory2.setVolume(1000);
    }

    @Test
    public void findAll_shouldReturnAllRecords() {
        List<ReadMemory> readMemoryList = Arrays.asList(readMemory1, readMemory2);
        when(readMemoryRepository.findAll()).thenReturn(readMemoryList);

        List<ReadMemory> allRecords = readMemoryService.findAll();

        assertEquals(readMemoryList, allRecords);
    }

    @Test
    public void findById_shouldReturnRecordWithId() {
        when(readMemoryRepository.findById(readMemory1.getId())).thenReturn(Optional.of(readMemory1));

        ReadMemory foundRecord = readMemoryService.findById(readMemory1.getId());

        assertEquals(readMemory1, foundRecord);
    }

    @Test
    public void findById_shouldThrowException() {
        when(readMemoryRepository.findById(readMemory1.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> readMemoryService.findById(readMemory1.getId()));
    }

    @Test
    public void save_shouldReturnSavedRecord() {
        when(readMemoryRepository.save(readMemory1)).thenReturn(readMemory1);

        ReadMemory savedRecord = readMemoryService.save(readMemory1);

        assertEquals(readMemory1, savedRecord);
    }

    @Test
    public void update_shouldReturnUpdatedRecord() {
        readMemory1.setVolume(200);
        when(readMemoryRepository.findById(readMemory1.getId())).thenReturn(Optional.of(readMemory1));

        ReadMemory updatedRecord = readMemoryService.update(readMemory1.getId(), readMemory1);

        assertEquals(readMemory1, updatedRecord);
        assertEquals(200, readMemory1.getVolume());
    }
}
