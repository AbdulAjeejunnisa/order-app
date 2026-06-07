package com.abdulajeejunnisa.orderapp.repository;

import com.abdulajeejunnisa.orderapp.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DriverRepository
        extends JpaRepository<Driver, Long> {
    List<Driver> findByAvailable(boolean available);
    List<Driver> findByOrdersIsEmpty();

    List<Driver> findByOrdersIsNotEmpty();
}