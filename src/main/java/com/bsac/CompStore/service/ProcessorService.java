package com.bsac.CompStore.service;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.business.Processor;
import com.bsac.CompStore.repository.ProcessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessorService {

    private final ProcessorRepository processorRepository;

    @Autowired
    public ProcessorService(ProcessorRepository processorRepository) {
        this.processorRepository = processorRepository;
    }

    public List<Processor> findAll() {
        return processorRepository.findAll();
    }

    public Processor findById(int id) {
        return processorRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public void delete(int id) {
        processorRepository.deleteById(id);
    }

    public Processor save(Processor processor) {
        return processorRepository.save(processor);
    }

    public Processor update(int id, Processor processor) {
        Processor savedProcessor = processorRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        savedProcessor.setBrand(processor.getBrand());
        savedProcessor.setPrice(processor.getPrice());
        savedProcessor.setCoresAmount(processor.getCoresAmount());
        savedProcessor.setSeries(processor.getSeries());
        savedProcessor.setFrequency(processor.getFrequency());
        return savedProcessor;
    }
}
