package com.dharbor.sintaxterrors.asset_service.command.asset;

import com.dharbor.sintaxterrors.asset_service.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.asset_service.dto.request.asset.GetAssetPageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.asset.AssetResponse;
import com.dharbor.sintaxterrors.asset_service.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.asset_service.exception.constant.AssetExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.service.AssetService;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ScopeConstant;
import com.dharbor.sintaxterrors.asset_service.utils.function.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class GetAssetPageCommand
        extends SafeAbstractCommand<GetAssetPageRequest, PaginationResponse<AssetResponse>>
        implements PostExecutorCommand {

    private final AssetService assetService;

    @Override
    public void execute() {
        log.info("GetAssetPageCommand - Execute");
        this.output = assetService.getAssetPage(this.input);
    }

    @Override
    public void postExecute() {
        log.info("GetAssetPageCommand - PostExecute");
        if (Utils.isNull(this.output)) {
            throw new ProcessErrorException(AssetExceptionConstants.FAILED_TO_GET_ASSET_PAGE);
        }
    }
}
