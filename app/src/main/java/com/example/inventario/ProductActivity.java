package com.example.inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.inventario.databinding.ActivityProductBinding;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityProductBinding productBinding;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productBinding = ActivityProductBinding.inflate(getLayoutInflater());
        View view = productBinding.getRoot();
        setContentView(view);

        productBinding.btnGuardarProd.setOnClickListener(this);
        productBinding.btnSearch.setOnClickListener(this);
        productBinding.btnLogout.setOnClickListener(this);

        dbHelper = new DbHelper(this);
    }

    public void registerProduct(View view) {

        if(productBinding.etNameProd.getText().toString().isEmpty()
            && productBinding.etDescription.getText().toString().isEmpty()
            && productBinding.etReference.getText().toString().isEmpty()
            && productBinding.etStock.getText().toString().isEmpty()
            && productBinding.etPrice.getText().toString().isEmpty()
            && productBinding.etBrand.getText().toString().isEmpty()
            && productBinding.etCategory.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe rellenar los campos", Toast.LENGTH_SHORT).show();
        } else {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues userData = new ContentValues();

            userData.put("name", productBinding.etNameProd.getText().toString());
            userData.put("description", productBinding.etDescription.getText().toString());
            userData.put("reference", productBinding.etReference.getText().toString());
            userData.put("stock", productBinding.etStock.getText().toString());
            userData.put("price", productBinding.etPrice.getText().toString());
            userData.put("brand", productBinding.etBrand.getText().toString());
            userData.put("category", productBinding.etCategory.getText().toString());

            long newProduct = db.insert("products", null, userData);
            Toast.makeText(this, "Save product con ID:" + newProduct, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGuardarProd:
                registerProduct(v);
                break;
            case R.id.btnSearch:
                Intent intent = new Intent(this, SearchProductActivity.class);
                startActivity(intent);
                break;
            case R.id.btnLogout:
                Intent intentLogin = new Intent(this, MainActivity.class);
                startActivity(intentLogin);
                break;
        }
    }
}