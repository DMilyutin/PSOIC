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
import android.widget.EditText;
import android.widget.ListView;

import com.psoik.andrey.getsmartkid.R;
import com.psoik.andrey.getsmartkid.ServerApi;
import com.psoik.andrey.getsmartkid.forAdapters.AdapterContribution;
import com.psoik.andrey.getsmartkid.forJSON.Contribution;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContributionActivity extends AppCompatActivity {

    ListView lvContribution;
    AdapterContribution adaperCon;

    final int msOnDay = 86400000;

    private AlertDialog dlg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contribution);

        lvContribution = findViewById(R.id.listBankContribution);

        getOllContribution();
    }

    private void getOllContribution(){

        ServerApi serverApi = LoginActivity.retrofit.create(ServerApi.class);

        Call<ArrayList<Contribution>> ollContribution = serverApi.getOllContribution(CheckChildrenActivity.children.getId());

        ollContribution.enqueue(new Callback<ArrayList<Contribution>>() {
            @Override
            public void onResponse(Call<ArrayList<Contribution>> call, Response<ArrayList<Contribution>> response) {
                if(response.isSuccessful()){
                    setAdapterContribution(response.body());

                }else Log.i("Loog", " not good" );
            }

            @Override
            public void onFailure(Call<ArrayList<Contribution>> call, Throwable t) {
                Log.i("Loog", "Failure");
            }
        });
    }


    private void formingNewContribution() {
        final View dialog = getLayoutInflater().inflate(R.layout.dialog_create_new_contribution, null);

        final EditText edName = dialog.findViewById(R.id.edNameNewContribution);
        final EditText edValue =dialog.findViewById(R.id.edValueNewContribution);
        final EditText edPercent =dialog.findViewById(R.id.edPercentNewContribution);
        final EditText edDate =dialog.findViewById(R.id.edDateNewContribution);


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("Добавить вклад", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name = edName.getText().toString();
                int value = Integer.parseInt(edValue.getText().toString());
                double percent = Double.parseDouble(edPercent.getText().toString());
                int date = Integer.parseInt(edDate.getText().toString());
                date = date*msOnDay;

                createNewContribution(CheckChildrenActivity.children.getId(),
                                        name, value, percent, date);
            }
        });
        builder.setTitle("Новый вклад");
        builder.setView(dialog);

        dlg = builder.create();
        dlg.show();
    }

    private void createNewContribution(int childId, String name, int value,
                                       double percent, int timestamp){
        ServerApi serverApi = LoginActivity.retrofit.create(ServerApi.class);

        Call<Contribution> contributionCall = serverApi.createNewContribution(childId, name, value, percent, timestamp);

        contributionCall.enqueue(new Callback<Contribution>() {
            @Override
            public void onResponse(Call<Contribution> call, Response<Contribution> response) {
                if(response.isSuccessful()){
                    dlg.dismiss();
                    getOllContribution();
                }
                else Log.i("Loog", "Херня");
            }

            @Override
            public void onFailure(Call<Contribution> call, Throwable t) {

            }
        });
    }

    private void setAdapterContribution(ArrayList<Contribution> list){
        adaperCon = new AdapterContribution(this, list);
        lvContribution.setAdapter(adaperCon);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bank_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        formingNewContribution();
        return true;
    }

}
