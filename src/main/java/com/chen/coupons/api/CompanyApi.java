package com.chen.coupons.api;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.chen.coupons.beans.Company;
import com.chen.coupons.beans.Customer;
import com.chen.coupons.controllers.CompanyController;
import com.chen.coupons.controllers.CustomerController;
import com.chen.coupons.exceptions.ApplicationException;
import com.chen.coupons.utils.CookiesUtils;


@RestController
@RequestMapping(value = "/Companies")
public class CompanyApi {

	@Autowired
	private CompanyController companyController;
	
	
	
	@PostMapping
	public void createCompany(@RequestBody Company company) throws ApplicationException{
		System.out.println(this.companyController.createCompany(company)); 
	}
	
	
	@GetMapping
	public List<Company> getAllCompanies()throws ApplicationException{
		 return this.companyController.getAllCompanies();			
	}
	
	
	@GetMapping ("/{companyId}")
	public Company getCompany(@PathVariable("companyId") long companyId)throws ApplicationException{
		 return this.companyController.getCompanyByComapnyId(companyId);			
	}
	
	@DeleteMapping("/{companyId}")
	public void deleteCompany(@PathVariable("companyId") long companyId)throws ApplicationException{
		this.companyController.deleteCompany(companyId);
	}

	
	@PutMapping
	public void updateCompany(@RequestBody Company company) throws ApplicationException{
		this.companyController.updateCompany(company);
	}
	
	
	
	

	
	
	
	
	
	
}
