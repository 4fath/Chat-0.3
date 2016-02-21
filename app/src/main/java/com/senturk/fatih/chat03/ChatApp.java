package com.senturk.fatih.chat03;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/**
 * Created by fatih on 13.6.2015.
 */
public class ChatApp extends Application {



    // Chat 0.3'un keyleri
    // Application ID=0rsjfrPn7FuKQpcbZQ3KSInXal1Onog1cuYY2x3a
    // Client ID=BRGprg8YSbzM1zDDXRfSwBYqMHwMfdEcCF3LWijo
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "0rsjfrPn7FuKQpcbZQ3KSInXal1Onog1cuYY2x3a", "BRGprg8YSbzM1zDDXRfSwBYqMHwMfdEcCF3LWijo");
        ParseUser.enableRevocableSessionInBackground();



        ParseInstallation.getCurrentInstallation().saveInBackground();


        ParsePush.subscribeInBackground("mesajlasma", new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }

            }
        });


       /* ParseInstallation suanda=ParseInstallation.getCurrentInstallation();
        String channeller=suanda.getObjectId();

        ParsePush.subscribeInBackground(channeller, new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }

            }
        });

        */






    }
}
