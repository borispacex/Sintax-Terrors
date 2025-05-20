package com.dharbor.sintaxterrors.asset_service.service;


import com.dharbor.sintaxterrors.asset_service.dto.request.category.CreateCategoryRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.category.GetCategoryPageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.category.UpdateCategoryRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.category.CategoryResponse;
import com.dharbor.sintaxterrors.asset_service.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.asset_service.exception.constant.CategoryExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.mapper.BaseMapper;
import com.dharbor.sintaxterrors.asset_service.mapper.CategoryMapper;
import com.dharbor.sintaxterrors.asset_service.model.entity.category.CategoryEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.category.CategoryEntity_;
import com.dharbor.sintaxterrors.asset_service.repository.CategoryRepository;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ScopeConstant;
import com.dharbor.sintaxterrors.asset_service.utils.function.Specifications;
import com.dharbor.sintaxterrors.asset_service.utils.function.Utils;
import jakarta.persistence.criteria.Predicate;
import org.springframework.context.annotation.Scope;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    private CategoryEntity preEntity;

    public CategoryServiceImpl(
            CategoryMapper categoryMapper,
            CategoryRepository categoryRepository
    ) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse saveCategory(CreateCategoryRequest request) {
        if (existsByName(request.getName(), null)) {
            throw new ProcessErrorException(CategoryExceptionConstants.CATEGORY_ALREADY_EXISTS);
        }

        CategoryEntity newEntity = categoryMapper.mapperToCategoryEntity(request);
        return categoryMapper.mapperToCategoryResponse(categoryRepository.save(newEntity));
    }

    @Override
    public void validateCreateCategoryRequest(CreateCategoryRequest createCategoryRequest) {
        if (existsByName(createCategoryRequest.getName(), null)) {
            throw new ProcessErrorException(CategoryExceptionConstants.CATEGORY_ALREADY_EXISTS);
        }
    }

    @Override
    public void validateUpdateCategoryRequest(UpdateCategoryRequest updateCategoryRequest) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findById(updateCategoryRequest.getId());

        if (!categoryEntity.isPresent()) {
            throw new ProcessErrorException(CategoryExceptionConstants.CATEGORY_NOT_FOUND);
        }

        preEntity = categoryEntity.get();
    }

    @Override
    public Boolean existsByName(String name, Long id) {
        Optional<CategoryEntity> categoryEntity = categoryRepository.findByName(name);
        return categoryEntity.isPresent() && !categoryEntity.get().getId().equals(id);
    }

    @Override
    public CategoryResponse updateCategory(UpdateCategoryRequest request) {
        preEntity = getCategoryById(request.getId());

        if (existsByName(request.getName(), request.getId())) {
            throw new ProcessErrorException(CategoryExceptionConstants.DUPLICATE_CATEGORY_NAME);
        }

        categoryMapper.updateCategoryEntity(request, preEntity);
        return categoryMapper.mapperToCategoryResponse(categoryRepository.save(preEntity));
    }

    @Override
    public CategoryEntity getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ProcessErrorException(
                        CategoryExceptionConstants.CATEGORY_NOT_FOUND + categoryId));
    }

    @Override
    public CategoryResponse findCategoryById(Long categoryId) {
        CategoryEntity entity = getCategoryById(categoryId);
        return categoryMapper.mapperToCategoryResponse(entity);
    }

    @Override
    public CategoryResponse deleteCategory(Long id) {
        CategoryEntity categoryEntity = getCategoryById(id);
        categoryRepository.delete(categoryEntity);
        return categoryMapper.mapperToCategoryResponse(categoryEntity);
    }

    @Override
    public PaginationResponse<CategoryResponse> getCategoryPage(GetCategoryPageRequest request) {
        List<CategoryResponse> categories = new ArrayList<>();
        Sort sort = Specifications.buildSorting(request.getOrder(), request.getDirection());
        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), sort);

        Page<CategoryEntity> pageResult = categoryRepository.findAll(buildSpecification(request), pageRequest);

        categories.addAll(
                pageResult.getContent().stream()
                        .map(categoryMapper::mapperToCategoryResponse)
                        .collect(Collectors.toList())
        );

        PaginationResponse<CategoryResponse> response = new PaginationResponse<>();
        response.setItems(categories);
        BaseMapper.setPaginationMetaData(response, pageResult);
        return response;
    }

    private Specification<CategoryEntity> buildSpecification(GetCategoryPageRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!Utils.isNull(request.getName())) {
                String[] words = request.getName().split(" ");
                for (String word : words) {
                    predicates.add(criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(CategoryEntity_.name)),
                            "%" + word.toLowerCase() + "%"));
                }
            }

            if (!Utils.isNull(request.getIsDepreciable())) {
                predicates.add(criteriaBuilder.equal(
                        root.get(CategoryEntity_.isDepreciable),
                        request.getIsDepreciable()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
