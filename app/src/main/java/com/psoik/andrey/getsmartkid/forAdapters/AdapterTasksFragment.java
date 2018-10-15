package com.psoik.andrey.getsmartkid.forAdapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.psoik.andrey.getsmartkid.R;
import com.psoik.andrey.getsmartkid.forJSON.Task;

import java.util.ArrayList;

public class AdapterTasksFragment extends BaseAdapter {
    private ArrayList<Task> list;


   private LayoutInflater inflater;

    public AdapterTasksFragment(Context context1, ArrayList<Task> list1){
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

        if(view == null){view = inflater.inflate(R.layout.for_list_tasks_fragment, viewGroup, false);}

        Task task = getTargetTask(i);
        //constraintLForListTask
        ConstraintLayout c = view.findViewById(R.id.constraintLForListTask);
        ((TextView) view.findViewById(R.id.tv_name_task_for_list_fragment)).setText(task.getName());
        String price = String.valueOf(task.getPrice());
        ((TextView) view.findViewById(R.id.tv_coast_task_for_list_fragment)).setText(price);

        //new - активное, approve - Предложено ребенком, check - ожидает проверки,
        //rejected - отклонено,Предложено ребенком Предложение отклонено
        String status = task.getStatus();
        String newStatus ;

        if(status.equals("new"))
            newStatus = "Активное";
        else if(status.equals("disapproved"))
            newStatus = "Предложение отклонено";
        else if(status.equals("approve"))
            newStatus = "Предложено ребенком";
        else if(status.equals("check"))
            newStatus = "Ожидает проверки";
        else if(status.equals("rejected"))
            newStatus = "Отклонено";
        else if(status.equals("confirmed")){
            newStatus = "Выполнено";
            view.setBackgroundColor(Color.argb(100,204, 255,204));
            }
        else newStatus = status;
        ((TextView) view.findViewById(R.id.tv_status_task_for_list_fragment)).setText(newStatus);


        return view;
    }

    public Task getTargetTask(int poss){
        return new Task(list.get(poss).getId(), list.get(poss).getChildId(), list.get(poss).getName()
                            , list.get(poss).getStatus(),list.get(poss).getExpireDate(), list.get(poss).getPrice()
                            , list.get(poss).getDescription());


    }


}
