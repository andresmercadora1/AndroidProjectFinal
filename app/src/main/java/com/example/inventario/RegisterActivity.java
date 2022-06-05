package com.example.inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.inventario.databinding.ActivityMainBinding;
import com.example.inventario.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityRegisterBinding registerBinding;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        View view = registerBinding.getRoot();
        setContentView(view);

        dbHelper = new DbHelper(this);

        registerBinding.btnRegister.setOnClickListener(this);
    }

    public void registerUser(View view) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues userData = new ContentValues();
        userData.put("name", registerBinding.etNombre.getText().toString());
        userData.put("email", registerBinding.etEmail.getText().toString());
        userData.put("password", registerBinding.etPassword.getText().toString());
        userData.put("identification", registerBinding.etNumeroIdentificacion.getText().toString());

        long newUser = db.insert("users", null, userData);
        Toast.makeText(this, "" + newUser, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                registerUser(v);
                break;
        }
    }
}