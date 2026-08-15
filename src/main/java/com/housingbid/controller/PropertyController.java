package com.housingbid.controller;

import com.housingbid.dto.PropertyResponse;
import com.housingbid.service.PropertyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/search")
    public PropertyResponse searchProperty(
            @RequestParam String postcode,
            @RequestParam int houseNumber
    ) {

        return propertyService.findProperty(
                postcode,
                houseNumber
        );
    }
}
