package com.psoik.andrey.getsmartkid.Activitys;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.psoik.andrey.getsmartkid.R;
import com.psoik.andrey.getsmartkid.ServerApi;
import com.psoik.andrey.getsmartkid.forAdapters.AdapterProductFragment;
import com.psoik.andrey.getsmartkid.forJSON.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RewardsFragment extends Fragment {

    ListView productList;
    AdapterProductFragment adapterProductFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmen_rewards, null);
        getActivity().setTitle("Награды");
        setHasOptionsMenu(true);
        MainActivity.lLMain.setVisibility(View.VISIBLE);
        productList = view.findViewById(R.id.lv_product_fragment);
        ollProducts();
        return view;
    }

    private void ollProducts(){
        ServerApi serverApi = LoginActivity.retrofit.create(ServerApi.class);

        Call<ArrayList<Product>> ollProduct = serverApi.getOllProduct(CheckChildrenActivity.children.getId());

        ollProduct.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                setAdaper(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

            }
        });
    }

    private void setAdaper(ArrayList<Product> products) {
        adapterProductFragment = new AdapterProductFragment(getActivity(), products);
        productList.setAdapter(adapterProductFragment);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.bank_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            Intent intent = new Intent(this.getContext(), AddNewProduct.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onResume() {
        ollProducts();
        super.onResume();
    }
}
