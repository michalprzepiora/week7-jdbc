package pl.com.przepiora.week7jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class Week7JdbcApplication {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        SpringApplication.run(Week7JdbcApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    void start(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("CREATE TABLE cars(car_id int, mark varchar(255), model varchar(255),colour varchar(255), year int, PRIMARY KEY (car_id))");

        System.out.println("ok");
    }

}
