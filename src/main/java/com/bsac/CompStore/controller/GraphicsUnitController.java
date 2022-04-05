package com.bsac.CompStore.controller;

import com.bsac.CompStore.GraphicsUnitService;
import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.business.GraphicsUnit;
import com.bsac.CompStore.repository.GraphicsUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/graphicsUnits")
public class GraphicsUnitController {

    private final GraphicsUnitService graphicsUnitService;

    @Autowired
    public GraphicsUnitController(GraphicsUnitService graphicsUnitRepository) {
        this.graphicsUnitService = graphicsUnitRepository;
    }

    @GetMapping
    public List<GraphicsUnit> findAll() {
        return graphicsUnitService.findAll();
    }

    @GetMapping("/{id}")
    public GraphicsUnit findById(@PathVariable int id) {
        return graphicsUnitService.findById(id);
    }

    @PostMapping
    public GraphicsUnit create(@Valid @RequestBody GraphicsUnit graphicsUnit) {
        return graphicsUnitService.save(graphicsUnit);
    }

    @PutMapping("/{id}")
    public GraphicsUnit update(@PathVariable int id, @Valid @RequestBody GraphicsUnit graphicsUnit) {
        return graphicsUnitService.update(id, graphicsUnit);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        graphicsUnitService.delete(id);
    }

}
