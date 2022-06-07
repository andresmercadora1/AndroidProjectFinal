package com.example.inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.inventario.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding mainBinding;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);

        mainBinding.btnSignin.setOnClickListener(this);
        mainBinding.btnSignUp.setOnClickListener(this);

        dbHelper = new DbHelper(this);

    }

    public void checkLogin(View view) {
        if(mainBinding.edPassword.getText().toString().isEmpty() && mainBinding.edEmail.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe rellenar los campos", Toast.LENGTH_SHORT).show();
        } else {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String emailToFind = mainBinding.edEmail.getText().toString();
            String passwordToFind = mainBinding.edPassword.getText().toString();
            Cursor cursor = db.rawQuery(
                    "SELECT * FROM users WHERE email = '" + emailToFind + "' AND password = '" + passwordToFind + "'", null
            );
            cursor.moveToNext();
            int valid = cursor.getCount();
            if(valid>0) {
                Intent intent = new Intent(this, ProductActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "La información ingresada es incorrecta", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void goToRehister() {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignin:
                checkLogin(v);
                break;
            case R.id.btnSignUp:
                goToRehister();
                break;
        }
    }
}