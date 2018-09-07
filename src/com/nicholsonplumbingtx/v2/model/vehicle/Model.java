package com.nicholsonplumbingtx.v2.model.vehicle;

/**
 * Created by Ethan Orcutt on 11/6/2016.
 * Project D.U.K.E.
 */
public class Model
{
    private int modelID;
    private int makeID;
    private String modelName;

    public Model(int modelID, int makeID, String modelName)
    {
        this.modelID = modelID;
        this.makeID = makeID;
        this.modelName = modelName;
    }

    public int getModelID()
    {
        return modelID;
    }

    public void setModelID(int modelID)
    {
        this.modelID = modelID;
    }

    public int getMakeID()
    {
        return makeID;
    }

    public void setMakeID(int makeID)
    {
        this.makeID = makeID;
    }

    public String getModelName()
    {
        return modelName;
    }

    public void setModelName(String modelName)
    {
        this.modelName = modelName;
    }

    @Override
    public String toString()
    {
        return getModelName();
    }
}
