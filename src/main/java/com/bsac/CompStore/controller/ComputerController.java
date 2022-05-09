package com.bsac.CompStore.controller;

import com.bsac.CompStore.exception.ErrorDetails;
import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.Computer;
import com.bsac.CompStore.model.Image;
import com.bsac.CompStore.repository.ImageRepository;
import com.bsac.CompStore.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/computers")
@CrossOrigin
public class ComputerController {

    private final ComputerService computerService;
    private final ImageRepository imageRepository;

    @Autowired
    public ComputerController(ComputerService computerService, ImageRepository imageRepository) {
        this.computerService = computerService;
        this.imageRepository = imageRepository;
    }

    @GetMapping
    public List<Computer> findAll(@RequestParam(required = false) String name) {
        List<Computer> computers;
        System.out.println(name);
        if (name != null) {
            computers = computerService.findByName(name);
        } else {
            computers = computerService.findAll();
        }
        return computers;
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

    @PostMapping("/{id}")
    public Computer update(@PathVariable int id, @RequestBody Computer computer) {
        return this.computerService.update(id, computer);
    }

    @PostMapping
    public Computer save(@Valid @RequestBody Computer computer) {
        System.out.println(computer);
        return computerService.save(computer);
    }

    @GetMapping("/{id}/images")
    public List<Image> findImages(@PathVariable int id) {
        Computer foundComputer = computerService.findById(id);
        return imageRepository.findByComputerId(foundComputer.getId());
    }

    @PostMapping("/{id}/images")
    @ResponseStatus(HttpStatus.OK)
    public void saveImage(@RequestParam("file") MultipartFile file, @PathVariable int id) throws IOException {
        final Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setPicByte(file.getBytes());
        final Computer computer = computerService.findById(id);
        image.setComputer(computer);
        imageRepository.save(image);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleError(ResourceNotFoundException exception) {
        return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
