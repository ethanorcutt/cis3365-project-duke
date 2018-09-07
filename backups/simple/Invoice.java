package simple;

/**
 * Created by Ethan Orcutt on 10/10/2016.
 */
public class Invoice
{
    private String projectName;
    private int invoiceID;
    private int projectID;
    private int invoiceTypeID;
    private int invoiceStatusID;
    private double changeOrderNetTotal;
    private double completedSum;
    private String notes;
    private double totalDue;
    private double totalStillDue;
    private String dateCreated;

    public Invoice(String projectName, int invoiceID, int projectID, int invoiceTypeID, int invoiceStatusID, double changeOrderNetTotal, double completedSum, String notes, double totalDue, double totalStillDue, String dateCreated)
    {
        this.projectName = projectName;
        this.invoiceID = invoiceID;
        this.projectID = projectID;
        this.invoiceTypeID = invoiceTypeID;
        this.invoiceStatusID = invoiceStatusID;
        this.changeOrderNetTotal = changeOrderNetTotal;
        this.completedSum = completedSum;
        this.notes = notes;
        this.totalDue = totalDue;
        this.totalStillDue = totalStillDue;
        this.dateCreated = dateCreated;
    }

    public String getProjectName()
    {
        return projectName;
    }

    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    public int getInvoiceID()
    {
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

    public String getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated)
    {
        this.dateCreated = dateCreated;
    }
}