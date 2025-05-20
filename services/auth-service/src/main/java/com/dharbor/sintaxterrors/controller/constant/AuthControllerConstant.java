package com.dharbor.sintaxterrors.controller.constant;

public final class AuthControllerConstant {

    public static class AuthSwagger {
        public static final String TAG_NAME = "Auth";

        public static final String TAG_DESCRIPTION = "Authentication service";

        public static final String TAG_DESCRIPTION_LOGIN = "Login with email and password";
        public static final String TAG_DESCRIPTION_REFRESH_TOKEN= "Login with refresh token";

        public static final String TAG_DESCRIPTION_VALID_TOKEN = "Validate token";
    }

    public static class AuthPath {
        public static final String BASE_PATH = "/auth";
    }

    public static class AuthResponse {
        public static final String IS_VALID_TOKEN = "Is valid token";
    }
}
