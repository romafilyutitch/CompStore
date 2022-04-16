package com.bsac.CompStore.service;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.Processor;
import com.bsac.CompStore.repository.ProcessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Processor> optionalProcessor = processorRepository.findById(id);
        if (optionalProcessor.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Processor was not found by id %d",id));
        }
        return optionalProcessor.get();
    }

    public void delete(int id) {
        processorRepository.deleteById(id);
    }

    public Processor save(Processor processor) {
        return processorRepository.save(processor);
    }

    public Processor update(int id, Processor processor) {
        Optional<Processor> optionalProcessor = processorRepository.findById(id);
        if (optionalProcessor.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Resource was not found by id %d", id));
        }
        Processor savedProcessor = optionalProcessor.get();
        savedProcessor.setBrand(processor.getBrand());
        savedProcessor.setCoresAmount(processor.getCoresAmount());
        savedProcessor.setSeries(processor.getSeries());
        savedProcessor.setFrequency(processor.getFrequency());
        return savedProcessor;
    }
}
