package com.nicholsonplumbingtx.v2.model.common;

/**
 * Created by Ethan Orcutt on 10/31/2016.
 * Project D.U.K.E.
 */
public class SalaryFrequency
{
    private int salaryFrequencyID;
    private String title;
    private String description;

    public SalaryFrequency(int salaryFrequencyID, String title, String description)
    {
        this.salaryFrequencyID = salaryFrequencyID;
        this.title = title;
        this.description = description;
    }

    public int getSalaryFrequencyID()
    {
        return salaryFrequencyID;
    }

    public void setSalaryFrequencyID(int salaryFrequencyID)
    {
        this.salaryFrequencyID = salaryFrequencyID;
    }

    private String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return getTitle();
    }
}
