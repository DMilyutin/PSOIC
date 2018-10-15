package com.psoik.andrey.getsmartkid.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.psoik.andrey.getsmartkid.R;
import com.psoik.andrey.getsmartkid.ServerApi;
import com.psoik.andrey.getsmartkid.forJSON.Children;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewChildren extends AppCompatActivity {

    private EditText edNameNewChildren;
    private EditText edEmailNewChildren;
    private EditText edPasswordNewChildren;
    private EditText edRepeatPasswordNewChildren;

    private Button addCildren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_children);

        edNameNewChildren = findViewById(R.id.edNameNewChildrenAddChildren);
        edEmailNewChildren = findViewById(R.id.edEmailNewChildrenAddChildren);
        edPasswordNewChildren = findViewById(R.id.edPasswordNewChildrenAddChildren);
        edRepeatPasswordNewChildren = findViewById(R.id.edRepeatPasswordNewChildrenAddChildren);

        addCildren = findViewById(R.id.btAddChildren);


    }

    private void checkMail(){

    }

    private void checkOllForms(String name, String email, String password, String repeatPassword) {

        // Todo Проверка занятости мыла


        if(!(name.isEmpty()||name.equals(""))&&!(email.isEmpty()||email.equals(""))&&
                !(password.isEmpty()||password.equals(""))&&
                !(repeatPassword.isEmpty()||repeatPassword.equals(""))){
            if(password.equals(repeatPassword)) registerNewChildren(email, password, name);
            else {
                Toast.makeText(this, "Пароли должны совпадать", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "Все поля должны быть заполнены", Toast.LENGTH_LONG).show();
        }

    }

    private void registerNewChildren(String mail, String password, String name){

        ServerApi serverApi = LoginActivity.retrofit.create(ServerApi.class);

        final Call<Children> regNewChildren = serverApi.registerNewChildren(mail, password, name);

        regNewChildren.enqueue(new Callback<Children>() {
            @Override
            public void onResponse(Call<Children> call, Response<Children> response) {
                if (response.isSuccessful())
                onBackPressed();
            }

            @Override
            public void onFailure(Call<Children> call, Throwable t) {

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
        String name = edNameNewChildren.getText().toString();
        String email = edEmailNewChildren.getText().toString();
        String password = edPasswordNewChildren.getText().toString();
        String repeatPassword = edRepeatPasswordNewChildren.getText().toString();
        checkOllForms(name, email,password,repeatPassword );
        return true;
    }
}
