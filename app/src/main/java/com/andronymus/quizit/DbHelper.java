package com.andronymus.quizit;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VLad on 03/02/2015.
 */
public class DbHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "Qizit.db";
    private final static int DB_version = 1;
    private final static String TAG = DbHelper.class.getName();
    private boolean tablesCreated = false;


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseManager.CREATE_TABLE_CATEGORY);
        db.execSQL(DatabaseManager.CREATE_TABLE_ANSWER);
        db.execSQL(DatabaseManager.CREATE_TABLE_QUESTION);
        tablesCreated = true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean getTablesCreated (){
        return tablesCreated;
    }


}
