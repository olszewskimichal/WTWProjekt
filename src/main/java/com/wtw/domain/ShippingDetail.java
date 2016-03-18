package com.wtw.domain;

import com.wtw.form.CustomerInfoForm;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ShippingDetail {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    private String doorNo;
    private String flatNo;
    private String streetName;
    private String areaName;
    private String state;
    private String zipCode;
    private String phoneNumber;
    @OneToOne
    @JoinColumn(name = "id")
    private Order order;

    public ShippingDetail() {
        this.date = LocalDate.now();
    }

    public ShippingDetail(final CustomerInfoForm customerInfoForm) {
        this.date = LocalDate.now();
        this.doorNo = customerInfoForm.getDoorNo();
        this.flatNo = customerInfoForm.getFlatNo();
        this.streetName = customerInfoForm.getStreetName();
        this.areaName = customerInfoForm.getAreaName();
        this.state = customerInfoForm.getState();
        this.zipCode = customerInfoForm.getZipCode();
        this.phoneNumber = customerInfoForm.getPhoneNumber();
    }

    public ShippingDetail(final LocalDate date, final String doorNo, final String flatNo, final String streetName, final String areaName, final String state, final String zipCode, final String phoneNumber) {
        this.date = LocalDate.now();
        this.doorNo = doorNo;
        this.flatNo = flatNo;
        this.streetName = streetName;
        this.areaName = areaName;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(final String flatNo) {
        this.flatNo = flatNo;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(final Order order) {
        this.order = order;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(final String doorNo) {
        this.doorNo = doorNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(final String streetName) {
        this.streetName = streetName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(final String areaName) {
        this.areaName = areaName;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "ShippingDetail{" +
                "id=" + id +
                ", date=" + date +
                ", doorNo='" + doorNo + '\'' +
                ", flatNo='" + flatNo + '\'' +
                ", streetName='" + streetName + '\'' +
                ", areaName='" + areaName + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
