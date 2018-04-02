package com.battcn.repository;

import com.battcn.entity.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@Repository
public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {

    List<Customer> findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);

}