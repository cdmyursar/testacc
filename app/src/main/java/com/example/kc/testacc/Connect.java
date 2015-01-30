package com.example.kc.testacc;

import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by kc on 1/29/2015.
 */
public class Connect {

    URL url = null;

    private Connection connection;
    private Statement statement;
    private ResultSet resultset;

    public void Connect() throws ClassNotFoundException, SQLException {
        try {
            url = new URL("192.168.1.10\\wkennel.mdb");
            URLConnection myURLConnection = url.openConnection();
            myURLConnection.connect();

        String logClass = "Connect";
        Log.v(logClass, "Driver Load");
        System.out.println("Start of connect class");
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        System.out.println("Driver loaded");
        Log.v(logClass, "Driver Loaded");

        Log.v(logClass, "Start of Connection");
        System.out.println("start of connection");
        connection = DriverManager.getConnection("jdbc:ucanaccess://" + myURLConnection );
        System.out.println("Database Connected");
        Log.v(logClass, "Database Connected Successfully");
        }catch (MalformedURLException E){
            E.printStackTrace();
        }catch(IOException E){
            E.printStackTrace();
        }

    }



    public void InnerJoin() throws SQLException{
        // INNER JOIN SQL, joining the GroomingLog, Pets, Clients Tables in a SQL statement
        // The ResultSet is scrollable.

        System.out.println("start of query statement");
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        System.out.println("Statement created");

        String SQL;
        SQL = "Select GLDescription, GLDate, CLLastName, PtPetName "
                + "From GroomingLog "
                + "INNER JOIN Pets "
                + "ON GroomingLog.GLPetID = Pets.PtSeq "
                + "INNER JOIN Clients "
                + "ON Pets.PtOwnerCode = Clients.CLSeq "
                + "WHERE GLDate='2014-10-01 00:00:00.000000'";


        System.out.println("start of Query execute");
        resultset = statement.executeQuery(SQL);
        System.out.println("Query executed");

        System.out.println("Start iterating");

        System.out.println("next");
        resultset.next();
        String DESCRIPTION = resultset.getString("GLDescription");
        String GLDATE = resultset.getString("GLDate");
        String CLLASTNAME = resultset.getString("CLLastName");
        String PETNAME = resultset.getString("PtPetName");
        System.out.println(GLDATE + " " + DESCRIPTION + " " + CLLASTNAME + " " + PETNAME);

        System.out.println("next");
        resultset.next();
        String DESCRIPTION1 = resultset.getString("GLDescription");
        String GLDATE1 = resultset.getString("GLDate");
        String CLLASTNAME1 = resultset.getString("CLLastName");
        String PETNAME1 = resultset.getString("PtPetName");
        System.out.println(GLDATE1 + " " + DESCRIPTION1 + " " + CLLASTNAME1 + " " + PETNAME1);

        System.out.println("previous");
        resultset.previous();
        String DESCRIPTION2 = resultset.getString("GLDescription");
        String GLDATE2 = resultset.getString("GLDate");
        String CLLASTNAME2 = resultset.getString("CLLastName");
        String PETNAME2 = resultset.getString("PtPetName");
        System.out.println(GLDATE2 + " " + DESCRIPTION2 + " " + CLLASTNAME2 + " " + PETNAME2);

        System.out.println("Done interating");
        statement.close();
        resultset.close();
    }

    public void Display() throws SQLException{

        System.out.println("start of query statement");
        statement = connection.createStatement();
        System.out.println("Statement created");

        String SQL;
        SQL = "SELECT * FROM GroomingLog WHERE GLDate='2014-10-02 00:00:00.000000' "; // this works '2014-10-02 00:00:00.000000'     GLDate=Date

        System.out.println("start of Query execute");
        resultset = statement.executeQuery(SQL);
        System.out.println("Query executed");

        int count = 0;

        System.out.println("Start of While loop");
        while(resultset.next()){


            String DESCRIPTION = resultset.getString("GLDescription");
            String GLDATE = resultset.getString("GLDate");
            String CLLASTNAME = resultset.getString("CLLastName");

            System.out.println(count + " " + GLDATE + " " + DESCRIPTION);

            count++;
            //}
        }
        System.out.println("exit while loop");
        statement.close();
    }

    public void Updating() throws SQLException{

        statement = connection.createStatement();

        String SQL3 = "UPDATE GroomingLog SET GLDescription='update from netbeans' WHERE GLDate='2014-10-02 00:00:00.000000' ";

        statement.execute(SQL3);
        statement.executeUpdate(SQL3);
        statement.close();

    }

    public void Updating(String a) throws SQLException{

        statement = connection.createStatement();

        String SQL3 = "UPDATE GroomingLog SET GLDescription='" + a + "' WHERE GLDate='2014-10-02 00:00:00.000000' ";

        statement.execute(SQL3);
        statement.executeUpdate(SQL3);
        statement.close();

    }

    public void CloseConn() throws SQLException{
        connection.close();
    }

}
