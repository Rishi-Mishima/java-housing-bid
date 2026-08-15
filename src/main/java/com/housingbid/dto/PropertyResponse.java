package com.housingbid.dto;

public class PropertyResponse {
    private int postcode;
    private String houseNumber;
    private String city;

    public PropertyResponse(String houseNumber, int postcode, String city) {
        this.houseNumber = houseNumber;
        this.postcode = postcode;
        this.city = city;
    }

    public int getPostcode() {
        return postcode;
    }


    public String getHouseNumber() {
        return houseNumber;
    }

    public String getCity() {
        return city;
    }

}
