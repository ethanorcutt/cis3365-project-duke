package simple;

/**
 * Created by Ethan Orcutt on 10/2/2016.
 */
public class JobDataSheet
{
    private int jobDataSheetID;
    private int jobDataSheetTypeID;
    private int supplierID;
    private String customerName;
    private String customerPhone;
    private String customerAddressLineOne;
    private String customerAddressLineTwo;
    private String customerCity;
    private String customerZipPostal;
    private int customerRegionID;
    private int customerCountryID;
    private String customerAccountNumber;
    private String customerSLSMNumber;
    private String jobName;
    private String jobAddressLineOne;
    private String jobAddressLineTwo;
    private String jobCity;
    private String jobZipPostal;
    private int jobRegionID;
    private int jobCountryID;
    private String jobNumber;
    private String jobSiteContactName;
    private String jobSiteContactNumber;
    private String generalName;
    private String generalAddressLineOne;
    private String generalAddressLineTwo;
    private String generalCity;
    private String generalZipPostal;
    private int generalRegionID;
    private int generalCountryID;
    private String generalPhoneNumber;
    private String generalFaxNumber;
    private String generalEmail;
    private String subName;
    private String subAddressLineOne;
    private String subAddressLineTwo;
    private String subCity;
    private String subZipPostal;
    private int subRegionID;
    private int subCountryID;
    private String subPhoneNumber;
    private String subFaxNumber;
    private String subEmail;
    private String propertyOwnerName;
    private String propertyOwnerAddressLineOne;
    private String propertyOwnerAddressLineTwo;
    private String propertyOwnerCity;
    private String propertyOwnerZipPostal;
    private int propertyOwnerRegionID;
    private int propertyOwnerCountryID;
    private String propertyOwnerPhoneNumber;
    private String propertyOwnerFaxNumber;
    private String propertyOwnerEmail;
    private String propertyLeaseHolderName;
    private String propertyLeaseHolderAddressLineOne;
    private String propertyLeaseHolderAddressLineTwo;
    private String propertyLeaseHolderCity;
    private String propertyLeaseHolderZipPostal;
    private int propertyLeaseHolderRegionID;
    private int propertyLeaseHolderCountryID;
    private String propertyLeaseHolderPhoneNumber;
    private String propertyLeaseHolderFaxNumber;
    private String propertyLeaseHolderEmail;
    private String bondSuretyName;
    private String bondSuretyAddressLineOne;
    private String bondSuretyAddressLineTwo;
    private String bondSuretyCity;
    private String bondSuretyZipPostal;
    private int bondSuretyRegionID;
    private int bondSuretyCountryID;
    private String bondSuretyPhoneNumber;
    private String bondSuretyFaxNumber;
    private String bondSuretyEmail;
    private String subSubName;
    private String subSubAddressLineOne;
    private String subSubAddressLineTwo;
    private String subSubCity;
    private String subSubZipPostal;
    private int subSubRegionID;
    private int subSubCountryID;
    private String subSubPhoneNumber;
    private String subSubFaxNumber;
    private String subSubEmail;
    private String bankName;
    private String bankCity;
    private String bankZipPostal;
    private int bankRegionID;
    private int bankCountryID;
    private double materialsEstimatedAmount;
    private String parcelNumber;
    private String parcelTrackAndLotNumber;
    private String todayDate;
    private String drawDate;
    private String apnNumber;
    private String expectedCompletionDate;
    private String descriptionOfProject;
    private String contractNumber;
    private boolean publicWorksJob;
    private int jobTypeID;
    private boolean taxExempt;

