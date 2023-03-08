package com.assessment.sogeti;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.UUID;


/*
This is a class implements CommandLineRunner and is annotated with @Component.
It sets up the initial database state upon application startup. The class depends on the DataSource bean, which is injected via @Autowired.

The run method creates a table for users if it doesn't exist yet, and inserts an initial user record with a generated auth token.
The password is hashed using the BCrypt algorithm, which is a common and secure way to store passwords.
 */
@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        // Create user table
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users (id INT AUTO_INCREMENT PRIMARY KEY, email VARCHAR(255), password VARCHAR(255), auth_token VARCHAR(255))");

        // Insert initial user record
        String email = "info@filmland-assessment.nl";
        //String password = "Javaiscool90";
        String password = BCrypt.hashpw("Javaiscool90", BCrypt.gensalt());
        String authToken = UUID.randomUUID().toString();
        jdbcTemplate.update("INSERT INTO users (email, password, auth_token) VALUES (?, ?, ?)", email, password, authToken);
    }
}
