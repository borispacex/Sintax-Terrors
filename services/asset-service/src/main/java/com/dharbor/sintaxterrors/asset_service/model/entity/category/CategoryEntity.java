package com.dharbor.sintaxterrors.asset_service.model.entity.category;


import com.dharbor.sintaxterrors.asset_service.model.constant.CategoryEntityConstants.CategoryTable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = CategoryTable.NAME)
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = CategoryTable.Id.NAME, nullable = false)
    private Long id;

    @Column(name = CategoryTable.Name.NAME, nullable = false, length = CategoryTable.Name.LENGTH)
    private String name;

    @Column(name = CategoryTable.Description.NAME)
    private String description;

    @Column(name = CategoryTable.UsefulLifeMonths.NAME)
    private Integer usefulLifeMonths;

    @Column(name = CategoryTable.IsDepreciable.NAME)
    private Boolean isDepreciable;
}