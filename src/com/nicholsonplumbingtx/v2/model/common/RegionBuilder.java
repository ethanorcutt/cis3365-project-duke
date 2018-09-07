package com.nicholsonplumbingtx.v2.model.common;

public class RegionBuilder {
    private int regionID;
    private String regionName;
    private String regionCode;

    public RegionBuilder setRegionID(int regionID) {
        this.regionID = regionID;
        return this;
    }

    public RegionBuilder setRegionName(String regionName) {
        this.regionName = regionName;
        return this;
    }

    public RegionBuilder setRegionCode(String regionCode) {
        this.regionCode = regionCode;
        return this;
    }

    public Region createRegion() {
        return new Region(regionID, regionName, regionCode);
    }
}