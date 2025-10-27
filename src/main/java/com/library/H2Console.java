package com.library;
import org.h2.tools.Server;

public class H2Console {
    public static void main(String[] args) throws Exception {
        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
        System.out.println("H2 konzola dostupna na: http://localhost:8082");
    }
}
