package com.eduardoproject.atdc.dto;

import com.eduardoproject.atdc.entities.enums.TypeUser;
import com.eduardoproject.atdc.resources.utils.UserInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@UserInsert
public class UserNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Campo Obrigatório.")
    @Length(min = 5, max = 80, message = "O nome deve ter entre cinco e oitenta caracteres.")
    private String name;
    @Email(message = "Email inválido.")
    private String email;
    @NotEmpty(message = "Campo Obrigatório.")
    private String password;
    private TypeUser typeUser;

    public UserNewDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTypeUser() {
        return typeUser.getTypeUser();
    }

    public void setTypeUser(TypeUser typeUser) {
        this.typeUser = typeUser;
    }
}
