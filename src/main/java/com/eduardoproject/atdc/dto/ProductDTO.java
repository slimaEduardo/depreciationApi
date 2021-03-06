package com.eduardoproject.atdc.dto;

import com.eduardoproject.atdc.entities.Category;
import com.eduardoproject.atdc.entities.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty
    @Length(min = 5, max = 80, message = "O nome deve ter entre cinco e oitenta caracteres.")
    private String name;
    private String description;
    private Double initialPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date purchasedDate;
    private Integer prodAge;
    private Double depreciationValue;
    private Double currentValue;
    private String categoryName;
    private String companyName;
    private Category category;


    public ProductDTO() {
    }

    public ProductDTO(Product obj) {
        this.id = obj.getId();
        this.name = obj.getName();
        this.description = obj.getDescription();
        this.initialPrice = obj.getInitialPrice();
        this.purchasedDate = obj.getPurchasedDate();
        this.categoryName = obj.getCategory().getName();
        this.companyName = obj.getCompany().getName();
        this.category = obj.getCategory();
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(Double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public Date getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public Double getDepreciationValue() {
        return depreciationValue;
    }

    public void setDepreciationValue(Double depreciationValue) {
        this.depreciationValue = depreciationValue;
    }



    public String getCategory() {
        return categoryName;
    }

    public void setCategory(String category) {
        this.categoryName = category;
    }

    public String getCompany() {
        return companyName;
    }

    public void setCompany(String company) {
        this.companyName = company;
    }

    public Integer getProdAge() {
        return CalcAge(this.getPurchasedDate());
    }

    public void setProdAge(Integer prodAge) {
        this.prodAge = prodAge;
    }

    public Double getCurrentValue() {
        Double value = this.getInitialPrice();
        Integer age = this.getProdAge();
        Double depValue = this.getDepretiationValue();
        return value - depValue*age;
    }

    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }

    public Double getDepretiationValue() {
        return this.CalcDepretiation(this.getInitialPrice(), this.category.getDepretiationRate());
    }

    public void setDepretiationValue(Double depretiationValue) {
        this.depreciationValue = depretiationValue;
    }

    private Double CalcDepretiation(Double initialPrice, Double depretiationRate) {
        return initialPrice*depretiationRate;
    }

    private Integer CalcAge(Date purchasedDate) {
        Calendar purshaseDate = new GregorianCalendar();
        purshaseDate.setTime(purchasedDate);
        // Cria um objeto calendar com a data atual
        Calendar today = Calendar.getInstance();
        // Obtém a idade baseado no ano
        int age = today.get(Calendar.YEAR) - purshaseDate.get(Calendar.YEAR);
        purshaseDate.add(Calendar.YEAR, age);
        if (today.before(purshaseDate)) {
            age--;
        }

        return age;
    }

}
