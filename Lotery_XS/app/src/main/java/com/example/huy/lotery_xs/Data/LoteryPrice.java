package com.example.huy.lotery_xs.Data;

import java.util.ArrayList;

/**
 * Created by Viethuy_Iris on 4/28/2017.
 */

public class LoteryPrice {
    private String location;
    private String date;
    private ArrayList<String> lstPrice;

    public LoteryPrice() {
    }

    public LoteryPrice(String location, String date, ArrayList<String> lstPrice) {

        this.location = location;
        this.date = date;
        this.lstPrice = lstPrice;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<String> getLstPrice() {
        return lstPrice;
    }

    public void setLstPrice(ArrayList<String> lstPrice) {
        this.lstPrice = lstPrice;
    }
}
