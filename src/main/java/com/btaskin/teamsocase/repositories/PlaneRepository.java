package com.btaskin.teamsocase.repositories;

import com.btaskin.teamsocase.domain.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneRepository extends JpaRepository<Plane, Long> {
}
