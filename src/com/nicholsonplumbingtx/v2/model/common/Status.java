package com.nicholsonplumbingtx.v2.model.common;

/**
 * Created by Ethan Orcutt on 10/30/2016.
 * Project D.U.K.E.
 */
public class Status
{
    private int statusID;
    private String statusTitle;
    private String statusDescription;

    public Status(int statusID, String statusTitle, String statusDescription)
    {
        this.statusID = statusID;
        this.statusTitle = statusTitle;
        this.statusDescription = statusDescription;
    }

    public int getStatusID()
    {
        return statusID;
    }

    public void setStatusID(int statusID)
    {
        this.statusID = statusID;
    }

    public String getStatusTitle()
    {
        return statusTitle;
    }

    public void setStatusTitle(String statusTitle)
    {
        this.statusTitle = statusTitle;
    }

    public String getStatusDescription()
    {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription)
    {
        this.statusDescription = statusDescription;
    }

    @Override
    public String toString()
    {
        return getStatusTitle();
    }
}
