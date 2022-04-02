package com.bsac.CompStore.repository;

import com.bsac.CompStore.model.business.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Integer> {

}
