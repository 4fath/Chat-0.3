package com.senturk.fatih.chat03;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fatih on 13.6.2015.
 */
public class UserList extends CustomActivity {
    private ArrayList<ParseUser> uList;
    public static ParseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);

        //getActionBar().setDisplayHomeAsUpEnabled(false);

        updateUserStatus(true);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        updateUserStatus(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUserList();
    }


    private void updateUserStatus(boolean online) {
        user.put("online", online);
        user.saveEventually();
    }


    private void loadUserList() {

        ParseUser suanki = ParseUser.getCurrentUser();

        if (suanki == null) {
            Toast.makeText(UserList.this, "Giris yapiniz", Toast.LENGTH_LONG).show();
            startActivity(new Intent(UserList.this, Login.class));
        } else {

            final ProgressDialog dia = ProgressDialog.show(this, null, getString(R.string.alert_loading));
            ParseUser.getQuery().whereNotEqualTo("username", user.getUsername())
                    .findInBackground(new FindCallback<ParseUser>() {
                        @Override
                        public void done(List<ParseUser> list, ParseException e) {
                            dia.dismiss();
                            if (list != null) {
                                if (list.size() == 0) {
                                    Toast.makeText(UserList.this, R.string.msg_no_user_found, Toast.LENGTH_LONG).show();
                                }
                                uList = new ArrayList<ParseUser>(list);
                                ListView listView = (ListView) findViewById(R.id.list);
                                listView.setAdapter(new UserAdapter());
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        startActivity(new Intent(UserList.this, Chat.class).putExtra(Const.EXTRA_DATA,
                                                uList.get(position).getUsername()));
                                    }
                                });
                            } else {
                                Utils.showDialog(UserList.this, getString(R.string.err_users) + "" + e.getMessage());
                                e.printStackTrace();
                            }
                        }
                    });
        }

    }


    private class UserAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return uList.size();
        }

        @Override
        public ParseUser getItem(int arg0) {
            return uList.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            return arg0;
        }

        @Override
        public View getView(int pos, View v, ViewGroup arg2) {
            if (v == null) {
                v = getLayoutInflater().inflate(R.layout.chat_item, null);
            }

            ParseUser c = getItem(pos);
            TextView lbl = (TextView) v;
            lbl.setText(c.getUsername());
            lbl.setCompoundDrawablesWithIntrinsicBounds(c.getBoolean("online") ? R.drawable.status_online :
                    R.drawable.ic_offline, 0, R.drawable.arrow, 0);

            return v;
        }
    }


}
