package com.bsac.CompStore.controller;

import com.bsac.CompStore.exception.ErrorDetails;
import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.business.Processor;
import com.bsac.CompStore.service.ProcessorService;
import jdk.jfr.Experimental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/processors")
public class ProcessorController {

    private final ProcessorService processorService;

    @Autowired
    public ProcessorController(ProcessorService processorService) {
        this.processorService = processorService;
    }

    @GetMapping
    public List<Processor> findAll() {
        return processorService.findAll();
    }

    @GetMapping("/{id}")
    public Processor findById(@PathVariable int id) {
        return processorService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        processorService.delete(id);
    }

    @PostMapping
    public Processor save(@Valid @RequestBody Processor processor) {
        return processorService.save(processor);
    }

    @PutMapping("/{id}")
    public Processor update(@PathVariable int id, @RequestBody Processor processor) {
        return processorService.update(id, processor);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleError(ResourceNotFoundException exception) {
        return new ResponseEntity<ErrorDetails>(new ErrorDetails(LocalDateTime.now(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
