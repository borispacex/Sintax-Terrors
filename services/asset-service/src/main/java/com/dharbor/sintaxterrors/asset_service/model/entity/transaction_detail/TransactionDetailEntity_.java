package com.dharbor.sintaxterrors.asset_service.model.entity.transaction_detail;


import com.dharbor.sintaxterrors.asset_service.model.entity.asset.AssetEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.transaction.TransactionEntity;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TransactionDetailEntity.class)
public class TransactionDetailEntity_ {

    public static volatile SingularAttribute<TransactionDetailEntity, Long> id;

    public static volatile SingularAttribute<TransactionDetailEntity, TransactionEntity> transaction;

    public static volatile SingularAttribute<TransactionDetailEntity, AssetEntity> asset;

    public static volatile SingularAttribute<TransactionDetailEntity, String> direction;

    public static volatile SingularAttribute<TransactionDetailEntity, String> reason;
}
