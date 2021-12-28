package com.nasr.todox;

public class TodoItem {

    String title;
    String date;

    TodoItem(String title,String date){
        this.date = date;
        this.title = title;
    };

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
