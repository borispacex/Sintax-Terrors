package com.dharbor.sintaxterrors.asset_service.controller.asset;

import com.dharbor.sintaxterrors.asset_service.command.asset.GetAssetByIdCommand;
import com.dharbor.sintaxterrors.asset_service.command.asset.GetAssetBySerialNumberCommand;
import com.dharbor.sintaxterrors.asset_service.command.asset.GetAssetPageCommand;
import com.dharbor.sintaxterrors.asset_service.command.asset.GetAssetsByIdListCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.controller.constant.AssetControllerConstants;
import com.dharbor.sintaxterrors.asset_service.dto.request.asset.GetAssetPageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonPaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.asset.AssetResponse;
import com.dharbor.sintaxterrors.asset_service.enums.asset.AssetStatus;
import com.dharbor.sintaxterrors.asset_service.enums.shared.BoliviaCity;
import com.dharbor.sintaxterrors.asset_service.enums.shared.SortDirection;
import com.dharbor.sintaxterrors.asset_service.utils.constant.FilterConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDate;
import java.util.List;

@Tag(name = AssetControllerConstants.AssetSwaggerDoc.TAG_NAME,
        description = AssetControllerConstants.AssetSwaggerDoc.TAG_DESCRIPTION)
@RequestMapping(value = AssetControllerConstants.AssetPath.BASE_PATH)
@RequestScope
@RestController
@RequiredArgsConstructor
public class GetAssetController {

    private final GetAssetByIdCommand getAssetByIdCommand;
    private final GetAssetBySerialNumberCommand getAssetBySerialNumberCommand;
    private final GetAssetPageCommand getAssetPageCommand;
    private final GetAssetsByIdListCommand getAssetsByIdListCommand;

    @Operation(summary = AssetControllerConstants.AssetSwaggerDoc.TAG_GET_ASSET_BY_ID)
    @GetMapping(AssetControllerConstants.AssetPath.BY_ID)
    public CommonResponse<AssetResponse> getAssetById(@PathVariable Long id) {
        getAssetByIdCommand.setInput(id);
        (new SafeCommandExecutor<Long, AssetResponse>()).safeExecution(getAssetByIdCommand);
        return new CommonResponse<>(getAssetByIdCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.name());
    }

    @Operation(summary = AssetControllerConstants.AssetSwaggerDoc.TAG_GET_ASSET_BY_SERIAL)
    @GetMapping(AssetControllerConstants.AssetPath.BY_SERIAL)
    public CommonResponse<AssetResponse> getAssetBySerialNumber(@PathVariable String serialNumber) {
        getAssetBySerialNumberCommand.setInput(serialNumber);
        (new SafeCommandExecutor<String, AssetResponse>()).safeExecution(getAssetBySerialNumberCommand);
        return new CommonResponse<>(getAssetBySerialNumberCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.name());
    }

    @Operation(summary = AssetControllerConstants.AssetSwaggerDoc.TAG_GET_ASSETS_BY_PAGE)
    @GetMapping
    public CommonPaginationResponse<AssetResponse> getAssetPage(
            @RequestParam(defaultValue = FilterConstant.DEFAULT_PAGE) Integer page,
            @RequestParam(defaultValue = FilterConstant.AMOUNT_MAX_BY_PAGE) Integer size,
            @RequestParam(required = false) String order,
            @RequestParam(defaultValue = FilterConstant.DEFAULT_FILTER_DIRECTION) SortDirection direction,
            @RequestParam(required = false) AssetStatus status,
            @RequestParam(required = false) String serialNumber,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String manufacturer,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long workspaceId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate warrantyFrom,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate warrantyTo,
            @RequestParam(required = false) BoliviaCity city,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Boolean underWarranty) {

        GetAssetPageRequest request = new GetAssetPageRequest(
                page,
                size,
                order,
                direction,
                serialNumber,
                model,
                manufacturer,
                status,
                categoryId,
                workspaceId,
                warrantyFrom,
                warrantyTo,
                underWarranty,
                search,
                city
        );
        getAssetPageCommand.setInput(request);
        (new SafeCommandExecutor<GetAssetPageRequest, PaginationResponse<AssetResponse>>())
                .safeExecution(getAssetPageCommand);
        return new CommonPaginationResponse<>(getAssetPageCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.name());
    }

    @Operation(summary = AssetControllerConstants.AssetSwaggerDoc.TAG_GET_ASSETS_BY_LIST)
    @PostMapping(AssetControllerConstants.AssetPath.BY_ID_LIST)
    public CommonResponse<List<AssetResponse>> getAssetsByIdList(@RequestBody List<Long> idList) {
        getAssetsByIdListCommand.setInput(idList);
        (new SafeCommandExecutor<List<Long>, List<AssetResponse>>()).safeExecution(getAssetsByIdListCommand);
        return new CommonResponse<>(getAssetsByIdListCommand.getOutput(), HttpStatus.OK, HttpStatus.OK.name());
    }
}
