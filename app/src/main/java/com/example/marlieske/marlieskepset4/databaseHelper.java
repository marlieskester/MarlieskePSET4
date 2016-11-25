package com.example.marlieske.marlieskepset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Marlieske on 21-11-2016.
 */

public class databaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "myDB.db";
    private static final String _ID = "_id";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE = "todo";
    String todo_id = "todo";
    String done_id = "done";
    String initial1 = "Welcome to TO DOlist";
    String initial2 = "Use checkboxes to mark as done.";
    String initial3 = "Tap item to change or delete.";

    public databaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String CREATE_TABLE = "CREATE TABLE " + TABLE + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " + todo_id + " TEXT, " + done_id + " TEXT );";
        //db.execSQL(CREATE_TABLE);
        db.execSQL("CREATE TABLE " + TABLE + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " + todo_id + " TEXT, " + done_id + " TEXT );");
    //    db.execSQL("insert into" + TABLE + "(" + todo_id + "," + done_id + ") VALUES (" + initial1 + ", " + initial2 + ", " + initial3 + ");");
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
        Log.d("create", values.toString());
        db.insert(TABLE, null, values);
    }

    public ArrayList<HashMap<String, String>> read() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT _id, " + todo_id + ", " + done_id + " FROM " + TABLE;
        ArrayList<HashMap<String, String >> myList = new ArrayList<>();
        HashMap<String, String> myToDo = new HashMap<>();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                myToDo.put("id", cursor.getString(cursor.getColumnIndex(_ID)));
                myToDo.put("todo", cursor.getString(cursor.getColumnIndex(todo_id)));
                myToDo.put("done", cursor.getString(cursor.getColumnIndex(done_id)));
            }
            while (cursor.moveToNext());
            myList.add(myToDo);
        }
        cursor.close();
        db.close();
        Log.d("arraylist", myList.toString());
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
