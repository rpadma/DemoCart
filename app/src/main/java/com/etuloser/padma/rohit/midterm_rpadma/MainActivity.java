package com.etuloser.padma.rohit.midterm_rpadma;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CustomAsync.Idata,CustomAdapter.Callback {


    private RecyclerView recyclerView;
    public DatabaseManager dm;
    ProgressDialog pd;
    ArrayList<CustomObject> cartlist=new ArrayList<CustomObject>();
    ArrayList<CustomObject> mainlist=new ArrayList<CustomObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dm = new DatabaseManager(this);
        recyclerView=(RecyclerView)findViewById(R.id.rvlist);
        recyclerView.setHasFixedSize(true);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni=cm.getActiveNetworkInfo();
        if(ni!=null && ni.isConnected()) {


            getdata();

        }
        else
        {
            Toast.makeText(this,"No Wifi / Mobile Data",Toast.LENGTH_SHORT).show();
        }



    }


    public void getdata()
    {
        pd=new ProgressDialog(this);
        pd.setTitle("Loading data");
        pd.show();
        String url="http://52.90.79.130:8080/MidTerm/get/products";

        new CustomAsync(this).execute(url);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i=new Intent(this,CartActivity.class);
            i.putExtra("cartlist",cartlist);
            i.putExtra("flag","1");
            startActivity(i);

        }else
             if(id==R.id.action_settings1)
             {


                 final List<String> uids=(List<String>) dm.getorders();

                CharSequence[] ordername = new CharSequence[uids.size()];


                 for ( int i=0;i<uids.size();i++) {

                     ordername[i] = "Order "+String.valueOf(i);

                 }

                 final AlertDialog.Builder editalertbuilder = new AlertDialog.Builder(this);
                 editalertbuilder.setTitle("Select an Order");
                 editalertbuilder.setItems(ordername, new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {


                        String orderuid= uids.get(which);
                         gethistoryview(orderuid);


                     }
                 }).show();



             }

        return super.onOptionsItemSelected(item);
    }

    public void gethistoryview(String uid)
    {

        ArrayList<CustomObject> col=new ArrayList<CustomObject>();
        col=(ArrayList<CustomObject>)dm.getdetails(uid);
        Intent i=new Intent(this,CartActivity.class);
        i.putExtra("cartlist",col);
        i.putExtra("flag","2");
        startActivity(i);

    }
    @Override
    public void sendData(ArrayList<CustomObject> colist) {

        mainlist=colist;
        Toast.makeText(this,colist.get(0).toString(),Toast.LENGTH_SHORT).show();
        pd.hide();

        displaydata(colist);

    }
    public void displaydata(ArrayList<CustomObject> colists)
    {

        //  Toast.makeText(this,"2",Toast.LENGTH_SHORT).show();
        recyclerView.setLayoutManager(new GridLayoutManager
                (this,
                        3,
                        GridLayoutManager.VERTICAL, false));

        CustomAdapter ca = new CustomAdapter(colists,this);

        recyclerView.setAdapter(ca);
        ca.notifyDataSetChanged();

    }


    @Override
    public void sentcartitem(int position) {

        cartlist.add(mainlist.get(position));
    }
}
