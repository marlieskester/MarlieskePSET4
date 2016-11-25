//package com.example.marlieske.marlieskepset4;
//
//import android.content.Context;
//import android.content.Intent;
//import android.database.Cursor;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.ListView;
//import android.widget.SimpleCursorAdapter;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
///**
// * Created by Marlieske on 24-11-2016.
// */
//
//public class listAdapter extends SimpleCursorAdapter {
//    databaseHelper helper;
//    public listAdapter(Context context, int layout, Cursor cursor, String[] from, int[] to) {
//        super(context, layout, cursor, from, to);
//    }
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
