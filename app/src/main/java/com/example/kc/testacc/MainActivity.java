package com.example.kc.testacc;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import java.sql.SQLException;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v("Connect", "Calling Connect()");
        Connect connect = new Connect();

        Log.v("Connect", "Connect() Created");

        try {
            Log.v("Connect try", "Trying to connect to database");
            connect.Connect();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.v("Class not found exception", "Class Not Found");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.v("SQLException ","SQLException");
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
