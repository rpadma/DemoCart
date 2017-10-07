package com.etuloser.padma.rohit.midterm_rpadma;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rohit on 3/20/2017.
 */

public class CustomObjectDAO {

    private SQLiteDatabase sdb;
    public CustomObjectDAO(SQLiteDatabase sdb) {
        this.sdb = sdb;
    }

    public  long save(CustomObject co)
    {
        ContentValues cv=new ContentValues();
        cv.put(CustomObjectTable._name,co.getName());  // need to change values in quotes
        cv.put(CustomObjectTable._price,co.getPrice());   // need to change values in quotes
        cv.put(CustomObjectTable._uid,co.getUID());
        cv.put(CustomObjectTable._imageurl,co.getImageurl());


        return sdb.insert(CustomObjectTable.TABLENAME,null,cv);

    }


    private CustomObject buildfromcursor(Cursor c)
    {
        CustomObject co=null;

        if(c!=null)
        {
            co=new CustomObject();
            co.set_id(c.getLong(0));
            co.setName(c.getString(1));
            co.setPrice(c.getString(2));
            co.setImageurl(c.getString(3));
           // co.setStatus(c.getString(3));
           // co.setUpdate_time(c.getString(4));


        }

        return co;

    }



    private String buildfromcursor1(Cursor c)
    {


        return c.getString(0);

    }


    public List<CustomObject> getorderdetails(String uid) {



        List<CustomObject> colist=new ArrayList<CustomObject>();
        Cursor c= sdb.query(CustomObjectTable.TABLENAME,new String[]{CustomObjectTable._id,CustomObjectTable._name,
                CustomObjectTable._price,CustomObjectTable._imageurl},CustomObjectTable._uid+" =?",
                new String[]{uid.toString()},null,null,null);

        if(c!=null && c.moveToFirst())
        {
            do {
                CustomObject co=buildfromcursor(c);
                if(co!=null)
                {
                    colist.add(co);
                }
            }while(c.moveToNext());
            if(!c.isClosed())
            {
                c.close();
            }
        }

        return colist;



    }


    public List<String> getorders()
    {

        List<String> uidlist=new ArrayList<String>();

        Cursor c= sdb.query(true,CustomObjectTable.TABLENAME,new String[]{CustomObjectTable._uid},null,null,null,null,null,null);

        if(c!=null && c.moveToFirst())
        {
            do {
                    uidlist.add(buildfromcursor1(c));

            }while(c.moveToNext());
            if(!c.isClosed())
            {
                c.close();
            }
        }

        return uidlist;
    }


}
