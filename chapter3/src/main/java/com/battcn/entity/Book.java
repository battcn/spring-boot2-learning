package com.battcn.entity;

/**
 * @author Levin
 * @since 2018/4/11 0011
 */
public class Book implements java.io.Serializable {

    private static final long serialVersionUID = -2164058270260403154L;

    public Book() {
        System.out.println("无参构造函数");
    }

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
