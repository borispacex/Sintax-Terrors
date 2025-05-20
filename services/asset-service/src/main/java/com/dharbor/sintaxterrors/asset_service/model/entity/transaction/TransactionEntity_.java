package com.dharbor.sintaxterrors.asset_service.model.entity.transaction;

import com.dharbor.sintaxterrors.asset_service.enums.transaction.TransactionType;
import com.dharbor.sintaxterrors.asset_service.model.entity.employee.EmployeeEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.transaction_detail.TransactionDetailEntity;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import java.time.LocalDateTime;

@StaticMetamodel(TransactionEntity.class)
public class TransactionEntity_ {

    public static volatile SingularAttribute<TransactionEntity, Long> id;

    public static volatile SingularAttribute<TransactionEntity, TransactionType> transactionType;

    public static volatile SingularAttribute<TransactionEntity, LocalDateTime> transactionDate;

    public static volatile SingularAttribute<TransactionEntity, EmployeeEntity> employee;

    public static volatile SingularAttribute<TransactionEntity, String> note;

    public static volatile ListAttribute<TransactionEntity, TransactionDetailEntity> transactionDetails;
}
