package com.senturk.fatih.chat03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;


public class StatusDetailView extends Activity {


    String objectId;

    protected TextView mStatusView;
    protected TextView mStatusUser;
    protected TextView mStatusTarih;

    protected EditText editlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_detail_view);

        //denemeler


        //denemeler

        Intent intent=getIntent();
        objectId=intent.getStringExtra("objectID");

        mStatusView=(TextView)findViewById(R.id.viewStatus);
        mStatusUser=(TextView)findViewById(R.id.viewStatusUser);
        mStatusTarih=(TextView)findViewById(R.id.statusTarih);

        ParseQuery<ParseObject> query=new ParseQuery<ParseObject>("Status");
        query.getInBackground(objectId, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e==null){

                    String userStatus=parseObject.getString("newStatus");
                    mStatusView.setText(userStatus);

                    String asilstatus=parseObject.getString("user");
                    mStatusUser.setText("["+asilstatus+"]");

                    //String tarih=parseObject.getString("createdAt");

                    Date tarih1=parseObject.getCreatedAt();
                    String logolo=tarih1.toString();

                    /*if (tarih1.toString()==null){
                        Toast.makeText(StatusDetailView.this,"tarihi cekemedim",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(StatusDetailView.this,"tarihi cekdim eheheh :)",Toast.LENGTH_LONG).show();
                        //String logolo=tarih1.toString();
                        Toast.makeText(StatusDetailView.this,logolo,Toast.LENGTH_LONG).show();
                    }
                    */

                    mStatusTarih.setText(logolo);


                }else{

                    Toast.makeText(StatusDetailView.this, "blgiler gelirken hata", Toast.LENGTH_LONG).show();
                    Toast.makeText(StatusDetailView.this, e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        });


    }


}
