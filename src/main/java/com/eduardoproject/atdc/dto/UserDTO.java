package com.eduardoproject.atdc.dto;

import com.eduardoproject.atdc.entities.User;
import com.eduardoproject.atdc.entities.enums.TypeUser;
import com.eduardoproject.atdc.resources.utils.UserUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@UserUpdate
public class UserDTO  implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;

    @NotEmpty(message = "Campo Obrigatório.")
    @Length(min = 3, max = 80, message = "O nome deve ter entre três e oitenta caracteres.")
    private String name;

    private String email;
    private String phone1;
    private String phone2;
    private  Integer profileId;

    public UserDTO() {

    }
    public UserDTO(User obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.email = obj.getEmail();
        this.phone1 = obj.getPhone1();
        this.phone2 = obj.getPhone2();
        this.profileId = obj.getProfileId();
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }
}
