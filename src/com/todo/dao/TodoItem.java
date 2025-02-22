package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
    private String title;
    private String desc;
    private String cate;
    private String current_date;
    private String due_date;


	public TodoItem(String cate, String title, String desc, String due_date){
        this.title=title;
        this.desc=desc;
        this.cate = cate;
        this.due_date = due_date;
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date = f.format(new Date());
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
	
    public String toSaveString() {
    	return cate + "##" + title + "##" + desc + "##" + due_date + "##" + current_date + "\n";
    }

	public String toString() {
		return "[" + cate + "] " + title+ " - " + desc + " - " + due_date + " - " + current_date;
	}
}
