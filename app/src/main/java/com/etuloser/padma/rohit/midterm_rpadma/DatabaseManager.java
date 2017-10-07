package com.etuloser.padma.rohit.midterm_rpadma;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Rohit on 3/20/2017.
 */

public class DatabaseManager {

    private Context context;
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    private CustomObjectDAO cDAO;

    public DatabaseManager(Context context) {
        this.context = context;
        dbHelper = new DatabaseHelper(this.context);
        db = dbHelper.getWritableDatabase();
        cDAO = new CustomObjectDAO(db);
    }
    public long save(CustomObject obj) {
        return cDAO.save(obj);
    }
    public List<CustomObject> getdetails(String uid) {
        return cDAO.getorderdetails(uid);
    }
    public List<String> getorders() {
        return cDAO.getorders();
    }


}

