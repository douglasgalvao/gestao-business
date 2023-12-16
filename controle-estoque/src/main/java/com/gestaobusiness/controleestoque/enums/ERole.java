package com.gestaobusiness.controleestoque.enums;

public enum ERole {
    ADMININISTRADOR("administrador");

    private final String roleName;

    ERole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return this.roleName;
    }

}
