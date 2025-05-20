package com.dharbor.sintaxterrors.exception.constant;

public final class UserExceptionConstant
{
    public static final String NOT_NULL_USERNAME = "The user name can not be null or empty";

    public static final String NOT_NULL_PASSWORD = "The password can not be null or empty";

    public static final String NOT_EMPTY_EMAIL = "The email can not be null or empty";

    public static final String INVALID_EMAIL = "The email is not valid";

    public static final String NOT_NULL_ROLE_ID = "The role id can not be null or empty";

    public static final String NOT_NULL_USER_STATE = "The user state can not be null or empty";

    public static final String NOT_NULL_USER_ID = "The user id can not be null";
    
    public static final String FAILED_TO_CREATE_USER_MESSAGE = "Failed to register user";

    public static final String FAILED_TO_UPDATE_USER_MESSAGE = "Failed to update user";

    public static final String FAILED_TO_GET_USER_MESSAGE = "Failed to get user";

    public static final String FAILED_TO_GET_PAGE_USER_MESSAGE = "Failed to get user";

    public static final String EMAIL_ALREADY_EXIST_MESSAGE = "This email already exists";

    public static final String USER_NOT_EXIST_MESSAGE ="The user with id: %s does not exist";

    public static final String USER_EMAIL_NOT_EXIST_MESSAGE ="The user with email: %s does not exist";
    public static final String USER_USERNAME_NOT_EXIST_MESSAGE ="The user with username: %s does not exist";

    public static final String USER_ALREADY_EXISTS_MESSAGE = "The user with email: %s already exists";
    public static final String INVALID_TOKEN_MESSAGE = "The token with get user is invalid";

}
