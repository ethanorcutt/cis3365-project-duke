package com.nicholsonplumbingtx.v2.controller.main_form_controller;

import com.nicholsonplumbingtx.v2.controller.common_controller.ScreenLoader;
import com.nicholsonplumbingtx.v2.database_connector.ReportDBConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ethan Orcutt on 11/6/2016.
 * Project D.U.K.E.
 */
public class ReportController extends ScreenLoader implements Initializable
{
    /* Object that handles all the connection information to the database */
    private ReportDBConnection eC;
    private String query;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        establishConnection();
    }

    private void establishConnection()
    {
        try
        {
            eC = new ReportDBConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /* Start Michelle Reports */

    @FXML
    void showClientInformation()
    {
        query = "SELECT\n" +
                "\tc.company_name AS [Client Name],\n" +
                "\tclS.title AS [Client Status],\n" +
                "\tc.address_line_one + ',  ' + c.city+ ',  ' +c.postal_zip_code [Address],\n" +
                "\tr.NAME as [Region Name],\n" +
                "\tcc.country_name AS [Country Name],\n" +
                "\tlC.first_name + '  ' + lC.last_name AS [Contact Name],\n" +
                "\tcTy.title AS [Contact Type],\n" +
                "\tcSt.title  AS [Contact Status],\n" +
                "'(' + LEFT(lc.phone_number, 3) + ') ' + SUBSTRING(lc.phone_number, 4,3) + '-' + RIGHT(lc.phone_number,4) AS [Phone Number]\n" +
                "\n" +
                "FROM Client as c\n" +
                "JOIN ClientStatus as clS ON c.client_status_id = clS.client_status_id\n" +
                "JOIN ClientContact as lC ON c.client_id = lC.client_id\n" +
                "JOIN ClientAssignedContact as LAC ON lC.client_contact_id = LAC.client_contact_id\n" +
                "JOIN ContactType as cTy ON LAC.contact_type_id = cTy.contact_type_id\n" +
                "JOIN ContactStatus as cSt ON LAC.contact_status_id = cSt.contact_status_id\n" +
                "JOIN Region as r ON c.region_id = r.region_id\n" +
                "JOIN Country as cc on c.country_id = cc.country_id\n" +
                "\n" +
                "WHERE \n" +
                "cSt.title = 'active'\n" +
                "\n" +
                "ORDER BY c.company_name;";

        eC.runReport(query);
    }

    @FXML
    void showGeneralClientContact()
    {
        query = "SELECT\n" +
                "c.company_name as [Company Name],\n" +
                "cc.first_name + ' '+ cc.last_name as [Main Contact Name],\n" +
                "ct.title as [Contact Type],\n" +
                "ct.description as [Contact Description],\n" +
                "'(' + LEFT(cc.phone_number, 3) + ') ' + SUBSTRING(cc.phone_number, 4,3) + '-' +RIGHT(cc.phone_number,4) AS[Phone Number],\n" +
                "cac.date_assigned as [Date Assigned],\n" +
                "cs.title as [Contact Status]\n" +
                "\n" +
                "FROM Client as c\n" +
                "JOIN ClientContact as cc on c.client_id = cc.client_id\n" +
                "JOIN ClientAssignedContact as cac on cc.client_contact_id = cac.client_contact_id\n" +
                "JOIN ContactType as ct on cac.contact_type_id = ct.contact_type_id\n" +
                "JOIN ContactStatus as cs on cac.contact_status_id = cs.contact_status_id\n" +
                "\n" +
                "WHERE\n" +
                "cs.title = 'active'\n" +
                "and ct.title = 'Superintendent' \n" +
                "\n" +
                "ORDER BY \n" +
                "c.company_name, ct.title;";

        eC.runReport(query);
    }

    @FXML
    void showVendorsPerRegion()
    {
        query = "SELECT\n" +
                "\tr.NAME AS [Region Name],\n" +
                "\tc.country_name as [Country Name],\n" +
                "\tCOUNT(r.NAME) AS [Number of Vendors in Region],\n" +
                "\tvs.title as [Vendor Status]\n" +
                "\n" +
                "FROM  Region as r\n" +
                "JOIN Country as c on r.country_id = c.country_id\n" +
                "JOIN Vendor as v on  r.region_id = v.region_id\n" +
                "JOIN VendorStatus as vs on v.vendor_status_id = vs.vendor_status_id\n" +
                "\n" +
                "where vs.title = 'approved'\n" +
                "\n" +
                "GROUP BY r.NAME,c.country_name,vs.title\n" +
                "\n" +
                "ORDER BY c.country_name,r.NAME;";

        eC.runReport(query);
    }

    @FXML
    void showProjectInformation()
    {
        query = "SELECT\n" +
                "c.company_name as [Company Name],\n" +
                "p.project_name as [Project Name],\n" +
                "ps.title as [Project Status],\n" +
                "p.project_notes [Description],\n" +
                "p.project_bid_date as [Bid Date],\n" +
                "p.project_start_date as [Start Date], \n" +
                "'$' + convert(varchar(20), i.total_due) as [Total Due], \n" +
                "'$' + convert(varchar(20), i.total_still_due)  as [Total Still Due]\n" +
                "\n" +
                "FROM Client as c\n" +
                "JOIN Project as p ON c.client_id = p.client_id\n" +
                "JOIN ProjectStatus as PS ON p.project_status_id = ps.project_status_id\n" +
                "JOIN Invoice as i on p.project_id = i.project_id\n" +
                "\n" +
                "where \n" +
                "ps.title = 'active'\n" +
                "\n" +
                "ORDER BY c.company_name,p.project_name;";

        eC.runReport(query);
    }

    @FXML
    void showProjectStatusAndContact()
    {
        String query = "SELECT\n" +
                "c.company_name AS [Company Name],\n" +
                "p.project_name as [Project Name],\n" +
                "ps.title as [Project Status],\n" +
                "pc.first_name + ' ' + pc.last_name AS [Project Contact Name],\n" +
                "'(' + LEFT(pc.phone_number, 3) + ') ' + SUBSTRING(pc.phone_number, 4,3) + '-' + RIGHT(pc.phone_number,4) AS [Project Contact Phone Number],\n" +
                "lC.first_name +' ' +lC.last_name AS [Client Contact Name],\n" +
                "'(' + LEFT(lc.phone_number, 3) + ') ' + SUBSTRING(lc.phone_number, 4,3) + '-' + RIGHT(lc.phone_number,4) AS [Client Contact Number],\n" +
                "cs.title as [Contact Status]\n" +
                "\n" +
                "\n" +
                "FROM Client as c\n" +
                "JOIN Project as p ON c.client_id = p.client_id\n" +
                "JOIN ProjectContact as PC ON p.project_id = PC.project_id\n" +
                "JOIN ProjectStatus as PS ON p.project_status_id = ps.project_status_id\n" +
                "JOIN ClientContact as LC ON c.client_id = LC.client_id\n" +
                "JOIN ClientAssignedContact as LAC ON LC.client_contact_id = LAC.client_contact_id\n" +
                "JOIN ContactStatus as cs ON LAC.contact_status_id = cs.contact_status_id\n" +
                "\n" +
                "WHERE \n" +
                "cs.title  = 'active' \n" +
                "and ps.title = 'active'\n" +
                "\n" +
                "ORDER BY c.company_name;";

        eC.runReport(query);
    }

    @FXML
    void showClientContactInformation()
    {
        String query = "SELECT\n" +
                "c.company_name AS [Company Name],\n" +
                "cc.first_name + ' ' + cc.last_name AS [Contact Name],\n" +
                "ct.title AS [Contact Type],\n" +
                "cs.title as [Contact Status],\n" +
                "p.project_name as [Project Assigned To],\n" +
                "ps.title as [Project Status],\n" +
                "cc.email as [Email],\n" +
                "'(' + LEFT(cc.phone_number, 3) + ') ' + SUBSTRING(cc.phone_number, 4,3) + '-' + RIGHT(cc.phone_number,4) AS [Phone Number]\n" +
                "\n" +
                "\n" +
                "FROM Client as c\n" +
                "JOIN Project as p ON c.client_id = p.client_id\n" +
                "JOIN ClientContact as CC ON c.client_id = CC.client_id\n" +
                "JOIN ClientAssignedContact as CAC ON CC.client_contact_id = CAC.client_contact_id\n" +
                "JOIN ContactStatus as cs ON CAC.contact_status_id = cs.contact_status_id\n" +
                "JOIN ContactType as ct ON CAC.contact_type_id= ct.contact_type_id\n" +
                "JOIN ProjectStatus as ps ON p.project_status_id = ps.project_status_id\n" +
                "\n" +
                "where\n" +
                "cs.title = 'active'\n" +
                "and ps.title = 'active'\n" +
                "\n" +
                "ORDER BY c.company_name, cs.title, p.project_name;";

        eC.runReport(query);
    }

    /* End Michelle Reports */

    /* Start Aaron Reports */

    @FXML
    void showUnassignedActiveEmployees()
    {
        String query = "SELECT\n" +
                "\teid.first_name AS [First Name],\n" +
                "\teid.last_name AS [Last Name],\n" +
                "\tesid.title AS [Employee Status],\n" +
                "\tpid.project_name AS [Project Name],\n" +
                "\tedate.entry_date AS [Date Hired]\n" +
                "\t\n" +
                "\n" +
                "FROM ProjectEmployee AS peid\n" +
                "FULL OUTER JOIN Employee AS eid ON eid.employee_id = peid.employee_id\n" +
                "LEFT JOIN Project AS pid ON pid.project_id = peid.project_id\n" +
                "JOIN EmployeeStatus AS esid ON eid.employee_status_id = esid.employee_status_id\n" +
                "JOIN EmployeeHistory AS edate ON eid.employee_id = edate.employee_id\n" +
                "\n" +
                "WHERE pid.project_name IS NULL\n" +
                "\n" +
                "ORDER BY [Employee Status];";

        eC.runReport(query);
    }

    @FXML
    void showEmployeeInformation()
    {
        String query = "SELECT \n" + "\teid.employee_id AS [Employee ID],\n" + "\teid.last_name AS [Last Name], \n" + "\teid.first_name AS [First Name],\n" + "\tsta.title AS [Employee Status],\n" + "\teid.address_line_one AS [Address Line 1],\n" + "\teid.address_line_two AS [Address Line 2],\n" + "\teid.city AS [City],\n" + "\teid.postal_zip_code AS [Postal Zip Code],\n" + "\tregid.name AS [Region],\n" + "\tcouid.country_name AS [Country],\n" + "\teid.phone_number AS [Phone Number],\n" + "\teid.email AS [Email],\n" + "\ttyp.title AS [Employee Type],\n" + "\tedate.entry_date AS [Date Hired]\n" + "\n" + "FROM Employee AS eid\n" + "JOIN EmployeeStatus AS sta ON eid.employee_status_id = sta.employee_status_id\n" + "JOIN EmployeeType AS typ ON eid.employee_type_id = typ.employee_type_id\n" + "JOIN EmployeeHistory AS edate ON eid.employee_id = edate.employee_id\n" + "JOIN Region AS regid ON eid.region_id = regid.region_id\n" + "JOIN Country AS couid ON eid.country_id = couid.country_id\n" + " \n" + "WHERE sta.title = 'Contractor'\n" + "\n" + "ORDER BY typ.title, sta.title;";

        eC.runReport(query);
    }

    @FXML
    void showHistoryLog()
    {
        String query = "SELECT\n" + "\tCONCAT(eid.first_name, ' ', eid.last_name) AS [Name],\n" + "\tetid.title AS [Occupation],\n" + "\tsalFreq.title AS [Salary Frequency],\n" + "\tehist.salary AS [Salary Amount],\n" + "\tehist.entry_date AS [Date Hired],\n" + "\tehist.description AS [History Log]\n" + "\n" + "FROM Employee AS eid\n" + "JOIN EmployeeType AS etid ON eid.employee_type_id = etid.employee_type_id\n" + "JOIN SalaryFrequency AS salFreq ON eid.salary_frequency_id = salFreq.salary_frequency_id \n" + "JOIN EmployeeHistory AS ehist ON eid.employee_id = ehist.employee_id\n" + "\n" + "WHERE etid.title = 'Journeyman Plumber'\n" + "\n" + "ORDER BY [Date Hired];";
        eC.runReport(query);
    }

    @FXML
    void showEmployeeActiveProjects()
    {
        String query = "SELECT \n" + "\teid.first_name AS [First Name],\n" + "\teid.last_name AS [Last Name],\n" + "\tpid.project_name AS [Project Name],\n" + "\tpid.project_start_date AS [Project Start Date],\n" + "\tpsid.title AS [Project Status]\n" + "\n" + "FROM ProjectEmployee AS peid\n" + "JOIN Employee AS eid ON peid.employee_id = eid.employee_id\n" + "JOIN Project AS pid ON pid.project_id = peid.project_id\n" + "JOIN ProjectStatus AS psid ON pid.project_status_id = psid.project_status_id\n" + "\n" + "WHERE psid.title = 'Active'\n" + "\n" + "ORDER BY pid.project_name, [Project Status];";
        eC.runReport(query);
    }

    @FXML
    void showEmployeeActiveVehicle()
    {
        String query = "SELECT\n" + "\tCONCAT(eid.first_name, ' ', eid.last_name) AS [Name],\n" + "\tevid.date_assigned AS [Date Assigned],\n" + "\tevid.notes AS [Notes],\n" + "\tmA.title AS [Make],\n" + "\tmO.title AS [Model],\n" + "\tvid.license_plate AS [License Plate],\n" + "\tvsid.title AS [Vehicle Status]\n" + "\n" + "FROM EmployeeVehicle AS evid\n" + "JOIN Employee AS eid ON eid.employee_id = evid.employee_id\n" + "JOIN Vehicle AS vid ON vid.vehicle_id = evid.vehicle_id\n" + "JOIN VehicleStatus AS vsid ON vid.vehicle_status_id = vsid.vehicle_status_id\n" + "JOIN Make AS mA ON vid.make_id = mA.make_id\n" + "JOIN Model AS mO ON vid.model_id = mO.model_id\n" + "\n" + "WHERE vsid.title = 'Active'\n" + "\n" + "ORDER BY [License Plate];";
        eC.runReport(query);
    }

    @FXML
    void showVehiclesInUse()
    {
        String query = "SELECT \n" + "CONCAT(eid.first_name, ' ', eid.last_name) AS [Name],\n" + "evid.date_assigned AS [Date Assigned],\n" + "evid.date_returned AS [Date Returned],\n" + "mA.title AS [Make],\n" + "mO.title AS [Model],\n" + "vsid.title AS [Vehicle Status]\n" + "\n" + "FROM EmployeeVehicle AS evid\n" + "JOIN Employee AS eid ON eid.employee_id = evid.employee_id\n" + "JOIN Vehicle AS vid ON vid.vehicle_id = evid.vehicle_id\n" + "JOIN VehicleStatus AS vsid ON vid.vehicle_status_id = vsid.vehicle_status_id\n" + "JOIN Make AS mA ON vid.make_id = mA.make_id\n" + "JOIN Model AS mO ON vid.model_id = mO.model_id\n" + "\n" + "WHERE evid.date_returned IS NULL\n" + "\n" + "ORDER BY [Vehicle Status];";
        eC.runReport(query);
    }

    /* End Aaron Reports */

    /* Start Chris Reports */

    @FXML
    void showActiveVehiclesPerProject()
    {
        String query = "SELECT\n" + " VehicleStatus.title as Status, Vehicle.license_plate as LicensePlate, EmployeeVehicle.employee_id as EmployeeID, Employee.last_name as LastName, Project.project_name\n" + "\n" + "FROM\n" + "Employee inner join EmployeeVehicle on Employee.employee_id = EmployeeVehicle.employee_id\n" + " \n" + "inner join Vehicle on EmployeeVehicle.vehicle_id = Vehicle.vehicle_id\n" + "\n" + "inner join VehicleStatus on Vehicle.vehicle_status_id = VehicleStatus.vehicle_status_id\n" + "\n" + "inner join ProjectEmployee on Employee.employee_id = ProjectEmployee.employee_id\n" + "\n" + "inner join Project on ProjectEmployee.project_id = Project.project_id\n" + "\n" + "WHERE VehicleStatus.title = 'Active'\n" + "\n" + "ORDER BY Project.project_name";

        eC.runReport(query);
    }

    @FXML
    void showVehicleEquipmentLog()
    {
        String query = "SELECT\n" + "EquipmentType.title, Equipment.equipment_id, VehicleEquipment.date_assigned, Vehicle.license_plate\n" + "\n" + "FROM\n" + "\n" + "Vehicle inner join VehicleEquipment on vehicle.vehicle_id = VehicleEquipment.vehicle_id\n" + "\n" + "inner join Equipment on VehicleEquipment.equipment_id = Equipment.equipment_id\n" + "\n" + "inner join EquipmentType on Equipment.equipment_type_id = EquipmentType.equipment_type_id";

        eC.runReport(query);
    }

    @FXML
    void showProjectMaterialCost()
    {
        String query = "SELECT \n" + "\n" + "JobDataSheet.job_name As [Job Name] , Project.project_name as [Project Name], '$'+ convert(varchar(20),JobDataSheet.materials_estimated_amount) AS [Required Material Amount],\n" + "Invoice.invoice_id as [Invoice Number]\n" + "\n" + "FROM \n" + "\n" + "Invoice join Project on Invoice.project_id = Project.project_id\n" + "\n" + "join ProjectSupplier on Project.project_id = ProjectSupplier.project_id\n" + "\n" + "join Supplier on ProjectSupplier.supplier_id = Supplier.supplier_id\n" + "\n" + "join JobDataSheet on Supplier.supplier_id = JobDataSheet.supplier_id\n" + "\n" + "Order by Project.project_name";

        eC.runReport(query);
    }

    @FXML
    void showSupplierMostUsed()
    {
        String query = "SELECT \n" + "\n" + "Supplier.company_name, COUNT(Supplier.company_name) as [Number of times used], '$' + REPLACE(CONVERT(varchar(20), (CAST(SUM(JobDataSheet.materials_estimated_amount) AS money)),1), '.00','') AS [Dollar Value]\n" + "\n" + "FROM\n" + "\n" + "Supplier join JobDataSheet on Supplier.supplier_id = JobDataSheet.supplier_id\n" + "\n" + "\n" + "GROUP BY Supplier.company_name \n" + "\n" + "Order by [Number of times used] DESC";

        eC.runReport(query);
    }

    @FXML
    void showExpectedCompletionDate()
    {
        String query = "SELECT\n" + "\n" + "JobDataSheet.job_name, JobDataSheet.expected_completion_date, Supplier.phone_number, PropertyOwner.property_owner_name, PropertyOwner.address_line_one, Project.project_name\n" + "\n" + "FROM\n" + "\n" + "JobDataSheet join Supplier on JobDataSheet.supplier_id = Supplier.supplier_id\n" + "\n" + "join PropertyOwner on JobDataSheet.property_owner_id = PropertyOwner.property_owner_id\n" + "\n" + "join ProjectSupplier on Supplier.supplier_id = ProjectSupplier.supplier_id\n" + "\n" + "join Project on ProjectSupplier.project_id = Project.project_id\n" + "\n" + "join ProjectStatus on Project.project_status_id = ProjectStatus.project_status_id\n" + "\n" + "WHERE \n" + "ProjectStatus.title = 'Active'\n" + "\n" + "ORDER BY \n" + "\n" + "JobDataSheet.expected_completion_date ";

        eC.runReport(query);
    }

    @FXML
    void showAllProjectStatus()
    {
        String query = "SELECT\n" + "\n" + "JobDataSheet.job_name, JobDataSheet.expected_completion_date, Supplier.phone_number, PropertyOwner.property_owner_name, PropertyOwner.address_line_one, Project.project_name\n" + "\n" + "FROM\n" + "\n" + "JobDataSheet join Supplier on JobDataSheet.supplier_id = Supplier.supplier_id\n" + "\n" + "join PropertyOwner on JobDataSheet.property_owner_id = PropertyOwner.property_owner_id\n" + "\n" + "join ProjectSupplier on Supplier.supplier_id = ProjectSupplier.supplier_id\n" + "\n" + "join Project on ProjectSupplier.project_id = Project.project_id\n" + "\n" + "join ProjectStatus on Project.project_status_id = ProjectStatus.project_status_id\n" + "\n" + "WHERE \n" + "ProjectStatus.title = 'Active'\n" + "\n" + "ORDER BY \n" + "\n" + "JobDataSheet.expected_completion_date ";

        eC.runReport(query);
    }

    /* End Chris Reports */

    /* Start Ethan Reports */

    @FXML
    void showQuickJDS()
    {
        query = "SELECT\n" +
                "\tp.project_name AS [Project],\n" +
                "\tjT.title AS [Job Type],\n" +
                "\tb.bank_name AS [Bank],\n" +
                "\tbC.bond_surety_name AS [Bond Company],\n" +
                "\t'(' + LEFT(bC.bond_surety_phone_number, 3) + ') ' + SUBSTRING(bC.bond_surety_phone_number, 4,3) + '-' + RIGHT(bC.bond_surety_phone_number,4) AS [Bond Company Phone #],\n" +
                "\tpO.property_owner_name AS [Property Owner],\n" +
                "\t'(' + LEFT(pO.phone_number, 3) + ') ' + SUBSTRING(pO.phone_number, 4,3) + '-' + RIGHT(pO.phone_number,4) AS [PO Phone #],\n" +
                "\tpLH.property_lease_holder_name AS [Property Lease Holder],\n" +
                "\t'(' + LEFT(pLH.phone_number, 3) + ') ' + SUBSTRING(pLH.phone_number, 4,3) + '-' + RIGHT(pLH.phone_number,4) AS [PLH Phone #]\n" +
                "\n" +
                "FROM Supplier AS s\n" +
                "JOIN ProjectSupplier AS pS ON s.supplier_id = pS.supplier_id\n" +
                "JOIN Project AS p ON pS.project_id = p.project_id\n" +
                "JOIN JobDataSheet AS jds ON s.supplier_id = jds.supplier_id\n" +
                "JOIN JobType AS jT ON jds.job_type_id = jT.job_type_id\n" +
                "JOIN Bank AS b ON jds.bank_id = b.bank_id\n" +
                "JOIN BondRecords AS bR ON jds.job_data_sheet_id = bR.job_data_sheet_id\n" +
                "JOIN BondCompany AS bC ON bR.bond_company_id = bC.bond_company_id\n" +
                "JOIN PropertyOwner AS pO ON jds.property_owner_id = pO.property_owner_id\n" +
                "JOIN PropertyLeaseholder AS pLH ON jds.property_lease_holder_id = pLH.property_lease_holder_id\n" +
                "\n" +
                "WHERE jT.title = 'Commercial'\n" +
                "\n" +
                "ORDER BY [Job Type], [Bank], [Bond Company];";

        eC.runReport(query);
    }

    @FXML
    void showProjectSupplierContact()
    {
        query = "SELECT DISTINCT\n" +
                "\ts.company_name AS [Supplier Company Name],\n" +
                "\tCONCAT(sC.first_name, ' ', sC.last_name) AS [Contact Name],\n" +
                "\t'(' + LEFT(sC.phone_number, 3) + ') ' + SUBSTRING(sC.phone_number, 4,3) + '-' + RIGHT(sC.phone_number,4) AS [Phone Number],\n" +
                "\tsC.email AS [Email],\n" +
                "\tsS.title AS [Contact Status],\n" +
                "\tp.project_name AS [Project Name]\n" +
                "\n" +
                "FROM Supplier AS s\n" +
                "JOIN ProjectSupplier AS pS ON pS.supplier_id = s.supplier_id\n" +
                "JOIN Project AS p ON p.project_id = pS.project_id\n" +
                "JOIN SupplierContact AS sC ON s.supplier_id = sC.supplier_contact_id\n" +
                "JOIN SupplierStatus AS sS ON s.supplier_status_id = sS.supplier_status_id\n" +
                "\n" +
                "WHERE sS.title = 'Active';";

        eC.runReport(query);
    }

    @FXML
    void showSupplierBalAndDate()
    {
        query = "SELECT\n" +
                "\tp.project_name AS [Project Name],\n" +
                "\ts.company_name AS [Supplier Company Name],\n" +
                "\tpS.date_added AS [Date Added],\n" +
                "\tsS.title AS [Supplier Status],\n" +
                "\tjds.expected_completion_date AS [Expected Due Date],\n" +
                "\t'$' + REPLACE(CONVERT(varchar(20), (CAST(SUM(jds.materials_estimated_amount) AS money)), 1), '.00', '') AS [Project Balance]\n" +
                "\n" +
                "FROM Project AS p\n" +
                "JOIN ProjectSupplier AS pS ON p.project_id = pS.project_id\n" +
                "JOIN Supplier AS s ON pS.supplier_id = s.supplier_id\n" +
                "JOIN SupplierStatus AS sS ON s.supplier_status_id = sS.supplier_status_id\n" +
                "JOIN JobDataSheet AS jds ON s.supplier_id = jds.supplier_id\n" +
                "\n" +
                "WHERE sS.title = 'Active'\n" +
                "GROUP BY p.project_name, s.company_name, pS.date_added, sS.title, jds.expected_completion_date, jds.materials_estimated_amount\n" +
                "ORDER BY [Supplier Company Name], [Supplier Status];";

        eC.runReport(query);
    }

    @FXML
    void showBankFrequency()
    {
        query = "SELECT\n" +
                "\tb.bank_name AS [Bank],\n" +
                "\tCOUNT(b.bank_name) AS [Number of Times Used],\n" +
                "\t'$' + REPLACE(CONVERT(varchar(20), (CAST(SUM(jds.materials_estimated_amount) AS money)), 1), '.00', '') AS [Dollar Value]\n" +
                "\n" +
                "FROM Supplier AS s\n" +
                "JOIN JobDataSheet AS jds ON jds.supplier_id = s.supplier_id\n" +
                "JOIN Bank AS b ON b.bank_id = jds.bank_id\n" +
                "\n" +
                "GROUP BY b.bank_name\n" +
                "\n" +
                "ORDER BY [Number of Times Used] DESC;";

        eC.runReport(query);
    }

    @FXML
    void showImportantContactForProjects()
    {
        query = "SELECT\n" +
                "\tp.project_name AS [Project Name],\n" +
                "\tCONCAT(pc.first_name, ' ', pc.last_name) AS [Contact],\n" +
                "\t'(' + LEFT(pc.phone_number, 3) + ') ' + SUBSTRING(pc.phone_number, 4,3) + '-' + RIGHT(pc.phone_number,4) AS [Phone Number],\n" +
                "\tpc.email AS [Email],\n" +
                "\tct.title AS [Role],\n" +
                "\tcs.title AS [Status]\n" +
                "\n" +
                "FROM Project as p\n" +
                "JOIN ProjectContact AS pc ON pc.project_id = p.project_id\n" +
                "JOIN ProjectAssignedContact AS pac ON pac.project_contact_id = pc.project_contact_id\n" +
                "JOIN ContactType AS ct ON ct.contact_type_id = pac.contact_type_id\n" +
                "JOIN ContactStatus AS cs ON cs.contact_status_id = pac.contact_status_id\n" +
                "\n" +
                "WHERE cs.title = 'Active'\n" +
                "ORDER BY p.project_name, [Role];";

        eC.runReport(query);
    }

    @FXML
    void showMostValuableClient()
    {
        query = "SELECT DISTINCT\n" +
                "\tc.company_name AS [Client],\n" +
                "\tCOUNT(p.client_id) AS [Number of Projects],\n" +
                "\t'$' + REPLACE(CONVERT(varchar(20), (CAST(SUM(jds.materials_estimated_amount) AS money)), 1), '.00', '') AS [Value of Projects]\n" +
                "\n" +
                "FROM Client AS c\n" +
                "JOIN Project AS p ON p.client_id = c.client_id\n" +
                "JOIN ProjectStatus AS prs ON prs.project_status_id = p.project_status_id\n" +
                "JOIN ProjectSupplier AS ps ON ps.project_id = p.project_id\n" +
                "JOIN Supplier AS s ON s.supplier_id = ps.supplier_id\n" +
                "JOIN JobDataSheet AS jds ON jds.supplier_id = s.supplier_id\n" +
                "\n" +
                "WHERE prs.title != 'Denied'\n" +
                "GROUP BY c.company_name, p.client_id\n" +
                "ORDER BY [Number of Projects] DESC, [Value of Projects];";

        eC.runReport(query);
    }

    /* End Ethan Reports */

    /* Start Daniel Reports */

    @FXML
    void showOverdueChangeOrders()
    {
        String query = "SELECT\n" + "  ProjectStatus.title AS [Project Status],\n" + "  Project.project_name AS [Project Name],\n" + "  ChangeOrderStatus.title AS [Change Order Status Description], \n" + "  ChangeOrder.date_created AS [Change Order Date],\n" + "  ProjectContact.first_name AS [First Name],\n" + "\n" + "  ProjectContact.last_name AS [Last Name],\n" + "  ProjectContact.phone_number AS [Phone Number],\n" + "  ProjectContact.email AS [Email]\n" + "  \n" + "  \n" + "FROM Project \n" + "JOIN Invoice ON Invoice.project_id = Project.project_ID \n" + "JOIN ChangeOrder ON ChangeOrder.invoice_id = Invoice.invoice_id\n" + "JOIN ProjectStatus ON Project.project_status_id = ProjectStatus.project_status_id\n" + "JOIN ChangeOrderStatus ON ChangeOrderStatus.change_order_status_id = ChangeOrder.change_order_status_id\n" + "JOIN ProjectContact ON ProjectContact.project_id = Project.project_id\n" + "\n" + "WHERE\n" + "ProjectStatus.title LIKE 'active' and ChangeOrderStatus.title LIKE 'overdue'\n" + "\n" + "ORDER BY [Change Order Date], [Project Name] ASC";

        eC.runReport(query);
    }

    @FXML
    void showRecentProjects()
    {
        String query = "SELECT\n" + "  ProjectStatus.title AS [Project Status],\n" + "  Project.project_name AS [Project Name],\n" + "  Project.project_start_date AS [Project Date],\n" + "  ProjectContact.first_name AS [First Name],\n" + "  ProjectContact.last_name AS [Last Name],\n" + "  ProjectContact.phone_number AS [Phone Number],\n" + "  ProjectContact.email AS [Email],\n" + "  ProjectType.title AS [Project Type]\n" + "  \n" + "  \n" + "FROM Project \n" + "JOIN ProjectStatus ON Project.project_status_id = ProjectStatus.project_status_id\n" + "JOIN ProjectContact ON ProjectContact.project_id = Project.project_id\n" + "JOIN ProjectType ON ProjectType.project_type_id = Project.project_type_id\n" + "\n" + "\n" + "ORDER BY [Project Date] DESC";

        eC.runReport(query);
    }

    @FXML
    void showRecentInvoices()
    {
        String query = "SELECT \n" + "  Invoice.date_created AS [Invoice Date],\n" + "  Project.project_name AS [Project Name],\n" + "  InvoiceStatus.title AS [Invoice Status],\n" + "  Client.company_name AS [Client]\n" + "  \n" + "  \n" + "FROM Project \n" + "JOIN Invoice ON Invoice.project_id = Project.project_ID \n" + "JOIN InvoiceStatus ON InvoiceStatus.invoice_status_id = Invoice.invoice_status_id\n" + "JOIN Client ON Client.client_id = Project.client_id\n" + "\n" + "\n" + "ORDER BY [Invoice Date] DESC, [Project Name]";

        eC.runReport(query);
    }

    @FXML
    void showRecentChangeOrders()
    {
        String query = "SELECT \n" + "  ChangeOrder.date_created AS [Change Order Date],\n" + "  Project.project_name AS [Project Name],\n" + "  ChangeOrderStatus.title AS [Change Order Status Description],\n" + "  Client.company_name AS [Client]\n" + "  \n" + "  \n" + "FROM Project \n" + "JOIN Invoice ON Invoice.project_id = Project.project_ID \n" + "JOIN ChangeOrder ON ChangeOrder.invoice_id = Invoice.invoice_id\n" + "JOIN ChangeOrderStatus ON ChangeOrderStatus.change_order_status_id = ChangeOrder.change_order_status_id\n" + "JOIN Client ON Client.client_id = Project.client_id\n" + "\n" + "\n" + "ORDER BY [Change Order Date], [Project Name] ASC";

        eC.runReport(query);
    }

    @FXML
    void showIncompleteProjects()
    {
        String query = "SELECT \n" + "  Project.project_start_date AS [Project Date],\n" + "  ProjectType.title AS [Project Type],\n" + "  Project.project_name AS [Project Name],\n" + "  ProjectStatus.title [Project Status],\n" + "  Client.company_name AS [Client]\n" + "  \n" + "  \n" + "FROM Project \n" + "JOIN ProjectStatus ON ProjectStatus.project_status_id = Project.project_status_id \n" + "JOIN ProjectType ON ProjectType.project_type_id = Project.project_type_id\n" + "JOIN Client ON Client.client_id = Project.client_id\n" + "\n" + "WHERE\n" + "ProjectStatus.title != 'Active' AND ProjectStatus.title != 'COMPLETE'\n" + "\n" + "ORDER BY [Project Date] DESC";

        eC.runReport(query);
    }

    @FXML
    void showOverdueInvoices()
    {
        String query = "SELECT\n" + "  ProjectStatus.title AS [Project Status],\n" + "  Project.project_name AS [Project Name],\n" + "  InvoiceStatus.title AS [Invoice Status Description], \n" + "  Invoice.date_created AS [Invoice Date],\n" + "  ProjectContact.first_name AS [First Name],\n" + "  ProjectContact.last_name AS [Last Name],\n" + "  ProjectContact.phone_number AS [Phone Number],\n" + "  ProjectContact.email AS [Email]\n" + "  \n" + "  \n" + "FROM Project \n" + "JOIN Invoice ON Invoice.project_id = Project.project_ID \n" + "JOIN ProjectStatus ON ProjectStatus.project_status_id = Project.project_status_id\n" + "JOIN InvoiceStatus ON InvoiceStatus.invoice_status_id = Invoice.invoice_status_id\n" + "JOIN ProjectContact ON ProjectContact.project_id = Project.project_id\n" + "\n" + "WHERE\n" + "ProjectStatus.title LIKE 'active' and InvoiceStatus.title LIKE 'overdue'\n" + "\n" + "ORDER BY [Invoice Date], [Project Name] ASC";

        eC.runReport(query);
    }

    /* End Daniel Reports */

    /* Start Robert Reports */

    @FXML
    void showActiveCommProjects()
    {
        String query = "SELECT\n" + "Project.project_id AS ProjectID, Project.project_name AS ProjectName,\n" + "ProjectEmployee.employee_id AS EmployeeID,Employee.first_name AS FirstName,\n" + "Employee.last_name AS LastName ,\n" + "ProjectEmployee.date_assigned AS DateAssigned,\n" + "\n" + "ProjectType.title AS ProjectType,\n" + "ProjectStatus.title AS ProjectStatus\n" + " \n" + " \n" + "FROM    ProjectEmployee\n" + " \n" + "INNER JOIN Project ON ProjectEmployee.project_id = Project.project_id\n" + "INNER JOIN Employee ON ProjectEmployee.employee_id = Employee.employee_id\n" + " \n" + "INNER JOIN ProjectType ON Project.project_type_id = ProjectType.project_type_id\n" + " \n" + "INNER JOIN ProjectStatus ON Project.project_status_id = ProjectStatus.project_status_id\n" + " \n" + "WHERE ProjectType.title = 'Commercial' AND \n" + "ProjectStatus.title = 'Active' ";

        eC.runReport(query);
    }

    @FXML
    void showAllProjects()
    {
        String query = "SELECT \n" + "Project.project_id AS ProjectID,Project.project_name AS ProjectName,project.Project_bid_date AS BidDate,Project.Project_start_date AS StartDate,\n" + "projectStatus.title as ProjectStatus,\n" + "ProjectType.title AS ProjectType,\n" + "Client.client_id AS ClientID, Client.company_name AS CompanyName,\n" + "Project.total_cost AS TotalCost\n" + "\n" + "FROM Project \n" + "INNER JOIN ProjectStatus  ON\tProject.project_status_id\t= ProjectStatus.project_status_id\n" + "INNER JOIN ProjectType ON \tProject.project_type_id\t\t= ProjectType.Project_type_id\n" + "INNER JOIN\tClient ON \tProject.client_id\t= Client.client_id\n" + "\n" + "\n" + "ORDER BY \n" + "Project.project_bid_date asc";

        eC.runReport(query);
    }

    @FXML
    void showEquipmentVendor()
    {
        String query = "SELECT Vendor.vendor_id as VendorID,Vendor.vendor_name AS VendorName,VendorStatus.title AS VendorStatus,VendorType.title as VendorType,\n" + "VendorContact.first_name AS FirstName, VendorContact.last_name AS LastName, VendorContact.phone_number AS PhoneNumber,VendorContact.email\n" + "AS Email\n" + " \n" + "\n" + "\n" + "FROM Vendor \n" + "INNER JOIN VendorType \tON\tVendor.vendor_type_id\t= VendorType.vendor_type_id\n" + "INNER JOIN VendorStatus\tON \tVendor.vendor_status_id\t= VendorStatus.vendor_status_id\n" + "INNER JOIN VendorContact ON\tVendor.vendor_id\t= VendorContact.vendor_id\n" + "\n" + "WHERE \n" + "VendorType.title = 'Equipment'";

        eC.runReport(query);
    }

    @FXML
    void showInactiveSub()
    {
        String query = "SELECT Vendor.vendor_id AS VendorID,Vendor.vendor_name AS VendorName,VendorStatus.title AS VendorStatus,VendorType.title as VendorType,\n" + "VendorContact.first_name AS FirstName, VendorContact.last_name AS LastName, VendorContact.phone_number AS PhoneNumber,VendorContact.email\n" + "AS Email\n" + " \n" + "\n" + "\n" + "FROM Vendor \n" + "INNER JOIN VendorType \tON\tVendor.vendor_type_id\t= VendorType.vendor_type_id\n" + "INNER JOIN VendorStatus\tON \tVendor.vendor_status_id\t= VendorStatus.vendor_status_id\n" + "INNER JOIN VendorContact ON\tVendor.vendor_id\t= VendorContact.vendor_id\n" + "\n" + "WHERE \n" + "VendorStatus.title = 'Inactive'";

        eC.runReport(query);
    }

    @FXML
    void showClientOutUSA()
    {
        String query = "SELECT \n" + "Client.client_id as ClientID, Client.first_name AS FirstName, Client.last_name AS LastName, Client.company_name AS Company,\n" + "Client.address_line_one AS LocationAddress,\n" + "Region.NAME as Region,\n" + "Country.country_name as Country,\n" + "ClientStatus.title as ClientStatus\n" + "\n" + "FROM Client\n" + "\n" + "INNER JOIN ClientStatus ON Client.client_status_id\t= ClientStatus.client_status_id\n" + "INNER JOIN Country  ON\tClient.country_id\t= Country.country_id \n" + "INNER JOIN Region  ON \tClient.region_id\t= Region.region_id\n" + "\n" + " WHERE ClientStatus.title = 'Active' AND \n" + "\t\tCountry.country_name != 'United States of America'";

        eC.runReport(query);
    }

    @FXML
    void showInvoiceOverAmount()
    {
        String query = "SELECT \n" + "Project.project_id AS ProjectID,Project.project_name AS ProjectName,ProjectStatus.title AS ProjectStatus,\n" + " Invoice.total_still_due AS TotalStillDue,Invoice.date_created AS InvoiceDateCreated, InvoiceStatus.title AS InvoiceStatus  \n" + "\n" + "\n" + "FROM Project\n" + "\n" + "INNER JOIN ProjectStatus ON Project.project_status_id\t= ProjectStatus.project_status_id\n" + "INNER JOIN Invoice ON \tProject.project_id\t= Invoice.project_id\n" + "INNER JOIN InvoiceStatus ON Invoice.invoice_status_id = InvoiceStatus.invoice_status_id \n" + "\n" + "WHERE \n" + "Invoice.total_still_due >= 500.00 AND \n" + "ProjectStatus.title = 'Complete'\n" + "\n" + "ORDER BY\n" + "Invoice.date_created ASC";

        eC.runReport(query);
    }

    /* End Robert Reports */

    /* Start Richard Reports */

    @FXML
    void showActiveOrders()
    {
        String query = "SELECT\n" + "\trfpo.rental_fp_order_id AS [Order Number],\n" + "\trfp.product_name AS [Product Name],\n" + "\t'$' + CAST(rfpol.unit_price AS VARCHAR(15)) [Price per Unit],\n" + "\trfpol.quantity AS [Quantity],\n" + "\t'$' + CAST(rfpol.order_line_total AS VARCHAR(15)) [Order Line Total],\n" + "\tv.vendor_name AS [Vendor Name],\n" + "\tp.project_name AS [Project Name]\n" + "\n" + "FROM RentalFPOrder as rfpo\n" + "JOIN RentalFPOrderLine AS rfpol ON rfpo.rental_fp_order_id = rfpol.rental_fp_order_id\n" + "JOIN RentalFP AS rfp ON rfpol.rental_fp_id = rfp.rental_fp_id\n" + "JOIN VendorProduct AS vp ON vp.rental_fp_id = rfp.rental_fp_id\n" + "JOIN Vendor AS v ON v.vendor_id = vp.vendor_id\n" + "JOIN Project AS p ON p.project_id = rfpo.project_id\n" + "JOIN OrderStatus AS os ON os.order_status_id = rfpo.order_status_id\n" + "\n" + "WHERE os.order_status_id = '1'\n" + "\n" + "ORDER BY [Order Number];\n";

        eC.runReport(query);
    }

    @FXML
    void showAvailableProducts()
    {
        String query = "SELECT\n" + "\tv.vendor_id AS [Vendor ID],\n" + "\tv.vendor_name AS [Vendor Name],\n" + "\trfp.product_name AS [Product Name],\n" + "\trfp.date_added AS [Date Added],\n" + "\tvc.first_name AS [Contact First Name],\n" + "\tvc.last_name AS [Contact Last Name],\n" + "\t'(' + LEFT(vc.phone_number, 3) + ') ' + SUBSTRING(vc.phone_number, 4,3) + '-' + RIGHT(vc.phone_number,4) AS [Contact Phone Number],\n" + "\tvc.email AS [Contact Email]\n" + "\n" + "FROM RentalFP AS rfp\n" + "JOIN VendorProduct AS vp ON vp.rental_fp_id = rfp.rental_fp_id\n" + "JOIN Vendor AS v ON v.vendor_id=vp.vendor_id\n" + "JOIN VendorContact AS vc ON vc.vendor_id = v.vendor_id\n" + "JOIN VendorAssignedContact AS vac ON vac.vendor_contact_id = vc.vendor_contact_id\n" + "\n" + "WHERE vac.contact_status_id = '1'\n" + "\n" + "ORDER BY [Vendor ID], [Product Name], [Date Added]\n";

        eC.runReport(query);
    }

    @FXML
    void showTopTwentyVendors()
    {
        String query = "SELECT TOP 20\n" + "\tv.vendor_id AS [Vendor ID],\n" + "\tv.vendor_name AS [Vendor Name],\n" + "\tCOUNT(rfpo.rental_fp_order_id) AS [Number of Orders],\n" + "\tvc.first_name AS [Contact First Name],\n" + "\tvc.last_name AS [Contact Last Name],\n" + "\t'(' + LEFT(vc.phone_number, 3) + ') ' + SUBSTRING(vc.phone_number, 4,3) + '-' + RIGHT(vc.phone_number,4) AS [Contact Phone Number],\n" + "\tvc.email AS [Contact Email]\n" + "\n" + "FROM RentalFPOrder as rfpo\n" + "JOIN RentalFPOrderLine AS rfpol ON rfpol.rental_fp_order_id = rfpo.rental_fp_order_id\n" + "JOIN RentalFP AS rfp ON rfp.rental_fp_id = rfpol.rental_fp_id\n" + "JOIN VendorProduct AS vp ON vp.rental_fp_id = rfp.rental_fp_id\n" + "JOIN Vendor AS v ON v.vendor_id=vp.vendor_id\n" + "JOIN VendorContact AS vc ON vc.vendor_id = v.vendor_id\n" + "JOIN VendorAssignedContact AS vac ON vac.vendor_contact_id = vc.vendor_contact_id\n" + "\n" + "GROUP BY v.vendor_id, v.vendor_name, vc.first_name, vc.last_name, vc.phone_number, vc.email\n" + "\n" + "ORDER BY [Number of Orders] DESC\n";

        eC.runReport(query);
    }

    @FXML
    void showActiveVendorsByType()
    {
        String query = "SELECT\n" + "\tvt.title AS [Vendor Type],\n" + "\tv.vendor_id AS [Vendor ID],\n" + "\tv.vendor_name AS [Vendor Name],\n" + "\tvc.first_name AS [Contact First Name],\n" + "\tvc.last_name AS [Contact Last Name],\n" + "\t'(' + LEFT(vc.phone_number, 3) + ') ' + SUBSTRING(vc.phone_number, 4,3) + '-' + RIGHT(vc.phone_number,4) AS [Contact Phone Number],\n" + "\tvc.email AS [Contact Email]\n" + "\n" + "FROM RentalFPOrder as rfpo\n" + "JOIN RentalFPOrderLine AS rfpol ON rfpol.rental_fp_order_id = rfpo.rental_fp_order_id\n" + "JOIN RentalFP AS rfp ON rfp.rental_fp_id = rfpol.rental_fp_id\n" + "JOIN VendorProduct AS vp ON vp.rental_fp_id = rfp.rental_fp_id\n" + "JOIN Vendor AS v ON v.vendor_id=vp.vendor_id\n" + "JOIN VendorContact AS vc ON vc.vendor_id = v.vendor_id\n" + "JOIN VendorType AS vt On vt.vendor_type_id = v.vendor_type_id\n" + "JOIN VendorAssignedContact AS vac ON vac.vendor_contact_id = vc.vendor_contact_id\n" + "JOIN VendorStatus AS vs ON vs.vendor_status_id = v.vendor_status_id\n" + "\n" + "WHERE vs.vendor_status_id = '1' AND vac.contact_status_id = '1'\n" + "\n" + "GROUP BY vt.title, v.vendor_id, v.vendor_name, vc.first_name, vc.last_name, vc.phone_number, vc.email\n" + "\n" + "ORDER BY [Vendor Type], [Vendor ID];";

        eC.runReport(query);
    }

    @FXML
    void showTopTwentyRegions()
    {
        String query = "SELECT TOP 20\n" + "\tr.NAME AS [Region Name],\n" + "\tco.country_name AS [Country Name],\n" + "\tCOUNT(rfpo.rental_fp_order_id) AS [Number of Orders],\n" + "\t'$' + REPLACE(CONVERT(varchar(20), (CAST(SUM(rfpo.order_total) AS MONEY)), 1), '.00', '') AS [Amount]\n" + " \n" + "\n" + "FROM Client AS c\n" + "JOIN Project AS p ON p.client_id = c.client_id\n" + "JOIN RentalFPOrder AS rfpo ON rfpo.project_id = p.project_id\n" + "JOIN Region AS r ON r.region_id = c.region_id\n" + "JOIN Country AS co ON co.country_id = r.country_id\n" + "\n" + "GROUP BY r.NAME, co.country_name\n" + "\n" + "ORDER BY [Number of Orders] DESC\n";

        eC.runReport(query);
    }

    @FXML
    void showOrderStatus()
    {
        String query = "SELECT\n" + "\trfpo.date_ordered AS [Date Ordered],\n" + "\tp.project_name AS [Project],\n" + "\trfp.product_name AS [Product],\n" + "\toS.title AS [Equipment Status], \n" + "\trfpo.rental_fp_order_id AS [Order Number],\n" + "\t'$' + REPLACE(CONVERT(varchar(20), (CAST(SUM(rfpo.order_total) AS money)), 1), '.00', '') AS [Amount]\n" + "\n" + "FROM Project AS p\n" + "JOIN RentalFPOrder AS rfpo ON rfpo.project_id = p.project_id\n" + "JOIN RentalFPOrderLine AS rfpol ON rfpol.rental_fp_order_id = rfpo.rental_fp_order_id\n" + "JOIN RentalFP AS rfp ON rfp.rental_fp_id = rfpol.rental_fp_id\n" + "JOIN OrderStatus AS os ON os.order_status_id = rfpo.order_status_id\n" + "\n" + "GROUP BY p.project_name, rfp.product_name, oS.title, rfpo.date_ordered, rfpo.rental_fp_order_id\n" + "\n" + "ORDER BY  [Date Ordered], [Project]\n";

        eC.runReport(query);
    }

    /* End Richard Reports */

    /* Start Muneer Reports */

    @FXML
    void showActiveProjectsPerRegion()
    {
        String query = "select \n" + "coun.country_name as [Country Name],\n" + " r.name AS [Region],\n" + " COUNT(r.name) AS [Number of Active Projects in Region],\n" + " ps.title as [Project Status],\n" + " '$' + REPLACE(CONVERT(varchar(20), (CAST(SUM(p.total_cost) AS money)), 1), '.00', '') AS [Project Cost Per Region]\n" + "\n" + "from Project as p\n" + "JOIN Client as c on c.client_id=p.client_id\n" + "JOIN Region as r on r.region_id=c.region_id\n" + "JOIN Country as coun on coun.country_id=r.country_id\n" + "JOIN ProjectStatus as ps on ps.project_status_id=p.project_status_id\n" + "\n" + "where ps.title='Active' and coun.country_name='United States of America'\n" + "group by r.NAME,ps.title,coun.country_name \n" + "\n" + "order by [Number of Active Projects in Region] desc";

        eC.runReport(query);
    }

    @FXML
    void showActiveClientsPerRegion()
    {
        String query = "SELECT\n" + "\n" + " r.name AS [Region],\n" + " COUNT(r.name) AS [Number of Active Clients in Region],\n" + " cs.title as [Status],\n" + " '$' + REPLACE(CONVERT(varchar(20), (CAST(SUM(p.total_cost) AS money)), 1), '.00', '') AS [Project Cost Per Region],\n" + " coun.country_name as [Country Name]\n" + "\n" + "FROM Client as c\n" + "JOIN Region as r on c.region_id = r.region_id\n" + "join Country as coun on coun.country_id=r.country_id\n" + "join ClientStatus as cs on cs.client_status_id=c.client_status_id\n" + "JOIN Project as p on p.client_id=c.client_id\n" + "\n" + "\n" + "\n" + "where cs.title='Active'\n" + "GROUP BY r.NAME,coun.country_name,cs.title\n" + "\n" + "ORDER BY  [Number of Active Clients in Region] desc";

        eC.runReport(query);
    }

    @FXML
    void showProjectStaffing()
    {
        String query = "SELECT\n" + "\tc.company_name as [Company Name],\n" + "\tCOUNT(p.project_name) AS [Number of Employees on Projects],\n" + "    p.project_name AS [Project Name],\n" + "\tps.title as [Project Status],\n" + "\tcoun.country_name as [Country Name]\n" + " \n" + "FROM  Project as p\n" + "JOIN ProjectEmployee as pe on pe.project_id=p.project_id\n" + "JOIN Employee as e on e.employee_id=pe.employee_id\n" + "JOIN ProjectStatus as ps on ps.project_status_id=p.project_status_id\n" + "JOIN Client as c on c.client_id=p.client_id\n" + "JOIN Country as coun on coun.country_id=c.country_id\n" + " \n" + "where ps.title='Active'\n" + " \n" + "GROUP BY p.project_name,ps.title,c.company_name,coun.country_name\n" + " \n" + "ORDER BY [Number of Employees on Projects] desc";

        eC.runReport(query);
    }

    @FXML
    void showActiveQuotes()
    {
        String query = "SELECT DISTINCT\n" + "  p.project_name AS [Project Name], \n" + "  q.date_created AS [Date Quote Created],\n" + "   '$' + REPLACE(CONVERT(varchar(20), (CAST((q.amount) AS money)), 1), '.00', '') AS [Quote Amount],\n" + "  qS.title AS [Quote Status],\n" + "  pc.first_name + ' ' + pc.last_name as [Project Contact Name],\n" + "  cT.title AS [Contact Type],\n" + "  '(' + LEFT(pc.phone_number, 3) + ') ' + SUBSTRING(pc.phone_number, 4,3) + '-' + RIGHT(pc.phone_number,4) AS [Phone Number]\n" + " \n" + "FROM Quote as q\n" + "JOIN QuoteStatus as qS ON q.quote_status_id = qS.quote_status_id\n" + "JOIN Project as p ON q.project_id = p.project_id\n" + "join ProjectContact AS pc on pc.project_id=p.project_id\n" + "join ProjectAssignedContact AS pac ON pac.project_contact_id = pc.project_contact_id\n" + "join ContactType AS cT ON cT.contact_type_id = pac.contact_type_id\n" + "\n" + "WHERE qs.title='Active' AND (cT.title = 'Accountant' OR cT.title = 'Secretary')\n" + "ORDER BY [Date Quote Created] desc";

        eC.runReport(query);
    }

    @FXML
    void showVehicleReport()
    {
        String query = "SELECT\n" + "\te.employee_id as [Employee ID],\n" + "\te.first_name + ' ' + e.last_name as [Employee Name],\n" + "\tvs.title as [Vehicle Status],\n" + "\tev.date_assigned as [Date Assigned],\n" + "\tv.license_plate as [Vehicle Plate],\n" + "\tm.title as [Vehicle make],\n" + "\tmo.title as [Vehicle Model],\n" + "\tvt.title as [Vehicle Type]\n" + "\n" + "FROM Vehicle as v\n" + "join VehicleType as vt on v.vehicle_type_id=vt.vehicle_type_id\n" + "join VehicleStatus as vs on v.vehicle_status_id=vs.vehicle_status_id\n" + "join EmployeeVehicle as ev on v.vehicle_id=ev.employee_id\n" + "join Make as m on v.make_id=m.make_id\n" + "join Model as mo on v.model_id=mo.model_id\n" + "join Employee as e on e.employee_id=ev.employee_id\n" + "WHERE vs.title='Active'\n" + "ORDER BY [Employee ID]";

        eC.runReport(query);
    }

    @FXML
    void showSupplierContactInfo()
    {
        String query = "SELECT\n" + "\n" + "\ts.company_name as [Company name],\n" + "\tss.title as [Supplier Status],\n" + "\tsac.date_assigned as [Date assigned],\n" + "\tsc.first_name + ' ' + sc.last_name as [Contact Name],\n" + "\t'(' + LEFT(sc.phone_number, 3) + ') ' + SUBSTRING(sc.phone_number, 4,3) + '-' + RIGHT(sc.phone_number,4) AS [Phone Number],\n" + "\tsc.email as [Email]\n" + "\n" + "FROM Supplier as s\n" + "join SupplierContact as sc on sc.supplier_contact_id=s.supplier_id\n" + "join SupplierAssignedContact as sac on sac.supplier_contact_id=sc.supplier_contact_id\n" + "join SupplierStatus as ss on ss.supplier_status_id=s.supplier_status_id\n" + "\n" + "WHERE ss.title='Active'\n" + "ORDER BY s.company_name";

        eC.runReport(query);
    }

    /* End Muneer Reports */
}