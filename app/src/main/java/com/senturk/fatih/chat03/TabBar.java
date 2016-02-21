package com.senturk.fatih.chat03;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

/**
 * Created by fatih on 17.6.2015.
 */
public class TabBar extends TabActivity implements TabHost.OnTabChangeListener {

    TabHost tabHost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_bar);


        tabHost=getTabHost();
        tabHost.setOnTabChangedListener(this);

        TabHost.TabSpec spec;
        Intent intent;



        //
        intent=new Intent().setClass(this, UserList.class);
        spec=tabHost.newTabSpec("First").setIndicator("").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, HomePageStatusler.class);
        spec = tabHost.newTabSpec("Second").setIndicator("").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, Settings.class);
        spec = tabHost.newTabSpec("Third").setIndicator("").setContent(intent);
        tabHost.addTab(spec);



        tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.kisiler_siyah);
        tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.durumlar_gri);

        tabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.ayarlar_gri);



    }

    @Override
    public void onTabChanged(String tabId) {

        for (int i=0;i<tabHost.getTabWidget().getChildCount();i++){
            if (i==0){
                tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.kisiler_gri);
            }else if (i==1){
                tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.durumlar_gri);
            }else if(i==2){
                tabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.ayarlar_gri);

            }

        }

        Log.i("tabs", "CurrentTab:" + tabHost.getCurrentTab());

        if (tabHost.getCurrentTab()==0)
            tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.drawable.kisiler_siyah);
        else if (tabHost.getCurrentTab()==1)
            tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.drawable.durumlar_siyah);
        else if (tabHost.getCurrentTab()==2)
            tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab()).setBackgroundResource(R.drawable.ayarlar_siyah);





    }
}