    public JobDataSheet(int jobDataSheetID, int jobDataSheetTypeID, int supplierID, String customerName,
                        String customerPhone, String customerAddressLineOne, String customerAddressLineTwo,
                        String customerCity, String customerZipPostal, int customerRegionID, int customerCountryID,
                        String customerAccountNumber, String customerSLSMNumber, String jobName,
                        String jobAddressLineOne, String jobAddressLineTwo, String jobCity, String jobZipPostal,
                        int jobRegionID, int jobCountryID, String jobNumber, String jobSiteContactName,
                        String jobSiteContactNumber, String generalName, String generalAddressLineOne,
                        String generalAddressLineTwo, String generalCity, String generalZipPostal,int generalRegionID,
                        int generalCountryID, String generalPhoneNumber, String generalFaxNumber, String generalEmail,
                        String subName, String subAddressLineOne, String subAddressLineTwo, String subCity,
                        String subZipPostal, int subRegionID, int subCountryID, String subPhoneNumber,
                        String subFaxNumber, String subEmail, String propertyOwnerName,
                        String propertyOwnerAddressLineOne, String propertyOwnerAddressLineTwo,
                        String propertyOwnerCity, String propertyOwnerZipPostal, int propertyOwnerRegionID,
                        int propertyOwnerCountryID, String propertyOwnerPhoneNumber, String propertyOwnerFaxNumber,
                        String propertyOwnerEmail, String propertyLeaseHolderName,
                        String propertyLeaseHolderAddressLineOne, String propertyLeaseHolderAddressLineTwo,
                        String propertyLeaseHolderCity, String propertyLeaseHolderZipPostal,
                        int propertyLeaseHolderRegionID, int propertyLeaseHolderCountryID,
                        String propertyLeaseHolderPhoneNumber, String propertyLeaseHolderFaxNumber,
                        String propertyLeaseHolderEmail, String bondSuretyName, String bondSuretyAddressLineOne,
                        String bondSuretyAddressLineTwo, String bondSuretyCity, String bondSuretyZipPostal,
                        int bondSuretyRegionID, int bondSuretyCountryID, String bondSuretyPhoneNumber,
                        String bondSuretyFaxNumber, String bondSuretyEmail, String subSubName,
                        String subSubAddressLineOne, String subSubAddressLineTwo, String subSubCity,
                        String subSubZipPostal, int subSubRegionID, int subSubCountryID, String subSubPhoneNumber,
                        String subSubFaxNumber, String subSubEmail, String bankName, String bankCity,
                        String bankZipPostal, int bankRegionID, int bankCountryID, double materialsEstimatedAmount,
                        String parcelNumber, String parcelTrackAndLotNumber, String todayDate, String drawDate,
                        String apnNumber, String expectedCompletionDate, String descriptionOfProject,
                        String contractNumber, boolean publicWorksJob, int jobTypeID, boolean taxExempt)
    {
        this.jobDataSheetID = jobDataSheetID;
        this.jobDataSheetTypeID = jobDataSheetTypeID;
        this.supplierID = supplierID;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerAddressLineOne = customerAddressLineOne;
        this.customerAddressLineTwo = customerAddressLineTwo;
        this.customerCity = customerCity;
        this.customerZipPostal = customerZipPostal;
        this.customerRegionID = customerRegionID;
        this.customerCountryID = customerCountryID;
        this.customerAccountNumber = customerAccountNumber;
        this.customerSLSMNumber = customerSLSMNumber;
        this.jobName = jobName;
        this.jobAddressLineOne = jobAddressLineOne;
        this.jobAddressLineTwo = jobAddressLineTwo;
        this.jobCity = jobCity;
        this.jobZipPostal = jobZipPostal;
        this.jobRegionID = jobRegionID;
        this.jobCountryID = jobCountryID;
        this.jobNumber = jobNumber;
        this.jobSiteContactName = jobSiteContactName;
        this.jobSiteContactNumber = jobSiteContactNumber;
        this.generalName = generalName;
        this.generalAddressLineOne = generalAddressLineOne;
        this.generalAddressLineTwo = generalAddressLineTwo;
        this.generalCity = generalCity;
        this.generalZipPostal = generalZipPostal;
        this.generalRegionID = generalRegionID;
        this.generalCountryID = generalCountryID;
        this.generalPhoneNumber = generalPhoneNumber;
        this.generalFaxNumber = generalFaxNumber;
        this.generalEmail = generalEmail;
        this.subName = subName;
        this.subAddressLineOne = subAddressLineOne;
        this.subAddressLineTwo = subAddressLineTwo;
        this.subCity = subCity;
        this.subZipPostal = subZipPostal;
        this.subRegionID = subRegionID;
        this.subCountryID = subCountryID;
        this.subPhoneNumber = subPhoneNumber;
        this.subFaxNumber = subFaxNumber;
        this.subEmail = subEmail;
        this.propertyOwnerName = propertyOwnerName;
        this.propertyOwnerAddressLineOne = propertyOwnerAddressLineOne;
        this.propertyOwnerAddressLineTwo = propertyOwnerAddressLineTwo;
        this.propertyOwnerCity = propertyOwnerCity;
        this.propertyOwnerZipPostal = propertyOwnerZipPostal;
        this.propertyOwnerRegionID = propertyOwnerRegionID;
        this.propertyOwnerCountryID = propertyOwnerCountryID;
        this.propertyOwnerPhoneNumber = propertyOwnerPhoneNumber;
        this.propertyOwnerFaxNumber = propertyOwnerFaxNumber;
        this.propertyOwnerEmail = propertyOwnerEmail;
        this.propertyLeaseHolderName = propertyLeaseHolderName;
        this.propertyLeaseHolderAddressLineOne = propertyLeaseHolderAddressLineOne;
        this.propertyLeaseHolderAddressLineTwo = propertyLeaseHolderAddressLineTwo;
        this.propertyLeaseHolderCity = propertyLeaseHolderCity;
        this.propertyLeaseHolderZipPostal = propertyLeaseHolderZipPostal;
        this.propertyLeaseHolderRegionID = propertyLeaseHolderRegionID;
        this.propertyLeaseHolderCountryID = propertyLeaseHolderCountryID;
        this.propertyLeaseHolderPhoneNumber = propertyLeaseHolderPhoneNumber;
        this.propertyLeaseHolderFaxNumber = propertyLeaseHolderFaxNumber;
        this.propertyLeaseHolderEmail = propertyLeaseHolderEmail;
        this.bondSuretyName = bondSuretyName;
        this.bondSuretyAddressLineOne = bondSuretyAddressLineOne;
        this.bondSuretyAddressLineTwo = bondSuretyAddressLineTwo;
        this.bondSuretyCity = bondSuretyCity;
        this.bondSuretyZipPostal = bondSuretyZipPostal;
        this.bondSuretyRegionID = bondSuretyRegionID;
        this.bondSuretyCountryID = bondSuretyCountryID;
        this.bondSuretyPhoneNumber = bondSuretyPhoneNumber;
        this.bondSuretyFaxNumber = bondSuretyFaxNumber;
        this.bondSuretyEmail = bondSuretyEmail;
        this.subSubName = subSubName;
        this.subSubAddressLineOne = subSubAddressLineOne;
        this.subSubAddressLineTwo = subSubAddressLineTwo;
        this.subSubCity = subSubCity;
        this.subSubZipPostal = subSubZipPostal;
        this.subSubRegionID = subSubRegionID;
        this.subSubCountryID = subSubCountryID;
        this.subSubPhoneNumber = subSubPhoneNumber;
        this.subSubFaxNumber = subSubFaxNumber;
        this.subSubEmail = subSubEmail;
        this.bankName = bankName;
        this.bankCity = bankCity;
        this.bankZipPostal = bankZipPostal;
        this.bankRegionID = bankRegionID;
        this.bankCountryID = bankCountryID;
        this.materialsEstimatedAmount = materialsEstimatedAmount;
        this.parcelNumber = parcelNumber;
        this.parcelTrackAndLotNumber = parcelTrackAndLotNumber;
        this.todayDate = todayDate;
        this.drawDate = drawDate;
        this.apnNumber = apnNumber;
        this.expectedCompletionDate = expectedCompletionDate;
        this.descriptionOfProject = descriptionOfProject;
        this.contractNumber = contractNumber;
        this.publicWorksJob = publicWorksJob;
        this.jobTypeID = jobTypeID;
        this.taxExempt = taxExempt;
    }

