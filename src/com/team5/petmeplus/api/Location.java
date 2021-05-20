package com.team5.petmeplus.api;

public class Location {
    private String fullAddress = "";
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String zip_code;
    private String state;

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getAddress3() {
        return address3;
    }

    public String getFullAddress() {
        if (address1 != null && !address1.isEmpty()) {
            fullAddress += address1;
        }

        if (address2 != null && !address2.isEmpty()) {
            fullAddress += ", " + address2;
        }

        if (address3 != null && !address3.isEmpty()) {
            fullAddress += ", " + address3;
        }

        if (fullAddress.isEmpty()) {
            fullAddress += city + ", " + state + " " + zip_code;
        } else {
            fullAddress += ", " + city + ", " + state + " " + zip_code;
        }

        return fullAddress;
    }

    public String getCity() {
        return city;
    }

    public String getZip_code() {
        return zip_code;
    }

    public String getState() {
        return state;
    }
}
