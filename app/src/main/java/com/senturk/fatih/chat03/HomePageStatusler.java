package com.senturk.fatih.chat03;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by fatih on 17.6.2015.
 */
public class HomePageStatusler extends ListActivity {


    protected List<ParseObject> mStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepagestatusler);


        ParseUser lanere = ParseUser.getCurrentUser();

        if (lanere == null) {
            Toast.makeText(HomePageStatusler.this, "Giris yapiniz", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(HomePageStatusler.this, Login.class));
            return;
        } else {


            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Status");

            query.orderByDescending("createdAt");
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> statusObjects, ParseException e) {

                    if (e == null) {
                        Toast.makeText(HomePageStatusler.this, "succes", Toast.LENGTH_LONG).show();

                        mStatus = statusObjects;

                        StatusAdapter adapter = new StatusAdapter(getListView().getContext(), mStatus);

                        setListAdapter(adapter);


                    } else {
                        Toast.makeText(HomePageStatusler.this, "there is a problem", Toast.LENGTH_LONG).show();
                        Toast.makeText(HomePageStatusler.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }


    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ParseUser suanki = ParseUser.getCurrentUser();

        if (suanki == null) {
            Toast.makeText(HomePageStatusler.this, "Detaylari gormek icin giris yapmak gerekli", Toast.LENGTH_LONG).show();
            startActivity(new Intent(HomePageStatusler.this,Login.class));
        } else {

            ParseObject statusObject = mStatus.get(position);

            String onjectId = statusObject.getObjectId();

            Intent goToIntent = new Intent(HomePageStatusler.this, StatusDetailView.class);
            goToIntent.putExtra("objectID", onjectId);
            startActivity(goToIntent);

        }

    }
}
