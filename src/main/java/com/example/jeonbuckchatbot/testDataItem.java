package com.example.jeonbuckchatbot;

public class testDataItem {
    private String content;
    private int viewType;
    private String time;

    public testDataItem(String content, Object o, int viewType, String time) {
        this.content = content;
        this.viewType = viewType;
        this.time = time;
    }

    public String getContent() { return content; }

    public String getTime() {
        return time;
    }

    public int getViewType() { return viewType; }
}
