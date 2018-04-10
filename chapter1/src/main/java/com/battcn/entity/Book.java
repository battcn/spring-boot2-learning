package com.battcn.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * book
 *
 * @author Levin
 * @since 2018/4/10 0010
 */
@Data
@Document(indexName = "book", type = "book", shards = 1, replicas = 0)
public class Book implements java.io.Serializable {

    private static final long serialVersionUID = 3323587549675354180L;

    @Id
    public String bookNo;
    @Field(searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    public String name;
    @Field(searchAnalyzer = "ik_word", analyzer = "ik_word")
    public String author;
    @Field(searchAnalyzer = "ik_word", analyzer = "ik_word")
    public String description;
    @Field
    public String tags;
    @Field
    public String cover;
}
