package com.dharbor.sintaxterrors.asset_service.repository;

import com.dharbor.sintaxterrors.asset_service.model.entity.employee.EmployeeEntity;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends
        JpaRepository<EmployeeEntity, Long>,
        JpaSpecificationExecutor<EmployeeEntity> {
    Optional<EmployeeEntity> findById(@NonNull Integer employeeId);

    Optional<EmployeeEntity> findByCi(@NonNull String employeeCi);

    Page<EmployeeEntity> findAll(Pageable pageable);

    List<EmployeeEntity> findAll(Specification<EmployeeEntity> name);

    Optional<EmployeeEntity> findByUserId(@NonNull Integer employeeId);

    @Query("SELECT e FROM EmployeeEntity e WHERE e.id IN :idList")
    List<EmployeeEntity> findByIdIn(@Param("idList") List<Integer> idList);

    @Query("SELECT DISTINCT e FROM EmployeeEntity e INNER JOIN e.transactions t")
    Page<EmployeeEntity> findAllWithTransactions(Pageable pageable);
}