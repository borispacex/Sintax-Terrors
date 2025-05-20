package com.dharbor.sintaxterrors.asset_service.exception.constant;

public final class CategoryExceptionConstants {
    private CategoryExceptionConstants() {}

    public static final String NOT_NULL_ID_MESSAGE = "Id cannot be null";

    public static final String NOT_NULL_NAME_MESSAGE = "Category name cannot be null";

    public static final String NOT_EMPTY_NAME_MESSAGE = "Category name cannot be empty";

    public static final String MAX_LENGTH_NAME_MESSAGE = "Category name cannot exceed {max} characters";

    public static final String NOT_NULL_DEPRECIABLE_MESSAGE = "Depreciable flag cannot be null";

    public static final String NOT_NULL_USEFUL_LIFE_MESSAGE = "Useful life months cannot be null";

    public static final String MIN_USEFUL_LIFE_MESSAGE = "Useful life months must be zero or positive";

    public static final String CATEGORY_NOT_FOUND = "Category not found";

    public static final String CATEGORY_ALREADY_EXISTS = "Category already exists";

    public static final String FAILED_TO_CREATE_CATEGORY = "Failed to create category";

    public static final String FAILED_TO_UPDATE_CATEGORY = "Failed to update category";

    public static final String FAILED_TO_DELETE_CATEGORY = "Failed to delete category";

    public static final String FAILED_TO_GET_CATEGORY = "Failed to get category";

    public static final String CATEGORY_IN_USE = "Category cannot be deleted because it's associated with existing assets";

    public static final String INVALID_CATEGORY_ID = "Invalid category ID provided";

    public static final String DUPLICATE_CATEGORY_NAME = "Category name already exists";

}
