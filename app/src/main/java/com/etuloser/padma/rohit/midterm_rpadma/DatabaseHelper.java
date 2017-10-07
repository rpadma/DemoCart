package com.etuloser.padma.rohit.midterm_rpadma;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rohit on 3/20/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DB_NAME="mynotedb";
    static final int version=1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        CustomObjectTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        CustomObjectTable.onUpgrade(db,oldVersion,newVersion);
    }
}
