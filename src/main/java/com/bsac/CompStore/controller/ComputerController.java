package com.bsac.CompStore.controller;

import com.bsac.CompStore.model.Computer;
import com.bsac.CompStore.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ComputerController {

    private final ComputerRepository repository;

    @Autowired
    public ComputerController(ComputerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/computers")
    public List<Computer> findAllComputers(@RequestParam(required = false) String name) {
        return name == null || name.isEmpty() ? repository.findAll() : repository.findComputersByNameContaining(name);
    }

    @GetMapping("/{id}")
    public Optional<Computer> findById(@PathVariable int id) {
        return repository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        repository.deleteById(id);
    }

    @PostMapping("/")
    public Computer save(@RequestBody Computer computer) {
        return repository.save(computer);
    }

    @PutMapping("/{id}")
    public Computer update(@PathVariable int id, @RequestBody Computer computer) {
        Optional<Computer> optionalComputer = repository.findById(id);
        Computer foundComputer = optionalComputer.get();
        foundComputer.setColor(computer.getColor());
        foundComputer.setGraphics(computer.getGraphics());
        foundComputer.setProcessor(computer.getProcessor());
        foundComputer.setHdd(computer.getHdd());
        foundComputer.setRam(computer.getRam());
        foundComputer.setYear(computer.getYear());
        return computer;
    }
}
