package com.bsac.CompStore.controller;

import com.bsac.CompStore.exception.ErrorDetails;
import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.business.RandomAccessMemory;
import com.bsac.CompStore.service.RandomAccessMemoryService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/randomAccessMemories")
public class RandomAccessMemoryController {

    private final RandomAccessMemoryService randomAccessMemoryService;

    @Autowired
    public RandomAccessMemoryController(RandomAccessMemoryService randomAccessMemoryService) {
        this.randomAccessMemoryService = randomAccessMemoryService;
    }

    @GetMapping
    public List<RandomAccessMemory> findAll() {
        return randomAccessMemoryService.findAll();
    }

    @GetMapping("/{id}")
    public RandomAccessMemory findById(@PathVariable int id) {
        return randomAccessMemoryService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        randomAccessMemoryService.delete(id);
    }

    @PostMapping
    public RandomAccessMemory save(@Valid @RequestBody RandomAccessMemory randomAccessMemory) {
        return randomAccessMemoryService.save(randomAccessMemory);
    }

    @PutMapping("/{id}")
    public RandomAccessMemory update(@PathVariable int id, @Valid @RequestBody RandomAccessMemory randomAccessMemory) {
        return randomAccessMemoryService.update(id, randomAccessMemory);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleError(ResourceNotFoundException exception) {
        return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
