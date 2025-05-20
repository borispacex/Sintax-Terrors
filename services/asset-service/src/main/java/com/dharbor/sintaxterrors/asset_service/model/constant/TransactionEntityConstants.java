package com.dharbor.sintaxterrors.asset_service.model.constant;

public final class TransactionEntityConstants {

    private TransactionEntityConstants() {
    }

    public static class TransactionTable {
        public static final String NAME = "transaction_";

        public static class Id {
            public static final String NAME = "id_";
        }

        public static class TransactionType {
            public static final String NAME = "transaction_type_";
            public static final int LENGTH = 10;
        }

        public static class TransactionDate {
            public static final String NAME = "transaction_date_";
        }

        public static class EmployeeJoin {
            public static final String NAME = "employee_id_";
            public static final String MAPPER = "transactions";
        }

        public static class Note {
            public static final String NAME = "note_";
        }

        public static class TransactionDetails {
            public static final String NAME = "transaction_details_";
            public static final String MAPPER = "transaction";
        }
    }
}