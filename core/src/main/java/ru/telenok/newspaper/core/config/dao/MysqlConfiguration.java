package ru.telenok.newspaper.core.config.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import ru.telenok.newspaper.common.CommonConstants;
import ru.telenok.newspaper.common.DaoConstants;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Profile(CommonConstants.Profile.MYSQL)
public class MysqlConfiguration {

    @Bean
    @ConfigurationProperties(DaoConstants.Property.DATABASE_PROPERTIES)
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean(name = CommonConstants.Beans.DATA_SOURCE)
    public DataSource dataSource(HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = CommonConstants.Beans.JPA_VENDOR_ADAPTER)
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");

        return adapter;
    }

    @Bean(name = CommonConstants.Beans.JPA_PROPERTIES)
    public Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.max_fetch_depth", 3);
        properties.put("hibernate.jdbc.fetch_size", 50);
        properties.put("hibernate.jdbc.batch_size", 10);
        properties.put("org.hibernate.flushMode", "COMMIT");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        return properties;
    }
}
