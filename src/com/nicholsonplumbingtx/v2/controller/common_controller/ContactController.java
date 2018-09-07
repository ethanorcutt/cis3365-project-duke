package com.nicholsonplumbingtx.v2.controller.common_controller;

import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.contact.Contact;
import com.nicholsonplumbingtx.v2.model.invoice.Invoice;

/**
 * Created by Ethan Orcutt on 11/9/2016.
 * Project D.U.K.E.
 */
public abstract class ContactController
{
    public abstract void loadInformation(Contact selectedContact);
    public abstract void loadInformation(Invoice selectedInvoice, Client selectedClient);
    protected java.sql.Date convertJavaDateToSqlDate(java.util.Date date)
    {
        return new java.sql.Date(date.getTime());
    }
}
