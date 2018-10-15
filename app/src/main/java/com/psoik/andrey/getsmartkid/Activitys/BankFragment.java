package com.psoik.andrey.getsmartkid.Activitys;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.psoik.andrey.getsmartkid.R;


public class BankFragment extends Fragment {

    Button btContribution;
    Button btLoan;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bank, null);
        getActivity().setTitle("Банк");
        MainActivity.lLMain.setVisibility(View.VISIBLE);
        btContribution = view.findViewById(R.id.btContributionBank);
        btLoan = view.findViewById(R.id.btLoan);

        btContribution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContributionActivity.class);
                startActivity(intent);
            }
        });
        btLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoanActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
