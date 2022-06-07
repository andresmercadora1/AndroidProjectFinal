package com.example.inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.inventario.databinding.ActivitySearchProductBinding;

public class SearchProductActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivitySearchProductBinding searchProductBinding;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchProductBinding = ActivitySearchProductBinding.inflate(getLayoutInflater());
        View view = searchProductBinding.getRoot();
        setContentView(view);

        dbHelper = new DbHelper(this);

        searchProductBinding.btnSearchProduct.setOnClickListener(this);
        searchProductBinding.btnCreateProduct.setOnClickListener(this);
    }

    public void findProduct(View v) {
        if(searchProductBinding.etSearch.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe ingresar el nombre del producto", Toast.LENGTH_SHORT).show();
        } else {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String referenceToFind = searchProductBinding.etSearch.getText().toString();
            Cursor cursor = db.rawQuery(
                    "SELECT * FROM products WHERE reference = '" + referenceToFind + "'", null
            );
            cursor.moveToNext();
            if(cursor.getCount()>0) {
                searchProductBinding.tvId.setText("Id: " + cursor.getInt(0));
                searchProductBinding.tvIName.setText("Name: " + cursor.getString(1));
                searchProductBinding.tvIDescription.setText("Description: " + cursor.getString(2));
                searchProductBinding.tvIReference.setText("Reference: " + cursor.getString(3));
                searchProductBinding.tvIStock.setText("Stock: " + cursor.getInt(4));
                searchProductBinding.tvIPrice.setText("Price: " + cursor.getFloat(5));
                searchProductBinding.tvIBrand.setText("Brand: " + cursor.getString(6));
                searchProductBinding.tvICategory.setText("Category: " + cursor.getString(7));
                searchProductBinding.etSearch.setText("");
            } else {
                Toast.makeText(this, "La información ingresada es incorrecta", Toast.LENGTH_SHORT).show();
                searchProductBinding.tvId.setText("Id:");
                searchProductBinding.tvIName.setText("Name:");
                searchProductBinding.tvIDescription.setText("Description:");
                searchProductBinding.tvIReference.setText("Reference:");
                searchProductBinding.tvIStock.setText("Stock:");
                searchProductBinding.tvIPrice.setText("Price");
                searchProductBinding.tvIBrand.setText("Brand:");
                searchProductBinding.tvICategory.setText("Category:");
                searchProductBinding.etSearch.setText("");
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSearchProduct:
                findProduct(v);
                break;
            case R.id.btnCreateProduct:
                Intent intent = new Intent(this, ProductActivity.class);
                startActivity(intent);
                break;
        }
    }
}