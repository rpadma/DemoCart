package com.etuloser.padma.rohit.midterm_rpadma;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Rohit on 3/20/2017.
 */

public class CustomObjectJutil {


    public static class CustomObjectJParser {

        public static ArrayList<CustomObject> GetParseredList(String in) throws JSONException {
            ArrayList<CustomObject> colist = new ArrayList<CustomObject>();

            JSONArray qarra = new JSONArray(in);


            for (int i = 0; i < qarra.length(); i++) {

                JSONObject jsonobj = qarra.getJSONObject(i);
                CustomObject co = new CustomObject();
                co.setName(jsonobj.getString("name").toString().trim());

                co.setPrice(jsonobj.getJSONArray("skus").getJSONObject(0).getString("sale_price"));
                co.setMspprice(jsonobj.getJSONArray("skus").getJSONObject(0).getString("msrp_price"));
                co.setImageurl(jsonobj.getJSONObject("image_urls").getJSONArray("300x400").getJSONObject(0).getString("url"));
                colist.add(co);
            }
            return colist;

        }
    }

}
