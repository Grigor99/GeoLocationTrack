package com.example.geolocation.controller;


import com.example.geolocation.model.Location;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

@RestController
@RequestMapping("/track")
public class LocationTrackController {

    private static final String dbLocation = "/Users/grigormartirosyan/IdeaProjects/geolocation/GeoLite2-City.mmdb";
    private static final String publicIpAddress = "http://checkip.amazonaws.com";

    @GetMapping
    public String sayHi() {

        try {
            URL whatismyip = new URL(publicIpAddress);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    whatismyip.openStream()));

            String publicIp = in.readLine(); //you get the IP as a String
            System.out.println(publicIp);

            File database = new File(dbLocation);
            DatabaseReader dbReader = new DatabaseReader.Builder(database)
                    .build();

            InetAddress ipAddress = InetAddress.getByName(publicIp);
            CityResponse response = dbReader.city(ipAddress);

            String countryName = response.getCountry().getName();
            String cityName = response.getCity().getName();
            String postal = response.getPostal().getCode();
            String state = response.getLeastSpecificSubdivision().getName();

            Location location = new Location();
            location.setCityName(cityName);
            location.setPostal(postal);
            location.setCountryName(countryName);
            location.setState(state);
            System.out.println("postal : " + location.getPostal() + "\n" +
                    "city : " + location.getCityName() + "\n" +
                    "country : " + location.getCountryName() + "\n" +
                    "state : " + location.getState());
            //save etc
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "hello";
    }
}
