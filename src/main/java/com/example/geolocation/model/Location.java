package com.example.geolocation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Location implements Serializable {
    private String countryName;
    private String cityName;
    private String postal;
    private String state;

}

/*
   System.out.println("country : " + countryName);
            System.out.println("cityName : " + cityName);
            System.out.println("postal : " + postal);
            System.out.println("state : " + state);
 */