package com.bsac.CompStore.service;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.RandomAccessMemory;
import com.bsac.CompStore.model.RandomAccessMemoryType;
import com.bsac.CompStore.repository.RandomAccessMemoryRepository;
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
public class RandomAccessMemoryServiceUnitTest {
    @Autowired
    private RandomAccessMemoryService randomAccessMemoryService;

    @MockBean
    private RandomAccessMemoryRepository randomAccessMemoryRepository;

    private RandomAccessMemory randomAccessMemory1 = new RandomAccessMemory();
    private RandomAccessMemory randomAccessMemory2 = new RandomAccessMemory();

    @BeforeEach
    public void setUp() {
        randomAccessMemory1.setId(1);
        randomAccessMemory1.setVolume(4);
        randomAccessMemory1.setPrice(1000);
        randomAccessMemory1.setType(RandomAccessMemoryType.DDR4);
        randomAccessMemory1.setFrequency(144);

        randomAccessMemory2.setId(2);
        randomAccessMemory2.setVolume(8);
        randomAccessMemory2.setType(RandomAccessMemoryType.DDR4);
        randomAccessMemory1.setFrequency(200);
        randomAccessMemory1.setPrice(3000);
    }

    @Test
    public void findAll_shouldFindAllRecords() {
        List<RandomAccessMemory> randomAccessMemoryList = Arrays.asList(randomAccessMemory1, randomAccessMemory2);
        when(randomAccessMemoryRepository.findAll()).thenReturn(randomAccessMemoryList);

        List<RandomAccessMemory> allRecords = randomAccessMemoryService.findAll();

        assertEquals(randomAccessMemoryList, allRecords);
    }

    @Test
    public void findById_shouldReturnRecordWithId() {
        when(randomAccessMemoryRepository.findById(randomAccessMemory1.getId())).thenReturn(Optional.of(randomAccessMemory1));


        RandomAccessMemory foundRecord = randomAccessMemoryService.findById(randomAccessMemory1.getId());

        assertEquals(randomAccessMemory1, foundRecord);
    }

    @Test
    public void findById_shouldThrowException_ifThereIsNoId() {
        when(randomAccessMemoryRepository.findById(randomAccessMemory1.getId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> randomAccessMemoryService.findById(randomAccessMemory1.getId()));
    }

    @Test
    public void save_shouldSaveRecord() {
        when(randomAccessMemoryRepository.save(randomAccessMemory1)).thenReturn(randomAccessMemory1);

        RandomAccessMemory savedRecord = randomAccessMemoryService.save(randomAccessMemory1);

        assertEquals(randomAccessMemory1, savedRecord);
    }

    @Test
    public void update_shouldReturnUpdatedRecord() {
        randomAccessMemory1.setVolume(1);
        when(randomAccessMemoryRepository.findById(randomAccessMemory1.getId())).thenReturn(Optional.of(randomAccessMemory1));

        RandomAccessMemory updatedRecord = randomAccessMemoryService.update(randomAccessMemory1.getId(), randomAccessMemory1);

        assertEquals(randomAccessMemory1, updatedRecord);
        assertEquals(1, updatedRecord.getVolume());
    }
}
