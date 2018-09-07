package com.nicholsonplumbingtx.v2.model.change_order;

import java.sql.Date;

/**
 * Created by Ethan Orcutt on 10/2/2016.
 * Project D.U.K.E.
 */
public class ChangeOrder
{
    private int changeOrderID;
    private int changeOrderStatusID;
    private int invoiceID;
    private double material;
    private double salesTax;
    private double directLabor;
    private double indirectCosts;
    private double equipmentToolsCosts;
    private double overheadCosts;
    private double subContracts;
    private double overheadPercentage;
    private double profitPercentage;
    private double bondPremiumPercentage;
    private double servicePercentage;
    private double finalTotal;
    private String exclusions;
    private int daysValid;
    private int contractExtension;
    private String notes;
    private Date dateCreated;
    private double subtotalF;
    private double subtotalJ;
    private double subtotalL;
    private boolean add;
    private boolean deduct;

    public ChangeOrder(int changeOrderID, int changeOrderStatusID, int invoiceID, double material, double salesTax, double directLabor, double indirectCosts, double equipmentToolsCosts, double overheadCosts, double subContracts, double overheadPercentage, double profitPercentage, double bondPremiumPercentage, double servicePercentage, double finalTotal, String exclusions, int daysValid, int contractExtension, String notes, Date dateCreated, double subtotalF, double subtotalJ, double subtotalL, boolean add, boolean deduct)
    {
        this.changeOrderID = changeOrderID;
        this.changeOrderStatusID = changeOrderStatusID;
        this.invoiceID = invoiceID;
        this.material = material;
        this.salesTax = salesTax;
        this.directLabor = directLabor;
        this.indirectCosts = indirectCosts;
        this.equipmentToolsCosts = equipmentToolsCosts;
        this.overheadCosts = overheadCosts;
        this.subContracts = subContracts;
        this.overheadPercentage = overheadPercentage;
        this.profitPercentage = profitPercentage;
        this.bondPremiumPercentage = bondPremiumPercentage;
        this.servicePercentage = servicePercentage;
        this.finalTotal = finalTotal;
        this.exclusions = exclusions;
        this.daysValid = daysValid;
        this.contractExtension = contractExtension;
        this.notes = notes;
        this.dateCreated = dateCreated;
        this.subtotalF = subtotalF;
        this.subtotalJ = subtotalJ;
        this.subtotalL = subtotalL;
        this.add = add;
        this.deduct = deduct;
    }

    public int getChangeOrderID()
    {
        return changeOrderID;
    }

    public void setChangeOrderID(int changeOrderID)
    {
        this.changeOrderID = changeOrderID;
    }

    public int getChangeOrderStatusID()
    {
        return changeOrderStatusID;
    }

    public void setChangeOrderStatusID(int changeOrderStatusID)
    {
        this.changeOrderStatusID = changeOrderStatusID;
    }

    public int getInvoiceID()
    {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID)
    {
        this.invoiceID = invoiceID;
    }

    public double getMaterial()
    {
        return material;
    }

    public void setMaterial(double material)
    {
        this.material = material;
    }

    public double getSalesTax()
    {
        return salesTax;
    }

    public void setSalesTax(double salesTax)
    {
        this.salesTax = salesTax;
    }

    public double getDirectLabor()
    {
        return directLabor;
    }

    public void setDirectLabor(double directLabor)
    {
        this.directLabor = directLabor;
    }

    public double getIndirectCosts()
    {
        return indirectCosts;
    }

    public void setIndirectCosts(double indirectCosts)
    {
        this.indirectCosts = indirectCosts;
    }

    public double getEquipmentToolsCosts()
    {
        return equipmentToolsCosts;
    }

    public void setEquipmentToolsCosts(double equipmentToolsCosts)
    {
        this.equipmentToolsCosts = equipmentToolsCosts;
    }

    public double getOverheadCosts()
    {
        return overheadCosts;
    }

    public void setOverheadCosts(double overheadCosts)
    {
        this.overheadCosts = overheadCosts;
    }

    public double getSubContracts()
    {
        return subContracts;
    }

    public void setSubContracts(double subContracts)
    {
        this.subContracts = subContracts;
    }

    public double getOverheadPercentage()
    {
        return overheadPercentage;
    }

    public void setOverheadPercentage(double overheadPercentage)
    {
        this.overheadPercentage = overheadPercentage;
    }

    public double getProfitPercentage()
    {
        return profitPercentage;
    }

    public void setProfitPercentage(double profitPercentage)
    {
        this.profitPercentage = profitPercentage;
    }

    public double getBondPremiumPercentage()
    {
        return bondPremiumPercentage;
    }

    public void setBondPremiumPercentage(double bondPremiumPercentage)
    {
        this.bondPremiumPercentage = bondPremiumPercentage;
    }

    public double getServicePercentage()
    {
        return servicePercentage;
    }

    public void setServicePercentage(double servicePercentage)
    {
        this.servicePercentage = servicePercentage;
    }

    public double getFinalTotal()
    {
        return finalTotal;
    }

    public void setFinalTotal(double finalTotal)
    {
        this.finalTotal = finalTotal;
    }

    public String getExclusions()
    {
        return exclusions;
    }

    public void setExclusions(String exclusions)
    {
        this.exclusions = exclusions;
    }

    public int getDaysValid()
    {
        return daysValid;
    }

    public void setDaysValid(int daysValid)
    {
        this.daysValid = daysValid;
    }

    public int getContractExtension()
    {
        return contractExtension;
    }

    public void setContractExtension(int contractExtension)
    {
        this.contractExtension = contractExtension;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public Date getDateCreated()
    {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
    }

    public double getSubtotalF()
    {
        return subtotalF;
    }

    public void setSubtotalF(double subtotalF)
    {
        this.subtotalF = subtotalF;
    }

    public double getSubtotalJ()
    {
        return subtotalJ;
    }

    public void setSubtotalJ(double subtotalJ)
    {
        this.subtotalJ = subtotalJ;
    }

    public double getSubtotalL()
    {
        return subtotalL;
    }

    public void setSubtotalL(double subtotalL)
    {
        this.subtotalL = subtotalL;
    }

    public boolean isAdd()
    {
        return add;
    }

    public void setAdd(boolean add)
    {
        this.add = add;
    }

    public boolean isDeduct()
    {
        return deduct;
    }

    public void setDeduct(boolean deduct)
    {
        this.deduct = deduct;
    }
}
