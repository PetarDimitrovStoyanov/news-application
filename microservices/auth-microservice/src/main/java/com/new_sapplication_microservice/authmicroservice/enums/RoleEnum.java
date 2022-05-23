package com.new_sapplication_microservice.authmicroservice.enums;

public enum RoleEnum {
    CREATOR("creator"),MANAGER("manager"), ADMIN("admin");
    private String name;

    RoleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
