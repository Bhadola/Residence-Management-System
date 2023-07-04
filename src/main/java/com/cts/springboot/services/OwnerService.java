package com.cts.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.springboot.entities.Owner;
import com.cts.springboot.repositories.OwnerRepo;

@Service
public class OwnerService {

	@Autowired
	private OwnerRepo OwnerRepo;
	public void addOwner(Owner owner) {
		OwnerRepo.save(owner);
	}

	public List<Owner> getAllOwners() {
		return OwnerRepo.findAll();
	}

	public Owner getOwnerById(int id) {
	    Optional<Owner> optionalOwner = OwnerRepo.findById(id);
	    if(optionalOwner.isPresent()) {
	    	return optionalOwner.get();
	    }
	    return null;
	}

	public void deleteOwner(int id) {
		OwnerRepo.deleteById(id);
	}
}
