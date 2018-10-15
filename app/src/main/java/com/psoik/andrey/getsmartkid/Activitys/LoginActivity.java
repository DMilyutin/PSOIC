package com.psoik.andrey.getsmartkid.Activitys;


import android.content.DialogInterface;
import android.content.Intent;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.psoik.andrey.getsmartkid.R;
import com.psoik.andrey.getsmartkid.ServerApi;
import com.psoik.andrey.getsmartkid.forJSON.BooleanRespons;
import com.psoik.andrey.getsmartkid.forJSON.Parent;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private static final String URL = "https://getsmartkid.com/";
    private Button btLogIn;
    private EditText edLogin;
    private EditText edPass;
    private AlertDialog dlg;
    private AlertDialog dlg2;
    private CheckBox cbRememberLoginAndPass;

    static Retrofit retrofit;
    ServerApi serverApi;

    OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btLogIn = findViewById(R.id.logIn);
        edLogin = findViewById(R.id.edEmail);
        edPass = findViewById(R.id.edPass);
        cbRememberLoginAndPass = findViewById(R.id.cbRememberLoginAndPass);

        btLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = edLogin.getText().toString();
                String pass = edPass.getText().toString();
                if (mail.equals("")|| pass.equals("")||mail.isEmpty()||pass.isEmpty()){
                    Toast.makeText(LoginActivity.this,
                            "Логин и пароль не могут быть пустыми", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    auto(mail, pass);
                }
            }
        });

        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);

        client = new OkHttpClient.Builder()
                .cookieJar(new JavaNetCookieJar(cookieManager))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        serverApi = retrofit.create(ServerApi.class);
    }

    private void auto(String mail, String pass){

        Call<Parent> autorization = serverApi.autoriz(mail, pass);

        autorization.enqueue(new Callback<Parent>() {
            @Override
            public void onResponse(Call<Parent> call, Response<Parent> response) {
                if(response.isSuccessful()){
                     Intent intent = new Intent(LoginActivity.this, CheckChildrenActivity.class);
                     startActivity(intent);
                }
                else{ checkErr(response);}
            }

            @Override
            public void onFailure(Call<Parent> call, Throwable t) {
                Log.i("Loog", "Taaaaaaa "+t.getMessage());
                return;
            }
        });
        //dlg2.dismiss();
    }

    private void showDialogLogging() {

        final View dialog = getLayoutInflater().inflate(R.layout.for_dialog_loging, null);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialog);

        dlg2 = builder.create();
        dlg2.show();


    }

    private void checkErr(Response<Parent> response) {
        try {
            String err =  response.errorBody().string();
            if(err.contains("wrong password"))
                Toast.makeText(LoginActivity.this, "Неверный пароль", Toast.LENGTH_SHORT).show();
            else if (err.contains("user doesn't exist"))
                Toast.makeText(LoginActivity.this, "Учетная запись не найдена", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(LoginActivity.this, "Ошибка, попробуйте позже", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void regisetNewParent(View view) {
        final View dialog = getLayoutInflater().inflate(R.layout.dialog_register_new_parent, null);

        final EditText newEmail = dialog.findViewById(R.id.edNewEmail);
        final EditText newPassword =dialog.findViewById(R.id.edNewPass);
        final EditText newName =dialog.findViewById(R.id.edNewName);


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("Готово", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String mail = newEmail.getText().toString();
                String pass = newPassword.getText().toString();
                String name = newName.getText().toString();

                if(!(mail.equals("")&&pass.equals("")&&name.equals("")))
                checkMail(mail, pass, name, true);
                dlg.dismiss();
            }
        });
        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dlg.dismiss();
            }
        });
        builder.setTitle("Регистрация");
        builder.setView(dialog);

        dlg = builder.create();
        dlg.show();
    }

    public void checkMail(final String mail, final String pass, final String name, final Boolean register) {

        Call<BooleanRespons> res = serverApi.checkMail(mail);

        res.enqueue(new Callback<BooleanRespons>() {
            @Override
            public void onResponse(Call<BooleanRespons> call, Response<BooleanRespons> response) {
                if (response.isSuccessful()){
                    Boolean t = response.body().getSuccess();
                    if(t){
                        Log.i("Loog", "Почта свободна "+ t);
                        if (register)
                    registerNewParent(mail, pass, name);
                    }
                    else
                        show();
                        Log.i("Loog", "Почта занята "+ t);
                        Toast.makeText(LoginActivity.this,
                                "mail - " + t, Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),
                            "mail - " + t, Toast.LENGTH_LONG).show();
                    Toast.makeText(LoginActivity.this,
                            "mail - " + t, Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<BooleanRespons> call, Throwable t) {

            }
        });

    }

    private void show(){
        Toast.makeText(LoginActivity.this,
                "mail - " , Toast.LENGTH_LONG).show();
    }

    private void registerNewParent(String mail,String pass,String name) {

        final Call<Parent> register = serverApi.registerNewParent(mail, pass, name);
        register.enqueue(new Callback<Parent>() {
            @Override
            public void onResponse(Call<Parent> call, Response<Parent> response) {
                if (response.isSuccessful()){
                    Log.i("Loog", "Регистрация прошла успешно");
                    Toast.makeText(LoginActivity.this,
                            "Регистрация прошла успешно", Toast.LENGTH_SHORT).show();}
            }

            @Override
            public void onFailure(Call<Parent> call, Throwable t) {

            }
        });
    }
}
