package com.battcn.service;

import com.battcn.entity.Book;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Levin
 * @since 2018/4/2 0002
 */
public interface BookService {

    /**
     * 根据名称查询书籍信息
     *
     * @param name 书籍信息
     * @return 数据集
     */
    List<Book> findByName(String name);

    /**
     * 根据名称删除书籍
     *
     * @param bookNo 书籍名称
     */
    void deleteByBookNo(String bookNo);


    /**
     * 保存书籍信息
     *
     * @param book 书籍信息
     * @return 保存结果
     */
    Book save(Book book);

    /**
     * 分页查询
     *
     * @param searchContent 检索内容
     * @return 检索的结果
     */
    Page<Book> searchBook(String searchContent);
}
