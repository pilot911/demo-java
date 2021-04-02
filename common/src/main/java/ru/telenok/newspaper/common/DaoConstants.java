package ru.telenok.newspaper.common;

public interface DaoConstants {

    interface Common {
        String SEARCH = "searchDataSource";
        String JPA_VENDOR_ADAPTER = "jpaVendorAdapter";
        String JPA_PROPERTIES = "jpaProperties";
    }

    interface Property {
        String DATABASE_PROPERTIES = "news.datasource";
        String LIQUIBASE_PROPERTIES = "news.liquibase";
        String PACKAGE_ENTITY = "ru.telenok.newspaper";
        String PERSISTENT_UNIT_NAME = "newspaper";
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

        interface ArticleTable {
            String TABLE_NAME = "ARTICLE";
            String CONTENT = "CONTENT";
            String CONTENT_SHORT = "CONTENT_SHORT";
            String TAG_ID = "TAG_ID";
        }

        interface FileTable {
            String TABLE_NAME = "FILE";

            String UPLOADED_NAME = "UPLOADED_NAME";
            String UPLOADED_MIME_TYPE = "UPLOADED_MIME_TYPE";
            String UPLOADED_EXTENSION = "UPLOADED_EXTENSION";
            String UPLOADED_SIZE = "UPLOADED_SIZE";
            String UPLOADED_WIDTH = "UPLOADED_WIDTH";
            String UPLOADED_HEIGHT = "UPLOADED_HEIGHT";
        }

        interface FileDataTable {
            String TABLE_NAME = "FILE_DATA";

            String ARTICLE_ID = "ARTICLE_ID";
            String FILE_ID = "FILE_ID";
        }

        interface UserTable {
            String TABLE_NAME = "USER";
            String USERNAME = "USERNAME";
            String PASSWORD = "PASSWORD";
        }

        interface UserGroupTable {
            String TABLE_NAME = "USER_GROUP";
        }

        interface TagArticleTable {
            String TABLE_NAME = "TAG_TO_ARTICLE";

            String ARTICLE_ID = "ARTICLE_ID";
            String TAG_ID = "TAG_ID";
        }

        interface ArticleFileTable {
            String TABLE_NAME = "ARTICLE_TO_FILE";

            String FILE_ID = "FILE_ID";
            String ARTICLE_ID = "ARTICLE_ID";
        }

        interface TagFileTable {
            String TABLE_NAME = "TAG_TO_FILE";

            String FILE_ID = "FILE_ID";
            String TAG_ID = "TAG_ID";
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

        interface TagTable {
            String TABLE_NAME = "TAG";
        }

        interface JwtRefreshToken {
            String TABLE_NAME = "JWT_REFRESH_TOKEN";
            String USER_ID = "USER_ID";
            String REFRESH_TOKEN_CODE = "CODE";
        }
    }
}
