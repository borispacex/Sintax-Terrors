package com.dharbor.sintaxterrors.asset_service.model.entity.category;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CategoryEntity.class)
public class CategoryEntity_ {

    public static volatile SingularAttribute<CategoryEntity, Long> id;

    public static volatile SingularAttribute<CategoryEntity, String> name;

    public static volatile SingularAttribute<CategoryEntity, String> description;

    public static volatile SingularAttribute<CategoryEntity, Integer> usefulLifeMonths;

    public static volatile SingularAttribute<CategoryEntity, Boolean> isDepreciable;
}
