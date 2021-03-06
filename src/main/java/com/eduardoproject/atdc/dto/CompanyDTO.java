package com.eduardoproject.atdc.dto;

import com.eduardoproject.atdc.entities.Company;


import java.io.Serializable;

public class CompanyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String fantasyName;
    private String cnpj;

    public CompanyDTO() {
    }

    public CompanyDTO(Company obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.fantasyName = obj.getFantasyName();
        this.cnpj = obj.getCnpj();
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
