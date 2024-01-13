package models;

public class Address {
    private String streetAddress;
    private String city;
    private String state;
    private String country;
    private Integer zipCode;

    public Address(String streetAddress, String city, String state, String country, Integer zipCode) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
    }
}
