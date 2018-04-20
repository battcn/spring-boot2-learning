package com.battcn;

/**
 * @author Levin
 * @since 2018/4/18 0018
 */
public class MyClassLoaderTest {


    public static void main(String[] args) {

        final ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

    }


}
