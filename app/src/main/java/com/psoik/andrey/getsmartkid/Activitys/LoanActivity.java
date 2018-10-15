package com.psoik.andrey.getsmartkid.Activitys;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.psoik.andrey.getsmartkid.R;
import com.psoik.andrey.getsmartkid.ServerApi;
import com.psoik.andrey.getsmartkid.forAdapters.AdapterLoan;
import com.psoik.andrey.getsmartkid.forJSON.Loan;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoanActivity extends AppCompatActivity {

    AdapterLoan adapterLoan;
    ListView lvLoan;


    final int msOnDay = 86400000;

    private AlertDialog dlg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);

        lvLoan = findViewById(R.id.lvLoan);

        gelOllLoan();
    }


    private void gelOllLoan(){
        ServerApi serverApi = LoginActivity.retrofit.create(ServerApi.class);

        Call<ArrayList<Loan>> ollLoan = serverApi.getOllLoan(CheckChildrenActivity.children.getId());

        ollLoan.enqueue(new Callback<ArrayList<Loan>>() {
            @Override
            public void onResponse(Call<ArrayList<Loan>> call, Response<ArrayList<Loan>> response) {
                if(response.isSuccessful()){
                    setAdapterLoan(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Loan>> call, Throwable t) {

            }
        });
    }

    private void formingNewLoan() {
        final View dialog = getLayoutInflater().inflate(R.layout.dialog_create_new_contribution, null);

        final EditText edName = dialog.findViewById(R.id.edNameNewContribution);
        final EditText edValue =dialog.findViewById(R.id.edValueNewContribution);
        final EditText edPercent =dialog.findViewById(R.id.edPercentNewContribution);
        final EditText edDate =dialog.findViewById(R.id.edDateNewContribution);


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("Добавить кредит", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String name = edName.getText().toString();
                int value = Integer.parseInt(edValue.getText().toString());
                double percent = Double.parseDouble(edPercent.getText().toString());
                int date = Integer.parseInt(edDate.getText().toString());
                date = date*msOnDay;

                createNewLoan(CheckChildrenActivity.children.getId(),
                        name, value, percent, date);
            }
        });
        builder.setTitle("Новый кредит");
        builder.setView(dialog);

        dlg = builder.create();
        dlg.show();
    }

    private void createNewLoan(int id, String name, int value, double percent, int date) {
        ServerApi serverApi = LoginActivity.retrofit.create(ServerApi.class);

        Call<Loan> loanCall = serverApi.createNewLoan(id, name, value, percent, date);

        loanCall.enqueue(new Callback<Loan>() {
            @Override
            public void onResponse(Call<Loan> call, Response<Loan> response) {
                if (response.isSuccessful()){
                    dlg.dismiss();
                    gelOllLoan();
                }
            }

            @Override
            public void onFailure(Call<Loan> call, Throwable t) {

            }
        });
    }

    private void setAdapterLoan(ArrayList<Loan> list) {
        adapterLoan = new AdapterLoan(this, list);
        lvLoan.setAdapter(adapterLoan);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bank_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        formingNewLoan();
        return true;
    }
}
