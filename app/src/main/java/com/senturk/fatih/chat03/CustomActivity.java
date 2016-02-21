package com.senturk.fatih.chat03;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by fatih on 13.6.2015.
 */
public class CustomActivity extends FragmentActivity implements View.OnClickListener {

    public static TouchEffect TOUCH=new TouchEffect();


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setupActionBar();
    }

    protected void setupActionBar() {
        final ActionBar actionBar=getActionBar();
        if(actionBar==null)
            return;

        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.drawable.icon);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_bg));
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    public View setTouchNClick(int id){
        View v=setClick(id);
        if (v!=null){
            v.setOnTouchListener(TOUCH);
        }
        return v;
    }

    private View setClick(int id) {
        View v=findViewById(id);
        if (v!=null){
            v.setOnClickListener(this);
        }
        return v;
    }


    @Override
    public void onClick(View v) {

    }
}
