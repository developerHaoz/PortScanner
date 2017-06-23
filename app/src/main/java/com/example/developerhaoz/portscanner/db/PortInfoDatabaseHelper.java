package com.example.developerhaoz.portscanner.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.orhanobut.logger.Logger;

/**
 * Created by developerHaoz on 2017/6/18.
 */

public class PortInfoDatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;

    private static final String CREATE_PORT_INFO = "create table Port("
            + "id integer primary key autoincrement, "
            + "port integer, "
            + "service text, "
            + "type text)";

    public PortInfoDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        this.mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PORT_INFO);
        Logger.d("SQlite create");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Port");
        onCreate(db);
    }
}











