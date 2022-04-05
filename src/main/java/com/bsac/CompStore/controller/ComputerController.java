package com.bsac.CompStore.controller;

import com.bsac.CompStore.model.business.Computer;
import com.bsac.CompStore.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/computers")
public class ComputerController {

    private ComputerRepository computerRepository;

    @Autowired
    public ComputerController(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    @GetMapping
    public List<Computer> findAll() {
        return computerRepository.findAll();
    }
}
