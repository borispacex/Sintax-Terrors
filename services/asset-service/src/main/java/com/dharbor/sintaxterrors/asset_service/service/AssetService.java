package com.dharbor.sintaxterrors.asset_service.service;

import com.dharbor.sintaxterrors.asset_service.dto.request.asset.CreateAssetRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.asset.GetAssetPageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.asset.UpdateAssetRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.asset.AssetFinancialResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.asset.AssetResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.asset.AssetShortResponse;
import com.dharbor.sintaxterrors.asset_service.model.entity.asset.AssetEntity;

import java.util.List;

public interface AssetService {
    AssetResponse saveAsset(CreateAssetRequest assetRequest);

    boolean existsBySerialNumber(String serialNumber);

    AssetResponse updateAsset(UpdateAssetRequest assetRequest);

    boolean existsById(Long id);

    AssetResponse getAssetResponseById(Long id);

    AssetEntity getAssetEntityById(Long id);

    AssetResponse getAssetBySerialNumber(String serialNumber);

    void validateCreateAssetRequest(CreateAssetRequest request);

    AssetShortResponse getShortAssetById(Long id);

    void validateUpdateAssetRequest(UpdateAssetRequest request);

    PaginationResponse<AssetResponse> getAssetPage(GetAssetPageRequest request);

    List<AssetResponse> getAssetsByIdList(List<Long> ids);

    List<AssetResponse> getAssetsByWorkspaceId(Long workspaceId);

    List<AssetResponse> getAssetsByCategoryId(Long categoryId);

    boolean isSerialNumberRegistered(String serialNumber, Long excludeId);
    AssetFinancialResponse getAssetFinancialInfo(Long assetId);
}