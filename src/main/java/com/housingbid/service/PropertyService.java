package com.housingbid.service;
import com.housingbid.dto.PropertyResponse;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    public PropertyResponse findProperty(String postcode, int houseNumber){
        return new PropertyResponse(
                postcode,
                houseNumber,
                "Den Haag");
    }


}
