package com.tours.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gardiary on 10/12/18.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.tours.persistence.dao"})
public class DatabaseConfig {

    @Value("${hibernate.dialect}")
    private String HIBERNATE_DIALECT;

    @Value("${hibernate.hbm2ddl.auto}")
    private String HIBERNATE_HBM2DDL_AUTO;

    @Value("${hibernate.show_sql}")
    private Boolean HIBERNATE_SHOW_SQL;

    @Value("${hibernate.format_sql}")
    private Boolean HIBERNATE_FORMAT_SQL;

    @Value("${hibernate.jdbc.lob.non_contextual_creation}")
    private Boolean HIBERNATE_LOB_CREATION;

    @Bean(destroyMethod = "close")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPersistenceProvider(new HibernatePersistenceProvider());
        emf.setJpaDialect(new HibernateJpaDialect());
        emf.setPackagesToScan("com.tours.persistence.entity");

        Map<String, Object> propertyMap = new HashMap();
        propertyMap.put("hibernate.dialect", HIBERNATE_DIALECT);
        propertyMap.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
        propertyMap.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
        propertyMap.put("hibernate.format_sql", HIBERNATE_FORMAT_SQL);
        propertyMap.put("hibernate.jdbc.lob.non_contextual_creation", HIBERNATE_LOB_CREATION);

        emf.setJpaPropertyMap(propertyMap);

        return emf;
    }

}
