package com.psoik.andrey.getsmartkid.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.psoik.andrey.getsmartkid.R;
import com.psoik.andrey.getsmartkid.ServerApi;
import com.psoik.andrey.getsmartkid.forJSON.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewProduct extends AppCompatActivity {

    EditText nameNewProduct;
    EditText priceNewProduct;

    Spinner spinnerCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);

        nameNewProduct = findViewById(R.id.edNameNewProduct);
        priceNewProduct = findViewById(R.id.edPriceNewProduct);
        spinnerCategories = findViewById(R.id.spinnerCategories);


    }

    private void newProduct(int childId, String name, String category, int price) {
        ServerApi serverApi = LoginActivity.retrofit.create(ServerApi.class);

        Call<Product> newProduct = serverApi.createNewProduct(childId, name, category, price);

        newProduct.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if(response.isSuccessful()){

                    onBackPressed();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.check_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int childId = CheckChildrenActivity.children.getId();
        String name = nameNewProduct.getText().toString();
        int price = Integer.parseInt(priceNewProduct.getText().toString());
        String category = spinnerCategories.getSelectedItem().toString();

        newProduct(childId, name, category, price);
        return true;
    }
}
