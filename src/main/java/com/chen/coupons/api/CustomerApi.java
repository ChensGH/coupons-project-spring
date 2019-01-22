package com.chen.coupons.api;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chen.coupons.beans.Customer;
import com.chen.coupons.controllers.CustomerController;
import com.chen.coupons.exceptions.ApplicationException;


@RestController
@RequestMapping(value = "/Customers")
public class CustomerApi {
	
	@Autowired
	private CustomerController customerController;
	
	
	//@RequestMapping(method = RequestMethod.POST) - spring3
	@PostMapping //-spring 4
	public void creatCustomer (@RequestBody Customer customer) throws ApplicationException{
		System.out.println(this.customerController.createCustomer(customer)); 
	}
	
	
	@GetMapping("/{customerId}")
	public Customer getCustumer(@PathVariable("customerId") long customerId)throws ApplicationException{
		 return this.customerController.getCustomerByCustomerId(customerId);			
	}
	
	
	@DeleteMapping("/{customerId}")
	public void deleteCustomer(@PathVariable("customerId") long customerId)throws ApplicationException{
		this.customerController.deleteCustomer(customerId);
	}
	
	
	@GetMapping
	public List<Customer> getAllCustumer()throws ApplicationException{
		 return this.customerController.getAllCustomers();			
	}
	
	@PutMapping
	public void updateCustomer(@RequestBody Customer customer) throws ApplicationException{
		this.customerController.updateCustomer(customer);
	}
	

	

	
	
	
	
	
	
	

}
