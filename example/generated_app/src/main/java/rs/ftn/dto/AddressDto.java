package rs.ftn.dto;

import java.util.*;



public class AddressDto {

    private Integer id;
    private String street;
    private Integer number;
    private Integer apartment;
    private String description;
    private Integer zipCode;

    public AddressDto() {

    }

    public AddressDto(Integer id,String street,Integer number,Integer apartment,String description,Integer zipCode) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.apartment = apartment;
        this.description = description;
        this.zipCode = zipCode;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getApartment() {
        return apartment;
    }

    public void setApartment(Integer apartment) {
        this.apartment = apartment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getDisplayName() {
        String displayName = "";

        displayName += street + " ";
        displayName += number + " ";
        displayName += apartment + " ";

        return displayName.trim();
    }

    @Override
    public String toString() {
        return "Address[" + getId() + "]";
    }

}
