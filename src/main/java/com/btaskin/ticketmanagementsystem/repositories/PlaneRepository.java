package com.btaskin.ticketmanagementsystem.repositories;

import com.btaskin.ticketmanagementsystem.domain.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneRepository extends JpaRepository<Plane, Long> {
}
