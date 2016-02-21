package com.senturk.fatih.chat03;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

/**
 * Created by fatih on 13.6.2015.
 */
public class Register extends CustomActivity {
    private EditText user;
    private EditText pwd;
    private EditText email;
    private EditText pwdAgain;
    private Button zatenUyeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        setTouchNClick(R.id.btnReg);

        user= (EditText) findViewById(R.id.user);
        pwd=  (EditText)findViewById(R.id.pwd);
        email=(EditText)findViewById(R.id.email);
        pwdAgain=(EditText)findViewById(R.id.pwdagain);
        zatenUyeBtn=(Button)findViewById(R.id.ZatenUyeyim);
        zatenUyeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });

    }


    @Override
    public void onClick(View v) {
        super.onClick(v);

        String u=user.getText().toString().trim();
        String p=pwd.getText().toString().trim();
        String e=email.getText().toString().trim();
        String eA=pwdAgain.getText().toString().trim();

        if(u.length()==0||p.length()==0||e.length()==0||eA.length()==0){
            Utils.showDialog(this,R.string.err_fields_empty);
            return;
        }

        if (!p.equals(eA)){
            Utils.showDialog(this, "Sifreler ayni olmali");
           // Toast.makeText(Register.this,"Girilen Sifreler Ayni olmali",Toast.LENGTH_LONG).show();
            return;
        }

        final ProgressDialog dia=ProgressDialog.show(this,null,getString(R.string.alert_wait));

        final ParseUser pu=new ParseUser();
        pu.setEmail(e);
        pu.setPassword(p);
        pu.setUsername(u);


        pu.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                dia.dismiss();
                if(e==null){

                    UserList.user=pu;
                   //startActivity(new Intent(Register.this,UserList.class));
                    //startActivity(new Intent(Register.this,MainActivity1.class));
                    startActivity(new Intent(Register.this,Login.class));

                    //startActivity(new Intent(Register.this,HomePageStatusler.class));

                    setResult(RESULT_OK);
                    finish();

                   /* ParseInstallation installation = ParseInstallation.getCurrentInstallation();
                    installation.put("device_id", "12345678901");
                    installation.saveInBackground();



                    */



                    ParseUser hemenUser=ParseUser.getCurrentUser();

                    ParseInstallation installation = ParseInstallation.getCurrentInstallation();
                    installation.put("owner",hemenUser.getUsername());

                    installation.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e==null){
                               // Utils.showDialog(Register.this,"sorun var gardas");
                            }else{
                                Utils.showDialog(Register.this,e.getMessage());

                            }
                        }
                    });



                    Toast.makeText(Register.this,installation.getObjectId(),Toast.LENGTH_LONG).show();
                    Toast.makeText(Register.this,hemenUser.getObjectId(),Toast.LENGTH_LONG).show();



                }else{
                    String yolla=e.getMessage();
                    if (yolla.equals("invalid email address")){
                        yolla="Gecersiz eposta adresi";
                    }else {
                        yolla="Bu kullanici adi daha once alinmis";

                        Utils.showDialog(Register.this, getString(R.string.err_singup) + "" + e.getMessage());
                    }
                   // Utils.showDialog(Register.this, getString(R.string.err_singup) + "" + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }
}
