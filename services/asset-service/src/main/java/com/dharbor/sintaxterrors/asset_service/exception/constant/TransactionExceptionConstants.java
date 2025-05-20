package com.dharbor.sintaxterrors.asset_service.exception.constant;


public final class TransactionExceptionConstants {
    private TransactionExceptionConstants() {}

    public static final String NOT_NULL_TRANSACTION_ID = "Transaction ID cannot be null";
    public static final String NOT_NULL_EMPLOYEE_ID = "Employee ID cannot be null";
    public static final String NOT_NULL_ASSET_IDS = "Asset IDs cannot be null";
    public static final String EMPTY_ASSET_LIST = "At least one asset must be specified";
    public static final String INVALID_TRANSACTION_TYPE = "Invalid transaction type";
    public static final String INVALID_DIRECTION = "Invalid direction (must be IN or OUT)";
    public static final String MAX_LENGTH_REASON = "Reason cannot exceed 255 characters";
    public static final String MAX_LENGTH_NOTE = "Note cannot exceed 500 characters";

    public static final String TRANSACTION_NOT_FOUND = "Transaction not found";
    public static final String TRANSACTION_DETAIL_NOT_FOUND = "Transaction detail not found";
    public static final String ASSET_NOT_AVAILABLE = "Asset is not available for assignment";
    public static final String ASSET_NOT_ASSIGNED = "Asset is not currently assigned";
    public static final String ASSET_ALREADY_ASSIGNED = "Asset is already assigned to another employee";
    public static final String INVALID_EXCHANGE = "Exchange must include both returned and new assets";
    public static final String EMPTY_EXCHANGE_LIST = "Both returned and new assets must be specified";

    public static final String FAILED_TO_CREATE_ASSIGNMENT = "Failed to create assignment transaction";
    public static final String FAILED_TO_CREATE_RETURN = "Failed to create return transaction";
    public static final String FAILED_TO_CREATE_EXCHANGE = "Failed to create exchange transaction";
    public static final String FAILED_TO_GET_TRANSACTION = "Failed to get transaction";
    public static final String FAILED_TO_GET_TRANSACTION_DETAILS = "Failed to get transaction details";
    public static final String FAILED_TO_GET_TRANSACTION_HISTORY = "Failed to get transaction history";
    public static final String FAILED_TO_GET_ASSIGNED_ASSETS = "Failed to get currently assigned assets";
    public static final String FAILED_TO_GET_TRANSACTION_PAGE = "Failed to get transaction page";
    public static final String TRANSACTION_PROCESSING_ERROR = "Error processing transaction";
    public static final String TRANSACTION_ROLLBACK_ERROR = "Error during transaction rollback";
    public static final String TRANSACTION_VALIDATION_ERROR = "Transaction validation failed";

    public static final String INVALID_TRANSACTION_STATE = "Transaction is in an invalid state for this operation";
    public static final String TRANSACTION_ALREADY_COMPLETED = "Transaction is already completed";
    public static final String TRANSACTION_CANNOT_BE_MODIFIED = "Transaction cannot be modified after creation";

    public static final String ASSET_STATUS_INCONSISTENCY = "Asset status is inconsistent with transaction type";
    public static final String TRANSACTION_TYPE_MISMATCH = "Transaction type doesn't match expected operation";
    public static final String DIRECTION_MISMATCH = "Direction doesn't match transaction type";
}
