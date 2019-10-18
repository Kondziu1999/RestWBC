package com.luv2code.springdemo.controllers;


import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.exceptions.CustomerNotFoundException;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId){
        Customer theCustomer=customerService.getCustomer(customerId);
        if(theCustomer==null){
            throw new CustomerNotFoundException("Customer not found"+ customerId);

        }
        return theCustomer;
    }
    //@ResponseBody is added automagically
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody @Valid Customer theCustomer){
        //setting id to zero will result in creating new Customer in DAO
        // since saveOrUpadate method accept check id which is in this case
        // equals to zero (null)
        theCustomer.setId(0);
        customerService.saveCustomer(theCustomer);
        return theCustomer;

    }


}