    public int getJobDataSheetID()
    {
        return jobDataSheetID;
    }

    public void setJobDataSheetID(int jobDataSheetID)
    {
        this.jobDataSheetID = jobDataSheetID;
    }

    public int getJobDataSheetTypeID()
    {
        return jobDataSheetTypeID;
    }

    public void setJobDataSheetTypeID(int jobDataSheetTypeID)
    {
        this.jobDataSheetTypeID = jobDataSheetTypeID;
    }

    public int getSupplierID()
    {
        return supplierID;
    }

    public void setSupplierID(int supplierID)
    {
        this.supplierID = supplierID;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getCustomerPhone()
    {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone)
    {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddressLineOne()
    {
        return customerAddressLineOne;
    }

    public void setCustomerAddressLineOne(String customerAddressLineOne)
    {
        this.customerAddressLineOne = customerAddressLineOne;
    }

    public String getCustomerAddressLineTwo()
    {
        return customerAddressLineTwo;
    }

    public void setCustomerAddressLineTwo(String customerAddressLineTwo)
    {
        this.customerAddressLineTwo = customerAddressLineTwo;
    }

    public String getCustomerCity()
    {
        return customerCity;
    }

    public void setCustomerCity(String customerCity)
    {
        this.customerCity = customerCity;
    }

    public String getCustomerZipPostal()
    {
        return customerZipPostal;
    }

    public void setCustomerZipPostal(String customerZipPostal)
    {
        this.customerZipPostal = customerZipPostal;
    }

    public int getCustomerRegionID()
    {
        return customerRegionID;
    }

    public void setCustomerRegionID(int customerRegionID)
    {
        this.customerRegionID = customerRegionID;
    }

    public int getCustomerCountryID()
    {
        return customerCountryID;
    }

    public void setCustomerCountryID(int customerCountryID)
    {
        this.customerCountryID = customerCountryID;
    }

    public String getCustomerAccountNumber()
    {
        return customerAccountNumber;
    }

    public void setCustomerAccountNumber(String customerAccountNumber)
    {
        this.customerAccountNumber = customerAccountNumber;
    }

    public String getCustomerSLSMNumber()
    {
        return customerSLSMNumber;
    }

    public void setCustomerSLSMNumber(String customerSLSMNumber)
    {
        this.customerSLSMNumber = customerSLSMNumber;
    }

    public String getJobName()
    {
        return jobName;
    }

    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }

    public String getJobAddressLineOne()
    {
        return jobAddressLineOne;
    }

    public void setJobAddressLineOne(String jobAddressLineOne)
    {
        this.jobAddressLineOne = jobAddressLineOne;
    }

    public String getJobAddressLineTwo()
    {
        return jobAddressLineTwo;
    }

    public void setJobAddressLineTwo(String jobAddressLineTwo)
    {
        this.jobAddressLineTwo = jobAddressLineTwo;
    }

    public String getJobCity()
    {
        return jobCity;
    }

    public void setJobCity(String jobCity)
    {
        this.jobCity = jobCity;
    }

    public String getJobZipPostal()
    {
        return jobZipPostal;
    }

    public void setJobZipPostal(String jobZipPostal)
    {
        this.jobZipPostal = jobZipPostal;
    }

    public int getJobRegionID()
    {
        return jobRegionID;
    }

    public void setJobRegionID(int jobRegionID)
    {
        this.jobRegionID = jobRegionID;
    }

    public int getJobCountryID()
    {
        return jobCountryID;
    }

    public void setJobCountryID(int jobCountryID)
    {
        this.jobCountryID = jobCountryID;
    }

    public String getJobNumber()
    {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber)
    {
        this.jobNumber = jobNumber;
    }

    public String getJobSiteContactName()
    {
        return jobSiteContactName;
    }

    public void setJobSiteContactName(String jobSiteContactName)
    {
        this.jobSiteContactName = jobSiteContactName;
    }

    public String getJobSiteContactNumber()
    {
        return jobSiteContactNumber;
    }

    public void setJobSiteContactNumber(String jobSiteContactNumber)
    {
        this.jobSiteContactNumber = jobSiteContactNumber;
    }

    public String getGeneralName()
    {
        return generalName;
    }

    public void setGeneralName(String generalName)
    {
        this.generalName = generalName;
    }

    public String getGeneralAddressLineOne()
    {
        return generalAddressLineOne;
    }

    public void setGeneralAddressLineOne(String generalAddressLineOne)
    {
        this.generalAddressLineOne = generalAddressLineOne;
    }

    public String getGeneralAddressLineTwo()
    {
        return generalAddressLineTwo;
    }

    public void setGeneralAddressLineTwo(String generalAddressLineTwo)
    {
        this.generalAddressLineTwo = generalAddressLineTwo;
    }

    public String getGeneralCity()
    {
        return generalCity;
    }

    public void setGeneralCity(String generalCity)
    {
        this.generalCity = generalCity;
    }

    public String getGeneralZipPostal()
    {
        return generalZipPostal;
    }

    public void setGeneralZipPostal(String generalZipPostal)
    {
        this.generalZipPostal = generalZipPostal;
    }

    public int getGeneralRegionID()
    {
        return generalRegionID;
    }

    public void setGeneralRegionID(int generalRegionID)
    {
        this.generalRegionID = generalRegionID;
    }

    public int getGeneralCountryID()
    {
        return generalCountryID;
    }

    public void setGeneralCountryID(int generalCountryID)
    {
        this.generalCountryID = generalCountryID;
    }

    public String getGeneralPhoneNumber()
    {
        return generalPhoneNumber;
    }

    public void setGeneralPhoneNumber(String generalPhoneNumber)
    {
        this.generalPhoneNumber = generalPhoneNumber;
    }

    public String getGeneralFaxNumber()
    {
        return generalFaxNumber;
    }

    public void setGeneralFaxNumber(String generalFaxNumber)
    {
        this.generalFaxNumber = generalFaxNumber;
    }

    public String getGeneralEmail()
    {
        return generalEmail;
    }

    public void setGeneralEmail(String generalEmail)
    {
        this.generalEmail = generalEmail;
    }

    public String getSubName()
    {
        return subName;
    }

    public void setSubName(String subName)
    {
        this.subName = subName;
    }

    public String getSubAddressLineOne()
    {
        return subAddressLineOne;
    }

    public void setSubAddressLineOne(String subAddressLineOne)
    {
        this.subAddressLineOne = subAddressLineOne;
    }

    public String getSubAddressLineTwo()
    {
        return subAddressLineTwo;
    }

    public void setSubAddressLineTwo(String subAddressLineTwo)
    {
        this.subAddressLineTwo = subAddressLineTwo;
    }

    public String getSubCity()
    {
        return subCity;
    }

    public void setSubCity(String subCity)
    {
        this.subCity = subCity;
    }

    public String getSubZipPostal()
    {
        return subZipPostal;
    }

    public void setSubZipPostal(String subZipPostal)
    {
        this.subZipPostal = subZipPostal;
    }

    public int getSubRegionID()
    {
        return subRegionID;
    }

    public void setSubRegionID(int subRegionID)
    {
        this.subRegionID = subRegionID;
    }

    public int getSubCountryID()
    {
        return subCountryID;
    }

    public void setSubCountryID(int subCountryID)
    {
        this.subCountryID = subCountryID;
    }

    public String getSubPhoneNumber()
    {
        return subPhoneNumber;
    }

    public void setSubPhoneNumber(String subPhoneNumber)
    {
        this.subPhoneNumber = subPhoneNumber;
    }

    public String getSubFaxNumber()
    {
        return subFaxNumber;
    }

    public void setSubFaxNumber(String subFaxNumber)
    {
        this.subFaxNumber = subFaxNumber;
    }

    public String getSubEmail()
    {
        return subEmail;
    }

    public void setSubEmail(String subEmail)
    {
        this.subEmail = subEmail;
    }

    public String getPropertyOwnerName()
    {
        return propertyOwnerName;
    }

    public void setPropertyOwnerName(String propertyOwnerName)
    {
        this.propertyOwnerName = propertyOwnerName;
    }

    public String getPropertyOwnerAddressLineOne()
    {
        return propertyOwnerAddressLineOne;
    }

    public void setPropertyOwnerAddressLineOne(String propertyOwnerAddressLineOne)
    {
        this.propertyOwnerAddressLineOne = propertyOwnerAddressLineOne;
    }

    public String getPropertyOwnerAddressLineTwo()
    {
        return propertyOwnerAddressLineTwo;
    }

    public void setPropertyOwnerAddressLineTwo(String propertyOwnerAddressLineTwo)
    {
        this.propertyOwnerAddressLineTwo = propertyOwnerAddressLineTwo;
    }

    public String getPropertyOwnerCity()
    {
        return propertyOwnerCity;
    }

    public void setPropertyOwnerCity(String propertyOwnerCity)
    {
        this.propertyOwnerCity = propertyOwnerCity;
    }

    public String getPropertyOwnerZipPostal()
    {
        return propertyOwnerZipPostal;
    }

    public void setPropertyOwnerZipPostal(String propertyOwnerZipPostal)
    {
        this.propertyOwnerZipPostal = propertyOwnerZipPostal;
    }

    public int getPropertyOwnerRegionID()
    {
        return propertyOwnerRegionID;
    }

    public void setPropertyOwnerRegionID(int propertyOwnerRegionID)
    {
        this.propertyOwnerRegionID = propertyOwnerRegionID;
    }

    public int getPropertyOwnerCountryID()
    {
        return propertyOwnerCountryID;
    }

    public void setPropertyOwnerCountryID(int propertyOwnerCountryID)
    {
        this.propertyOwnerCountryID = propertyOwnerCountryID;
    }

    public String getPropertyOwnerPhoneNumber()
    {
        return propertyOwnerPhoneNumber;
    }

    public void setPropertyOwnerPhoneNumber(String propertyOwnerPhoneNumber)
    {
        this.propertyOwnerPhoneNumber = propertyOwnerPhoneNumber;
    }

    public String getPropertyOwnerFaxNumber()
    {
        return propertyOwnerFaxNumber;
    }

    public void setPropertyOwnerFaxNumber(String propertyOwnerFaxNumber)
    {
        this.propertyOwnerFaxNumber = propertyOwnerFaxNumber;
    }

    public String getPropertyOwnerEmail()
    {
        return propertyOwnerEmail;
    }

    public void setPropertyOwnerEmail(String propertyOwnerEmail)
    {
        this.propertyOwnerEmail = propertyOwnerEmail;
    }

    public String getPropertyLeaseHolderName()
    {
        return propertyLeaseHolderName;
    }

    public void setPropertyLeaseHolderName(String propertyLeaseHolderName)
    {
        this.propertyLeaseHolderName = propertyLeaseHolderName;
    }

    public String getPropertyLeaseHolderAddressLineOne()
    {
        return propertyLeaseHolderAddressLineOne;
    }

    public void setPropertyLeaseHolderAddressLineOne(String propertyLeaseHolderAddressLineOne)
    {
        this.propertyLeaseHolderAddressLineOne = propertyLeaseHolderAddressLineOne;
    }

    public String getPropertyLeaseHolderAddressLineTwo()
    {
        return propertyLeaseHolderAddressLineTwo;
    }

    public void setPropertyLeaseHolderAddressLineTwo(String propertyLeaseHolderAddressLineTwo)
    {
        this.propertyLeaseHolderAddressLineTwo = propertyLeaseHolderAddressLineTwo;
    }

    public String getPropertyLeaseHolderCity()
    {
        return propertyLeaseHolderCity;
    }

    public void setPropertyLeaseHolderCity(String propertyLeaseHolderCity)
    {
        this.propertyLeaseHolderCity = propertyLeaseHolderCity;
    }

    public String getPropertyLeaseHolderZipPostal()
    {
        return propertyLeaseHolderZipPostal;
    }

    public void setPropertyLeaseHolderZipPostal(String propertyLeaseHolderZipPostal)
    {
        this.propertyLeaseHolderZipPostal = propertyLeaseHolderZipPostal;
    }

    public int getPropertyLeaseHolderRegionID()
    {
        return propertyLeaseHolderRegionID;
    }

    public void setPropertyLeaseHolderRegionID(int propertyLeaseHolderRegionID)
    {
        this.propertyLeaseHolderRegionID = propertyLeaseHolderRegionID;
    }

    public int getPropertyLeaseHolderCountryID()
    {
        return propertyLeaseHolderCountryID;
    }

    public void setPropertyLeaseHolderCountryID(int propertyLeaseHolderCountryID)
    {
        this.propertyLeaseHolderCountryID = propertyLeaseHolderCountryID;
    }

    public String getPropertyLeaseHolderPhoneNumber()
    {
        return propertyLeaseHolderPhoneNumber;
    }

    public void setPropertyLeaseHolderPhoneNumber(String propertyLeaseHolderPhoneNumber)
    {
        this.propertyLeaseHolderPhoneNumber = propertyLeaseHolderPhoneNumber;
    }

    public String getPropertyLeaseHolderFaxNumber()
    {
        return propertyLeaseHolderFaxNumber;
    }

    public void setPropertyLeaseHolderFaxNumber(String propertyLeaseHolderFaxNumber)
    {
        this.propertyLeaseHolderFaxNumber = propertyLeaseHolderFaxNumber;
    }

    public String getPropertyLeaseHolderEmail()
    {
        return propertyLeaseHolderEmail;
    }

    public void setPropertyLeaseHolderEmail(String propertyLeaseHolderEmail)
    {
        this.propertyLeaseHolderEmail = propertyLeaseHolderEmail;
    }

    public String getBondSuretyName()
    {
        return bondSuretyName;
    }

    public void setBondSuretyName(String bondSuretyName)
    {
        this.bondSuretyName = bondSuretyName;
    }

    public String getBondSuretyAddressLineOne()
    {
        return bondSuretyAddressLineOne;
    }

    public void setBondSuretyAddressLineOne(String bondSuretyAddressLineOne)
    {
        this.bondSuretyAddressLineOne = bondSuretyAddressLineOne;
    }

    public String getBondSuretyAddressLineTwo()
    {
        return bondSuretyAddressLineTwo;
    }

    public void setBondSuretyAddressLineTwo(String bondSuretyAddressLineTwo)
    {
        this.bondSuretyAddressLineTwo = bondSuretyAddressLineTwo;
    }

    public String getBondSuretyCity()
    {
        return bondSuretyCity;
    }

    public void setBondSuretyCity(String bondSuretyCity)
    {
        this.bondSuretyCity = bondSuretyCity;
    }

    public String getBondSuretyZipPostal()
    {
        return bondSuretyZipPostal;
    }

    public void setBondSuretyZipPostal(String bondSuretyZipPostal)
    {
        this.bondSuretyZipPostal = bondSuretyZipPostal;
    }

    public int getBondSuretyRegionID()
    {
        return bondSuretyRegionID;
    }

    public void setBondSuretyRegionID(int bondSuretyRegionID)
    {
        this.bondSuretyRegionID = bondSuretyRegionID;
    }

    public int getBondSuretyCountryID()
    {
        return bondSuretyCountryID;
    }

    public void setBondSuretyCountryID(int bondSuretyCountryID)
    {
        this.bondSuretyCountryID = bondSuretyCountryID;
    }

    public String getBondSuretyPhoneNumber()
    {
        return bondSuretyPhoneNumber;
    }

    public void setBondSuretyPhoneNumber(String bondSuretyPhoneNumber)
    {
        this.bondSuretyPhoneNumber = bondSuretyPhoneNumber;
    }

    public String getBondSuretyFaxNumber()
    {
        return bondSuretyFaxNumber;
    }

    public void setBondSuretyFaxNumber(String bondSuretyFaxNumber)
    {
        this.bondSuretyFaxNumber = bondSuretyFaxNumber;
    }

    public String getBondSuretyEmail()
    {
        return bondSuretyEmail;
    }

    public void setBondSuretyEmail(String bondSuretyEmail)
    {
        this.bondSuretyEmail = bondSuretyEmail;
    }

    public String getSubSubName()
    {
        return subSubName;
    }

    public void setSubSubName(String subSubName)
    {
        this.subSubName = subSubName;
    }

    public String getSubSubAddressLineOne()
    {
        return subSubAddressLineOne;
    }

    public void setSubSubAddressLineOne(String subSubAddressLineOne)
    {
        this.subSubAddressLineOne = subSubAddressLineOne;
    }

    public String getSubSubAddressLineTwo()
    {
        return subSubAddressLineTwo;
    }

    public void setSubSubAddressLineTwo(String subSubAddressLineTwo)
    {
        this.subSubAddressLineTwo = subSubAddressLineTwo;
    }

    public String getSubSubCity()
    {
        return subSubCity;
    }

    public void setSubSubCity(String subSubCity)
    {
        this.subSubCity = subSubCity;
    }

    public String getSubSubZipPostal()
    {
        return subSubZipPostal;
    }

    public void setSubSubZipPostal(String subSubZipPostal)
    {
        this.subSubZipPostal = subSubZipPostal;
    }

    public int getSubSubRegionID()
    {
        return subSubRegionID;
    }

    public void setSubSubRegionID(int subSubRegionID)
    {
        this.subSubRegionID = subSubRegionID;
    }

    public int getSubSubCountryID()
    {
        return subSubCountryID;
    }

    public void setSubSubCountryID(int subSubCountryID)
    {
        this.subSubCountryID = subSubCountryID;
    }

    public String getSubSubPhoneNumber()
    {
        return subSubPhoneNumber;
    }

    public void setSubSubPhoneNumber(String subSubPhoneNumber)
    {
        this.subSubPhoneNumber = subSubPhoneNumber;
    }

    public String getSubSubFaxNumber()
    {
        return subSubFaxNumber;
    }

    public void setSubSubFaxNumber(String subSubFaxNumber)
    {
        this.subSubFaxNumber = subSubFaxNumber;
    }

    public String getSubSubEmail()
    {
        return subSubEmail;
    }

    public void setSubSubEmail(String subSubEmail)
    {
        this.subSubEmail = subSubEmail;
    }

    public String getBankName()
    {
        return bankName;
    }

    public void setBankName(String bankName)
    {
        this.bankName = bankName;
    }

    public String getBankCity()
    {
        return bankCity;
    }

    public void setBankCity(String bankCity)
    {
        this.bankCity = bankCity;
    }

    public String getBankZipPostal()
    {
        return bankZipPostal;
    }

    public void setBankZipPostal(String bankZipPostal)
    {
        this.bankZipPostal = bankZipPostal;
    }

    public int getBankRegionID()
    {
        return bankRegionID;
    }

    public void setBankRegionID(int bankRegionID)
    {
        this.bankRegionID = bankRegionID;
    }

    public int getBankCountryID()
    {
        return bankCountryID;
    }

    public void setBankCountryID(int bankCountryID)
    {
        this.bankCountryID = bankCountryID;
    }

    public double getMaterialsEstimatedAmount()
    {
        return materialsEstimatedAmount;
    }

    public void setMaterialsEstimatedAmount(double materialsEstimatedAmount)
    {
        this.materialsEstimatedAmount = materialsEstimatedAmount;
    }

    public String getParcelNumber()
    {
        return parcelNumber;
    }

    public void setParcelNumber(String parcelNumber)
    {
        this.parcelNumber = parcelNumber;
    }

    public String getParcelTrackAndLotNumber()
    {
        return parcelTrackAndLotNumber;
    }

    public void setParcelTrackAndLotNumber(String parcelTrackAndLotNumber)
    {
        this.parcelTrackAndLotNumber = parcelTrackAndLotNumber;
    }

    public String getTodayDate()
    {
        return todayDate;
    }

    public void setTodayDate(String todayDate)
    {
        this.todayDate = todayDate;
    }

    public String getDrawDate()
    {
        return drawDate;
    }

    public void setDrawDate(String drawDate)
    {
        this.drawDate = drawDate;
    }

    public String getApnNumber()
    {
        return apnNumber;
    }

    public void setApnNumber(String apnNumber)
    {
        this.apnNumber = apnNumber;
    }

    public String getExpectedCompletionDate()
    {
        return expectedCompletionDate;
    }

    public void setExpectedCompletionDate(String expectedCompletionDate)
    {
        this.expectedCompletionDate = expectedCompletionDate;
    }

    public String getDescriptionOfProject()
    {
        return descriptionOfProject;
    }

    public void setDescriptionOfProject(String descriptionOfProject)
    {
        this.descriptionOfProject = descriptionOfProject;
    }

    public String getContractNumber()
    {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber)
    {
        this.contractNumber = contractNumber;
    }

    public boolean isPublicWorksJob()
    {
        return publicWorksJob;
    }

    public void setPublicWorksJob(boolean publicWorksJob)
    {
        this.publicWorksJob = publicWorksJob;
    }

    public int getJobTypeID()
    {
        return jobTypeID;
    }

    public void setJobTypeID(int jobTypeID)
    {
        this.jobTypeID = jobTypeID;
    }

    public boolean isTaxExempt()
    {
        return taxExempt;
    }

    public void setTaxExempt(boolean taxExempt)
    {
        this.taxExempt = taxExempt;
    }
}