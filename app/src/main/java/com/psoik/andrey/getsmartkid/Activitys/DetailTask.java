package com.psoik.andrey.getsmartkid.Activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.psoik.andrey.getsmartkid.R;
import com.psoik.andrey.getsmartkid.ServerApi;
import com.psoik.andrey.getsmartkid.forJSON.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTask extends AppCompatActivity {

    private int id;

    private TextView nameTask;
    private TextView status;
    private TextView expire;
    private TextView price;
    private TextView description;

    private Button good;
    private Button bead;

    ServerApi serverApi;

    private AlertDialog dlg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_task);

        Intent intent = getIntent();
        Task task = intent.getParcelableExtra("Task");
        id = task.getId();

        serverApi = LoginActivity.retrofit.create(ServerApi.class);

        String statusStr = checkStatus(task.getStatus());
        String expri = getNormalFormat(task.getExpireDate());
        String pr = String.valueOf(task.getPrice());

        nameTask=findViewById(R.id.tvNameDetailTask);
        status=findViewById(R.id.tvStatusDetailTask);
        expire=findViewById(R.id.tvDateDetailTask);
        price=findViewById(R.id.tvPriceDetailTask);
        description=findViewById(R.id.tvDescriptionDetailTask);

        good=findViewById(R.id.btGoodDetailTask);
        bead=findViewById(R.id.btBeadDetailTask);

        nameTask.setText(task.getName());
        status.setText(statusStr);
        expire.setText(expri);
        price.setText(pr);
        description.setText(task.getDescription());

        checkVisibilButton();





        good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sts = status.getText().toString();
                if (sts.equals("Предложено ребенком"))
                approveTask();
                else {
                    confirmTask();

                }
            }
        });

        bead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sts = status.getText().toString();
                if (sts.equals("Предложено ребенком"))
                disapproveTask();
                else {
                    rejectTask();
                }
            }
        });
    }

    private void rejectTask() {
        final Call<Task> reject = serverApi.rejectTask(id);
        reject.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if(response.isSuccessful()){
                    status.setText("Отклонено");
                    checkVisibilButton();
                    Log.i("Loog", "rejectTask");
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {

            }
        });
    }

    private void confirmTask() {
        final Call<Task> confirm = serverApi.confirmTask(id);

        confirm.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if(response.isSuccessful()){
                    status.setText("Выполнено");
                    checkVisibilButton();
                    Log.i("Loog", "confirmTask");
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {

            }
        });

    }

        private void approveTask(){
        final Call<Task> approve = serverApi.approveTask(id);

        approve.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if(response.isSuccessful()){
                    Log.i("Loog", "approveTask");
                    status.setText("Активно");
                    checkVisibilButton();
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {

            }
        });
    }

    private void disapproveTask() {
        final Call<Task> disapprove = serverApi.disapproveTask(id);

        disapprove.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if(response.isSuccessful()){
                    Log.i("Loog", "disapproveTask");
                    status.setText("Предложение отклонено");
                    checkVisibilButton();
                }
                else {
                    Log.i("Loog", "Res task ошибка" + response.errorBody().byteStream() );
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {

            }
        });

    }

    private void rilDeleteTask(){
        final View dialog = getLayoutInflater().inflate(R.layout.for_dialog_delete_task, null);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("Удалить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteTask();
            }
        });
        builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dlg2.dismiss();
            }
        });
        builder.setView(dialog);

        dlg2 = builder.create();
        dlg2.show();
    }

    private  void deleteTask(){
        final Call<Task> delete = serverApi.deleteTask(id);

        delete.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if(response.isSuccessful()){
                    Log.i("Loog", "deleteTask");
                    onBackPressed();
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {

            }
        });

    }

    private void checkVisibilButton() {
        if((status.getText().equals("Ожидает проверки"))||(status.getText().equals("Предложено ребенком"))){
            good.setVisibility(View.VISIBLE);
            bead.setVisibility(View.VISIBLE);
        }
        else {
            good.setVisibility(View.INVISIBLE);
            bead.setVisibility(View.INVISIBLE);
        }
    }

    public static String getNormalFormat(String expireDate) {

        SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX" );
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd.MM.yyyy");

        String d = "Нет";
        try {
            Date date = oldDateFormat.parse(expireDate);
            d =  newDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    private String checkStatus(String status) {

        String newStatus ;

        if(status.equals("new"))
            newStatus = "Активное";
        else if(status.equals("approve"))
            newStatus = "Предложено ребенком";
        else if(status.equals("disapproved"))
            newStatus = "Предложение отклонено";
        else if(status.equals("check"))
            newStatus = "Ожидает проверки";
        else if(status.equals("rejected"))
            newStatus = "Отклонено";
        else if(status.equals("confirmed"))
            newStatus = "Выполнено";
        else newStatus = status;
        return newStatus;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        rilDeleteTask();
        return true;
    }
}
