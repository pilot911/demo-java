package ru.telenok.newspaper.common;

public interface ControllerConstants {

    String API_v1_0 = "/api/v1.0";

    interface Parameter {
        String ID = "id";
        String IDS = "ids";
        String TAG_CODES = "tag_codes";
        String TEXT = "text";
    }

    interface RestApi {
        interface V1 {
            String ARTICLE_BY_ID = API_v1_0 + "/article/{id}";
            String ARTICLE = API_v1_0 + "/article";
            String ARTICLES_BY_TAG_CODES = API_v1_0 + "/articles-by-tag-codes";

            String USER_BY_ID = API_v1_0 + "/user/{id}";
            String USER = API_v1_0 + "/user";

            String USER_GROUP_BY_ID = API_v1_0 + "/user-group/{id}";
            String USER_GROUP = API_v1_0 + "/user-group";

            String USER_AUTHORITY_BY_ID = API_v1_0 + "/user-authority/{id}";
            String USER_AUTHORITY = API_v1_0 + "/user-authority";

            String TAG_BY_ID = API_v1_0 + "/tag/{id}";
            String TAG = API_v1_0 + "/tag";

            String FILE_UPLOAD = API_v1_0 + "/file-upload";
            String FILE = API_v1_0 + "/file";
            String FILE_BY_ID = API_v1_0 + "/file/{id}";
            String FILES_BY_TEXT = API_v1_0 + "/files-by-text";

        }
    }
}
