package com.lbs.data.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value="/customers", method= RequestMethod.GET)
    public List<Customer> getAllCustomers(){

        return customerService.getAllCustomers();
    }

    @RequestMapping(value= "/customer/{id}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable String id){
        return customerService.getCustomer(id);
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public void addCustomer(@RequestBody Customer customer){//your request payload is going to contain a JSON representation of this topic instance.
        //with this line of code you say take the JSON object and convert it into Topic
        customerService.addCustomer(customer);
    }

    @RequestMapping(value="/customers/{id}", method = RequestMethod.PUT)
    public void updateCustomer(@RequestBody Customer customer, @PathVariable String id){
        customerService.updateCustomer(id, customer);
    }

    @RequestMapping(value="/customers/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable String id){
        customerService.deleteCustomer(id);
    }
}
