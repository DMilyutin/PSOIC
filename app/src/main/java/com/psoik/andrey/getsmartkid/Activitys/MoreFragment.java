package com.psoik.andrey.getsmartkid.Activitys;

import android.annotation.SuppressLint;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.psoik.andrey.getsmartkid.R;

public class MoreFragment extends Fragment {

    TextView tvCheckChildren;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, null);
        getActivity().setTitle("Дополнительно");
        setHasOptionsMenu(true);
        MainActivity.lLMain.setVisibility(View.GONE);

        tvCheckChildren = view.findViewById(R.id.tvCheckChildren);
        tvCheckChildren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s();
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.more_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

  private void s(){
      getActivity().onBackPressed();
  }

}
