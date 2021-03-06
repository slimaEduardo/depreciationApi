package com.eduardoproject.atdc.entities.enums;

public enum TypeUser {
    ADMIN(1, "ADMINISTRATOR"),
    USER(2, "NORMALUSER");

    private int cod;
    private String typeUser;

    TypeUser(int cod, String typeUser) {
        this.cod = cod;
        this.typeUser = typeUser;
    }

    public int getCod(){
        return cod;
    }

    public String getTypeUser(){
        return  typeUser;
    }

    public static  TypeUser toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(TypeUser x : TypeUser.values()){
            if(cod.equals(x.getCod())){}
            return x;
        }
        throw new IllegalArgumentException("Id inválido" + cod);
    }

    public static  TypeUser toEnum(String typeUser){
        if(typeUser == null){
            return null;
        }
        for(TypeUser x : TypeUser.values()){
            if(typeUser.equals(x.getTypeUser())){
                return x;
            }

        }
        throw new IllegalArgumentException("Id inválido" + typeUser);
    }
}
