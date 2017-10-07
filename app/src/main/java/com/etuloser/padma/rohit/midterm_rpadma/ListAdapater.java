package com.etuloser.padma.rohit.midterm_rpadma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Rohit on 3/20/2017.
 */

public class ListAdapater extends ArrayAdapter<CustomObject> {


    Context context;
    int crsc;
    ArrayList<CustomObject> lgame=new ArrayList<>();

    public ListAdapater(Context context, int resource, ArrayList<CustomObject> objects) {
        super(context, resource, objects);
        this.lgame=objects;
        this.crsc=resource;
        this.context=context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(crsc, parent, false);
        }

        ImageView iv=(ImageView)convertView.findViewById(R.id.lvimage);
        TextView tvprice=(TextView)convertView.findViewById(R.id.lvprice);
        TextView tvtitle=(TextView)convertView.findViewById(R.id.lvtitle);


        CustomObject co=lgame.get(position);

        if(co.getImageurl()!=null) {
            Picasso.with(context).load(co.getImageurl()).into(iv, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {


                }
            });
        }

        if(co.getName().length()>0)
        {
            tvtitle.setText(co.getName());
        }

        if(co.getPrice().length()>0)
        {
            tvtitle.setText(co.getPrice());
        }

        return convertView;
    }

}
