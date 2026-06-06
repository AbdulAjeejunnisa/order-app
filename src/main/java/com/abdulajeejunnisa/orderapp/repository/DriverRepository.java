package com.abdulajeejunnisa.orderapp.repository;

import com.abdulajeejunnisa.orderapp.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository
        extends JpaRepository<Driver, Long> {

}