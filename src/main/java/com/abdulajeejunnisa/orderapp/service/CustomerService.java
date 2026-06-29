package com.abdulajeejunnisa.orderapp.service;

import com.abdulajeejunnisa.orderapp.dto.UpdateCustomerRequest;
import com.abdulajeejunnisa.orderapp.model.Customer;
import com.abdulajeejunnisa.orderapp.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }
    public Customer updateCustomer(Long id, UpdateCustomerRequest request) {

        Customer customer = getCustomerById(id);

        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhoneNo(request.getPhoneNo());
        customer.setAddress(request.getAddress());

        return customerRepository.save(customer);
    }


    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));
    }
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}