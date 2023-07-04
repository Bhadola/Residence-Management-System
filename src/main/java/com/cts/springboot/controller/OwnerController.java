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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cts.springboot.entities.Owner;
import com.cts.springboot.entities.Property;
import com.cts.springboot.entities.Tenant;
import com.cts.springboot.services.OwnerService;
import com.cts.springboot.services.PropertyService;
import com.cts.springboot.services.TenantService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	private OwnerService ownerService;
	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private TenantService tenantService;

	@GetMapping("/login")
	public String loginPage(Model m) {
		return "owner/login";
	}

	@GetMapping("/register")
	public String registerPage(Model m) {
		return "owner/register";
	}

	@PostMapping("/registerdata")
	public String ownerRegister(@ModelAttribute Owner owner, @RequestParam("profileImg") MultipartFile file,
			HttpSession session) {
		System.out.println(owner);

		try {
			if (file.isEmpty()) {
				System.out.println("File isn't selected ");
			} else {
				owner.setImgURL("own"+owner.getId()+file.getOriginalFilename());
				File savefile = new ClassPathResource("static/images").getFile();
				String finalpath=savefile.getAbsolutePath()+File.separator+"own"+owner.getId()+file.getOriginalFilename();	
				Path path = Paths.get(finalpath);
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			}
			ownerService.addOwner(owner);
			session.setAttribute("msg", "Owner added successfully ");
		} catch (Exception e) {
			System.out.println("Some error occured " + e.getMessage());
		}
		return "redirect:/owner/login";
	}

	@PostMapping("/verifylogin")
	public String verifyUser(@RequestParam String username, @RequestParam String password) {
		List<Owner> allOwners = ownerService.getAllOwners();
		for (Owner owner : allOwners) {
			String fullname = owner.getFirst() + " " + owner.getLast();
			if (fullname.equals(username) && password.equals(owner.getPassword())) {
				String returnpath = "redirect:/owner/dashboard/" + owner.getId();
				return returnpath;
			}
		}
		return "redirect:/owner/login";
	}

	@GetMapping("/dashboard/{id}")
	public String userDashboard(@PathVariable int id, Model m) {
		Owner owner = ownerService.getOwnerById(id);
		m.addAttribute("ownerdash", owner);
		m.addAttribute("properties", owner.getProperties());
		return "owner/dashboard";
	}
	
	@GetMapping("/registerprop/{id}")
	public String registerPropertyPage(@PathVariable int id, Model m) {
		Owner owner = ownerService.getOwnerById(id);		//receive the owner to add the data to
		m.addAttribute("owner", owner);
		return "/owner/registerprop";
	}
	
	@PostMapping("/registerproperty")
	public String uploadinDB(@ModelAttribute Property property,@RequestParam int ownerid, @RequestParam("profileImg") MultipartFile file) {
		Owner owner=ownerService.getOwnerById(ownerid);
		try {
			if (file.isEmpty()) {
				System.out.println("File isn't selected ");
			} else {
				property.setImgURL("prop"+property.getId()+file.getOriginalFilename());
				File savefile = new ClassPathResource("static/images").getFile();
				String finalpath=savefile.getAbsolutePath()+File.separator+"prop"+property.getId()+file.getOriginalFilename();	
				Path path = Paths.get(finalpath);
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			}
			property.setOwnerinprop(owner);
			propertyService.addProperty(property);
			
		} catch (Exception e) {
			System.out.println("Some error occured " + e.getMessage());
			return "redirect:/owner/login";					//in case of exceptions
		}
		String returnpath = "redirect:/owner/dashboard/" + owner.getId();
		return returnpath;
	}
	@GetMapping("/delete_prop/{id}")
	public String deleteTenant(@PathVariable int id){
		int ownerid=propertyService.getPropertyById(id).getOwnerinprop().getId();
		try {
			Tenant temp=propertyService.getPropertyById(id).getTenantinprop();
			temp.setPropertybought(0);
			tenantService.addTenant(temp);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		propertyService.deleteProperty(id);
		String returnpath="redirect:/owner/dashboard/"+ownerid;
		return returnpath;
	}

}
