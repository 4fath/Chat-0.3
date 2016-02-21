package com.senturk.fatih.chat03;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class Login extends CustomActivity {

    private EditText user;
    private EditText pwd;

    protected Button reseteGiden;

    protected Button regeGider;
    protected Button logaGider;

    //private String sonSifre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        reseteGiden=(Button)findViewById(R.id.SifremiUnuttum);

        setTouchNClick(R.id.btnLogin);
        setTouchNClick(R.id.btnReg);

        user=(EditText)findViewById(R.id.userlogin);
        pwd=(EditText)findViewById(R.id.pwdlogin);
        reseteGiden=(Button)findViewById(R.id.SifremiUnuttum);

        regeGider=(Button)findViewById(R.id.btnReg);
        logaGider=(Button)findViewById(R.id.btnLogin);

        regeGider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });





        reseteGiden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,PasswordReset.class));
            }
        });

        //ParseUser simdikiuser=ParseUser.getCurrentUser();




    }


    @Override
    public void onClick(View v) {
        super.onClick(v);


        if (v.getId()==R.id.btnReg){
            startActivity(new Intent(this,Register.class));
        }else{

            String u=user.getText().toString();
            final String p=pwd.getText().toString();

            String sonSifre=p;

            if (u.length()==0||p.length()==0){

                Utils.showDialog(this,R.string.err_fields_empty);
                return;
            }

            final ProgressDialog dia=ProgressDialog.show(this,null,getString(R.string.alert_wait));

            ParseUser.logInInBackground(u, p, new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    dia.dismiss();

                    if (parseUser!=null){
                        UserList.user=parseUser;
                        //startActivity(new Intent(Login.this,UserList.class));
                        //startActivity(new Intent(Login.this,MainActivity1.class));

                        //sonradan
                        //String parseUsername=parseUser.getUsername();
                       // ParseQuery<ParseUser> query=new ParseQuery<ParseUser>("User");

                        //query.whereEqualTo("username",parseUsername);
                        boolean deger=parseUser.getBoolean("emailVerified");

                        if (deger){
                            Const.burdanBak=1;
                            startActivity(new Intent(Login.this, TabBar.class));

                            //Buraya konaacak owner kismi

                            ParseUser hemenUser=ParseUser.getCurrentUser();

                            ParseInstallation installation = ParseInstallation.getCurrentInstallation();
                            installation.put("owner", hemenUser.getUsername());
                            installation.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        // Utils.showDialog(Register.this,"sorun var gardas");
                                    } else {
                                        Utils.showDialog(Login.this, e.getMessage());

                                    }
                                }
                            });










                        }else{
                            Utils.showDialog(Login.this,"Email adresinizi onaylayin ");
                            Toast.makeText(Login.this,"Email adresinizi onaylayin",Toast.LENGTH_LONG).show();
                        }

                        //sonrdan


                        //startActivity(new Intent(Login.this, TabBar.class));


                    }else{
                        Utils.showDialog(Login.this,getString(R.string.err_login)+""+e.getMessage());
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==10&&resultCode==RESULT_OK)
            finish();
    }
}
