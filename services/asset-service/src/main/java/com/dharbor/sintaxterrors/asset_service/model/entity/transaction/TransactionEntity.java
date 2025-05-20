package com.dharbor.sintaxterrors.asset_service.model.entity.transaction;

import com.dharbor.sintaxterrors.asset_service.enums.transaction.TransactionType;
import com.dharbor.sintaxterrors.asset_service.model.constant.TransactionEntityConstants.TransactionTable;
import com.dharbor.sintaxterrors.asset_service.model.entity.employee.EmployeeEntity;
import com.dharbor.sintaxterrors.asset_service.model.entity.transaction_detail.TransactionDetailEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = TransactionTable.NAME)
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = TransactionTable.Id.NAME, nullable = false)
    private Long id;

    @Column(name = TransactionTable.TransactionType.NAME, nullable = false, length = TransactionTable.TransactionType.LENGTH)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = TransactionTable.TransactionDate.NAME, nullable = false)
    private LocalDateTime transactionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = TransactionTable.EmployeeJoin.NAME, nullable = false)
    @JsonBackReference
    private EmployeeEntity employee;

    @Column(name = TransactionTable.Note.NAME)
    private String note;

    @OneToMany(mappedBy = TransactionTable.TransactionDetails.MAPPER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TransactionDetailEntity> transactionDetails;
}
