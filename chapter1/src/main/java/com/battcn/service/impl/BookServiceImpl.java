package com.battcn.service.impl;

import com.battcn.entity.Book;
import com.battcn.repository.BookRepository;
import com.battcn.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Levin
 * @since 2018/4/2 0002
 */
@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    /**
     * 分页参数 -> TODO 代码可迁移到具体项目的公共 common 模块
     */
    private static final Integer PAGE_NUMBER = 0;
    private static final Integer PAGE_SIZE = 10;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public List<Book> findByName(String name) {
        return this.bookRepository.findByName(name);
    }

    @Override
    public void deleteByBookNo(String bookNo) {
        // TODO 这里采用的都是内置封装好的语法,简单粗暴
        this.bookRepository.deleteById(bookNo);
    }

    @Override
    public Book save(Book book) {
        return this.bookRepository.save(book);
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
        // TODO  与 bookRepository.searchBook(searchContent); 结果一致,只是一种自己构建的查询,一种用语法直接查询.相比起来这种方式更优雅
        return bookRepository.search(build);
    }


}
