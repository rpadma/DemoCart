package com.etuloser.padma.rohit.midterm_rpadma;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Rohit on 3/20/2017.
 */

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    ArrayList<CustomObject> colist;
    Callback callback;

    public CustomAdapter(ArrayList<CustomObject> colist,Callback callback)
    {
        this.colist=colist;
        this.callback=callback;

    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txttitle;
        public TextView txtprice;
        public ImageView imgview;
        public TextView txtdisc;
        public Button btnadd;
        public LinearLayout ll;



        public ViewHolder(View v) {

            super(v);

            txttitle=(TextView)v.findViewById(R.id.temptitle);
            txtprice=(TextView)v.findViewById(R.id.tempprice);
            txtdisc=(TextView)v.findViewById(R.id.tempdiscount);
            imgview=(ImageView)v.findViewById(R.id.tempcimage1);
            btnadd=(Button)v.findViewById(R.id.btnaddtocart);

        }


    }


    public static interface Callback{

        void sentcartitem(int position);

    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.childcardview, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;


    }

    @Override
    public void onBindViewHolder(final CustomAdapter.ViewHolder holder, final int position) {

        CustomObject co = colist.get(position);

        if (co.getImageurl().length() > 0) {

            // holder.imagecview.setBackground(null);
            Picasso.with(holder.imgview.getContext()).load(co.getImageurl()).into(holder.imgview);
        }

        if(co.getName()!=null) {
            holder.txttitle.setText("Title:"+co.getName());
        }

        if(co.getPrice().length()>0 && co.getPrice()!=null)
        {
            holder.txtprice.setText("Price:"+co.getPrice());
        }

        double msp=Double.valueOf(co.getMspprice());
        double sp=Double.valueOf(co.getPrice());
        double tempdisc=((msp-sp)/msp)*100;
            holder.txtdisc.setText("Discount:"+String.valueOf(tempdisc)+"%");

        holder.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        callback.sentcartitem(position);

                holder.btnadd.setEnabled(false);
                  //      Toast.makeText(v.getContext(),"addd",Toast.LENGTH_SHORT).show();

            }
        });



        Log.d("error",String.valueOf(position));
        }

    @Override
    public int getItemCount() {
        return colist.size();
    }
}
