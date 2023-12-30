package com.cinema.definelocal.db.instance;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class InstanceDatasourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.instance")
    public DataSourceProperties instanceDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource instanceDataSource() {
        return instanceDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

}
