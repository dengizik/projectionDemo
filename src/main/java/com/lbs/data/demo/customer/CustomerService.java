package com.lbs.data.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public List<Customer> getAllCustomers() {


        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }


    public Customer getCustomer(String id){

       return customerRepository.findOne(id);
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void updateCustomer(String id, Customer customer) {

        customerRepository.save(customer);//repository finds the customer and updated if it exists, if not it inserts
    }

    public void deleteCustomer(String id) {
//        Customers.removeIf(t -> t.getId().equals(id));
        customerRepository.delete(id);
    }
}
