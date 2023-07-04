package com.cts.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.springboot.entities.Property;

@Repository
public interface PropertyRepo extends JpaRepository<Property, Integer> {

}
