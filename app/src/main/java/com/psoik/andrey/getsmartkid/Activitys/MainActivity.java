package com.psoik.andrey.getsmartkid.Activitys;

import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.psoik.andrey.getsmartkid.R;

public class MainActivity extends AppCompatActivity {

    HistoryFragment historyFragment;
    TaskFragment taskFragment;
    RewardsFragment rewardsFragment;
    BankFragment bankFragment;
    MoreFragment moreFragment;

    FragmentTransaction fragmentTransaction;

    TextView tvNameChildren;
    TextView tvBalanceChildren;
    static LinearLayout lLMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        historyFragment = new HistoryFragment();
        taskFragment = new TaskFragment();
        rewardsFragment = new RewardsFragment();
        bankFragment = new BankFragment();
        moreFragment = new MoreFragment();

        tvNameChildren = findViewById(R.id.tvNameChildrenMainActivity);
        tvBalanceChildren = findViewById(R.id.tvBalChildrenMainActivity);
        lLMain = findViewById(R.id.lLActivityMain);

        tvNameChildren.setText(CheckChildrenActivity.children.getName());
        Double bal = CheckChildrenActivity.children.getBalances()[0].getValue();
        tvBalanceChildren.setText(String.valueOf(bal));

        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentMenu, historyFragment);
        fragmentTransaction.commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                fragmentTransaction = getFragmentManager().beginTransaction();

                switch (menuItem.getItemId()){
                    case R.id.action_history:
                        fragmentTransaction.replace(R.id.fragmentMenu, historyFragment);
                        break;
                    case R.id.action_task:
                        fragmentTransaction.replace(R.id.fragmentMenu, taskFragment);
                        break;
                    case R.id.action_zp:
                        fragmentTransaction.replace(R.id.fragmentMenu, rewardsFragment);
                        break;
                    case R.id.action_bank:
                        fragmentTransaction.replace(R.id.fragmentMenu, bankFragment);
                        break;
                    case R.id.action_more:
                        fragmentTransaction.replace(R.id.fragmentMenu, moreFragment);
                        break;
                }

                //fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            }
        });
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bank_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }*/
}
