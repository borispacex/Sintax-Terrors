package com.dharbor.sintaxterrors.asset_service.exception.constant;

public final class AssetExceptionConstants {
    private AssetExceptionConstants() {}

    public static final String NOT_NULL_ID_MESSAGE = "Asset ID cannot be null";

    public static final String NOT_BLANK_SERIAL_NUMBER_MESSAGE = "Serial number cannot be blank";

    public static final String MAX_LENGTH_SERIAL_NUMBER_MESSAGE = "Serial number cannot exceed 100 characters";

    public static final String MAX_LENGTH_MODEL_MESSAGE = "Model cannot exceed 100 characters";

    public static final String MAX_LENGTH_MANUFACTURER_MESSAGE = "Manufacturer cannot exceed 100 characters";

    public static final String NOT_NULL_CATEGORY_MESSAGE = "Category cannot be null";

    public static final String INVALID_PURCHASE_DATE_MESSAGE = "Purchase date must be in the past or present";

    public static final String INVALID_PURCHASE_COST_MESSAGE = "Purchase cost must be positive";

    public static final String INVALID_CURRENT_VALUE_MESSAGE = "Current value must be positive";

    public static final String NOT_BLANK_STATUS_MESSAGE = "Status cannot be blank";

    public static final String ASSET_NOT_FOUND = "Asset not found";

    public static final String DUPLICATE_SERIAL_NUMBER = "Serial number already exists";

    public static final String FAILED_TO_CREATE_ASSET = "Failed to create asset";

    public static final String FAILED_TO_GET_ASSET = "Failed to get asset";

    public static final String FAILED_TO_GET_ASSET_PAGE = "Failed to get asset page";

    public static final String NO_EMPTY_LIST_MESSAGE = "The asset ids list cannot be empty";

    public static final String FAILED_TO_UPDATE_ASSET = "Failed to update asset";

    public static final String INVALID_CATEGORY = "Invalid category specified";

    public static final String INVALID_WORKSPACE = "Invalid workspace specified";

    public static final String ASSET_UPDATE_CONFLICT = "Asset update conflict";

}