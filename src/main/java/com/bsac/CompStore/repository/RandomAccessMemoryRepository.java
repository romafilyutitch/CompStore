package com.bsac.CompStore.repository;

import com.bsac.CompStore.model.RandomAccessMemory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RandomAccessMemoryRepository extends JpaRepository<RandomAccessMemory, Integer> {
}
