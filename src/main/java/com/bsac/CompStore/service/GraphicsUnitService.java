package com.bsac.CompStore.service;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.GraphicsUnit;
import com.bsac.CompStore.repository.GraphicsUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GraphicsUnitService {
    private final GraphicsUnitRepository graphicsUnitRepository;

    @Autowired
    public GraphicsUnitService(GraphicsUnitRepository graphicsUnitRepository) {
        this.graphicsUnitRepository = graphicsUnitRepository;
    }

    public List<GraphicsUnit> findAll() {
        return graphicsUnitRepository.findAll();
    }

    public GraphicsUnit findById(int id) {
        Optional<GraphicsUnit> optionalGraphicsUnit = graphicsUnitRepository.findById(id);
        if (optionalGraphicsUnit.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Graphics unit wasn't found by id %d", id));
        }
        return optionalGraphicsUnit.get();
    }

    public GraphicsUnit save(GraphicsUnit graphicsUnit) {
        return graphicsUnitRepository.save(graphicsUnit);
    }

    public GraphicsUnit update(int id, GraphicsUnit graphicsUnit) {
        Optional<GraphicsUnit> optionalSavedGraphicsUnit = graphicsUnitRepository.findById(id);
        if (optionalSavedGraphicsUnit.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Graphics unit wasn't found by id %d",id));
        }
        final GraphicsUnit savedGraphicsUnit = optionalSavedGraphicsUnit.get();
        savedGraphicsUnit.setBrand(graphicsUnit.getBrand());
        savedGraphicsUnit.setModel(graphicsUnit.getModel());
        savedGraphicsUnit.setType(graphicsUnit.getType());
        return savedGraphicsUnit;
    }

    public void delete(int id) {
        graphicsUnitRepository.deleteById(id);
    }

}
