package com.wtw.form;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CustomerInfoForm {
    @Size(min = 4, max = 20)
    private String name;

    @Size(min = 4, max = 30)
    private String lastName;

    @NotNull
    @Pattern(regexp = "^([1-9][0-9]*)$")
    private String doorNo;

    @NotNull
    @Pattern(regexp = "^([0-9]|[1-9][0-9]*)$")
    private String flatNo;

    @Size(min = 4, max = 30)
    private String streetName;
    @Size(min = 4, max = 30)
    private String areaName;
    @Size(min = 4, max = 30)
    private String state;

    @Pattern(regexp = "^(\\d{2}-\\d{3})$")
    private String zipCode;

    @Pattern(regexp = "^((\\(0-\\d\\d\\) \\d\\d\\d \\d\\d \\d\\d)|(\\d\\d\\d \\d\\d\\d \\d\\d\\d)|(\\d\\d\\d\\d\\d\\d\\d\\d\\d)|(0-\\d\\d\\)\\d\\d\\d\\d\\d\\d\\d)|(0\\d\\d\\)\\d\\d\\d\\d\\d\\d\\d))$")
    private String phoneNumber;

    public CustomerInfoForm() {
    }

    public CustomerInfoForm(String name, String lastName, String doorNo, String flatNo, String streetName, String areaName, String state, String zipCode, String phoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.doorNo = doorNo;
        this.flatNo = flatNo;
        this.streetName = streetName;
        this.areaName = areaName;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public String getFlatNo() {
        return flatNo;
    }

    public void setFlatNo(String flatNo) {
        this.flatNo = flatNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "CustomerInfoForm{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
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
