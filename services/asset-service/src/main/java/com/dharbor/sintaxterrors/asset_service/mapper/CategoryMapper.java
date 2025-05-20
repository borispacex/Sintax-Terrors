package com.dharbor.sintaxterrors.asset_service.mapper;

import com.dharbor.sintaxterrors.asset_service.dto.request.category.CreateCategoryRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.category.UpdateCategoryRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.category.CategoryResponse;
import com.dharbor.sintaxterrors.asset_service.model.entity.category.CategoryEntity;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ResponseConstant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        imports = {ResponseConstant.SuccessResponse.class})
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "id", ignore = true)
    CategoryEntity mapperToCategoryEntity(CreateCategoryRequest source);

    CategoryResponse mapperToCategoryResponse(CategoryEntity source);

    @Mapping(target = "id", ignore = true)
    void updateCategoryEntity(UpdateCategoryRequest source, @MappingTarget CategoryEntity target);
}