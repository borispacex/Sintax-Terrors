package com.dharbor.sintaxterrors.asset_service.model.constant;

public final class AssetEntityConstants {

    private AssetEntityConstants() {
    }

    public static class AssetTable {
        public static final String NAME = "asset_";

        public static class Id {
            public static final String NAME = "id_";
        }

        public static class SerialNumber {
            public static final String NAME = "serial_number_";
            public static final int LENGTH = 100;
        }

        public static class Model {
            public static final String NAME = "model_";
            public static final int LENGTH = 100;
        }

        public static class Manufacturer {
            public static final String NAME = "manufacturer_";
            public static final int LENGTH = 100;
        }

        public static class CategoryJoin {
            public static final String NAME = "category_id_";
            public static final String MAPPER = "assets";
        }

        public static class WorkspaceJoin {
            public static final String NAME = "workspace_id_";
            public static final String MAPPER = "assets";
        }

        public static class PurchaseDate {
            public static final String NAME = "purchase_date_";
        }

        public static class PurchaseCost {
            public static final String NAME = "purchase_cost_";
        }

        public static class Status {
            public static final String NAME = "status_";
            public static final int LENGTH = 50;
        }

        public static class WarrantyExpiration {
            public static final String NAME = "warranty_expiration_";
        }

        public static class City {
            public static final String NAME = "city_";
            public static final int LENGTH = 50;
        }

        public static class Notes {
            public static final String NAME = "notes_";
        }
    }
}