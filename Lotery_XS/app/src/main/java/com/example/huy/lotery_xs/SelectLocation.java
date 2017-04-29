package com.example.huy.lotery_xs;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huy.lotery_xs.Data.LoteryPrice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class SelectLocation extends Fragment {
    ListView lstView;
    ArrayList<TextView> lstText = new ArrayList<TextView>();
    Calendar c = Calendar.getInstance();
    String today = new SimpleDateFormat("dd-MM").format(new Date());
    public SelectLocation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_select_location,container,false);

        lstView = (ListView) view.findViewById(R.id.lstPrice);

            // Inflate the layout for this fragment
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String query = "http://thanhhungqb.tk:8080/kqxsmn";
                new GetingLotery().execute(query);
            }
        });
        return view;
    }
    class GetingLotery extends AsyncTask<String,Integer,String>{
        @Override
        protected String doInBackground(String... params) {
            return ReadURL.docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject root = new JSONObject(s);
                ArrayList<LoteryPrice> lstLocation = new ArrayList<LoteryPrice>();
                String[] lstQuery =  new String[]{"AG","BD","BP","BL","BTH","CM","CT","HCM"};
                String[] lst = new String[]{"AN GIANG","BINH DUONG","BINH PHUOC","BAO LOC","BINH THUAN","CA MAU","CAN THO","HCM"};
                for(int i = 0 ;i < 8 ; i++){
                    try {
                        String content = root.getString(lstQuery[i]);
                        JSONObject root_child = new JSONObject(content);
                        // root_child

                        String content2 = root_child.getString(today);
                        JSONObject root_child2 = new JSONObject(content2);

                        if (content2 != null) {
                            ArrayList<String> lstGiaithuong = new ArrayList<String>();
                            lstGiaithuong.add(root_child2.getString("DB"));
                            for (int j = 1; j < 9; j++) {
                                lstGiaithuong.add(root_child2.getString("" + j + ""));
                            }
                            LoteryPrice loteryPrice = new LoteryPrice();
                            loteryPrice.setLocation(lst[i]);
                                loteryPrice.setDate(today);
                               loteryPrice.setLstPrice(lstGiaithuong);

                               lstLocation.add(loteryPrice);

                        }

                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }

                }
                if(lstLocation.size() == 0){
                    Toast.makeText(getActivity(),"Khong Co Ket Qua Xo So Cho Ngay Hom Nay", Toast.LENGTH_SHORT).show();
                }
            LocationAdapter adapter = new LocationAdapter(getActivity().getApplicationContext(),R.layout.itemprice,lstLocation);
                lstView.setAdapter(adapter);
            } catch (JSONException e) {
                Toast.makeText(getActivity(),"Khong Co Ket Qua Xs Cho Ngay Hom Nay",Toast.LENGTH_SHORT);
                e.printStackTrace();
            }


        }
    }
}
