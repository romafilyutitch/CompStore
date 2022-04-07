package com.bsac.CompStore.service;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.business.RandomAccessMemory;
import com.bsac.CompStore.repository.RandomAccessMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RandomAccessMemoryService {

    private final RandomAccessMemoryRepository randomAccessMemoryRepository;

    @Autowired
    public RandomAccessMemoryService(RandomAccessMemoryRepository randomAccessMemoryRepository) {
        this.randomAccessMemoryRepository = randomAccessMemoryRepository;
    }

    public List<RandomAccessMemory> findAll() {
        return randomAccessMemoryRepository.findAll();
    }

    public RandomAccessMemory findById(int id) {
        Optional<RandomAccessMemory> optionalRandomAccessMemory = randomAccessMemoryRepository.findById(id);
        if(optionalRandomAccessMemory.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Random access memory wasn't found by id %d", id));
        }
        return optionalRandomAccessMemory.get();
    }

    public void delete(int id) {
        randomAccessMemoryRepository.deleteById(id);
    }

    public RandomAccessMemory save(RandomAccessMemory randomAccessMemory) {
        return randomAccessMemoryRepository.save(randomAccessMemory);
    }

    public RandomAccessMemory update(int id, RandomAccessMemory randomAccessMemory) {
        Optional<RandomAccessMemory> optionalRandomAccessMemory = randomAccessMemoryRepository.findById(id);
        if(optionalRandomAccessMemory.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Random access memory wasn't found by id %d", id));
        }
        RandomAccessMemory savedRandomAccessMemory = optionalRandomAccessMemory.get();
        savedRandomAccessMemory.setFrequency(randomAccessMemory.getFrequency());
        savedRandomAccessMemory.setPrice(randomAccessMemory.getPrice());
        savedRandomAccessMemory.setVolume(randomAccessMemory.getVolume());
        savedRandomAccessMemory.setType(randomAccessMemory.getType());
        return savedRandomAccessMemory;
    }
}
