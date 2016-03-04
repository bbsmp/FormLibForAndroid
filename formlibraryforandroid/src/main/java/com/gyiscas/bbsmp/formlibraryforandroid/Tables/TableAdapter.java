package com.gyiscas.bbsmp.formlibraryforandroid.Tables;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by bbsmp on 15/12/8.
 */
public class TableAdapter extends BaseAdapter {

    List<TableItemFactory>  mDstas;
    JSONObject jsonObject;

    private LayoutInflater mInflater;
    private Context mContext;

    public TableAdapter(Context mContext, List<TableItemFactory> datas) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        mDstas = datas;
        jsonObject  = new JSONObject();

    }

    public JSONObject getJsonObject(){
        return jsonObject;
    }

    public String getJsonString(){
        return jsonObject.toString();
    }

    @Override
    public int getCount() {
        return mDstas.size();
    }

    public List<TableItemFactory> getAllItems(){
        return mDstas;
    }

    @Override
    public Object getItem(int position) {
        return mDstas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return mDstas.get(position).getView(mContext, convertView, parent, mInflater,position,this,jsonObject);
    }
}
