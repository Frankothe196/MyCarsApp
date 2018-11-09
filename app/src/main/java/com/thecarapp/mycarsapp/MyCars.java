package com.thecarapp.mycarsapp;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyCars extends BaseAdapter {

    Activity activity;
    List<usercars> lstcars;
    LayoutInflater inflater;


    @Override
    public int getCount() {
        return lstcars.size();
    }

    @Override
    public Object getItem(int i) {
        return lstcars.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        inflater=(LayoutInflater)activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.activity_listcars,null);

        TextView txtYear=(TextView)itemView.findViewById(R.id.caryear);
        TextView txtMake=(TextView)itemView.findViewById(R.id.carmake);
        TextView txtModel=(TextView)itemView.findViewById(R.id.carModel);
        TextView txtEngine=(TextView)itemView.findViewById(R.id.carengine);
        TextView txtReg=(TextView)itemView.findViewById(R.id.carreg);

        txtYear.setText(lstcars.get(i).getYear());
        txtMake.setText(lstcars.get(i).getMake());
        txtModel.setText(lstcars.get(i).getModel());
        txtEngine.setText(lstcars.get(i).getEngine());
        txtReg.setText(lstcars.get(i).getRegNo());

        return itemView;

    }

}
