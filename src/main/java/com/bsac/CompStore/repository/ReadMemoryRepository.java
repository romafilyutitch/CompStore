package com.bsac.CompStore.repository;

import com.bsac.CompStore.model.ReadMemory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadMemoryRepository extends JpaRepository<ReadMemory, Integer> {
}
