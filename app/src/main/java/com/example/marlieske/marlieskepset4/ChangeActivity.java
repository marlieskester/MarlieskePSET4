package com.example.marlieske.marlieskepset4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ChangeActivity extends AppCompatActivity {
    EditText ETNew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        // afmaken: intent
        ETNew = (EditText) findViewById(R.id.ETNew)
        ETNew.setText();
    }

    public void save(View view) {
        String addedToDo = ETNew.getText().toString();
        view.getId();
        databaseHelper.update(_id, addedToDo);
        toHome();
        finish();
    }

    public void remove(View view) {
        databaseHelper.delete(view.getId());
        toHome();
        finish();
    }

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
