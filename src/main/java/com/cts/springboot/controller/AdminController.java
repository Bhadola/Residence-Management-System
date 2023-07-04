package com.cts.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.springboot.entities.Owner;
import com.cts.springboot.entities.Property;
import com.cts.springboot.entities.Tenant;
import com.cts.springboot.services.OwnerService;
import com.cts.springboot.services.PropertyService;
import com.cts.springboot.services.TenantService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private TenantService tenantService;
	@Autowired
	private OwnerService ownerService;
	@Autowired
	private PropertyService propertyService;
	
	@GetMapping("/login")
	public String loginPage(Model m) {
		return "admin/login";
	}

	@PostMapping("/verifylogin")
	public String verifyUser(@RequestParam String username, @RequestParam String password) {
		if (username.equals("root") && password.equals("root")) {
			String returnpath = "redirect:/admin/dashboard";
			return returnpath;
		}
		return "redirect:/admin/login";
	}

	@GetMapping("/dashboard")
	public String dashboard() {
		return "admin/dashboard";
	}

	@GetMapping("/tenant_details")
	public String tenantDetails(Model m) {
		List<Tenant> tenants=tenantService.getAllTenants();
		m.addAttribute("tenlist",tenants);
	    return "admin/tenant_details";
	}
	@GetMapping("/owner_details")
	public String ownerDetails(Model m) {
		List<Owner> owners=ownerService.getAllOwners();
		m.addAttribute("ownlist",owners);
	    return "admin/owner_details";
	}
	@GetMapping("/property_details")
	public String propertyDetails(Model m) {
		List<Property> properties=propertyService.getAllProperties();
		m.addAttribute("proplist",properties);
	    return "admin/property_details";
	}
	@GetMapping("/delete_ten/{id}")
	public String deleteTenant(@PathVariable int id){
		try {
		int propid=tenantService.getTenantById(id).getProperty().getId();				//before deleting tenant release the property
		Property assignedprop=propertyService.getPropertyById(propid);
		assignedprop.setOwned(0);
		propertyService.addProperty(assignedprop);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		tenantService.deleteTenant(id);						//can safely be deleted now
		return "redirect:/admin/tenant_details";
	}
	@GetMapping("/delete_own/{id}")
	public String deleteOwner(@PathVariable int id){
		List<Property> proplists=ownerService.getOwnerById(id).getProperties();
		proplists.forEach(e->{
			try {
				Tenant temp=e.getTenantinprop();
				temp.setPropertybought(0);						//release the tenant from property
				tenantService.addTenant(temp);					//update in db
			}catch(Exception er) {
				System.out.println(er.getMessage());
			}
			propertyService.deleteProperty(e.getId());					//delete the prop
		});
		ownerService.deleteOwner(id);
		return "redirect:/admin/owner_details";
	}
	@GetMapping("/delete_prop/{id}")
	public String deleteProperty(@PathVariable int id){
		try {
			Tenant tempten=propertyService.getPropertyById(id).getTenantinprop();
			tempten.setPropertybought(0);
			tenantService.addTenant(tempten);
		}
		catch(Exception e) {								//tenant might be null
			System.out.println(e.getMessage());
		}
		propertyService.deleteProperty(id);
		return "redirect:/admin/property_details";
	}
}
