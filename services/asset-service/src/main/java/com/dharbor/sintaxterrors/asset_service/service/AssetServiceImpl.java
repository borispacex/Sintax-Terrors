package com.dharbor.sintaxterrors.asset_service.service;

import com.dharbor.sintaxterrors.asset_service.dto.request.asset.CreateAssetRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.asset.GetAssetPageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.asset.UpdateAssetRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.asset.AssetFinancialResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.asset.AssetResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.asset.AssetShortResponse;
import com.dharbor.sintaxterrors.asset_service.enums.shared.BoliviaCity;
import com.dharbor.sintaxterrors.asset_service.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.asset_service.exception.constant.AssetExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.exception.constant.CategoryExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.exception.constant.WorkspaceExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.mapper.AssetMapper;
import com.dharbor.sintaxterrors.asset_service.mapper.BaseMapper;
import com.dharbor.sintaxterrors.asset_service.model.entity.asset.AssetEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.asset.AssetEntity_;
import com.dharbor.sintaxterrors.asset_service.model.entity.category.CategoryEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.workspace.WorkspaceEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.workspace.WorkspaceEntity_;
import com.dharbor.sintaxterrors.asset_service.repository.AssetRepository;
import com.dharbor.sintaxterrors.asset_service.repository.CategoryRepository;
import com.dharbor.sintaxterrors.asset_service.repository.WorkspaceRepository;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ScopeConstant;
import com.dharbor.sintaxterrors.asset_service.utils.function.Specifications;
import com.dharbor.sintaxterrors.asset_service.utils.function.Utils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class AssetServiceImpl implements AssetService {
    private final AssetMapper assetMapper;
    private final AssetRepository assetRepository;
    private final CategoryRepository categoryRepository;
    private final WorkspaceRepository workspaceRepository;

    private CategoryEntity categoryEntity;

    private WorkspaceEntity workspaceEntity;

    private AssetEntity assetEntity;

    public AssetServiceImpl(
            AssetMapper assetMapper,
            AssetRepository assetRepository,
            CategoryRepository categoryRepository,
            WorkspaceRepository workspaceRepository) {
        this.assetMapper = assetMapper;
        this.assetRepository = assetRepository;
        this.categoryRepository = categoryRepository;
        this.workspaceRepository = workspaceRepository;
    }

    @Override
    public AssetResponse saveAsset(CreateAssetRequest assetRequest) {
        validateCreateAssetRequest(assetRequest);

        AssetEntity assetEntity = assetMapper.mapperToAssetEntity(assetRequest);
        assetEntity.setCategory(this.categoryEntity);

        if (assetRequest.getWorkspaceId() != null) {
            assetEntity.setWorkspace(this.workspaceEntity);
        }

        return assetMapper.mapperToAssetResponse(assetRepository.save(assetEntity));
    }

    @Override
    public boolean existsBySerialNumber(String serialNumber) {
        return assetRepository.findBySerialNumber(serialNumber).isPresent();
    }

    @Override
    public AssetResponse updateAsset(UpdateAssetRequest assetRequest) {
        validateUpdateAssetRequest(assetRequest);

        AssetEntity preEntity = this.assetEntity;
        assetMapper.updateAssetEntity(assetRequest, preEntity);

        preEntity.setCategory(this.categoryEntity);
        preEntity.setWorkspace(assetRequest.getWorkspaceId() != null ? this.workspaceEntity : null);

        return assetMapper.mapperToAssetResponse(assetRepository.save(preEntity));
    }

    @Override
    public boolean existsById(Long id) {
        return assetRepository.findById(id).isPresent();
    }

    @Override
    public AssetResponse getAssetResponseById(Long id) {
        AssetEntity entity = getAssetEntityById(id);
        return assetMapper.mapperToAssetResponse(entity);
    }

    @Override
    public AssetEntity getAssetEntityById(Long id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new ProcessErrorException(AssetExceptionConstants.ASSET_NOT_FOUND));
    }

    @Override
    public AssetResponse getAssetBySerialNumber(String serialNumber) {
        return assetRepository.findBySerialNumber(serialNumber)
                .map(assetMapper::mapperToAssetResponse)
                .orElseThrow(() -> new ProcessErrorException(AssetExceptionConstants.ASSET_NOT_FOUND));
    }

    @Override
    public void validateCreateAssetRequest(CreateAssetRequest request) {
        if (existsBySerialNumber(request.getSerialNumber())) {
            throw new ProcessErrorException(AssetExceptionConstants.DUPLICATE_SERIAL_NUMBER);
        }

        this.categoryEntity = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ProcessErrorException(CategoryExceptionConstants.CATEGORY_NOT_FOUND));

        if (request.getWorkspaceId() != null) {
            this.workspaceEntity = workspaceRepository.findById(request.getWorkspaceId())
                    .orElseThrow(() -> new ProcessErrorException(WorkspaceExceptionConstants.WORKSPACE_NOT_FOUND));
        }
    }

    @Override
    public AssetShortResponse getShortAssetById(Long id) {
        return assetRepository.findById(id)
                .map(assetMapper::mapperToShortAssetResponse)
                .orElseThrow(() -> new ProcessErrorException(AssetExceptionConstants.ASSET_NOT_FOUND));
    }

    @Override
    public void validateUpdateAssetRequest(UpdateAssetRequest request) {
        this.assetEntity = getAssetEntityById(request.getId());

        if (isSerialNumberRegistered(request.getSerialNumber(), request.getId())) {
            throw new ProcessErrorException(AssetExceptionConstants.DUPLICATE_SERIAL_NUMBER);
        }

        this.categoryEntity = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ProcessErrorException(CategoryExceptionConstants.CATEGORY_NOT_FOUND));

        if (request.getWorkspaceId() != null) {
            this.workspaceEntity = workspaceRepository.findById(request.getWorkspaceId())
                    .orElseThrow(() -> new ProcessErrorException(WorkspaceExceptionConstants.WORKSPACE_NOT_FOUND));
        }
    }

    @Override
    public PaginationResponse<AssetResponse> getAssetPage(GetAssetPageRequest request) {
        List<AssetResponse> assets = new ArrayList<>();
        Sort sort = Specifications.buildSorting(request.getOrder(), request.getDirection());
        PageRequest pageRequest = PageRequest.of(request.getPage() - 1, request.getSize(), sort);

        Page<AssetEntity> pageResult = assetRepository.findAll(buildSpecification(request), pageRequest);

        assets.addAll(
                pageResult.getContent().stream()
                        .map(assetMapper::mapperToAssetResponse)
                        .collect(Collectors.toList())
        );

        PaginationResponse<AssetResponse> response = new PaginationResponse<>();
        response.setItems(assets);
        BaseMapper.setPaginationMetaData(response, pageResult);
        return response;
    }

    private Specification<AssetEntity> buildSpecification(GetAssetPageRequest request) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (!Utils.isNull(request.getSerialNumber())) {
                predicates.add(builder.like(
                        builder.lower(root.get(AssetEntity_.serialNumber)),
                        "%" + request.getSerialNumber().toLowerCase() + "%"
                ));
            }

            if (!Utils.isNull(request.getModel())) {
                predicates.add(builder.like(
                        builder.lower(root.get(AssetEntity_.model)),
                        "%" + request.getModel().toLowerCase() + "%"
                ));
            }

            if (!Utils.isNull(request.getStatus())) {
                predicates.add(builder.equal(root.get(AssetEntity_.status), request.getStatus()));
            }

            if (!Utils.isNull(request.getCategoryId())) {
                predicates.add(builder.equal(root.get(AssetEntity_.category).get("id"), request.getCategoryId()));
            }

            if (!Utils.isNull(request.getWorkspaceId())) {
                predicates.add(builder.equal(root.get(AssetEntity_.workspace).get("id"), request.getWorkspaceId()));
            }

            if (!Utils.isNull(request.getWarrantyExpirationFrom()) && !Utils.isNull(request.getWarrantyExpirationTo())) {
                predicates.add(builder.between(
                        root.get(AssetEntity_.warrantyExpiration),
                        request.getWarrantyExpirationFrom(),
                        request.getWarrantyExpirationTo()
                ));
            }

            if (!Utils.isNull(request.getUnderWarranty())) {
                LocalDate now = LocalDate.now();
                if (request.getUnderWarranty()) {
                    predicates.add(builder.greaterThanOrEqualTo(root.get(AssetEntity_.warrantyExpiration), now));
                } else {
                    predicates.add(builder.lessThan(root.get(AssetEntity_.warrantyExpiration), now));
                }
            }

            if (!Utils.isNull(request.getCity())) {
                predicates.add(builder.equal(root.get(AssetEntity_.city), request.getCity()));
            }

            if (!Utils.isNull(request.getSearch())) {
                predicates.add(builder.equal(
                        root.get(AssetEntity_.city),
                        BoliviaCity.valueOf(request.getSearch().toUpperCase())
                ));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public List<AssetResponse> getAssetsByIdList(List<Long> ids) {
        return assetRepository.findByIdIn(ids).stream()
                .map(assetMapper::mapperToAssetResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssetResponse> getAssetsByWorkspaceId(Long workspaceId) {
        return assetRepository.findByWorkspaceId(workspaceId).stream()
                .map(assetMapper::mapperToAssetResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssetResponse> getAssetsByCategoryId(Long categoryId) {
        return assetRepository.findByCategoryId(categoryId).stream()
                .map(assetMapper::mapperToAssetResponse)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isSerialNumberRegistered(String serialNumber, Long excludeId) {
        Optional<AssetEntity> asset = assetRepository.findBySerialNumber(serialNumber);
        return asset.isPresent() && !asset.get().getId().equals(excludeId);
    }

    @Override
    public AssetFinancialResponse getAssetFinancialInfo(Long assetId) {
        AssetEntity asset = assetRepository.findById(assetId)
                .orElseThrow(() -> new EntityNotFoundException("Asset not found with id: " + assetId));

        return assetMapper.mapperToFinancialResponse(asset);
    }
}
