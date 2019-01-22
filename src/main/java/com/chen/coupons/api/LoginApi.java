package com.chen.coupons.api;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chen.coupons.beans.Company;
import com.chen.coupons.beans.Customer;
import com.chen.coupons.beans.UserLoginDetails;
import com.chen.coupons.controllers.CompanyController;
import com.chen.coupons.controllers.CustomerController;
import com.chen.coupons.dao.CompanyDao;
import com.chen.coupons.dao.CustomerDao;
import com.chen.coupons.enums.UserType;
import com.chen.coupons.exceptions.ApplicationException;


@RestController
@RequestMapping(value = "/login")
public class LoginApi {	
	
	@Autowired
	CompanyController companyController;
	
	@Autowired
	CustomerController customerController;

	
	
	//@RequestMapping(value = "/login", method = RequestMethod.POST)
	//@RequestMapping(method = RequestMethod.POST)
	@PostMapping
	public void login(HttpServletRequest request, HttpServletResponse response,@RequestBody UserLoginDetails userLoginDetails) throws ApplicationException{
		
		//if the user is a company user type
		if(userLoginDetails.getUserType() == UserType.COMPANY){
			if (companyController.companyCheckLogin(userLoginDetails.getUserName(), userLoginDetails.getPassword()) != null){
				request.getSession(); // if the credentials are correct, get a session
				request.getSession().setMaxInactiveInterval(30*60); ////setting session to expire in 30 mins
				
				Company company = companyController.companyCheckLogin(userLoginDetails.getUserName(), userLoginDetails.getPassword());//getting the company that just loggged in
				long id = company.getCompanyId();
				//userId userId = new UserId(id); //???
				String userId =String.valueOf(id);
				Cookie idCookie = new Cookie("userId",userId);
				response.addCookie(idCookie);	
				Cookie userTypeCookie = new Cookie("userType",userLoginDetails.getUserType().name());
				response.addCookie(userTypeCookie);	
				Cookie loginCookie = new Cookie("loginStatus","success");
				//setting cookie to expire in 30 mins
				loginCookie.setMaxAge(30*60);
				response.addCookie(loginCookie);
				
				//return Response.status(200).entity(id).build();
				response.setStatus(200);
			}
			else {
				//return Response.status(401).entity(null).build();
				response.setStatus(401);
			}
		}
			
		else if(userLoginDetails.getUserType() == UserType.CUSTOMER ){
			if (customerController.customerCheckLogin(userLoginDetails.getUserName(), userLoginDetails.getPassword()) != null){
				request.getSession(); // if the credentials are correct, get a session.
				Customer customer = customerController.customerCheckLogin(userLoginDetails.getUserName(), userLoginDetails.getPassword());
				long id = customer.getCustomerId();
				String userId =String.valueOf(id);
				Cookie idCookie = new Cookie("userId",userId);
				response.addCookie(idCookie);
				Cookie userTypeCookie = new Cookie("userType",userLoginDetails.getUserType().name());
				response.addCookie(userTypeCookie);	
				Cookie loginCookie = new Cookie("loginStatus","success");
				//setting cookie to expire in 30 mins
				loginCookie.setMaxAge(30*60);
				response.addCookie(loginCookie);
				//return Response.status(200).entity(id).build();
				response.setStatus(200);
			}
				
				//return Response.status(401).entity(null).build();
				response.setStatus(401);
			}
			
			//return Response.status(401).entity(null).build();
			response.setStatus(401);
		}  
		

}
	


