package com.nicholsonplumbingtx.v2.model.invoice;

import com.nicholsonplumbingtx.v2.model.common.Status;
import com.nicholsonplumbingtx.v2.model.common.Type;

import java.sql.Date;

/**
 * Created by Ethan Orcutt on 10/10/2016.
 * Project D.U.K.E.
 */
public class Invoice
{
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

    public Invoice(int invoiceID, int projectID, String projectName, int invoiceTypeID, int invoiceStatusID, double changeOrderNetTotal, double completedSum, String notes, double totalDue, double totalStillDue, Date dateCreated, Status invoiceStatus, Type invoiceType)
    {
        this.invoiceID = invoiceID;
        this.projectID = projectID;
        this.projectName = projectName;
        this.invoiceTypeID = invoiceTypeID;
        this.invoiceStatusID = invoiceStatusID;
        this.changeOrderNetTotal = changeOrderNetTotal;
        this.completedSum = completedSum;
        this.notes = notes;
        this.totalDue = totalDue;
        this.totalStillDue = totalStillDue;
        this.dateCreated = dateCreated;
        this.invoiceStatus = invoiceStatus;
        this.invoiceType = invoiceType;
    }

    public int getInvoiceID()    {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID)
    {
        this.invoiceID = invoiceID;
    }

    public int getProjectID()
    {
        return projectID;
    }

    public void setProjectID(int projectID)
    {
        this.projectID = projectID;
    }

    public String getProjectName()
    {
        return projectName;
    }

    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    public int getInvoiceTypeID()
    {
        return invoiceTypeID;
    }

    public void setInvoiceTypeID(int invoiceTypeID)
    {
        this.invoiceTypeID = invoiceTypeID;
    }

    public int getInvoiceStatusID()
    {
        return invoiceStatusID;
    }

    public void setInvoiceStatusID(int invoiceStatusID)
    {
        this.invoiceStatusID = invoiceStatusID;
    }

    public double getChangeOrderNetTotal()
    {
        return changeOrderNetTotal;
    }

    public void setChangeOrderNetTotal(double changeOrderNetTotal)
    {
        this.changeOrderNetTotal = changeOrderNetTotal;
    }

    public double getCompletedSum()
    {
        return completedSum;
    }

    public void setCompletedSum(double completedSum)
    {
        this.completedSum = completedSum;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public double getTotalDue()
    {
        return totalDue;
    }

    public void setTotalDue(double totalDue)
    {
        this.totalDue = totalDue;
    }

    public double getTotalStillDue()
    {
        return totalStillDue;
    }

    public void setTotalStillDue(double totalStillDue)
    {
        this.totalStillDue = totalStillDue;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public Status getInvoiceStatus()
    {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Status invoiceStatus)
    {
        this.invoiceStatus = invoiceStatus;
    }

    public Type getInvoiceType()
    {
        return invoiceType;
    }

    public void setInvoiceType(Type invoiceType)
    {
        this.invoiceType = invoiceType;
    }

    @Override
    public String toString()
    {
        return "#" + getInvoiceID() + "\tDate: " + getDateCreated();
    }
}