package com.eduardoproject.atdc.dto;

import com.eduardoproject.atdc.resources.utils.CompanyInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@CompanyInsert
public class CompanyNewDTO {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Campo Obrigatório.")
    @Length(min = 5, max = 80, message = "O nome deve ter entre cinco e oitenta caracteres.")
    private String name;
    @NotEmpty(message = "Campo Obrigatório.")
    private String fantasyName;
    @NotEmpty(message = "Campo Obrigatório.")
    private String cnpj;

    public CompanyNewDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
