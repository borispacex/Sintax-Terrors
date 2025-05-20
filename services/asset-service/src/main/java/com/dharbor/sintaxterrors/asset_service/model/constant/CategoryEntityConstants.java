package com.dharbor.sintaxterrors.asset_service.model.constant;

public final class CategoryEntityConstants {

    private CategoryEntityConstants() {
    }

    public static class CategoryTable {
        public static final String NAME = "category_";

        public static class Id {
            public static final String NAME = "id_";
        }

        public static class Name {
            public static final String NAME = "name_";
            public static final int LENGTH = 100;
        }

        public static class Description {
            public static final String NAME = "description_";
        }

        public static class UsefulLifeMonths {
            public static final String NAME = "useful_life_months_";
        }

        public static class IsDepreciable {
            public static final String NAME = "is_depreciable_";
        }
    }
}
