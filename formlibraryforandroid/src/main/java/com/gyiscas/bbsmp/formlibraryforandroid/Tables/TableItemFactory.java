package com.gyiscas.bbsmp.formlibraryforandroid.Tables;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.json.JSONObject;

/**
 * Created by bbsmp on 15/12/8.
 */
public interface TableItemFactory {

    public int getLayoutType();
    public int getLayout();
    public Object getItem();
    public void clearnItemValue(TableItemFactory tableItemFactory);
    public Object getNewItem(boolean cleanValue);

    public View getView(Context context, View convertView, ViewGroup parent, LayoutInflater inflater, int position, TableAdapter tableAdapter, JSONObject jsonObject);
}
