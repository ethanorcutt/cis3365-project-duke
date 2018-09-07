package com.nicholsonplumbingtx.v2.model.common;

/**
 * Created by Ethan Orcutt on 10/2/2016.
 * Project D.U.K.E.
 */
public class Country
{
    private int countryID;
    private String countryName;
    private String countryCode;

    public Country(int countryID, String countryName, String countryCode)
    {
        this.countryID = countryID;
        this.countryName = countryName;
        this.countryCode = countryCode;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    private String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString()
    {
        return getCountryName();
    }
}
