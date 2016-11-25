package com.example.marlieske.marlieskepset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Marlieske on 21-11-2016.
 */

public class databaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "myDB.db";
    private static final String _ID = "_id";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE = "to-do's";
    String todo_id = "to-do";
    String done_id = "done";

    public databaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " + todo_id + " TEXT, " + done_id + "TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void create(MainActivity.Items item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(todo_id, item.todo);
        values.put(done_id, item.done);
        db.insert(TABLE, null, values);
    }

    public ArrayList<HashMap<String, String>> read() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT _id , " + todo_id + ", " + done_id + "FROM " + TABLE;
        ArrayList<HashMap<String, String>> myList = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> myToDo = new HashMap<>();
                myToDo.put("id", cursor.getString(cursor.getColumnIndex("_id")));
                myToDo.put("todo", cursor.getString(cursor.getColumnIndex(todo_id)));
                myToDo.put("done", cursor.getString(cursor.getColumnIndex(done_id)));
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return myList;
    }

    public void getID (String todo) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT _id FROM " + TABLE + "WHERE " + todo_id + " equals " + todo;
    }

    public void update (MainActivity.Items item) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(todo_id, item.todo);
        values.put(done_id, item.done);
        db.update(TABLE, values, _ID, null);
    }

    public void delete(String _ID) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE, _ID, null);
    }
}
