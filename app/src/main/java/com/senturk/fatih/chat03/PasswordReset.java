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

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;


public class PasswordReset extends Activity {

    protected EditText ResetEmail;
    protected Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        ResetEmail=(EditText)findViewById(R.id.editTextPasswordReset);
        resetButton=(Button)findViewById(R.id.buttonPasswordReset);




        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailReset = ResetEmail.getText().toString().trim();
                sendReset(emailReset);
            }
        });



    }

    private void sendReset(String emailReset) {

        ParseUser.requestPasswordResetInBackground(emailReset, new RequestPasswordResetCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {


                    //Utils.showDialog(PasswordReset.this, "Sifre yenileme linki hesabiniz gonderildi");
                    startActivity(new Intent(PasswordReset.this, Login.class));
                    Toast.makeText(PasswordReset.this, "Sifre yenileme linki eposta adresinize gonderildi", Toast.LENGTH_LONG).show();




                } else {
                    Utils.showDialog(PasswordReset.this,"Boyle bir eposta adresiyle iliskili hesap bulunamadi");
                    Toast.makeText(PasswordReset.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }


}
