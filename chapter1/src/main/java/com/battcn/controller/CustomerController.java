package com.battcn.controller;

import com.battcn.entity.Book;
import com.battcn.entity.Customer;
import com.battcn.service.CustomerService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Levin
 * @since 2018/4/2 0002
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/customer")
@Api(value = "人员管理", description = "人员管理", tags = {"2.0"})
public class CustomerController {

    private final CustomerService customerService;


    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        this.customerService.save(new Customer("Alice", "Smith"));
        this.customerService.save(new Customer("Bob", "Smith"));
        return customerService.save(customer);
    }

    @GetMapping
    public List<Customer> findByLastName(String lastName) {
        return customerService.findByLastName(lastName);
    }

    @GetMapping("/{first_name}")
    public List<Customer> findByFirstName(@PathVariable("first_name") String firstName) {
        return customerService.findByFirstName(firstName);
    }

    @GetMapping("/book")
    public Page<Book> findBook(@RequestParam("content") String content) {
        return customerService.searchBook(content);
    }
}
