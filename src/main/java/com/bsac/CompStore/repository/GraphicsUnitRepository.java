package com.bsac.CompStore.repository;

import com.bsac.CompStore.model.business.GraphicsUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphicsUnitRepository extends JpaRepository<GraphicsUnit, Integer> {

}
