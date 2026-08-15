package com.housingbid.dto;

public class PropertyResponse {
    private String postcode;
    private int houseNumber;
    private String city;

    public PropertyResponse(int houseNumber, String postcode, String city) {
        this.houseNumber = houseNumber;
        this.postcode = postcode;
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }


    public int getHouseNumber() {
        return houseNumber;
    }

    public String getCity() {
        return city;
    }

}
