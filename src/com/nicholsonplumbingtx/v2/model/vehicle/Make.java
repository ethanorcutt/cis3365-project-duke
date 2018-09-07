package com.nicholsonplumbingtx.v2.model.vehicle;

/**
 * Created by Ethan Orcutt on 11/6/2016.
 * Project D.U.K.E.
 */
public class Make
{
    private int makeID;
    private String makeName;

    public Make(int makeID, String makeName)
    {
        this.makeID = makeID;
        this.makeName = makeName;
    }

    public int getMakeID()
    {
        return makeID;
    }

    public void setMakeID(int makeID)
    {
        this.makeID = makeID;
    }

    public String getMakeName()
    {
        return makeName;
    }

    public void setMakeName(String makeName)
    {
        this.makeName = makeName;
    }

    @Override
    public String toString()
    {
        return getMakeName();
    }
}
