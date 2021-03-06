package com.eduardoproject.atdc.entities.enums;

public enum UserProfile {

    ADMIN(1,"ROLE_ADMIN"),
    USER(2,"ROLE_USER");

    private int cod;
    private String description;


    private UserProfile(int cod, String description) {
        this.cod = cod;
        this.description = description;
    }


    public int getCod() {
        return cod;
    }


    public String getDescription() {
        return description;
    }

    public static UserProfile toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }
        for (UserProfile x : UserProfile.values()) {
            if(cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido" + cod);
    }

}
