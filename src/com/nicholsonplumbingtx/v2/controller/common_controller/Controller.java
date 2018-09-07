package com.nicholsonplumbingtx.v2.controller.common_controller;

import com.nicholsonplumbingtx.v2.model.client.Client;
import com.nicholsonplumbingtx.v2.model.project.Project;

/**
 * Created by Ethan Orcutt on 10/29/2016.
 * Project D.U.K.E.
 */
public abstract class Controller
{
    protected abstract void loadInformation(int vehicleID);
    public abstract void loadInformation(String selectedName);
    protected abstract void loadInformation(Client selectedClient);
    public abstract void loadInformation(Client selectedClient, Project selectedProject);
    protected abstract void collectInformation();
    protected java.sql.Date convertJavaDateToSqlDate(java.util.Date date)
    {
        return new java.sql.Date(date.getTime());
    }
}
