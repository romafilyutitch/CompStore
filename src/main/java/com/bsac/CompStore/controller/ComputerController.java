package com.bsac.CompStore.controller;

import com.bsac.CompStore.exception.ErrorDetails;
import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.business.Computer;
import com.bsac.CompStore.repository.ComputerRepository;
import com.bsac.CompStore.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/computers")
public class ComputerController {

    private final ComputerService computerService;

    @Autowired
    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @GetMapping
    public List<Computer> findAll() {
        return computerService.findAll();
    }

    @GetMapping("/{id}")
    public Computer findById(@PathVariable int id) {
        return computerService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        computerService.delete(id);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleError(ResourceNotFoundException exception) {
        return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
