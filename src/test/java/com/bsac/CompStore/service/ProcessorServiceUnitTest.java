package com.bsac.CompStore.service;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.Processor;
import com.bsac.CompStore.repository.ProcessorRepository;
import org.aspectj.lang.annotation.Before;
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
public class ProcessorServiceUnitTest {
    @Autowired
    private ProcessorService processorService;

    @MockBean
    private ProcessorRepository processorRepository;

    private Processor processor1 = new Processor();
    private Processor processor2 = new Processor();

    @BeforeEach
    public void setUp() {
        processor1.setId(1);
        processor1.setPrice(1000);
        processor1.setSeries("Intel Core i5");
        processor1.setBrand("Intel");
        processor1.setCoresAmount(4);
        processor1.setFrequency(2.1);

        processor2.setId(2);
        processor2.setPrice(2000);
        processor2.setSeries("Intel Core i7");
        processor2.setBrand("Intel");
        processor2.setCoresAmount(8);
        processor2.setFrequency(3.1);
    }

    @Test
    public void findAll_shouldReturnAllRecords() {
        List<Processor> processorList = Arrays.asList(processor1, processor2);
        when(processorRepository.findAll()).thenReturn(processorList);

        List<Processor> allRecords = processorService.findAll();

        assertEquals(processorList, allRecords);
    }

    @Test
    public void findById_shouldReturnRecordWithId() {
        when(processorRepository.findById(processor1.getId())).thenReturn(Optional.of(processor1));

        Processor foundProcessor = processorService.findById(processor1.getId());

        assertEquals(processor1, foundProcessor);
    }

    @Test
    public void findById_shouldThrowException_ifThereIsNoId() {
        when(processorRepository.findById(processor1.getId())).thenThrow(ResourceNotFoundException.class);

        assertThrows(ResourceNotFoundException.class, () -> processorService.findById(processor1.getId()));
    }

    @Test
    public void save_shouldReturnSavedRecord() {
        when(processorRepository.save(processor1)).thenReturn(processor1);

        Processor savedProcessor = processorService.save(processor1);

        assertEquals(processor1, savedProcessor);
    }

    @Test
    public void shouldReturnUpdatedRecord() {
        processor1.setCoresAmount(1);
        when(processorRepository.findById(processor1.getId())).thenReturn(Optional.of(processor1));

        Processor updatedRecord = processorService.update(processor1.getId(), processor1);

        assertEquals(1, updatedRecord.getCoresAmount());
    }

}
