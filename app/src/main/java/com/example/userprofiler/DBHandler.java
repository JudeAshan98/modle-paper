package com.example.userprofiler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DATABSE_NAME = "Profile.db";
    public static final String TABLE_NAME = "UserInfo";
    public static final String COL_1 = "_ID";
    public static final String COL_2 = "userName";
    public static final String COL_3 = "dateOfBirth";
    public static final String COL_4 = "Gender";
    public static final String COL_5 = "password";

    public DBHandler(@Nullable Context context) {
        super(context, DATABSE_NAME,null, 1);
     //   SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("CREATE TABLE " + TABLE_NAME +"(_ID integer primary key autoincrement, userName text,dateOfBirth DATE,Gender text,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addInfo(String userName,String dateOfBirth,String Gender,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,userName);
        contentValues.put(COL_3,dateOfBirth);
        contentValues.put(COL_4,Gender);
        contentValues.put(COL_5,password);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result==-1){
            return false;
            }
        else{
            return true;
        }

    }

    public boolean updateInfo(String userName,String dateOfBirth,String Gender,String id,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,userName);
        contentValues.put(COL_3,dateOfBirth);
        contentValues.put(COL_4,Gender);
        contentValues.put(COL_5,password);
        long res = db.update(TABLE_NAME,contentValues,"_ID=?",new String[]{id} );
        return true;

    }
    public Cursor readAllInfo(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cr = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cr;
    }

    public Cursor readAllInfo(String userName){
        SQLiteDatabase db = this.getWritableDatabase();
     //   Cursor cr = db.rawQuery("SELECT * FROM "+TABLE_NAME+"_ID = ?",new String[]{userName},null);
        Cursor cr = db.rawQuery("SELECT * FROM " + TABLE_NAME + " where userName = '" +userName + "'" , null);
    //    Cursor cr = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+ COL_2 + "="+ userName,null);
        return cr;
    }

    public boolean deleteInfo(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long result = db.delete(TABLE_NAME,"_ID= ?",new String[]{id});
        return true;
    }

}
