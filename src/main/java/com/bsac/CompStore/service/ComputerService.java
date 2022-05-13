package com.bsac.CompStore.service;

import com.bsac.CompStore.exception.ResourceNotFoundException;
import com.bsac.CompStore.model.Computer;
import com.bsac.CompStore.model.Review;
import com.bsac.CompStore.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        savedComputer.setReviews(computer.getReviews());
        this.computerRepository.save(savedComputer);
        return savedComputer;
    }

    public List<Computer> findByName(String name) {
        return computerRepository.findByNameContains(name);
    }

    public Computer postReview(Computer computer, Review review) {
        computer.getReviews().add(review);
        return this.computerRepository.save(computer);
    }

    public List<Computer> findTopByAverage() {
        List<Computer> topByAverage = new ArrayList<>();
        Map<Double, Computer> computerAverageScoreMap = new HashMap<>();
        List<Double> averageQueue = new ArrayList<Double>();
        List<Computer> allComputers = this.computerRepository.findAll();
        allComputers.forEach(computer -> {
            List<Review> reviews = computer.getReviews();
            final double reviewsSize = reviews.size();
            double reviewsSum = reviews.stream().map(Review::getScore).reduce(0, Integer::sum);
            computerAverageScoreMap.put(reviewsSum / reviewsSize, computer);
            averageQueue.add(reviewsSum / reviewsSize);
        });
        averageQueue.sort(Comparator.comparingDouble(one -> (double) one).reversed());
        System.out.println(averageQueue);
        if(averageQueue.size() > 5) {
            for (int i = 0; i < 5; i++) {
                topByAverage.add(computerAverageScoreMap.get(averageQueue.remove(i)));
            }
        } else {
            for (int i = 0; i < averageQueue.size(); i++) {
                topByAverage.add(computerAverageScoreMap.get(averageQueue.remove(i)));
            }
        }
        return topByAverage;
    }
}
