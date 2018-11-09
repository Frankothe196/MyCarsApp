package com.thecarapp.mycarsapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<usercars> {
private Activity activity;
   private List<usercars> lstUsers;
    LayoutInflater inflater;


    public ListViewAdapter(Activity activity, List<usercars>lstUsers) {
        super(activity,R.layout.activity_listcars, lstUsers);
        this.activity = activity;
        this.lstUsers = lstUsers;
    }

    @Override
    public int getCount() {
        return lstUsers.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
          LayoutInflater inflater= activity.getLayoutInflater();
           View itemView= inflater.inflate(R.layout.activity_listcars,null,true);

        TextView txtYear = (TextView)itemView.findViewById(R.id.lstcaryear);
        TextView txtmake = (TextView)itemView.findViewById(R.id.lstcarmake);
        TextView txtmodel = (TextView)itemView.findViewById(R.id.lstcarmodel);
        TextView txtengine = (TextView)itemView.findViewById(R.id.lstcarengine);
        TextView txtregno = (TextView)itemView.findViewById(R.id.lstcarreg);

       usercars users = lstUsers.get(position);

        txtYear.setText(lstUsers.get(position).getYear());
        txtmake.setText(lstUsers.get(position).getMake());
        txtmodel.setText(lstUsers.get(position).getModel());
        txtengine.setText(lstUsers.get(position).getEngine());
        txtregno.setText(lstUsers.get(position).getRegNo());

        return itemView;

    }
}
