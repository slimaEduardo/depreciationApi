package com.eduardoproject.atdc.dto;

import com.eduardoproject.atdc.entities.Category;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;
    @NotEmpty
    @Length(min = 3, max = 80, message = "O nome deve ter entre três e oitenta caracteres.")
    private String name;
    private Double depreciationRate;
    private Integer lifeCycle;

    public CategoryDTO() {

    }

    public CategoryDTO(Category obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.depreciationRate = obj.getDepretiationRate();
        this.lifeCycle = obj.getLifeCycle();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDepretiationRate() {
        return depreciationRate;
    }

    public void setDepretiationRate(Double depretiationRate) {
        this.depreciationRate = depretiationRate;
    }

    public Integer getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(Integer lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
