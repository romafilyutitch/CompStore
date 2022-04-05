package com.bsac.CompStore.controller;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.business.Computer;
import com.bsac.CompStore.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/computers")
public class ComputerController {

    private final ComputerRepository graphicsUnitService;

    @Autowired
    public ComputerController(ComputerRepository computerRepository) {
        this.graphicsUnitService = computerRepository;
    }

    @GetMapping
    public List<Computer> findAll() {
        return graphicsUnitService.findAll();
    }

    @GetMapping("/{id}")
    private Computer findById(@PathVariable int id) {
        return graphicsUnitService.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteById(@PathVariable int id) {
        graphicsUnitService.deleteById(id);
    }
}
