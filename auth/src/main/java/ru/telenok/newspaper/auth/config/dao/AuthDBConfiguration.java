package ru.telenok.newspaper.auth.config.dao;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import ru.telenok.newspaper.auth.config.AuthConstants;
import ru.telenok.newspaper.common.CommonConstants;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = {"ru.telenok.newspaper.auth.dao"},
        entityManagerFactoryRef = AuthConstants.Beans.ENTITY_MANAGER_FACTORY,
        transactionManagerRef = AuthConstants.Beans.TRANSACTION_MANAGER
)
public class AuthDBConfiguration {

    @Autowired
    @Qualifier(AuthConstants.Beans.DATA_SOURCE)
    private DataSource dataSource;

//    public AuthDBConfiguration(@Qualifier(AuthConstants.Beans.DATA_SOURCE) DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean(name = AuthConstants.Beans.ENTITY_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean(
            @Qualifier(AuthConstants.Beans.JPA_VENDOR_ADAPTER) JpaVendorAdapter jpaVendorAdapter,
            @Qualifier(AuthConstants.Beans.JPA_PROPERTIES) Properties jpaProperties) {

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setJpaVendorAdapter(jpaVendorAdapter);
        factory.setPackagesToScan(AuthConstants.Property.PACKAGES_TO_SCAN);
        factory.setJpaProperties(jpaProperties);
        factory.setPersistenceUnitName(AuthConstants.Property.PERSISTENT_UNIT_NAME);

        return factory;
    }

    @Bean(name = AuthConstants.Beans.TRANSACTION_MANAGER)
    public JpaTransactionManager jpaTransactionManager(@Qualifier(AuthConstants.Beans.ENTITY_MANAGER_FACTORY) EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }

    @Bean(name = AuthConstants.Beans.JDBC_TEMPLATE)
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = AuthConstants.Beans.JDBC_TEMPLATE_NAMED)
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean(name = AuthConstants.Beans.LIQUIBASE_PROPERTIES)
    @ConfigurationProperties(prefix = AuthConstants.Property.LIQUIBASE_PROPERTIES, ignoreUnknownFields = false)
    public LiquibaseProperties liquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean(name = AuthConstants.Beans.LIQUIBASE_PROPERTIES_SPRING)
    public SpringLiquibase liquibase(@Qualifier(AuthConstants.Beans.LIQUIBASE_PROPERTIES) LiquibaseProperties properties) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(properties.getChangeLog());
        liquibase.setShouldRun(properties.isEnabled());
        liquibase.setDropFirst(properties.isDropFirst());

        return liquibase;
    }
}
