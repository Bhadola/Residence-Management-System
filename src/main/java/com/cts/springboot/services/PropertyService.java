package com.cts.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.springboot.entities.Property;
import com.cts.springboot.repositories.PropertyRepo;

@Service
public class PropertyService {
	
	@Autowired
	private PropertyRepo propertyRepo;
	
	public void addProperty(Property property) {
		propertyRepo.save(property);
	}
	
	public List<Property> getAllProperties() {
		return propertyRepo.findAll();
	}

	public Property getPropertyById(int id) {
	    Optional<Property> optionalProperty = propertyRepo.findById(id);
	    if(optionalProperty.isPresent()) {
	    	return optionalProperty.get();
	    }
	    return null;
	}

	public void deleteProperty(int id) {
		propertyRepo.deleteById(id);
	}
}
