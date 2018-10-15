package com.psoik.andrey.getsmartkid.forAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.psoik.andrey.getsmartkid.Activitys.DetailTask;
import com.psoik.andrey.getsmartkid.R;
import com.psoik.andrey.getsmartkid.forJSON.Loan;

import java.util.ArrayList;

public class AdapterLoan extends BaseAdapter {


    private ArrayList<Loan> list;
    private LayoutInflater inflater;

    public AdapterLoan(Context context1, ArrayList<Loan> list1){
        list = list1;
        inflater = (LayoutInflater) context1.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){view = inflater.inflate(R.layout.for_list_loan, viewGroup, false);}


        Loan loan = getLoan(i);


        ((TextView) view.findViewById(R.id.tvNameLoanForList)).setText(loan.getName());
        ((TextView) view.findViewById(R.id.tvStatusLoanForList)).setText(loan.getStatus());
        ((TextView) view.findViewById(R.id.tvTotalValueLoanForList)).setText(String.valueOf(loan.getTotalValue()));
        String date = DetailTask.getNormalFormat(loan.getExpireDate());
        ((TextView) view.findViewById(R.id.tvExpireDataLoanForList)).setText(date);
        ((TextView) view.findViewById(R.id.tvPercentLoanForList)).setText(String.valueOf(loan.getPercent()));



        return view;
    }

    private Loan getLoan(int poss) {
        return new Loan(list.get(poss).getId(), list.get(poss).getName(),
                list.get(poss).getValue(), list.get(poss).getPercent(),
                list.get(poss).getTotalValue(),list.get(poss).getExpireDate(),
                list.get(poss).getTimestamp(), list.get(poss).getStatus());
    }
}
