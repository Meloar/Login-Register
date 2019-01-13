package com.example.balzsmohr.logreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Adatbazis extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Felhasznalok.db";
    public static final String TABLE_NAME = "Felhasznalo_tabla";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "FELHASZNALONEV";
    public static final String COL_3 = "JELSZO";
    public static final String COL_4 = "TELJESNEV";
    public static final String COL_5 = "TELEFONSZAM";

    public Adatbazis(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, FELHASZNALONEV TEXT, JELSZO INTEGER, TELJESNEV TEXT, TELEFONSZAM INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

    }

    public boolean adatFelvetel(String felhasznalonev, String jelszo, String teljesnev, String telefonszam)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, felhasznalonev);
        contentValues.put(COL_3, jelszo);
        contentValues.put(COL_4, teljesnev);
        contentValues.put(COL_5, telefonszam);


        long eredmeny = db.insert(TABLE_NAME,null,contentValues);

        if (eredmeny == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor loginLekeres(String felhasznalo)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor eredmeny = db.rawQuery("SELECT FELHASZNALONEV, JELSZO FROM " + TABLE_NAME + " WHERE FELHASZNALONEV =" + "'" + felhasznalo + "'" , null);
        return eredmeny;
    }

    //Belépés
    public int adatTorles(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int eredmeny = db.delete(TABLE_NAME, "ID=?", new String[]{id});
        return eredmeny;
    }

}
