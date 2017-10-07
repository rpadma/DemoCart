package com.etuloser.padma.rohit.midterm_rpadma;

import android.os.AsyncTask;

import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Rohit on 3/20/2017.
 */

public class CustomAsync extends AsyncTask<String,Void,ArrayList<CustomObject>> {

    Idata act;

    public CustomAsync(Idata act) {
    this.act=act;
    }

    @Override
    protected ArrayList<CustomObject> doInBackground(String... params) {

        InputStream in=null;

        try {
            URL uri=new URL(params[0]);
            HttpURLConnection con=(HttpURLConnection)uri.openConnection();
            con.connect();
            con.setRequestMethod("GET");
            int responseCode=con.getResponseCode();
            if(responseCode==con.HTTP_OK)
            {
                in=con.getInputStream();
            // json code
                BufferedReader br=new BufferedReader(new InputStreamReader(in));

                StringBuilder sb=new StringBuilder();
                String line=br.readLine();

                while(line!=null)
                {
                    sb.append(line);
                    line=br.readLine();
                }

                return  CustomObjectJutil.CustomObjectJParser.GetParseredList(sb.toString());

                //json end code

                //xml return
               // return CustomObjectXutil.CustomObjectXParser.GetParseredList(in);
          }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        finally {
            if(in!=null)
            {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        /*catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        */


        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<CustomObject> custobjlist) {

        act.sendData(custobjlist);
        super.onPostExecute(custobjlist);
    }

    public static interface Idata
    {
        public void sendData(ArrayList<CustomObject> colist);
    }
}
