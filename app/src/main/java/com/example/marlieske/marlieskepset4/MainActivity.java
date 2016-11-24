package com.example.marlieske.marlieskepset4;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.marlieske.marlieskepset4.R.id.ETNew;

public class MainActivity extends AppCompatActivity {
    Items item;
    databaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void listviewadapter(ArrayAdapter<String> stringArrayAdapter) {
        // nog afmaken
        ListView listView = (ListView) findViewById(R.id.LV);
        ArrayList myList = helper.read();
        ArrayAdapter listAdapter = new ArrayAdapter(this, R.layout.listview, myList);
        //this.listviewadapter(new ArrayAdapter<String>(this, R.layout.listview, R.id.TVItem));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item.done = true;
                helper.update(item);
            }
        });
        listView.setOnItemSelectedListener());{
            String thisItem = ((TextView) view).getText().toString();
            Intent toChange = new Intent(getApplicationContext(), ChangeActivity.class);
            toChange.putExtra("item", thisItem);
            startActivity(toChange);
        }
    }
    }

    public void checkBox(View view) {

        item.done = true;
        helper.update(item);
        }


    public class Items{
        String todo;
        Boolean done;
    }

    public void addToList(View view) {
        // when adding a new item to the list
        Items newItem = null;
        EditText ETNew = (EditText) findViewById(ETNew);
        newItem.todo = ETNew.getText().toString();
        newItem.done = false;
        helper.create(newItem);
    }
}
