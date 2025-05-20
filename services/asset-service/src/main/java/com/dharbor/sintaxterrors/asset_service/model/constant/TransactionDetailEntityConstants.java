package com.dharbor.sintaxterrors.asset_service.model.constant;

public final class TransactionDetailEntityConstants {

    private TransactionDetailEntityConstants() {
    }

    public static class TransactionDetailTable {
        public static final String NAME = "transaction_detail_";

        public static class Id {
            public static final String NAME = "id_";
        }

        public static class TransactionJoin {
            public static final String NAME = "transaction_id_";
            public static final String MAPPER = "transactionDetails";
        }

        public static class AssetJoin {
            public static final String NAME = "asset_id_";
            public static final String MAPPER = "transactionDetails";
        }

        public static class Direction {
            public static final String NAME = "direction_";
            public static final int LENGTH = 10;
        }

        public static class Reason {
            public static final String NAME = "reason_";
        }
    }
}
