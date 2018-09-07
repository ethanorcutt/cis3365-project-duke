package com.nicholsonplumbingtx.v2.model.common;

/**
 * Created by Ethan Orcutt on 10/30/2016.
 * Project D.U.K.E.
 */
public class Type
{
    private int typeID;
    private String typeTitle;
    private String typeDescription;

    public Type(int typeID, String typeTitle, String typeDescription)
    {
        this.typeID = typeID;
        this.typeTitle = typeTitle;
        this.typeDescription = typeDescription;
    }

    public int getTypeID()
    {
        return typeID;
    }

    public void setTypeID(int typeID)
    {
        this.typeID = typeID;
    }

    public String getTypeTitle()
    {
        return typeTitle;
    }

    public void setTypeTitle(String typeTitle)
    {
        this.typeTitle = typeTitle;
    }

    public String getTypeDescription()
    {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription)
    {
        this.typeDescription = typeDescription;
    }

    @Override
    public String toString()
    {
        return getTypeTitle();
    }
}
