package com.nicholsonplumbingtx.v2.model.change_order;

import java.sql.Date;

public class ChangeOrderBuilder {
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

    public ChangeOrderBuilder setChangeOrderID(int changeOrderID)
    {
        this.changeOrderID = changeOrderID;
        return this;
    }

    public ChangeOrderBuilder setChangeOrderStatusID(int changeOrderStatusID)
    {
        this.changeOrderStatusID = changeOrderStatusID;
        return this;
    }

    public ChangeOrderBuilder setInvoiceID(int invoiceID)
    {
        this.invoiceID = invoiceID;
        return this;
    }

    public ChangeOrderBuilder setMaterial(double material)
    {
        this.material = material;
        return this;
    }

    public ChangeOrderBuilder setSalesTax(double salesTax)
    {
        this.salesTax = salesTax;
        return this;
    }

    public ChangeOrderBuilder setDirectLabor(double directLabor)
    {
        this.directLabor = directLabor;
        return this;
    }

    public ChangeOrderBuilder setIndirectCosts(double indirectCosts)
    {
        this.indirectCosts = indirectCosts;
        return this;
    }

    public ChangeOrderBuilder setEquipmentToolsCosts(double equipmentToolsCosts)
    {
        this.equipmentToolsCosts = equipmentToolsCosts;
        return this;
    }

    public ChangeOrderBuilder setOverheadCosts(double overheadCosts)
    {
        this.overheadCosts = overheadCosts;
        return this;
    }

    public ChangeOrderBuilder setSubContracts(double subContracts)
    {
        this.subContracts = subContracts;
        return this;
    }

    public ChangeOrderBuilder setOverheadPercentage(double overheadPercentage)
    {
        this.overheadPercentage = overheadPercentage;
        return this;
    }

    public ChangeOrderBuilder setProfitPercentage(double profitPercentage)
    {
        this.profitPercentage = profitPercentage;
        return this;
    }

    public ChangeOrderBuilder setBondPremiumPercentage(double bondPremiumPercentage)
    {
        this.bondPremiumPercentage = bondPremiumPercentage;
        return this;
    }

    public ChangeOrderBuilder setServicePercentage(double servicePercentage)
    {
        this.servicePercentage = servicePercentage;
        return this;
    }

    public ChangeOrderBuilder setFinalTotal(double finalTotal)
    {
        this.finalTotal = finalTotal;
        return this;
    }

    public ChangeOrderBuilder setExclusions(String exclusions)
    {
        this.exclusions = exclusions;
        return this;
    }

    public ChangeOrderBuilder setDaysValid(int daysValid)
    {
        this.daysValid = daysValid;
        return this;
    }

    public ChangeOrderBuilder setContractExtension(int contractExtension)
    {
        this.contractExtension = contractExtension;
        return this;
    }

    public ChangeOrderBuilder setNotes(String notes)
    {
        this.notes = notes;
        return this;
    }

    public ChangeOrderBuilder setDateCreated(Date dateCreated)
    {
        this.dateCreated = dateCreated;
        return this;
    }

    public ChangeOrderBuilder setSubtotalF(double subtotalF)
    {
        this.subtotalF = subtotalF;
        return this;
    }

    public ChangeOrderBuilder setSubtotalJ(double subtotalJ)
    {
        this.subtotalJ = subtotalJ;
        return this;
    }

    public ChangeOrderBuilder setSubtotalL(double subtotalL)
    {
        this.subtotalL = subtotalL;
        return this;
    }

    public ChangeOrderBuilder setAdd(boolean add)
    {
        this.add = add;
        return this;
    }

    public ChangeOrderBuilder setDeduct(boolean deduct)
    {
        this.deduct = deduct;
        return this;
    }

    public ChangeOrder createChangeOrder()
    {
        return new ChangeOrder(changeOrderID, changeOrderStatusID, invoiceID, material, salesTax, directLabor, indirectCosts, equipmentToolsCosts, overheadCosts, subContracts, overheadPercentage, profitPercentage, bondPremiumPercentage, servicePercentage, finalTotal, exclusions, daysValid, contractExtension, notes, dateCreated, subtotalF, subtotalJ, subtotalL, add, deduct);
    }
}