package com.wtw.form;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Admin on 2016-02-28.
 */
public class ProductCreateForm {

    @Size(min = 4, max = 15)
    private String name;

    @NotBlank
    private String description;

    @NotNull
    @Pattern(regexp = "^\\d{0,9}(\\.\\d{1,9})?$")
    private String price;

    @NotNull
    @Pattern(regexp = "^([0-9]|[1-9][0-9]*)$")
    private String unitsInStock;

    private String imageURL;


    public ProductCreateForm() {

    }

    public ProductCreateForm(String name, String description, String price, String unitsInStock, String imageURL) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.unitsInStock = unitsInStock;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(String unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "ProductCreateForm{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", unitsInStock=" + unitsInStock +
                ", imageURL=" + imageURL +
                '}';
    }
}

