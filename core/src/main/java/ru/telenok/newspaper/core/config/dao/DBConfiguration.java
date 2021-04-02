package ru.telenok.newspaper.core.config.dao;

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
import ru.telenok.newspaper.common.CommonConstants;
import ru.telenok.newspaper.common.DaoConstants;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = {"ru.telenok.newspaper.core.dao"},
        entityManagerFactoryRef = CommonConstants.Beans.ENTITY_MANAGER_FACTORY,
        transactionManagerRef = CommonConstants.Beans.TRANSACTION_MANAGER
)
public class DBConfiguration {

    @Autowired
    @Qualifier(CommonConstants.Beans.DATA_SOURCE)
    private DataSource dataSource;
//
//    public DBConfiguration( DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean(name = CommonConstants.Beans.ENTITY_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean(
            @Qualifier(CommonConstants.Beans.JPA_VENDOR_ADAPTER) JpaVendorAdapter jpaVendorAdapter,
            @Qualifier(CommonConstants.Beans.JPA_PROPERTIES) Properties jpaProperties
    ) {

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setJpaVendorAdapter(jpaVendorAdapter);
        factory.setPackagesToScan(DaoConstants.Property.PACKAGE_ENTITY);
        factory.setPersistenceUnitName(DaoConstants.Property.PERSISTENT_UNIT_NAME);
        factory.setJpaProperties(jpaProperties);

        return factory;
    }

    @Bean(name = CommonConstants.Beans.TRANSACTION_MANAGER)
    public JpaTransactionManager jpaTransactionManager(@Qualifier(CommonConstants.Beans.ENTITY_MANAGER_FACTORY) EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }

    @Bean(name = CommonConstants.Beans.JDBC_TEMPLATE)
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = CommonConstants.Beans.JDBC_TEMPLATE_NAMED)
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean(name = CommonConstants.Beans.LIQUIBASE_PROPERTIES)
    @ConfigurationProperties(prefix = DaoConstants.Property.LIQUIBASE_PROPERTIES)
    public LiquibaseProperties liquibaseProperties() {
        return new LiquibaseProperties();
    }

    @Bean(name = CommonConstants.Beans.LIQUIBASE_PROPERTIES_SPRING)
    public SpringLiquibase liquibase(@Qualifier(CommonConstants.Beans.LIQUIBASE_PROPERTIES) LiquibaseProperties properties) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(properties.getChangeLog());
        liquibase.setShouldRun(properties.isEnabled());
        liquibase.setDropFirst(properties.isDropFirst());

        return liquibase;
    }
}
