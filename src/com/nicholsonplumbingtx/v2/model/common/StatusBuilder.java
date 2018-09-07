package com.nicholsonplumbingtx.v2.model.common;

public class StatusBuilder {
    private int statusID;
    private String statusTitle;
    private String statusDescription;

    public StatusBuilder setStatusID(int statusID)
    {
        this.statusID = statusID;
        return this;
    }

    public StatusBuilder setStatusTitle(String statusTitle)
    {
        this.statusTitle = statusTitle;
        return this;
    }

    public StatusBuilder setStatusDescription(String statusDescription)
    {
        this.statusDescription = statusDescription;
        return this;
    }

    public Status createStatus()
    {
        return new Status(statusID, statusTitle, statusDescription);
    }
}