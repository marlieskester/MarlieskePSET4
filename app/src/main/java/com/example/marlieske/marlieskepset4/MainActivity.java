package com.example.marlieske.marlieskepset4;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.marlieske.marlieskepset4.R.id.ETNew;
import static com.example.marlieske.marlieskepset4.R.layout.listview;
import static com.example.marlieske.marlieskepset4.R.string.my_new_to_do;

public class MainActivity extends AppCompatActivity {
    Items item;
    databaseHelper helper;
    Boolean isChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new databaseHelper(this);
        //adaptList();
//        Intent checkChanged = getIntent();
//        String id = checkChanged.getStringExtra("id");
//        // helper.getID(item.todo);
//        item.done = checkChanged.getBooleanExtra("check", isChecked);
//        helper.update(item);
        adaptList();
    }

//    private void adaptList() {
//        listAdapter adapter = null;
//        Cursor cursor = null;
//        adapter.myAdapter(listview, this, cursor);
//    }
    public void adaptList() {
        ArrayList<HashMap<String, String>> items = helper.read();
        if (items == null){
            Log.d("leeg", "items");
            Toast.makeText(this, "no to-do's to show", Toast.LENGTH_SHORT).show();
        }
        else {
            Log.d("niet leeg", "items");
            ArrayAdapter<HashMap<String, String>> myAdapter = new ArrayAdapter<HashMap<String, String>>(this, R.layout.dummylistview, items);
            ListView myLV = (ListView) findViewById(R.id.LV);
            myLV.setAdapter(myAdapter);
        }

    }

    public class Items{
        String todo;
        Boolean done;
    }

    public void addToList(View view) {
        // when adding a new item to the list
        Items newItem = new Items();
        EditText ETNew = (EditText) findViewById(R.id.ETNew);
        newItem.todo = ETNew.getText().toString();
        newItem.done = false;
        helper.create(newItem);
        Toast.makeText(this, "Item added", Toast.LENGTH_SHORT).show();
        adaptList();
        ETNew.setText("");
        ETNew.setHint("my new to-do");
    }
}
