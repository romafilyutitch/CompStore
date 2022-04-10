package com.bsac.CompStore.repository;

import com.bsac.CompStore.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Integer> {

    List<Computer> findByNameContains(String name);
}
