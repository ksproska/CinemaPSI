package com.cinema.clientservice.db.common;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class CommonDatasourceConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource.common")
    public DataSourceProperties commonDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.common.hikari")
    public DataSource commonDataSource() {
        return commonDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }
}
