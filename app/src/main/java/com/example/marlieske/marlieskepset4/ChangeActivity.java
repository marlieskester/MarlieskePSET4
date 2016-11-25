package com.example.marlieske.marlieskepset4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ChangeActivity extends AppCompatActivity {
    EditText ETNew;
    String myToDo;
    databaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        Intent toChange = getIntent();
        myToDo = toChange.getStringExtra("item");
        ETNew = (EditText) findViewById(R.id.ETNew);
        ETNew.setText(myToDo);
    }

    public void save(View view) {
        MainActivity.Items item = null;
        item.todo = ETNew.getText().toString();
        //String id = helper.getID(myToDo);
        helper.update(item);
        toHome();
        finish();
    }

//    public void remove(View view) {
//        String ID = helper.getID(myToDo);
//        helper.delete(ID);
//        toHome();
//        finish();
//    }

    public void toMain(View view) {
        toHome();
        finish();
    }

    public void toHome(){
        Intent toHome = new Intent(this, MainActivity.class);
        startActivity(toHome);
        finish();
    }
}
