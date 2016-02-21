package com.senturk.fatih.chat03;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.Dictionary;
import java.util.List;

/**
 * Created by fatih on 17.6.2015.
 */
public class StatusAdapter extends ArrayAdapter<ParseObject> {


    protected Context mContext;
    protected List<ParseObject> mStatus;


    public StatusAdapter(Context context, List<ParseObject> status) {
        super(context, R.layout.homepagecustom,status);

        mContext=context;
        mStatus=status;
    }

    @SuppressLint("WrongViewCast")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.homepagecustom,null);
            holder=new ViewHolder();

            holder.usernameHPli=(TextView)convertView.findViewById(R.id.usernameHP);
            holder.comment=(TextView)convertView.findViewById(R.id.statusHP);

            convertView.setTag(holder);


        }else{
            holder=(ViewHolder)convertView.getTag();
        }


        ParseObject statusObject=mStatus.get(position);

        String username=statusObject.getString("user");
        holder.usernameHPli.setText(username);

        String comment=statusObject.getString("newStatus");
        holder.comment.setText(comment);



        return convertView;

    }

    public static class ViewHolder{
        TextView usernameHPli;
        TextView comment;
    }
}
