package com.senturk.fatih.chat03;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseUser;


public class DispatchActivity extends Activity {


    public DispatchActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ParseUser.getCurrentUser() != null) {


            startActivity(new Intent(this, Login.class));
        } else {
            // Start and intent for the logged out activity
            startActivity(new Intent(this, Login.class));
        }

    }


}
