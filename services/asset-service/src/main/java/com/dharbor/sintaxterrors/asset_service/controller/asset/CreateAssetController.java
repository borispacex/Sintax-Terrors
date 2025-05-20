package com.dharbor.sintaxterrors.asset_service.controller.asset;

import com.dharbor.sintaxterrors.asset_service.command.asset.CreateAssetCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeCommandExecutor;
import com.dharbor.sintaxterrors.asset_service.controller.constant.AssetControllerConstants;
import com.dharbor.sintaxterrors.asset_service.dto.request.asset.CreateAssetRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.CommonResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.asset.AssetResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@Tag(name = AssetControllerConstants.AssetSwaggerDoc.TAG_NAME,
        description = AssetControllerConstants.AssetSwaggerDoc.TAG_DESCRIPTION)
@RequestMapping(value = AssetControllerConstants.AssetPath.BASE_PATH)
@RequestScope
@RequiredArgsConstructor
@RestController
public class CreateAssetController {

    private final CreateAssetCommand createAssetCommand;

    @Operation(summary = AssetControllerConstants.AssetSwaggerDoc.TAG_CREATE_ASSET)
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<AssetResponse> createAsset(
            @Valid @RequestBody CreateAssetRequest request) {
        createAssetCommand.setInput(request);
        (new SafeCommandExecutor<CreateAssetRequest, AssetResponse>()).safeExecution(createAssetCommand);
        return new CommonResponse<>(
                createAssetCommand.getOutput(),
                HttpStatus.CREATED,
                HttpStatus.CREATED.name());
    }
}
