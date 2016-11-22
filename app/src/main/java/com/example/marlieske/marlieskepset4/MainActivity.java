package com.example.marlieske.marlieskepset4;

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Items item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void listviewadapter () {
        // nog afmaken
        ListView listView = (ListView) findViewById(R.id.LV);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // nog doen
                TextView TVList = (TextView) view.findViewById(R.id.TVList);
            }
        });
    }


    public class Items{
        String todo;
        Boolean done;
    }

    public void addToList(View view) {
        // when adding a new item to the list
        Items newItem;
        EditText ETNew = (EditText) findViewById(R.id.ETNew);
        newItem.todo = ETNew.getText().toString();
        databaseHelper.create(newItem);
    }
}
