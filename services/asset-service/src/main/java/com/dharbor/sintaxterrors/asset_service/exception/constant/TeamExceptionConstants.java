package com.dharbor.sintaxterrors.asset_service.exception.constant;

public class TeamExceptionConstants {

    public static final String NOT_NULL_ID_MESSAGE = "Id cannot be null";

    public static final String NOT_NULL_PROJECT_MANAGER_ID_MESSAGE = "The project_manager id cannot be null";

    public static final String NOT_EMPTY_NAME_MESSAGE = "The name can not be empty";

    public static final String FAILED_TO_GET_TEAM = "Failed to get team";

    public static final String FAILED_TO_CREATE_TEAM = "Failed to create team";

    public static final String FAILED_TO_UPDATE_TEAM = "Failed to update team";

    public static final String TEAM_NOT_FOUND = "The team does not exist";

    public static final String PROJECT_MANAGER_NOT_FOUND = "The Project manager with id: %s does not exist";

    public static final String TEAM_ALREADY_REGISTERED = "This team already exist";

    public static final String PROJECT_MANAGER_IS_ASSIGNED_TO_ANOTHER_TEAM = "The project manager is already assigned to another team with the same role";

    public static final String CANNOT_BE_TWO_OFFICIAL_PROJECT_MANAGERS = "Two official project managers cannot be in the same Team";
}
