package com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutImplements.BasicImplements;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


import com.gyiscas.bbsmp.formlibraryforandroid.R;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableAdapter;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableItemFactory;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.EditTextBeanTableItem;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by qintianhao on 15/12/8.
 */
public class TableItemEditText implements TableItemFactory {
    private EditTextBeanTableItem mItem;
    JSONObject jsonObject;
    public TableItemEditText(EditTextBeanTableItem item){
        this.mItem = item;
    }

    @Override
    public int getLayoutType() {
        return mItem.getType();
    }

    @Override
    public int getLayout() {
        int layout = R.layout.layout_table_item_edittext;
        if (mItem.getSubType()==1){
            layout = R.layout.layout_table_item_edittext_short;
//            || Utils.getWordCount(mItem.getName().toString())>10
        }
        return layout;
    }

    @Override
    public Object getItem() {
        return mItem;
    }

    @Override
    public void clearnItemValue(TableItemFactory tableItemFactory) {
        ((EditTextBeanTableItem)(tableItemFactory.getItem())).setValue("");
    }

    @Override
    public Object getNewItem(boolean cleanValue) {
        EditTextBeanTableItem tempETB = new EditTextBeanTableItem();
        tempETB.setId(mItem.getId());
        tempETB.setType(mItem.getType());
        tempETB.setName(mItem.getName());
        tempETB.setSubType(mItem.getSubType());
        tempETB.setSubmitKey(mItem.getSubmitKey());
        if (null == jsonObject){
            jsonObject = new JSONObject();
        }
        if (cleanValue){
            tempETB.setValue("");
            try {
                jsonObject.put(mItem.getSubmitKey(), "");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            tempETB.setValue(mItem.getValue());
            try {
                jsonObject.put(mItem.getSubmitKey(), mItem.getValue().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return tempETB;
    }

    @Override
    public View getView(Context context, View convertView, ViewGroup parent,LayoutInflater inflater,int position, TableAdapter tableAdapter,JSONObject jsonObj  ) {
        convertView = inflater.inflate(getLayout(), null);
        this.jsonObject = jsonObj;
        TextView tv = (TextView) convertView.findViewById(R.id.id_item_name);
        EditText etv = (EditText) convertView.findViewById(R.id.id_item_value_et);
        tv.setText(mItem.getName());
        if (!"".equals(mItem.getValue().toString())){
            etv.setText(mItem.getValue().toString());
        }
        try {
            jsonObject.remove(mItem.getSubmitKey());
            jsonObject.put(mItem.getSubmitKey(),etv.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        etv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mItem.setValue(s.toString());
                try {
                    jsonObject.remove(mItem.getSubmitKey());
                    jsonObject.put(mItem.getSubmitKey(), mItem.getValue().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return convertView;
    }
}
