package com.cts.springboot.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cts.springboot.entities.Property;
import com.cts.springboot.entities.Tenant;
import com.cts.springboot.services.PropertyService;
import com.cts.springboot.services.TenantService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/tenant")
public class TenantController {

	@Autowired
	private TenantService tenantService;
	@Autowired
	private PropertyService propertyService;

	@GetMapping("/login")
	public String loginPage(Model m) {
		return "tenant/login";
	}

	@GetMapping("/register")
	public String registerPage(Model m) {
		return "tenant/register";
	}

	@PostMapping("/registerdata")
	public String tenantRegister(@ModelAttribute Tenant tenant, @RequestParam("profileImg") MultipartFile file,
			HttpSession session) {
		System.out.println(tenant);

		try {
			if (file.isEmpty()) {
				System.out.println("File isn't selected ");
			} else {
				tenant.setImgURL("ten"+tenant.getId()+file.getOriginalFilename());							//set final path for img
				File savefile = new ClassPathResource("static/images").getFile();
				String finalpath=savefile.getAbsolutePath()+File.separator+"ten"+tenant.getId()+file.getOriginalFilename();		
				Path path = Paths.get(finalpath);
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			}
			tenantService.addTenant(tenant);
			session.setAttribute("msg", "success");
		} catch (Exception e) {
			session.setAttribute("msg", "error");
		}
		return "redirect:/tenant/login";
	}
	
	@PostMapping("/verifylogin")
	public String verifyUser(@RequestParam String username,@RequestParam String password,Model m) {
		List<Tenant> allTenants = tenantService.getAllTenants();
		for(Tenant tenant:allTenants) {
			System.out.println(tenant);
			String fullname=tenant.getFirst()+" "+tenant.getLast();
			if(fullname.equals(username)&&password.equals(tenant.getPassword())) {
					String returnpath="redirect:/tenant/dashboard/"+tenant.getId();
					return returnpath;
				}
		}
		return "redirect:/tenant/login";				//incase of error login
	}
	@GetMapping("/dashboard/{id}")
	public String userDashboard(@PathVariable int id, Model m) {
		Tenant tenant=tenantService.getTenantById(id);
		m.addAttribute("tenantdash",tenant);
		m.addAttribute("property",tenant.getProperty());			//receiving null
		System.out.println(tenant.getProperty());
		return "tenant/dashboard";
	}
	
	@GetMapping("/registerprop/{id}")
	public String registerPropertyPage(@PathVariable int id, Model m) {
		Tenant tenant = tenantService.getTenantById(id);		//receive the owner to add the data to
		m.addAttribute("properties",propertyService.getAllProperties());
		m.addAttribute("tenant", tenant);
		return "/tenant/registerprop";
	}
	

	@PostMapping("/finalizeprop")
	public String finalizeRent(@RequestParam int tenantId,@RequestParam int propId) {
		try {
			Tenant tenant=tenantService.getTenantById(tenantId);
			Property property=propertyService.getPropertyById(propId);
			property.setTenantinprop(tenant);
			property.setOwned(1);
			tenant.setPropertybought(1);
			propertyService.addProperty(property);								//to update table
			tenantService.addTenant(tenant);									//to update in db
			String returnpath="redirect:/tenant/dashboard/"+tenant.getId();
			return returnpath;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return "redirect:/";		//in case of an error
	}
	
	@PutMapping("/update")
	public String updateTenant(@ModelAttribute Tenant tenant) {
		tenantService.addTenant(tenant);
		return "redirect:/tenant_details";
	}
	
	@GetMapping("/release_property/{id}")
	public String releaseProperty(@PathVariable int id){
		//we have tenantid here
		try {
			Property property=tenantService.getTenantById(id).getProperty();
			property.setTenantinprop(null);
			property.setOwned(0);
			propertyService.addProperty(property);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		Tenant tenant=tenantService.getTenantById(id);
		tenant.setPropertybought(0);
		tenantService.addTenant(tenant);
		return "redirect:/tenant/dashboard/"+id;
	}
	

}
