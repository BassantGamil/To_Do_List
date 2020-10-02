package com.orp.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.orp.todolist.databinding.ItemToDoBinding;
import com.orp.todolist.model.TaskModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class TaskAdapter extends RecyclerView.Adapter<BindingViewHolder<ItemToDoBinding>> {
    Context context;
    ArrayList<TaskModel> taskModelArrayList;

    public TaskAdapter(Context context, ArrayList<TaskModel> taskModelArrayList) {
        this.context = context;
        this.taskModelArrayList = taskModelArrayList;
    }

    //    @Override
//    public View getView(int position, View view, ViewGroup viewGroup) {
////        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////        View itemSplash = inflater.inflate(R.layout.item_to_do, null);
//        ViewHolder viewHolder;
//        if (view == null) {
//            view = LayoutInflater.from(context).inflate(R.layout.item_to_do, null);
//
//            TextView nameTextView = view.findViewById(R.id.name_txt_view_item);
//            TextView descriptionTextView = view.findViewById(R.id.description_txt_view_item);
//            TextView dateTextView = view.findViewById(R.id.date_txt_view_item);
//            TextView timeTextView = view.findViewById(R.id.time_txt_view_item);
//            viewHolder = new ViewHolder();
//
//            TaskModel taskModel = taskModelArrayList.get(position);
//            String name = taskModel.getName();
//            String description = taskModel.getDescription();
//
//            int date = taskModelArrayList.get(position).getDate();
//            int time = taskModelArrayList.get(position).getDate();
//
//            nameTextView.setText(name);
//            descriptionTextView.setText(description);
//            dateTextView.setText(date);
//            timeTextView.setText(time);
//
//            return view;
//        }
//    }


    @NonNull
    @Override
    public BindingViewHolder<ItemToDoBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BindingViewHolder<>(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_to_do, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder<ItemToDoBinding> holder, int position) {
        holder.getBinding().setTaskItem(taskModelArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return taskModelArrayList.size();
    }

    public void addData(List<TaskModel> taskModels) {
        taskModelArrayList.clear();
        taskModelArrayList.addAll(taskModels);
        notifyDataSetChanged();
    }
}