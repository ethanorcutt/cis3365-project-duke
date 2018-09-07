package com.nicholsonplumbingtx.v2.common;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

/**
 * Created by Ethan Orcutt on 10/15/2016.
 */
abstract class DBConnector
{
    public final SQLServerDataSource dS = new SQLServerDataSource();

    DBConnector()
    {
        dS.setPortNumber(1433);
        dS.setServerName("npdatabase.cqukdhljbzns.us-west-2.rds.amazonaws.com");
        dS.setDatabaseName("DukeDB");
        dS.setUser("dukeConnector");
        dS.setPassword("test1234");
    }
}
