package com.dharbor.sintaxterrors.exception.constant;

public final class RoleExceptionConstant {
    public static final String NOT_NULL_ROLE_NAME = "The role name can not be null or empty";

    public static final String NOT_NULL_ID = "The id can not be null";

    public static final String FAILED_TO_CREATE_ROLE_MESSAGE = "Failed to register role";

    public static final String FAILED_TO_GET_ROLE_MESSAGE = "Failed to get role";

    public static final String FAILED_TO_GET_PAGE_ROLE_MESSAGE = "Failed to get roles";

    public static final String FAILED_TO_UPDATE_ROLE_MESSAGE = "Failed to update role";

    public static final String ROLE_NOT_EXIST_MESSAGE = "The role with id: %s does not exist";

    public static final String ROLE_ALREADY_EXISTS_MESSAGE = "The role %s already exists";
    
    public static final String FAILED_TO_VALIDATE_TEAM_ROLES_MESSAGE = "Failed to validate team roles";

    public static final String USER_PROJECT_MANAGER_NOT_FOUND = "The user associated with the project manager does not exist.";

    public static final String USER_PROJECT_MANAGER_DOES_NOT_HAVE_THE_REQUIRED_ROLE = "The user associated with the project manager does not have the required role";
}