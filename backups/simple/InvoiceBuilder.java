package simple;

public class InvoiceBuilder {
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

    public InvoiceBuilder setProjectName(String projectName)
    {
        this.projectName = projectName;
        return this;
    }

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

    public InvoiceBuilder setDateCreated(String dateCreated)
    {
        this.dateCreated = dateCreated;
        return this;
    }

    public Invoice createInvoice()
    {
        return new Invoice(projectName, invoiceID, projectID, invoiceTypeID, invoiceStatusID, changeOrderNetTotal, completedSum, notes, totalDue, totalStillDue, dateCreated);
    }
}