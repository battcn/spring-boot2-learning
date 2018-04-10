package com.battcn.service.impl;

import com.battcn.entity.Book;
import com.battcn.entity.Customer;
import com.battcn.repository.BookRepository;
import com.battcn.repository.CustomerRepository;
import com.battcn.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
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
    private final BookRepository bookRepository;

    /**
     * 分页参数 -> TODO 代码可迁移到具体项目的公共 common 模块
     */
    private static final Integer PAGE_NUMBER = 0;
    private static final Integer PAGE_SIZE = 10;

    /**
     * 搜索模式
     * 权重分求和模式
     */
    private static final String SCORE_MODE_SUM = "sum";
    /**
     * 由于无相关性的分值默认为 1 ，设置权重分最小值为 10
     */
    private static final Float MIN_SCORE = 10.0F;

    private static final Pageable PAGEABLE = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, BookRepository bookRepository) {
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
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

    @Override
    public Page<Book> searchBook(String searchContent) {
        Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);
        final MatchPhraseQueryBuilder name = QueryBuilders.matchPhraseQuery("name", searchContent).boost(3.0F);
        final MatchPhraseQueryBuilder author = QueryBuilders.matchPhraseQuery("author", searchContent).boost(2.0F);
        final MatchPhraseQueryBuilder description = QueryBuilders.matchPhraseQuery("description", searchContent).boost(1.0F);
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().should(name).should(author).should(description);
        final NativeSearchQuery build = new NativeSearchQueryBuilder().withQuery(queryBuilder).withPageable(pageable).build();
        log.info("\n" + build.getQuery().toString());
        return bookRepository.search(build);
    }


}
