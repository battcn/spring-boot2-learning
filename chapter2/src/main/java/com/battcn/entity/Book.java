package com.battcn.entity;

import lombok.Data;

/**
 * @author Levin
 * @since 2018/4/11 0011
 */
@Data
public class Book implements java.io.Serializable {

    private static final long serialVersionUID = -2164058270260403154L;

    private String id;
    private String name;


}
