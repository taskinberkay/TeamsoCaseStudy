package com.btaskin.teamsocase.repositories;

import com.btaskin.teamsocase.domain.PlaneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneModelRepository extends JpaRepository<PlaneModel, Long> {
}
