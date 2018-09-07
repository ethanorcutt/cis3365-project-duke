package com.nicholsonplumbingtx.v2.model.common;

/**
 * Created by Ethan Orcutt on 10/2/2016.
 * Project D.U.K.E.
 */
public class Region
{
    private int regionID;
    private String regionName;
    private String regionCode;

    public Region(int regionID, String regionName, String regionCode) {
        this.regionID = regionID;
        this.regionName = regionName;
        this.regionCode = regionCode;
    }

    public int getRegionID() {
        return regionID;
    }

    public void setRegionID(int regionID) {
        this.regionID = regionID;
    }

    private String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    @Override
    public String toString()
    {
        return getRegionName();
    }
}
