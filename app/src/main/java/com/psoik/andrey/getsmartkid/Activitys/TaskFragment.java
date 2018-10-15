package com.psoik.andrey.getsmartkid.Activitys;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.psoik.andrey.getsmartkid.R;
import com.psoik.andrey.getsmartkid.ServerApi;
import com.psoik.andrey.getsmartkid.forAdapters.AdapterTasksFragment;
import com.psoik.andrey.getsmartkid.forJSON.Task;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskFragment extends Fragment {

    ListView lvTasksFragment;
    AdapterTasksFragment adapterTasksFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, null);
        MainActivity.lLMain.setVisibility(View.VISIBLE);
        getActivity().setTitle("Задачи");
        setHasOptionsMenu(true);
        lvTasksFragment = view.findViewById(R.id.lv_tasks_fragment);
        ollTasks();


        lvTasksFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Task task = adapterTasksFragment.getTargetTask(i);
                Intent intent = new Intent(getActivity(), DetailTask.class);
                intent.putExtra("Task", (Parcelable) task);
                startActivity(intent);

            }
        });


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.bank_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            Intent intent = new Intent(this.getContext(), AddNewTaskActivity.class);
            startActivity(intent);
        }
        return true;
    }

    private void ollTasks() {
        ServerApi serverApi = LoginActivity.retrofit.create(ServerApi.class);

        Call<ArrayList<Task>> ollTasks = serverApi.getTasks(CheckChildrenActivity.children.getId());

        ollTasks.enqueue(new Callback<ArrayList<Task>>() {
            @Override
            public void onResponse(Call<ArrayList<Task>> call, Response<ArrayList<Task>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                    setTasksAdaper(response.body());
                    }
                }
                else {
                    Log.i("Loog", "Res task ошибка" + response.errorBody().byteStream() );
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Task>> call, Throwable t) {
                Log.i("Loog", "Res task TTTTT : " + t.getMessage());
            }
        });
    }

    private void setTasksAdaper(ArrayList<Task> body) {
        adapterTasksFragment = new AdapterTasksFragment(getActivity(), body);
        lvTasksFragment.setAdapter(adapterTasksFragment);
    }

    @Override
    public void onResume() {
        ollTasks();
        super.onResume();
    }
}
