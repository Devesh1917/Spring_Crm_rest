package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	//Autowire the customer service
	@Autowired
	private CustomerService customerService;
	//add mapping for customer
	@GetMapping("/customer")
	public List<Customer> getCustomer() {
		return customerService.getCustomers();
		
	}
	
	//add mapping for /api/customer/{customerid}
	@GetMapping("/customer/{customerId}")
	public Customer getCustomerById(@PathVariable int customerId) {
		Customer theCustomer=customerService.getCustomer(customerId);
		
		if (theCustomer==null) {
			throw new CustomerNotFoundException("Customer id not found - " + customerId);
		}
		return theCustomer;
	}
	@PostMapping("/customer")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		theCustomer.setId(0);
		customerService.saveCustomer(theCustomer);
		return theCustomer;
		
	}
	@PutMapping("/customer")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		return theCustomer;
		
	}
	@DeleteMapping("/customer/{customerId}")
	public String DeleteCustomer(@PathVariable int customerId) {
		Customer theCustomer=customerService.getCustomer(customerId);
		if (theCustomer==null) {
			throw new CustomerNotFoundException("Customer id not found : "+customerId);
		}
		customerService.deleteCustomer(customerId);
		return "Deleted Customer Id : "+customerId;
	}
}
