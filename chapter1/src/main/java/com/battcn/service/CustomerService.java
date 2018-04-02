package com.battcn.service;

import com.battcn.entity.Customer;

import java.util.List;

/**
 * @author Levin
 * @since 2018/4/2 0002
 */
public interface CustomerService {

    List<Customer> findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);

    Customer save(Customer customer);
}
