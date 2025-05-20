package com.dharbor.sintaxterrors.asset_service.controller.constant;

public class AssetControllerConstants {
    private AssetControllerConstants() {
    }

    public static class AssetPath {
        public static final String BASE_PATH = "/asset";
        public static final String BY_ID = "/{id}";
        public static final String BY_SERIAL = "/serial/{serialNumber}";
        public static final String PAGE = "/page";
        public static final String BY_ID_LIST = "/list";
        public static final String BY_WORKSPACE = "/workspace/{workspaceId}";
        public static final String BY_CATEGORY = "/category/{categoryId}";
        public static final String FINANCIAL_REPORT = "/financial-report";
        public static final String TRANSFER = "/transfer";
        public static final String STATUS = "/status";
        public static final String FINANCIAL_INFO = "/{id}/financial";
    }

    public static class AssetSwaggerDoc {
        public static final String TAG_NAME = "Asset";
        public static final String TAG_DESCRIPTION = "Operations for Asset Management";

        public static final String TAG_CREATE_ASSET = "Create asset";
        public static final String TAG_UPDATE_ASSET = "Update asset";
        public static final String TAG_GET_ASSET_BY_ID = "Get asset by id";
        public static final String TAG_GET_ASSET_BY_SERIAL = "Get asset by serial number";
        public static final String TAG_GET_ASSETS_BY_PAGE = "Get assets by page";
        public static final String TAG_GET_ASSETS_BY_LIST = "Get list of assets by ID list";
        public static final String TAG_GET_ASSETS_BY_WORKSPACE = "Get assets by workspace";
        public static final String TAG_GET_ASSETS_BY_CATEGORY = "Get assets by category";
        public static final String TAG_GET_FINANCIAL_REPORT = "Get asset financial report";
        public static final String TAG_TRANSFER_ASSET = "Transfer asset to another workspace";
        public static final String TAG_CHANGE_ASSET_STATUS = "Change asset status";
        public static final String TAG_GET_FINANCIAL_INFO = "Get asset financial information";
    }
}
