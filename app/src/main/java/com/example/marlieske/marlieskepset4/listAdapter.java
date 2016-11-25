package com.example.marlieske.marlieskepset4;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Marlieske on 24-11-2016.
 */

public class listAdapter extends ArrayAdapter<MainActivity.Items> {
    databaseHelper helper;

    public listAdapter(Context context, int resource, int textview, ArrayList items) {
        super(context, resource, textview, items);
        helper = new databaseHelper(context);
    }


    public View getView(final Context context, View convertView, ViewGroup parent) {
        ArrayList<HashMap<String, String>> items = helper.read();

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview, null);
        }
//        MainActivity.Items thisposition = getItem(position);
        final TextView TVItem = (TextView) convertView.findViewById(R.id.TVItem);
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0, j = items.size(); i < j; i++) {
            map = items.get(i);
            TVItem.setText(map.toString());
        }
        final String thisItem = map.toString();
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox2);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String id = helper.getID(thisItem);
                helper.checkchange(id, isChecked);
            }
        });
        ListView myLV = (ListView) convertView.findViewById(R.id.LV);
        myLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                String thisItem = TVItem.getText().toString();
                String ID = helper.getID(thisItem);
                Intent toChange = new Intent(context, ChangeActivity.class);
                toChange.putExtra("item", thisItem);
                context.startActivity(toChange);
                return true;
            }
        });

        return convertView;
    }
}



//
//    public void myAdapter(View view, final Context context, Cursor cursor) {
//        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox2);
//        final TextView TVItem = (TextView) view.findViewById(R.id.TVItem);
//        ListView myLV = (ListView) view.findViewById(R.id.LV);
//        ArrayList myList = helper.read();
//        //TVItem.setText(myList);
//        ArrayAdapter listAdapter = new ArrayAdapter(context, R.layout.listview, myList);
//        myLV.setAdapter(listAdapter);
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                Intent checkChanged = new Intent(context, MainActivity.class);
//                checkChanged.putExtra("check", isChecked);
//                context.startActivity(checkChanged);
//            }
//        });
//
//        myLV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                String thisItem = TVItem.getText().toString();
//                String ID = helper.getID(thisItem);
//                Intent toChange = new Intent(context, ChangeActivity.class);
//                toChange.putExtra("item", thisItem);
//                context.startActivity(toChange);
//            }
//        });
//    }
//}
