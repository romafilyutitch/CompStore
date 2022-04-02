package com.bsac.CompStore.repository;

import com.bsac.CompStore.model.business.Processor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessorRepository extends JpaRepository<Processor, Integer> {
}
