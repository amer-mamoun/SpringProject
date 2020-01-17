package org.uni;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeesApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeesApplication.class, args);
    }

    public static String formatAddLocation(String requestURL, String id) {
        return String.format("%s/%s", requestURL, id);
    }
    public static String formatEditLocation(String requestURL) {
        return String.format("%s", requestURL);
    }

}
