package com.psoik.andrey.getsmartkid.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.psoik.andrey.getsmartkid.R;
import com.psoik.andrey.getsmartkid.ServerApi;
import com.psoik.andrey.getsmartkid.forAdapters.AdapterCheckChildren;
import com.psoik.andrey.getsmartkid.forJSON.Balances;
import com.psoik.andrey.getsmartkid.forJSON.Children;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CheckChildrenActivity extends AppCompatActivity {

    static Children children;
    private ArrayList<Children> childrenList;
    private ListView lvOllChildren;
    AdapterCheckChildren adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_children);

    childrenList = new ArrayList<>();
    lvOllChildren = findViewById(R.id.lv_check_children);

    myChildren();

      lvOllChildren.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              children = adapter.getIndoDetail(i);
              Balances[] b = children.getBalances();
              Log.i("Loog", String.valueOf(b.length));
              Log.i("Loog", String.valueOf(b[0].getAsset()) + b[0].getValue());
              Log.i("Loog", String.valueOf(b[1].getAsset()) + b[1].getValue());
              Log.i("Loog", String.valueOf(b[2].getAsset()) + b[2].getValue());
              Log.i("Loog", String.valueOf(b[3].getAsset()) + b[3].getValue());
              Log.i("Loog", String.valueOf(b[4].getAsset()) + b[4].getValue());
              Log.i("Loog", String.valueOf(b[5].getAsset()) + b[5].getValue());
              Intent intent = new Intent(CheckChildrenActivity.this, MainActivity.class);
              startActivity(intent);
          }
      });
    }

    private void myChildren(){
        ServerApi serverApi = LoginActivity.retrofit.create(ServerApi.class);

        final Call<ArrayList<Children>> ollChildren = serverApi.getOllChildren();

        ollChildren.enqueue(new Callback<ArrayList<Children>>() {
            @Override
            public void onResponse(Call<ArrayList<Children>> call, Response<ArrayList<Children>> response) {
                if(response.isSuccessful()) {
                    Log.i("Loog", String.valueOf(response.body().size()));
                    int size = -1;
                    if (response.body() != null) {
                        size = response.body().size();
                    }
                    if (size != -1) {
                        setAdapterList(response.body());
                    }
                }
                else {
                    Log.i("Loog", "Res ошибка" + response.errorBody().byteStream() );
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Children>> call, Throwable t) {
                Log.i("Loog", "Res TTTTT : " + t.getMessage());
            }
        });
    }

    private void setAdapterList(ArrayList<Children> children) {
        adapter = new AdapterCheckChildren(this, children);
        lvOllChildren.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bank_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(CheckChildrenActivity.this, AddNewChildren.class);
        startActivity(intent);

        return true;
    }

    @Override
    protected void onResume() {
        myChildren();
        super.onResume();
    }
}
