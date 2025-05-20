package com.dharbor.sintaxterrors.asset_service.service;


import com.dharbor.sintaxterrors.asset_service.dto.request.request.CreateRequestRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.request.GetRequestPageRequest;
import com.dharbor.sintaxterrors.asset_service.dto.request.request.UpdateRequestRequest;
import com.dharbor.sintaxterrors.asset_service.dto.response.PaginationResponse;
import com.dharbor.sintaxterrors.asset_service.dto.response.request.RequestResponse;

public interface RequestService {
    RequestResponse saveRequest(CreateRequestRequest request);

    RequestResponse deleteRequest(Integer teamId);

    void validateCreateRequestRequest(CreateRequestRequest request);

    void validateUpdateRequestRequest(UpdateRequestRequest team);

    RequestResponse updateRequest(UpdateRequestRequest request);

    RequestResponse findRequestResponseById(Integer teamId);

    PaginationResponse<RequestResponse> getRequestPage(GetRequestPageRequest request);
}