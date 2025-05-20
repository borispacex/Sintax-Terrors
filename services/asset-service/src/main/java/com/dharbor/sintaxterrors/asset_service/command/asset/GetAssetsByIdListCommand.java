package com.dharbor.sintaxterrors.asset_service.command.asset;

import com.dharbor.sintaxterrors.asset_service.command.spec.PostExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.PreExecutorCommand;
import com.dharbor.sintaxterrors.asset_service.command.spec.SafeAbstractCommand;
import com.dharbor.sintaxterrors.asset_service.dto.response.asset.AssetResponse;
import com.dharbor.sintaxterrors.asset_service.exception.ProcessErrorException;
import com.dharbor.sintaxterrors.asset_service.exception.constant.AssetExceptionConstants;
import com.dharbor.sintaxterrors.asset_service.service.AssetService;
import com.dharbor.sintaxterrors.asset_service.utils.constant.ScopeConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@Scope(ScopeConstant.SCOPE_PROTOTYPE)
public class GetAssetsByIdListCommand
        extends SafeAbstractCommand<List<Long>, List<AssetResponse>>
        implements PreExecutorCommand, PostExecutorCommand {

    private final AssetService assetService;

    @Override
    public void execute() {
        log.info("GetAssetsByIdListCommand - Execute");
        this.output = assetService.getAssetsByIdList(this.input);
    }

    @Override
    public void preExecute() {
        log.info("GetAssetsByIdListCommand - PreExecute");
        if (this.input.isEmpty()) {
            throw new ProcessErrorException(AssetExceptionConstants.NO_EMPTY_LIST_MESSAGE);
        }
    }

    @Override
    public void postExecute() {
        log.info("GetAssetsByIdListCommand - PostExecute");
        if (this.output.isEmpty()) {
            throw new ProcessErrorException(AssetExceptionConstants.FAILED_TO_GET_ASSET);
        }
    }
}
