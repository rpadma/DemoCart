package com.etuloser.padma.rohit.midterm_rpadma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CartActivity extends AppCompatActivity {
    public DatabaseManager dm;
    ListView lv;
    String flag;
    Button btncancel,btncheck;
    TextView txttotal;
 //   String UID;
    ArrayList<CustomObject> colist=new ArrayList<CustomObject>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        dm = new DatabaseManager(this);
        lv=(ListView)findViewById(R.id.lvcart);
        txttotal=(TextView)findViewById(R.id.txttotal);
        btncancel=(Button)findViewById(R.id.btncancel);
        btncheck=(Button)findViewById(R.id.btnchkout);
        if(getIntent().getExtras()!=null)
        {
            colist=(ArrayList<CustomObject>)(getIntent().getExtras().getSerializable("cartlist"));
            flag=getIntent().getExtras().getString("flag");
         //   UID=getIntent().getExtras().getString("uid");
        }

        if(flag.equals("2"))
        {
            btncheck.setVisibility(View.INVISIBLE);
            txttotal.setVisibility(View.INVISIBLE);
        }

    if (colist.size() > 0) {
        displaylist();
    }

    lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            // Toast.makeText(this,colist.get(position).getName().toString(),Toast.LENGTH_SHORT).show();

            colist.remove(position);
            displaylist();

            return true;

        }
    });


    }


    public void displaylist()
    {

        double totalprice=0;

        for(CustomObject co : colist)
        {
            totalprice+=Double.valueOf(co.getPrice());
        }
        txttotal.setText("Total:   "+String.valueOf(totalprice)+"$");
        ListAdapater ca=new ListAdapater(this,R.layout.listcartview,colist);
        ca.notifyDataSetChanged();
        lv.setAdapter(ca);



    }

    public void Cancelclick(View v)
    {

        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }

    public void Checkout(View v)
    {

        String uuid = String.valueOf(UUID.randomUUID());

        for(CustomObject co:colist) {


            co.setUID(uuid);
          long id= dm.save(co);

            Log.d("id:",String.valueOf(id));


        }

        colist=null;

        finish();

    }





}
