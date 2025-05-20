package com.dharbor.sintaxterrors.asset_service.model.constant;

public final class EmployeeEntityConstants {
    private EmployeeEntityConstants() {
    }

    public static class EmployeeTable {
        public static final String NAME = "employee_";

        public static class Id {
            public static final String NAME = "id_";
        }

        public static class Ci {
            public static final String NAME = "ci_";

            public static final int LENGTH = 20;
        }

        public static class FirstName {
            public static final String NAME = "first_name_";

            public static final int LENGTH = 50;
        }

        public static class MiddleName {
            public static final String NAME = "middle_name_";

            public static final int LENGTH = 50;
        }

        public static class LastName {
            public static final String NAME = "last_Name_";

            public static final int LENGTH = 50;
        }

        public static class SecondLastName {
            public static final String NAME = "second_last_name_";

            public static final int LENGTH = 50;
        }

        public static class PersonalEmail {
            public static final String NAME = "personal_email_";
        }

        public static class WorkEmail {
            public static final String NAME = "work_email_";
        }

        public static class BirthDate {
            public static final String NAME = "birth_date_";
        }

        public static class Country {
            public static final String NAME = "country_";

            public static final int LENGTH = 50;
        }

        public static class City {
            public static final String NAME = "city_";

            public static final int LENGTH = 50;
        }

        public static class CellphoneNumber {
            public static final String NAME = "cellphone_number_";

            public static final int LENGTH = 20;
        }

        public static class Status {
            public static final String NAME = "status_";

            public static final int LENGTH = 50;
        }

        public static class UserId {
            public static final String NAME = "user_id_";
        }

        public static class TeamJoin {
            public static final String NAME = "team_id_";

            public static final String DEV_LEAD_MAPPER = "devLead";

            public static final String QA_LEAD_MAPPER = "qaLead";

            public static final String PROJECT_MANAGER_MAPPER = "projectManager";
        }

        public static class EmploymentContractsJoin {
            public static final String MAPPER = "employee";
        }

        public static class EventJoin {
            public static final String MAPPER = "employee";
        }

        public static class NotifiedEventJoin {
            public static final String MAPPER = "notifier";
        }

        public static class UploadedImageID {
            public static final String NAME = "uploaded_image_id_";

            public static final int LENGTH = 1024;
        }

        public static class SelectedImageID {
            public static final String NAME = "selected_image_id_";

            public static final int LENGTH = 1024;
        }

        public static class Transaction {
            public static final String NAME = "transaction_";

            public static final String MAPPER = "employee";
        }
    }
}