package com.psoik.andrey.getsmartkid.Activitys;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.psoik.andrey.getsmartkid.R;
import com.psoik.andrey.getsmartkid.ServerApi;
import com.psoik.andrey.getsmartkid.forJSON.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewTaskActivity extends AppCompatActivity {

    EditText nameNewTask;
    EditText priceNewTask;
    EditText descriptionNewTask;

    TextView dateNewTask;

    CalendarView calendarView;
    Calendar c;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);

        nameNewTask = findViewById(R.id.etNameNewTask);
        priceNewTask = findViewById(R.id.etPriceNewTask);
        descriptionNewTask = findViewById(R.id.etDescriptionNewTask);

        calendarView = findViewById(R.id.calendarDateTask);
        c =  Calendar.getInstance();



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView1, int year, int month, int dayOfMonth) {
                //date = year+month+dayOfMonth;
                Log.i("Loog", "year  "+year);
                Log.i("Loog", "month "+month);
                Log.i("Loog", "dayOfMonth "+dayOfMonth);

               // d = new Date(year+0, month+1, dayOfMonth);
               // String s = d.toString();



                c.set(year, month, dayOfMonth);
                Log.i("Loog", "C "+c.getTime().toString());
            }
        });

    }

    private void sendTask(int childId, String name, String expire, int price, String description) {
        ServerApi serverApi = LoginActivity.retrofit.create(ServerApi.class);

        Call<Task> newTask = serverApi.createNewTask(childId, name, expire,price,description);

        newTask.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if(response.isSuccessful()){
                    onBackPressed();

                }
                else{ Log.i("Loog", "Response " +response.code());
                        Log.i("Loog", "Response " +response.errorBody().byteStream());}
            }
            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                Log.i("Loog", "Taaaaaaa "+t.getMessage());
                return;
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
        String name = nameNewTask.getText().toString();
        //Date d = c.getTime();
        @SuppressLint("SimpleDateFormat") String expire = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(c.getTime());
        int price = Integer.parseInt(priceNewTask.getText().toString());
        String description = descriptionNewTask.getText().toString();
        sendTask(childId, name,expire,price,description);

        return true;
    }
}
