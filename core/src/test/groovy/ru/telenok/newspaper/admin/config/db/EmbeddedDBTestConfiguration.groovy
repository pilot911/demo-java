package ru.telenok.newspaper.admin.config.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.hibernate.FlushMode
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import ru.telenok.newspaper.common.DaoConstants

import javax.sql.DataSource

import static ru.telenok.newspaper.common.DaoConstants.Common.*

@TestConfiguration
class EmbeddedDBTestConfiguration {

    @Primary
    @Bean(name = SEARCH)
    DataSource searchServiceDataSource() {
        new HikariDataSource(hikariConfigSearchService())
    }

    @Bean
    @Primary
    HikariConfig hikariConfigSearchService() {

        def properties = new Properties().tap {
            put("useSSL", false)
            put("rewriteBatchedStatements", true)
            put("cachePrepStmts", true)
            put("prepStmtCacheSize", 250)
            put("prepStmtCacheSqlLimit", 2048)
            put("useServerPrepStmts", true)
            put("useLocalSessionState", true)
            put("useLocalTransactionState", true)
            put("cacheResultSetMetadata", true)
            put("cacheServerConfiguration", true)
            put("elideSetAutoCommits", true)
            put("maintainTimeStats", false)
            put("characterEncoding", "UTF-8")
        }

        new HikariConfig().tap {
            username = "sa"
            maxLifetime = 25000
            setJdbcUrl("jdbc:hsqldb:mem:embedded;shutdown=true;hsqldb.write_delay_millis=0;readonly=true;")
            setDriverClassName("org.hsqldb.jdbc.JDBCDriver")
            setMinimumIdle(8)
            setMaximumPoolSize(8)
            setDataSourceProperties(properties)
        }
    }

    @Bean(name = JPA_VENDOR_ADAPTER)
    JpaVendorAdapter jpaVendorAdapter() {
        new HibernateJpaVendorAdapter().tap {
            showSql = true
            databasePlatform = "org.hibernate.dialect.HSQLDialect"
        }
    }

    @Bean(name = JPA_PROPERTIES)
    Properties jpaProperties() {
        new Properties().tap {
            put("hibernate.max_fetch_depth", 3)
            put("hibernate.jdbc.fetch_size", 50)
            put("hibernate.jdbc.batch_size", 10)
            put("org.hibernate.flushMode", FlushMode.COMMIT)
            put("hibernate.show_sql", true)
            put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect")
        }
    }
}
