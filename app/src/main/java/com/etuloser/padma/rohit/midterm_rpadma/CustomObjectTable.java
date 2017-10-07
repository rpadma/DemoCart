package com.etuloser.padma.rohit.midterm_rpadma;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Rohit on 3/20/2017.
 */

public class CustomObjectTable {

    static final String TABLENAME="products";
    // columanames
    static final String _id="id";
    static final String _name="name";
    static final String _price="price";
    static  final String _discount="discount";
    static final String _imageurl="imgurl";
    static final String _uid="uid";


    static public void onCreate(SQLiteDatabase db)
    {

        StringBuilder sb=new StringBuilder();
        sb.append("CREATE TABLE  "+TABLENAME+" (");
        sb.append(_id+" integer primary key autoincrement, ");
        sb.append(_name+"  text  not null, ");
        sb.append(_price+" text not null, ");
        sb.append(_uid+" text not null, ");
        sb.append(_imageurl+" text ");
        sb.append(");");

        try
        {

            db.execSQL(sb.toString());
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    static public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
    {
        db.execSQL("DROP TABLE IF EXIST "+TABLENAME);
        CustomObjectTable.onCreate(db);

    }

}


