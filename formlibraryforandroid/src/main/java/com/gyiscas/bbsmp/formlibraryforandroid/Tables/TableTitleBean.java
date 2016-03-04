package com.gyiscas.bbsmp.formlibraryforandroid.Tables;

/**
 * Created by bbsmp on 15/12/14.
 */
public class TableTitleBean {
    private String title;
    private String content;
    private boolean isSubmited;


    public TableTitleBean(String title, String content, boolean isSubmited) {
        this.title = title;
        this.content = content;
        this.isSubmited = isSubmited;
    }

    public TableTitleBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSubmited() {
        return isSubmited;
    }

    public void setIsSubmited(boolean isSubmited) {
        this.isSubmited = isSubmited;
    }
}
