package com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutImplements.BasicImplements;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.gyiscas.bbsmp.formlibraryforandroid.R;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableAdapter;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableItemFactory;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.TextLableBeanTableItem;

import org.json.JSONObject;

/**
 * Created by bbsmp on 15/12/8.
 */
public class TableItemLableText implements TableItemFactory {
    private TextLableBeanTableItem item;
    public TableItemLableText(TextLableBeanTableItem item){
        this.item = item;
    }

    @Override
    public int getLayoutType() {
        return item.getType();
    }


    @Override
    public int getLayout() {
        return R.layout.layout_table_item_lable;
    }

    @Override
    public Object getItem() {
        return item;
    }

    @Override
    public void clearnItemValue(TableItemFactory tableItemFactory) {

    }

    @Override
    public Object getNewItem(boolean cleanValue) {
        return item;
    }

    @Override
    public View getView(Context context, View convertView, ViewGroup parent,LayoutInflater inflater,int position, TableAdapter tableAdapter, JSONObject jsonObject ) {
        convertView = inflater.inflate(getLayout(), null);
        TextView title = (TextView) convertView.findViewById(R.id.id_lable_name);
        String name = "";
        if (null!=item.getName()){
            name=item.getName();
        }
        title.setText(name);
        title.setFocusable(true);
        title.requestFocus();
        return convertView;
    }
}
