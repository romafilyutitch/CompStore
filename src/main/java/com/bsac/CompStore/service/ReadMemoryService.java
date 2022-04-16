package com.bsac.CompStore.service;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.ReadMemory;
import com.bsac.CompStore.repository.ReadMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReadMemoryService {

    private final ReadMemoryRepository readMemoryRepository;

    @Autowired
    public ReadMemoryService(ReadMemoryRepository readMemoryRepository) {
        this.readMemoryRepository = readMemoryRepository;
    }

    public List<ReadMemory> findAll() {
        return readMemoryRepository.findAll();
    }

    public ReadMemory findById(int id) {
        Optional<ReadMemory> optionalReadMemory = readMemoryRepository.findById(id);
        if (optionalReadMemory.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Processor wasn't found by id %d", id));
        }
        return optionalReadMemory.get();
    }

    public void delete(int id) {
        readMemoryRepository.deleteById(id);
    }

    public ReadMemory save(ReadMemory readMemory) {
        return readMemoryRepository.save(readMemory);
    }

    public ReadMemory update(int id, ReadMemory readMemory) {
        Optional<ReadMemory> optionalReadMemory = readMemoryRepository.findById(id);
        if (optionalReadMemory.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Processor wasn't found by id %d", id));
        }
        ReadMemory savedReadMemory = optionalReadMemory.get();
        savedReadMemory.setType(readMemory.getType());
        savedReadMemory.setVolume(readMemory.getVolume());
        return savedReadMemory;
    }
}
