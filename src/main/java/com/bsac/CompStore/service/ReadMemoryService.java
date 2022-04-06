package com.bsac.CompStore.service;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.business.ReadMemory;
import com.bsac.CompStore.repository.ReadMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ReadMemory findAll(int id) {
        return readMemoryRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public void delete(int id) {
        readMemoryRepository.deleteById(id);
    }

    public ReadMemory save(ReadMemory readMemory) {
        return readMemoryRepository.save(readMemory);
    }

    public ReadMemory update(int id, ReadMemory readMemory) {
        ReadMemory savedReadMemory = readMemoryRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        savedReadMemory.setPrice(readMemory.getPrice());
        savedReadMemory.setType(readMemory.getType());
        savedReadMemory.setVolume(readMemory.getVolume());
        return savedReadMemory;
    }
}
