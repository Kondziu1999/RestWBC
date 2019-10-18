package com.luv2code.springdemo.validation;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class EmailValidator implements ConstraintValidator<Email,String> {


    private CustomerService customerService;
    public  EmailValidator(){}
    public  EmailValidator(CustomerService customerService){
        this.customerService=customerService;
    }

    @Override
    public void initialize(Email constraint) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {


        List<Customer> customers= customerService.getCustomerByEmail(value);

        return customers.size()==0;

    }
}
