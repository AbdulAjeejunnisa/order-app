package com.abdulajeejunnisa.orderapp.repository;

import com.abdulajeejunnisa.orderapp.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository
        extends JpaRepository<Location, Long> {

}