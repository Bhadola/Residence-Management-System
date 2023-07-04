package com.cts.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.springboot.entities.Owner;

@Repository
public interface OwnerRepo extends JpaRepository<Owner, Integer> {

}
