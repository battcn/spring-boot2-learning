package com.battcn.service;

import com.battcn.entity.Book;
import com.battcn.entity.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Levin
 * @since 2018/4/2 0002
 */
public interface CustomerService {

    List<Customer> findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);

    Customer save(Customer customer);

    Page<Book> searchBook(String searchContent);
}
