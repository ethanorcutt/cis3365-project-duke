package com.nicholsonplumbingtx.v2.model.common;

public class CountryBuilder {
    private int countryID;
    private String countryName;
    private String countryCode;

    public CountryBuilder setCountryID(int countryID) {
        this.countryID = countryID;
        return this;
    }

    public CountryBuilder setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public CountryBuilder setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public Country createCountry() {
        return new Country(countryID, countryName, countryCode);
    }
}