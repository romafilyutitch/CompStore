package com.bsac.CompStore.service;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.business.GraphicsUnit;
import com.bsac.CompStore.model.business.GraphicsUnitType;
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
        return graphicsUnitRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public GraphicsUnit save(GraphicsUnit graphicsUnit) {
        return graphicsUnitRepository.save(graphicsUnit);
    }

    public GraphicsUnit update(int id, GraphicsUnit graphicsUnit) {
        final Optional<GraphicsUnit> optionalGraphicsUnit = graphicsUnitRepository.findById(id);
        final GraphicsUnit savedGraphicsUnit = optionalGraphicsUnit.orElseThrow(ResourceNotFoundException::new);
        savedGraphicsUnit.setBrand(graphicsUnit.getBrand());
        savedGraphicsUnit.setModel(graphicsUnit.getModel());
        savedGraphicsUnit.setType(graphicsUnit.getType());
        savedGraphicsUnit.setPrice(graphicsUnit.getPrice());
        return savedGraphicsUnit;
    }

    public void delete(int id) {
        graphicsUnitRepository.deleteById(id);
    }

}
