package com.dharbor.sintaxterrors.asset_service.service;

import com.dharbor.sintaxterrors.asset_service.dto.request.category.CreateCategoryRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.category.GetCategoryPageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.category.UpdateCategoryRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.category.CategoryResponse;
import com.dharbor.sintaxterrors.asset_service.model.entity.category.CategoryEntity;

public interface CategoryService {

    CategoryResponse saveCategory(CreateCategoryRequest createCategoryRequest);

    void validateCreateCategoryRequest(CreateCategoryRequest createCategoryRequest);

    void validateUpdateCategoryRequest(UpdateCategoryRequest updateCategoryRequest);

    Boolean existsByName(String categoryName, Long id);

    CategoryResponse updateCategory(UpdateCategoryRequest updateCategoryRequest);

    CategoryEntity getCategoryById(Long categoryId);

    CategoryResponse findCategoryById(Long input);

    CategoryResponse deleteCategory(Long id);

    PaginationResponse<CategoryResponse> getCategoryPage(GetCategoryPageRequest input);
}
