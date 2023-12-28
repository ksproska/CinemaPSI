package com.cinema.repertoire.db.instance;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        basePackageClasses = InstanceTemp.class,
        entityManagerFactoryRef = "instanceEntityManagerFactory",
        transactionManagerRef = "instanceTransactionManager"
)
public class InstanceJpaConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean instanceEntityManagerFactory(
            @Qualifier("instanceDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(dataSource)
                .packages(InstanceTemp.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager instanceTransactionManager(
            @Qualifier("instanceEntityManagerFactory") LocalContainerEntityManagerFactoryBean instanceEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(instanceEntityManagerFactory.getObject()));
    }
}