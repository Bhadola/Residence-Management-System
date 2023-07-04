package com.cts.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.springboot.entities.Tenant;
import com.cts.springboot.repositories.TenantRepo;

@Service
public class TenantService {

	@Autowired
	private TenantRepo tenantRepo;
	public void addTenant(Tenant tenant) {
		tenantRepo.save(tenant);
	}

	public List<Tenant> getAllTenants() {
		return tenantRepo.findAll();
	}

	public Tenant getTenantById(int id) {
	    Optional<Tenant> optionalTenant = tenantRepo.findById(id);
	    if(optionalTenant.isPresent()) {
	    	return optionalTenant.get();
	    }
	    return null;
	}

	public void deleteTenant(int id) {
		tenantRepo.deleteById(id);
	}
}
