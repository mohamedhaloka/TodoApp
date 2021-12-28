package com.nasr.todox;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TodoViewModel extends ViewModel {


    public MutableLiveData<TodoItem> todo = new MutableLiveData();



    void setNewData(TodoItem todoItem)
    {
        System.out.println(todoItem.title);
        todo.setValue(todoItem);
    }

}
