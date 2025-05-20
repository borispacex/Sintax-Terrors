package com.dharbor.sintaxterrors.service;

import com.dharbor.sintaxterrors.config.FeignAuthConfig;
import com.dharbor.sintaxterrors.dto.CommonResponse;
import com.dharbor.sintaxterrors.dto.response.user.EmployeeShortResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "asset-service", path = "/employee", configuration = FeignAuthConfig.class)
public interface EmployeeFeingService {

    @GetMapping("/user/{id}")
    CommonResponse<EmployeeShortResponse> getEmployeeByUserId(@PathVariable("id") Integer id);
}