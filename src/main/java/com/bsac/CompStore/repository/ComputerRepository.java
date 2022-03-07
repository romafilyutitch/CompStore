package com.bsac.CompStore.repository;

import com.bsac.CompStore.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface ComputerRepository extends JpaRepository<Computer, Integer> {
}
