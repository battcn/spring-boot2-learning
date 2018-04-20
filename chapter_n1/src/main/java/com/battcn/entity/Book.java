package com.battcn.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * book
 *
 * @author Levin
 * @since 2018/4/10 0010
 */
@Document(indexName = "book", type = "book", shards = 1, replicas = 0)
public class Book implements java.io.Serializable {

    private static final long serialVersionUID = 3323587549675354180L;

    @Id
    private String bookNo;
    @Field(searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String name;
    @Field(searchAnalyzer = "ik_word", analyzer = "ik_word")
    private String author;
    @Field(searchAnalyzer = "ik_word", analyzer = "ik_word")
    private String description;
    @Field
    private String tags;
    @Field
    private String cover;


    public String getBookNo() {
        return bookNo;
    }

    public void setBookNo(String bookNo) {
        this.bookNo = bookNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
