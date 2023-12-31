package com.cinema.clientservice.db.common;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.cinema.clientservice.db.common",
        entityManagerFactoryRef = "commonEntityManagerFactory",
        transactionManagerRef = "commonTransactionManager"
)
public class CommonJpaConfiguration {

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean commonEntityManagerFactory(
            @Qualifier("commonDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages("com.cinema.clientservice.db.common")
                .build();
    }

    @Bean
    public PlatformTransactionManager commonTransactionManager(
            @Qualifier("commonEntityManagerFactory") LocalContainerEntityManagerFactoryBean commonEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(commonEntityManagerFactory.getObject()));
    }

}
