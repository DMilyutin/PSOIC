package com.psoik.andrey.getsmartkid.forAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.psoik.andrey.getsmartkid.R;
import com.psoik.andrey.getsmartkid.forJSON.Product;


import java.util.ArrayList;

public class AdapterProductFragment extends BaseAdapter {

    private ArrayList<Product> list;


    private LayoutInflater inflater;

    public AdapterProductFragment(Context context1, ArrayList<Product> list1){
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

        if(view == null){view = inflater.inflate(R.layout.for_list_product_fragment, viewGroup, false);}

        Product product = getTargetProduct(i);

       ((TextView) view.findViewById(R.id.tvNameProduckForListProductFragment)).setText(product.getName());
       String price = String.valueOf(product.getPrice());
       ((TextView) view.findViewById(R.id.tvPriceProduckForListProductFragment)).setText(price);
       //((TextView) view.findViewById(R.id.tv_status_task_for_list_fragment)).setText(task.getStatus());


        return view;
    }

    public Product getTargetProduct(int poss){
        return new Product(list.get(poss).getId(), list.get(poss).getChildId(), list.get(poss).getName(),
                list.get(poss).getPrice(), list.get(poss).getCategory());

    }

}
