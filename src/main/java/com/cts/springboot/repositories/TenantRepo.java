package com.cts.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.springboot.entities.Tenant;

@Repository
public interface TenantRepo extends JpaRepository<Tenant, Integer> {
	
//	@Query("select t from tenant t where t.first+' '+t.last=:fullname")
//	public Tenant getUserByUserName(@Param("fullname")String name);	//pass two parameters fname and lname to combine as a uname
	
}
