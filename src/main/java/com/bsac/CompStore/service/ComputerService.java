package com.bsac.CompStore.service;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.business.Computer;
import com.bsac.CompStore.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComputerService {

    private final ComputerRepository computerRepository;

    @Autowired
    public ComputerService(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    public List<Computer> findAll() {
        return computerRepository.findAll();
    }

    public Computer findById(int id) {
        Optional<Computer> optionalComputer = computerRepository.findById(id);
        if(optionalComputer.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Computer wasn't find by id %d", id));
        }
        return optionalComputer.get();
    }

    public void delete(int id) {
        computerRepository.deleteById(id);
    }

    public Computer save(Computer computer) {
        return computerRepository.save(computer);
    }

    public Computer update(int id, Computer computer) {
        Optional<Computer> optionalComputer = computerRepository.findById(id);
        if (optionalComputer.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Computer wasn't found by id %d", id));
        }
        Computer savedComputer = optionalComputer.get();
        savedComputer.setName(computer.getName());
        savedComputer.setBrand(computer.getBrand());
        savedComputer.setPrice(computer.getPrice());
        savedComputer.setPurpose(computer.getPurpose());
        savedComputer.setOperationSystem(computer.getOperationSystem());
        savedComputer.setGraphicsUnit(computer.getGraphicsUnit());
        savedComputer.setProcessor(computer.getProcessor());
        savedComputer.setReadMemory(computer.getReadMemory());
        savedComputer.setRandomAccessMemory(computer.getRandomAccessMemory());
        savedComputer.setYear(computer.getYear());
        return savedComputer;
    }
}
