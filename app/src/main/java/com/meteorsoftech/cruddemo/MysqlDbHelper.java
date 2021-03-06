package com.meteorsoftech.cruddemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Delete on 6/24/2017.
 */

public class MysqlDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="register.DB";
    private static final int DATABASE_VERSION=1;
    protected static final String CREATE_QUERY=
            "CREATE TABLE "+ RegistrationContract.UserContract.TABLE_NAME+"" +
                    "("+ RegistrationContract.UserContract.USER_NAME+" TEXT,"+
                    RegistrationContract.UserContract.USER_MOB+" TEXT,"+
                    RegistrationContract.UserContract.USER_EMAIL+" TEXT,"+
                    RegistrationContract.UserContract.USER_ADDRESS+" TEXT);";

    public MysqlDbHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.e("Database Operation","Database is created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.e("Database Operation","Table is Created");

    }
    public void addInformation(String name,String mobileno,String email,String address,SQLiteDatabase db)
    {
        ContentValues values = new ContentValues();
        values.put(RegistrationContract.UserContract.USER_NAME,name);
        values.put(RegistrationContract.UserContract.USER_MOB,mobileno);
        values.put(RegistrationContract.UserContract.USER_EMAIL,email);
        values.put(RegistrationContract.UserContract.USER_ADDRESS,address);
        db.insert(RegistrationContract.UserContract.TABLE_NAME,null,values);

    }
    public Cursor getinformation(SQLiteDatabase db)
    {
        Cursor cursor;
        String[] projection={RegistrationContract.UserContract.USER_NAME,
                RegistrationContract.UserContract.USER_MOB,
                RegistrationContract.UserContract.USER_EMAIL,
                RegistrationContract.UserContract.USER_ADDRESS};
        cursor = db.query(RegistrationContract.UserContract.TABLE_NAME,projection,null,null,null,null,null);
        return cursor;
    }

    public Cursor SearchInformation(String username,SQLiteDatabase sqLiteDatabase)
    {
        String[] projection={RegistrationContract.UserContract.USER_MOB,
                RegistrationContract.UserContract.USER_EMAIL,
                RegistrationContract.UserContract.USER_ADDRESS};
        String selection= RegistrationContract.UserContract.USER_NAME+" LIKE ?";
        String[] selection_args={username};
        Cursor cursor = sqLiteDatabase.query(RegistrationContract.UserContract.TABLE_NAME,
                            projection,selection,selection_args,null,null,null);
        return cursor;
    }
    public void deleteinfromation(String username,SQLiteDatabase sqLiteDatabase)
    {
        String selection= RegistrationContract.UserContract.USER_NAME+" LIKE ?";
        String[] selection_args={username};
        sqLiteDatabase.delete(RegistrationContract.UserContract.TABLE_NAME,selection,selection_args);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
