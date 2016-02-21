package com.senturk.fatih.chat03;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;


public class Settings extends Activity {

    protected EditText mStatus;
    protected Button mStatusButton;
    protected Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ParseUser habugada = ParseUser.getCurrentUser();

        if (Const.burdanBak==0) {
            Toast.makeText(Settings.this, "Giris yapmak gerekli", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Settings.this, Login.class));
        } else {


            mStatus = (EditText) findViewById(R.id.updateStatusText);
            mStatusButton = (Button) findViewById(R.id.updateStatusButton);
            logoutBtn = (Button) findViewById(R.id.uygulamdanCik);

            logoutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Const.burdanBak=0;
                    ParseUser.logOut();
                    startActivity(new Intent(Settings.this, Login.class));
                }
            });
            mStatusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String habu = mStatus.getText().toString();
                    if (!habu.isEmpty()) {

                        ParseUser statusUser = ParseUser.getCurrentUser();
                        if (statusUser == null) {
                            Utils.showDialog(Settings.this, "Lutfen giris yapiniz");
                            startActivity(new Intent(Settings.this, Login.class));
                        } else {
                            String statusUserName = statusUser.getUsername();

                            String newStatus = mStatus.getText().toString();
                            ParseObject statusObject = new ParseObject("Status");
                            statusObject.put("newStatus", newStatus);
                            statusObject.put("user", statusUserName);
                            statusObject.saveInBackground();
                            startActivity(new Intent(Settings.this, TabBar.class));
                        }

                    } else {
                        Utils.showDialog(Settings.this, "Durum bos olamaz");
                        Toast.makeText(Settings.this, "bod rurum", Toast.LENGTH_LONG).show();
                    }

                }
            });


        }
    }


}
