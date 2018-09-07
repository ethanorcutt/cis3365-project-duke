package com.nicholsonplumbingtx.v2.model.common;

public class TypeBuilder {
    private int typeID;
    private String typeTitle;
    private String typeDescription;

    public TypeBuilder setTypeID(int typeID)
    {
        this.typeID = typeID;
        return this;
    }

    public TypeBuilder setTypeTitle(String typeTitle)
    {
        this.typeTitle = typeTitle;
        return this;
    }

    public TypeBuilder setTypeDescription(String typeDescription)
    {
        this.typeDescription = typeDescription;
        return this;
    }

    public Type createType()
    {
        return new Type(typeID, typeTitle, typeDescription);
    }
}