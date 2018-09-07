package com.nicholsonplumbingtx.v2.model.common;

public class SalaryFrequencyBuilder {
    private int salaryFrequencyID;
    private String title;
    private String description;

    public SalaryFrequencyBuilder setSalaryFrequencyID(int salaryFrequencyID)
    {
        this.salaryFrequencyID = salaryFrequencyID;
        return this;
    }

    public SalaryFrequencyBuilder setTitle(String title)
    {
        this.title = title;
        return this;
    }

    public SalaryFrequencyBuilder setDescription(String description)
    {
        this.description = description;
        return this;
    }

    public SalaryFrequency createSalaryFrequency()
    {
        return new SalaryFrequency(salaryFrequencyID, title, description);
    }
}