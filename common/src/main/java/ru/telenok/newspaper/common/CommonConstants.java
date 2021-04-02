package ru.telenok.newspaper.common;

public interface CommonConstants {

    interface Profile {
        String H2 = "h2";
        String MYSQL = "mysql";
    }

    interface Beans {
        String DATA_SOURCE = "datasource";
        String JPA_VENDOR_ADAPTER = "jpa_vendor_adapter";
        String JPA_PROPERTIES = "jpa_properties";

        String ENTITY_MANAGER_FACTORY = "entityManagerFactory";
        String TRANSACTION_MANAGER = "transactionManager";
        String JDBC_TEMPLATE = "jdbc_template";
        String JDBC_TEMPLATE_NAMED = "jdbc_template_named";
        String LIQUIBASE_PROPERTIES = "liquibase_properties";
        String LIQUIBASE_PROPERTIES_SPRING = "liquibase_properties_spring";
    }
}
