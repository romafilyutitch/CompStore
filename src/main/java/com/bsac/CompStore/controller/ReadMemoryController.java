package com.bsac.CompStore.controller;

import com.bsac.CompStore.exception.ErrorDetails;
import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.ReadMemory;
import com.bsac.CompStore.service.ReadMemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/readMemories")
public class ReadMemoryController {

    private final ReadMemoryService readMemoryService;

    @Autowired
    public ReadMemoryController(ReadMemoryService readMemoryService) {
        this.readMemoryService = readMemoryService;
    }

    @GetMapping
    public List<ReadMemory> findAll() {
        return readMemoryService.findAll();
    }

    @GetMapping("/{id}")
    public ReadMemory findById(@PathVariable int id) {
        return readMemoryService.findAll(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        readMemoryService.delete(id);
    }

    @PostMapping
    public ReadMemory save(@Valid @RequestBody ReadMemory readMemory) {
        return readMemoryService.save(readMemory);
    }

    @PutMapping("/{id}")
    public ReadMemory update(@PathVariable int id, @Valid @RequestBody ReadMemory readMemory) {
        return readMemoryService.update(id, readMemory);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleError(ResourceNotFoundException exception) {
        return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
