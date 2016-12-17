package edu.upc.jonatan.eetakemongo;

/**
 * Created by Jonatan on 14/12/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super (context, name, factory, version);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(codigo integer primary key autoincrement, nick text, password text)");
        db.execSQL("INSERT INTO user values ('admin','admin')");
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("create table user(codigo integer primary key autoincrement, nick text, password text)");
        db.execSQL("insert into user values ('admin','admin')");
    }
}
