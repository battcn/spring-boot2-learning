package com.battcn.service.impl;

import com.battcn.entity.Customer;
import com.battcn.repository.CustomerRepository;
import com.battcn.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Levin
 * @since 2018/4/2 0002
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    /**
     * 分页参数 -> TODO 代码可迁移到具体项目的公共 common 模块
     */
    private static final Integer PAGE_NUMBER = 0;
    private static final Integer pageSize = 10;
    private static final Pageable PAGEABLE = PageRequest.of(PAGE_NUMBER, pageSize);

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> findByFirstName(String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

    @Override
    public List<Customer> findByLastName(String lastName) {
        return customerRepository.findByLastName(lastName);
    }

    @Override
    public Customer save(Customer customer) {
        customer.setId(UUID.randomUUID().toString().replace("-", "").toUpperCase());
        return customerRepository.save(customer);
    }




}
