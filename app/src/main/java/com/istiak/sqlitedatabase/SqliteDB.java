package com.istiak.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqliteDB extends SQLiteOpenHelper {

    private static final int DB_VERSION=1;
    private static final String DB_NAME="database.db";
    private static final String TABLE="info";
    private static final String COLUMN1="id";
    private static final String COLUMN2="name";
    private static final String COLUMN3="phone";

    public SqliteDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query;
        query="CREATE TABLE "+TABLE+" (id INTEGER PRIMARY KEY, name TEXT, phone TEXT)";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);

    }




    //for insert data
    public boolean insertData(String id,String name,String phone)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN1,id);
        values.put(COLUMN2,name);
        values.put(COLUMN3,phone);

        long check=db.insert(TABLE,null,values);
        if (check==-1)  //data insert na hole -1 return kore
        {
            return false;

        }

        else
        {
            return true;
        }

    }



    //for view data
    public Cursor display()
    {
        SQLiteDatabase db=getReadableDatabase();
        Cursor result;
        result=db.rawQuery("SELECT * FROM "+TABLE,null);
        return result;

    }


    //for update
    public boolean updateData(String id,String name,String phone)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN1,id);
        values.put(COLUMN2,name);
        values.put(COLUMN3,phone);

        long check=db.update(TABLE,values,"id = ?",new String[] {id});
        if (check==-1)
        {
            return false;

        }

        else
        {
            return true;
        }

    }



    //for delete
    public int deleteData(String id)
    {
        SQLiteDatabase db=getWritableDatabase();

        return db.delete(TABLE,"id=?",new String[] {id});
    }

}
