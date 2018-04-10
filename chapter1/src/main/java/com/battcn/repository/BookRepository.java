package com.battcn.repository;

import com.battcn.entity.Book;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Levin
 */
@Repository
public interface BookRepository extends ElasticsearchRepository<Book, String> {

    @Query("{\n" +
            "    \"bool\": {\n" +
            "      \"should\": [\n" +
            "        {\n" +
            "          \"match_phrase\": {\n" +
            "            \"name\": {\n" +
            "              \"query\": \"?0\",\n" +
            "              \"boost\": 3\n" +
            "            }\n" +
            "          }\n" +
            "        },\n" +
            "        {\n" +
            "          \"match_phrase\": {\n" +
            "            \"author\": {\n" +
            "              \"query\": \"?0\",\n" +
            "              \"boost\": 2\n" +
            "            }\n" +
            "          }\n" +
            "        },\n" +
            "        {\n" +
            "          \"match_phrase\": {\n" +
            "            \"description\": {\n" +
            "              \"query\": \"?0\",\n" +
            "              \"boost\": 1\n" +
            "            }\n" +
            "          }\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "}")
    List<Book> searchBook(String searchContent);

}