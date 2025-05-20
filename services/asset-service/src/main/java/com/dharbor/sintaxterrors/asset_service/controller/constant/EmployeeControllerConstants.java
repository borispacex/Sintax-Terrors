package com.dharbor.sintaxterrors.asset_service.controller.constant;

public class EmployeeControllerConstants {
    private EmployeeControllerConstants() {
    }

    public static class EmployeePath {

        public static final String BASE_PATH = "/employee";
        public static final String BY_ID_LIST = "/list";

    }

    public static class EmployeeSwaggerDoc {

        public static final String TAG_NAME = "Employee";

        public static final String TAG_DESCRIPTION = "Operations for Employee";

        public static final String TAG_CREATE_EMPLOYEE = "Create employee";

        public static final String TAG_UPDATE_EMPLOYEE = "Update employee";

        public static final String TAG_GET_EMPLOYEES_BY_PAGE = "Get employees by page";

        public static final String TAG_GET_EMPLOYEES_WITH_TRANSACTION_PAGE = "Get employees with transaction page";

        public static final String TAG_GET_EMPLOYEE_BY_ID = "Get employee by id";

        public static final String TAG_GET_EMPLOYEES_BY_LIST = "Get list of employees by id list";

        public static final String TAG_GET_EMPLOYEE_PDF_REPORT = "Get an employee report in pdf format";

        public static final String TAG_GET_EMPLOYEE_EXCEL_REPORT = "Get an employee report in excel format";

        public static final String TAG_GET_EMPLOYEES_BY_ID_USER = "Get employee by id user";

        public static final String TAG_GET_SHORT_EMPLOYEES_BY_USER_ID = "Get short employee by user id";
    }
}