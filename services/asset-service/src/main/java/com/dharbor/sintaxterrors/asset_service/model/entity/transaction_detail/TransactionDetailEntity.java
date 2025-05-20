package com.dharbor.sintaxterrors.asset_service.model.entity.transaction_detail;

import com.dharbor.sintaxterrors.asset_service.model.constant.TransactionDetailEntityConstants.TransactionDetailTable;
import com.dharbor.sintaxterrors.asset_service.model.entity.asset.AssetEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.transaction.TransactionEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = TransactionDetailTable.NAME)
public class TransactionDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TransactionDetailTable.Id.NAME, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = TransactionDetailTable.TransactionJoin.NAME, nullable = false)
    @JsonBackReference
    private TransactionEntity transaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = TransactionDetailTable.AssetJoin.NAME, nullable = false)
    private AssetEntity asset;

    @Column(name = TransactionDetailTable.Direction.NAME, nullable = false, length = TransactionDetailTable.Direction.LENGTH)
    private String direction;

    @Column(name = TransactionDetailTable.Reason.NAME)
    private String reason;
}
