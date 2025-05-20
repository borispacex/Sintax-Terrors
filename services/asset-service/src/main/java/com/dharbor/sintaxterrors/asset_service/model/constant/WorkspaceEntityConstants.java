package com.dharbor.sintaxterrors.asset_service.model.constant;

public final class WorkspaceEntityConstants {

    private WorkspaceEntityConstants() {
    }

    public static class WorkspaceTable {
        public static final String NAME = "workspaces_";

        public static class Id {
            public static final String NAME = "id_";
        }

        public static class City {
            public static final String NAME = "city_";
            public static final int LENGTH = 50;
        }

        public static class Location {
            public static final String NAME = "location_";
            public static final int LENGTH = 255;
        }
    }
}
