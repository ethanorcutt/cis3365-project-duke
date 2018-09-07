package com.nicholsonplumbingtx.v2.model.invoice;

import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;

import java.sql.Date;

public class InvoiceBuilder {
    private int invoiceID;
    private int projectID;
    private String projectName;
    private int invoiceTypeID;
    private int invoiceStatusID;
    private double changeOrderNetTotal;
    private double completedSum;
    private String notes;
    private double totalDue;
    private double totalStillDue;
    private Date dateCreated;
    private Status invoiceStatus;
    private Type invoiceType;

    public InvoiceBuilder setInvoiceID(int invoiceID)
    {
        this.invoiceID = invoiceID;
        return this;
    }

    public InvoiceBuilder setProjectID(int projectID)
    {
        this.projectID = projectID;
        return this;
    }

    public InvoiceBuilder setProjectName(String projectName)
    {
        this.projectName = projectName;
        return this;
    }

    public InvoiceBuilder setInvoiceTypeID(int invoiceTypeID)
    {
        this.invoiceTypeID = invoiceTypeID;
        return this;
    }

    public InvoiceBuilder setInvoiceStatusID(int invoiceStatusID)
    {
        this.invoiceStatusID = invoiceStatusID;
        return this;
    }

    public InvoiceBuilder setChangeOrderNetTotal(double changeOrderNetTotal)
    {
        this.changeOrderNetTotal = changeOrderNetTotal;
        return this;
    }

    public InvoiceBuilder setCompletedSum(double completedSum)
    {
        this.completedSum = completedSum;
        return this;
    }

    public InvoiceBuilder setNotes(String notes)
    {
        this.notes = notes;
        return this;
    }

    public InvoiceBuilder setTotalDue(double totalDue)
    {
        this.totalDue = totalDue;
        return this;
    }

    public InvoiceBuilder setTotalStillDue(double totalStillDue)
    {
        this.totalStillDue = totalStillDue;
        return this;
    }

    public InvoiceBuilder setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
        return this;
    }

    public InvoiceBuilder setInvoiceStatus(Status invoiceStatus)
    {
        this.invoiceStatus = invoiceStatus;
        return this;
    }

    public InvoiceBuilder setInvoiceType(Type invoiceType)
    {
        this.invoiceType = invoiceType;
        return this;
    }

    public Invoice createInvoice()
    {
        return new Invoice(invoiceID, projectID, projectName, invoiceTypeID, invoiceStatusID, changeOrderNetTotal, completedSum, notes, totalDue, totalStillDue, dateCreated, invoiceStatus, invoiceType);
    }
}