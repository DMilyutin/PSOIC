package com.psoik.andrey.getsmartkid.forAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.psoik.andrey.getsmartkid.Activitys.DetailTask;
import com.psoik.andrey.getsmartkid.R;
import com.psoik.andrey.getsmartkid.forJSON.Contribution;

import java.util.ArrayList;

public class AdapterContribution extends BaseAdapter {

    private ArrayList<Contribution> list;
    private LayoutInflater inflater;

    public AdapterContribution(Context context1, ArrayList<Contribution> list1){
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
        if(view == null){view = inflater.inflate(R.layout.for_list_contribution, viewGroup, false);}

        Contribution contribution = getContribution(i);

        ((TextView) view.findViewById(R.id.tvNameContributionForList)).setText(contribution.getName());
        ((TextView) view.findViewById(R.id.tvStatusContributionForList)).setText(contribution.getStatus());
        ((TextView) view.findViewById(R.id.tvTotalValueContributionForList)).setText(String.valueOf(contribution.getTotalValue()));
        String date = DetailTask.getNormalFormat(contribution.getExpireDate());
        ((TextView) view.findViewById(R.id.tvExpireDataContributionForList)).setText(date);
        ((TextView) view.findViewById(R.id.tvPercentContributionForList)).setText(String.valueOf(contribution.getPercent()));



        return view;
    }

    private Contribution getContribution(int poss) {
        return new Contribution(list.get(poss).getId(), list.get(poss).getName(),
                                list.get(poss).getValue(), list.get(poss).getPercent(),
                                list.get(poss).getTotalValue(),list.get(poss).getExpireDate(),
                                list.get(poss).getTimestamp(), list.get(poss).getStatus());
    }
}
