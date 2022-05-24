package com.newsApplicationMicroservice.userMicroservice.enums;

public enum RoleEnum {
    CANDIDATE("candidate"), CREATOR("creator"),MANAGER("manager"), ADMIN("admin");
    private String name;

    RoleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
