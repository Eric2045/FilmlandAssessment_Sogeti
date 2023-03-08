package com.assessment.sogeti;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/*
 class named DataConfig that is annotated with @Configuration, indicating that it provides bean definitions.
 The class defines a method dataSource() that returns a DataSource object.
 The dataSource() method uses the EmbeddedDatabaseBuilder class to create an in-memory H2 database and
 initializes it with a script located at resources/schema.sql. The EmbeddedDatabaseType.H2 specifies the type of the embedded database.
 */
@Configuration
public class DataConfig {
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("resources/schema.sql")
                .build();
    }
}
