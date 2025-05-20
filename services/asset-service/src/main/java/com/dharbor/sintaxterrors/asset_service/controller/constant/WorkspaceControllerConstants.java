package com.dharbor.sintaxterrors.asset_service.controller.constant;

public final class WorkspaceControllerConstants {
    public static class WorkspacePath {
        public static final String BASE_PATH = "/workspace";
        public static final String BY_ID_LIST = "/list";
    }

    public static class WorkspaceSwaggerDoc {

        public static final String TAG_NAME = "Workspace";

        public static final String TAG_DESCRIPTION = "Operations for workspace";

        public static final String TAG_CREATE_WORKSPACE = "Create workspace";

        public static final String TAG_UPDATE_WORKSPACE = "Update workspace";

        public static final String TAG_GET_WORKSPACES_BY_PAGE = "Get workspaces by page";

        public static final String TAG_GET_WORKSPACE_BY_ID = "Get workspace by id";

        public static final String TAG_GET_WORKSPACES_BY_LIST = "Get list of workspaces by id list";

        public static final String TAG_DELETE_WORKSPACE = "Delete workspace";
    }
}
