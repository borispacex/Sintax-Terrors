package com.dharbor.sintaxterrors.asset_service.controller.constant;

public class TransactionControllerConstants {
    private TransactionControllerConstants() {
    }

    public static class TransactionPath {
        public static final String BASE_PATH = "/transaction";
        public static final String BY_ID = "/{id}";
        public static final String DETAILS = "/{id}/details";
        public static final String ASSET_HISTORY = "/asset/{assetId}/history";
        public static final String EMPLOYEE_ASSETS = "/employee/{employeeId}/assets";
        public static final String EMPLOYEE_HISTORY = "/employee/{employeeId}/history";
    }

    public static class TransactionSwaggerDoc {
        public static final String TAG_NAME = "Transaction";
        public static final String TAG_DESCRIPTION = "Operations for Asset Transaction Management";

        public static final String TAG_CREATE_ASSIGNMENT = "Create asset assignment";
        public static final String TAG_CREATE_RETURN = "Create asset return";
        public static final String TAG_CREATE_EXCHANGE = "Create asset exchange";
        public static final String TAG_GET_TRANSACTION_BY_ID = "Get transaction by id";
        public static final String TAG_GET_TRANSACTION_DETAILS = "Get transaction details";
        public static final String TAG_GET_ASSET_HISTORY = "Get asset transaction history";
        public static final String TAG_GET_EMPLOYEE_ASSETS = "Get currently assigned assets by employee";
        public static final String TAG_GET_TRANSACTION_BY_PAGE = "Get transaction by page";
        public static final String TAG_GET_EMPLOYEE_HISTORY = "Get transaction history by employee";
    }
}