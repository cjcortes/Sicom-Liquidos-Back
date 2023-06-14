package com.sicom.ms;
<<<<<<< HEAD

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SICOMApplication {
=======
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class SICOMApplication {
    @Value("${spring.application.timezone}")
    private String timeZone;
>>>>>>> release

    public static void main(String[] args) {
        SpringApplication.run(SICOMApplication.class, args);
    }

<<<<<<< HEAD
=======
    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
    }
>>>>>>> release
}
