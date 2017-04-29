package com.example.huy.lotery_xs;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huy.lotery_xs.Data.LoteryPrice;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class FilterLotery extends Fragment {
    Spinner lstProvince;
    TextView dateSet;
    ImageView getDatebtn,okbtn;
    ListView listLo;
    String today = new SimpleDateFormat("dd-MM").format(new Date());

    public FilterLotery() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_filter_lotery, container, false);
        String[] lstQuery =  new String[]{"AG","BD","BP","BL","BTH","CM","CT","HCM"};
        String[] lst = new String[]{"AN GIANG","BINH DUONG","BINH PHUOC","BAO LOC","BINH THUAN","CA MAU","CAN THO","HCM"};
        listLo = (ListView) view.findViewById(R.id.listLo);
        lstProvince = (Spinner)view.findViewById(R.id.listPlace);
        dateSet = (TextView)view.findViewById(R.id.showDate);
        okbtn = (ImageView)view.findViewById(R.id.okbtn);
        dateSet.setText(today);
        getDatebtn = (ImageView)view.findViewById(R.id.getDatebtn);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,lst);
       lstProvince.setAdapter(adapter);

        getDatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new SelectDate();
                dialogFragment.show(getFragmentManager(),"Date Picker");
            }
        });
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetLot().execute("http://thanhhungqb.tk:8080/kqxsmn");
            }
        });

        return view;
    }
    class GetLot extends AsyncTask<String,Integer,String> {
        @Override
        protected String doInBackground(String... params) {

            return ReadURL.docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            try {

                String[] lst = new String[]{"AN GIANG","BINH DUONG","BINH PHUOC","BAO LOC","BINH THUAN","CA MAU","CAN THO","HCM"};
                String[] lstQuery =  new String[]{"AG","BD","BP","BL","BTH","CM","CT","HCM"};
                ArrayList<LoteryPrice> lstLocation = new ArrayList<LoteryPrice>();
                JSONObject root = new JSONObject(s);
                int position =  lstProvince.getSelectedItemPosition();
                String content = root.getString(lstQuery[position]);
                JSONObject root_child = new JSONObject(content);
                String content2 = root_child.getString(dateSet.getText().toString());
                JSONObject root_child2 = new JSONObject(content2);
                if (content2 != null) {
                    ArrayList<String> lstGiaithuong = new ArrayList<String>();
                    lstGiaithuong.add(root_child2.getString("DB"));
                    for (int j = 1; j < 9; j++) {
                        lstGiaithuong.add(root_child2.getString("" + j + ""));
                    }
                    LoteryPrice loteryPrice = new LoteryPrice();
                    loteryPrice.setLocation(lstProvince.getSelectedItem().toString());
                    loteryPrice.setDate(dateSet.getText().toString());
                    loteryPrice.setLstPrice(lstGiaithuong);

                    lstLocation.add(loteryPrice);

                }
                LocationAdapter adapter = new LocationAdapter(getActivity().getApplicationContext(),R.layout.itemprice,lstLocation);
                listLo.setAdapter(adapter);
             //   Toast.makeText(getActivity(), "thanh cong", Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                Toast.makeText(getActivity(),"Hien Khong Co Ket Qua Can Tim",Toast.LENGTH_SHORT).show();
            }

        }
    }
}
