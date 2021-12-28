package com.nasr.todox;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    ArrayList<TodoItem> todoList =new  ArrayList<TodoItem>();

    Context context;

    @NonNull
    @Override
    public TodoAdapter.TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TodoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.TodoViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.todoTitle.setText(todoList.get(position).getTitle());
        holder.todoDate.setText(todoList.get(position).getDate());

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtra("todoTitle",todoList.get(position).getTitle());
                intent.putExtra("todoDate",todoList.get(position).getDate());
                context.startActivity(intent);
            }
        });
    }



    void setNewData(TodoItem todoItem){
        System.out.println(todoItem.title);
        todoList.add(todoItem);
        notifyDataSetChanged();
    }



    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        TextView todoTitle ;
        TextView todoDate ;
        Button imageButton ;
        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            todoTitle = itemView.findViewById(R.id.todoTitle);
            todoDate = itemView.findViewById(R.id.todoDate);
            imageButton = itemView.findViewById(R.id.imgBtn);

        }
    }
}
