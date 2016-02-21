package com.senturk.fatih.chat03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;


public class UpdateStatus extends Activity {

    protected EditText mStatusUpdate;
    protected Button mStatusButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status);



        mStatusUpdate=(EditText)findViewById(R.id.updateStatusText);
        mStatusButton=(Button)findViewById(R.id.updateStatusButton);

        String durum=mStatusButton.getText().toString().trim();
        if (durum==null){
            Utils.showDialog(UpdateStatus.this,"Durum bos olamaz,birseyler yazin");
            return;
        }else{

        }

        mStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseUser currentUser= ParseUser.getCurrentUser();
                String currentUserName=currentUser.getUsername();

                String newStatus=mStatusUpdate.getText().toString();

                ParseObject statusObject=new ParseObject("Status");
                statusObject.put("newStatus",newStatus);
                statusObject.put("user",currentUserName);

                statusObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {

                        if (e==null){
                            Toast.makeText(UpdateStatus.this, "logololo", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(UpdateStatus.this, "logjasgfkhasololo", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateStatus.this,MainActivity1.class));
                        }
                    }
                });
            }
        });








    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_status, menu);
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
