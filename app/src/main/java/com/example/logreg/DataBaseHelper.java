package com.example.logreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static felhasznalo felhasznalo;
    String nev;

    private static final int DATABASEversion = 1;
    private static final String DBname = "Felhasznalok.db";

    private static final String TABLE_NAME = "felhasznalo";
    private static final String COL_ID = "id";
    private static final String COL_EMAIL = "email";
    private static final String COL_USERNAME = "username";
    private static final String COL_PASSWORD = "password";
    private static final String COL_FULLNAME = "fullname";

    private SQLiteDatabase db;

    public DataBaseHelper(Context context){ super(context,DBname,null,DATABASEversion);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME + "(" +
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_EMAIL + "VARCHAR(30)," +
                COL_USERNAME + "VARCHAR(30)," +
                COL_PASSWORD + "VARCHAR(30)," +
                COL_FULLNAME + "VARCHAR(30))";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
    }

    public boolean dataRecord(String email, String username, String password, String fullname)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMAIL, email);
        values.put(COL_USERNAME, username);
        values.put(COL_PASSWORD, password);
        values.put(COL_FULLNAME, fullname);

        long lines = db.insert(TABLE_NAME,null,values);
        if(lines == -1)
        {
            return false;
        }
        else
            {
                return true;
            }
    }

    public Cursor dataSelect()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+ TABLE_NAME,null);
    }
}
