package com.gloresoft;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:currency-convertor", "--user", "sa", "--password", "" });
    }


}
