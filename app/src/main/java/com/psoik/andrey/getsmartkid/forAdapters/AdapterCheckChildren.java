package com.psoik.andrey.getsmartkid.forAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.psoik.andrey.getsmartkid.R;
import com.psoik.andrey.getsmartkid.forJSON.Balances;
import com.psoik.andrey.getsmartkid.forJSON.Children;
import java.util.ArrayList;


public class AdapterCheckChildren extends BaseAdapter {
    ArrayList<Children> list;


    LayoutInflater inflater;

    public AdapterCheckChildren(Context context1, ArrayList<Children> list1){
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
    public View getView(int i, View view1, ViewGroup viewGroup) {
        View view = view1;

        if(view == null){view = inflater.inflate(R.layout.for_list_check_children, viewGroup, false);}

        Children info = getIndoDetail(i);
        Balances[] balances1 = info.getBalances();
        Balances balances = balances1[0];
        String s = String.valueOf(balances.getValue());
        ((TextView) view.findViewById(R.id.tv_for_list_check_children_name)).setText(info.getName());
        ((TextView) view.findViewById(R.id.tv_for_list_check_children_balance)).setText(s);

        return view;
    }

    public Children getIndoDetail(int poss){

        Children Children = new Children(list.get(poss).getId(), list.get(poss).getName(),
                list.get(poss).getParentId(), list.get(poss).getBalances());

        return Children;
    }



}
