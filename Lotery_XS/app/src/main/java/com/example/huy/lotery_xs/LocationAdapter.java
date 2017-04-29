package com.example.huy.lotery_xs;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.huy.lotery_xs.Data.LoteryPrice;


import java.util.ArrayList;

/**
 * Created by Viethuy_Iris on 4/28/2017.
 */

public class LocationAdapter extends ArrayAdapter<LoteryPrice> {
    int resId;
    ArrayList<LoteryPrice> lstLotery;
    Context context;

    public LocationAdapter( Context context, int resId, ArrayList<LoteryPrice> lstLotery) {
        super(context, resId,lstLotery);
        this.context = context;
        this.resId = resId;
        this.lstLotery = lstLotery;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(resId,null);
        ArrayList<TextView> lstTextView = new ArrayList<>();
        TextView giaiDB = (TextView)convertView.findViewById(R.id.giaiDB);
        lstTextView.add(giaiDB);
        TextView giai1 = (TextView) convertView.findViewById(R.id.giai1);
        lstTextView.add(giai1);
        TextView giai2 = (TextView) convertView.findViewById(R.id.giai2);
        lstTextView.add(giai2);
        TextView giai3 = (TextView) convertView.findViewById(R.id.giai3);
        lstTextView.add(giai3);
        TextView giai4 = (TextView) convertView.findViewById(R.id.giai4);
        lstTextView.add(giai4);
        TextView giai5 = (TextView) convertView.findViewById(R.id.giai5);
        lstTextView.add(giai5);
        TextView giai6 = (TextView) convertView.findViewById(R.id.giai6);
        lstTextView.add(giai6);
        TextView giai7 = (TextView) convertView.findViewById(R.id.giai7);
        lstTextView.add(giai7);
        TextView giai8 = (TextView) convertView.findViewById(R.id.giai8);
        lstTextView.add(giai8);

        TextView title = (TextView)convertView.findViewById(R.id.nameLocation);
        TextView date = (TextView)convertView.findViewById(R.id.date);
        LoteryPrice loteryPrice =  lstLotery.get(position);
        if(loteryPrice != null){
            for(int i = 0 ;i < 9 ; i++){
                lstTextView.get(i).setText(loteryPrice.getLstPrice().get(i));

            }
            title.setText(loteryPrice.getLocation());
            date.setText(loteryPrice.getDate());
        }

        return convertView;
    }
}
