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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chen.coupons.beans.Company;
import com.chen.coupons.beans.Coupon;
import com.chen.coupons.beans.Customer;
import com.chen.coupons.beans.Purchase;
import com.chen.coupons.controllers.CouponController;
import com.chen.coupons.enums.CouponType;
import com.chen.coupons.exceptions.ApplicationException;



@RestController
@RequestMapping(value = "/Coupons")
public class CouponsApi {

	@Autowired
	private CouponController couponConroller;


	@PostMapping
	public @ResponseBody void createCoupon(@RequestBody Coupon coupon) throws ApplicationException{
		System.out.println(this.couponConroller.createCoupon(coupon));
	}
	
	

	@GetMapping("/{couponId}")
	public Coupon getCoupon(@PathVariable("couponId") long couponId)throws ApplicationException{
		 return this.couponConroller.getCouponById(couponId);			
	}
	
	
	@DeleteMapping("/{couponId}")
	public void deleteCoupon(@PathVariable("couponId") long couponId)throws ApplicationException{
		this.couponConroller.deleteCouponById(couponId);
	}
	
	@PutMapping
	public void updateCoupon(@RequestBody Coupon coupon) throws ApplicationException{
		System.out.println(coupon);
		this.couponConroller.updateCoupon(coupon);
	}
	
	
	@GetMapping
	public List<Coupon> getAllCoupons()throws ApplicationException{
		 return this.couponConroller.getAllCoupons();			
	}
		
	
	@GetMapping("/activeCoupons")
	public List<Coupon> getAllActiveCoupons()throws ApplicationException{
		 return this.couponConroller.getAllActiveCoupons();			
	}
	
	
	@GetMapping("/byType")
	public List<Coupon> getCoupnsByType(@RequestParam("type") String type)throws ApplicationException{
		CouponType couponType = CouponType.valueOf(type);
		 return this.couponConroller.getCouponsByType(couponType);		
	}
	

	@GetMapping("/byEndDate")
	public List<Coupon> getCoupnsBeforeEndDate(@RequestParam("endDate") String endDate)throws ApplicationException{
		 return this.couponConroller.getCouponsBeforeEndDate(endDate);	
	}
	

	@GetMapping("/byPrices")
	public List<Coupon> getCoupnsByPrices(@RequestParam("min") double minPrice, @RequestParam("max") double maxPrice)throws ApplicationException{
		 return this.couponConroller.getCouponsByPrices(minPrice, maxPrice);	
	}
	
	
	//@RequestMapping(value = "/{companyId}/CompanyActiveCoupons", method = RequestMethod.GET)
	@GetMapping("/{companyId}/CompanyActiveCoupons")
	public List<Coupon> getCompanyActiveCoupons(@PathVariable("companyId") long companyId)throws ApplicationException{
		 return this.couponConroller.getActiveCouponsByCompanyId(companyId);
	}
	

	//@RequestMapping(value = "/{companyId}/AllCompanyCoupons", method = RequestMethod.GET)
	@GetMapping("/{companyId}/AllCompanyCoupons")
	public List<Coupon> getAllCompanyCoupons(@PathVariable("companyId") long companyId)throws ApplicationException{
		 return this.couponConroller.getAllCouponsByCompanyId(companyId);
	}
	
	
	
	//@RequestMapping(value = "/{customerId}/CustomerActiveCoupons", method = RequestMethod.GET)
	@GetMapping("/{customerId}/CustomerActiveCoupons")
	public List<Coupon> getCustomerActiveCoupons(@PathVariable("customerId") long customerId)throws ApplicationException{
		 return this.couponConroller.getAllCustomerActiveCoupons(customerId);
	}
	
	
	//@RequestMapping(value = "/{customerId}/AllCustomerCoupons", method = RequestMethod.GET)
	@GetMapping("/{customerId}/AllCustomerCoupons")
	public List<Coupon> getAllCustomerCoupons(@PathVariable("customerId") long customerId)throws ApplicationException{
		 return this.couponConroller.getAllCustomerCoupons(customerId);
	}
	

	 //@RequestMapping(value = "/purchaseCoupon", method = RequestMethod.POST)
	@PostMapping("/purchaseCoupon")
	 public void purchaseCoupon (@RequestBody  Purchase purchase) throws ApplicationException{
		 long couponId = purchase.getCouponId();
		 long customerId=purchase.getCustomerId();
		 int amount = purchase.getAmount();
		 this.couponConroller.purchaseCoupon(customerId, couponId, amount);
	 }
	
	
	
	
	
	
	
	
	
	
	
}
