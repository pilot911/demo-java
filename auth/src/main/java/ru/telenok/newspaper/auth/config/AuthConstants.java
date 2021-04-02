package ru.telenok.newspaper.auth.config;

public interface AuthConstants {

    interface Error {
        interface Username {
            String EXISTS = "USERNAME_EXISTS";
            String IS_EMPTY = "USERNAME_IS_EMPTY";
            String TOO_SHORT = "USERNAME_TOO_SHORT";
            String TOO_LONG = "USERNAME_TOO_LONG";
        }

        interface Password {
            String IS_EMPTY = "USERNAME_IS_EMPTY";
            String TOO_SHORT = "PASSWORD_TOO_SHORT";
        }
    }

    interface Register {
        String SUCCESS = "SUCCESS";
    }

    interface AuthController {

        interface V1 {
            String API = "/api/v1.0";

            String AUTHENTICATE_REGISTER = API + "/registration";
            String AUTHENTICATE_JWT_TOKENT_CREATE = API + "/token/create";
            String AUTHENTICATE_JWT_TOKENT_REFRESH = API + "/token/refresh";
        }
    }

    interface Roles {
        String ROLE_ADMIN = "ROLE_ADMIN";
        String ROLE_ACTUATOR_ADMIN = "ACTUATOR_ADMIN";

        String ROLE_READ_CATEGORY = "ROLE_READ_CATEGORY";
        String ROLE_UPDATE_CATEGORY = "ROLE_UPDATE_CATEGORY";
        String ROLE_CREATE_ARTICLE = "ROLE_CREATE_ARTICLE";
    }

    interface Profile {
        String POSTGRESQL = "POSTGRESQL";
        String H2 = "H2";
    }

    interface Beans {
        String HIKARI_CONFIG = "auth_hikari_config";
        String DATA_SOURCE = "auth_datasource";
        String JPA_VENDOR_ADAPTER = "auth_jpa_vendor_adapter";
        String JPA_PROPERTIES = "auth_jpa_properties";

        String ENTITY_MANAGER_FACTORY = "auth_entity_manager_factory";
        String TRANSACTION_MANAGER = "auth_transaction_manager";
        String JDBC_TEMPLATE = "auth_jdbc_template";
        String JDBC_TEMPLATE_NAMED = "auth_jdbc_template_named";
        String LIQUIBASE_PROPERTIES = "auth_liquibase_properties";
        String LIQUIBASE_PROPERTIES_SPRING = "auth_liquibase_properties_spring";
    }

    interface Common {
        String SEARCH = "searchDataSource";
        String JPA_VENDOR_ADAPTER = "jpaVendorAdapter";
        String JPA_PROPERTIES = "jpaProperties";
    }

    interface Property {
        String DATABASE_PROPERTIES = "auth.datasource";
        String LIQUIBASE_PROPERTIES = "auth.liquibase";
        String PACKAGES_TO_SCAN = "ru.telenok.newspaper.auth.model";
        String PERSISTENT_UNIT_NAME = "auth";
    }

    interface Table {
        String ID = "ID";
        String DESCRIPTION = "DESCRIPTION";
        String SORT = "SORT";
        String CREATED_AT = "CREATED_AT";
        String UPDATED_AT = "UPDATED_AT";
        String STARTED_AT = "STARTED_AT";
        String END_AT = "END_AT";
        String ACTIVE = "ACTIVE";
        String TITLE = "TITLE";
        String CODE = "CODE";
        String URL = "URL";
        String USER_ID = "USER_ID";
        String STATE = "STATE";

        interface UserTable {
            String TABLE_NAME = "USER";
            String USERNAME = "USERNAME";
            String PASSWORD = "PASSWORD";
        }

        interface UserGroupTable {
            String TABLE_NAME = "USER_GROUP";
        }

        interface UserGroupToUserTable {
            String TABLE_NAME = "USER_GROUP_TO_USER";
            String USER_ID = "USER_ID";
            String GROUP_ID = "USER_GROUP_ID";
        }

        interface UserAuthorityTable {
            String TABLE_NAME = "USER_AUTHORITY";
        }

        interface UserGroupToUserAuthorityTable {
            String TABLE_NAME = "USER_GROUP_TO_USER_AUTHORITY";
            String AUTHORITY_ID = "USER_AUTHORITY_ID";
            String GROUP_ID = "USER_GROUP_ID";
        }

        interface JwtRefreshToken {
            String TABLE_NAME = "JWT_REFRESH_TOKEN";
            String USER_ID = "USER_ID";
            String REFRESH_TOKEN_CODE = "CODE";
        }
    }
}
