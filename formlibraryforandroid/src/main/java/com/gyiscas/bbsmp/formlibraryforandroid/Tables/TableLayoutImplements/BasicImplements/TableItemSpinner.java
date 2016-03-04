package com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutImplements.BasicImplements;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;


import com.gyiscas.bbsmp.formlibraryforandroid.Adapter.CommonAdapter;
import com.gyiscas.bbsmp.formlibraryforandroid.Adapter.ViewHolder;
import com.gyiscas.bbsmp.formlibraryforandroid.Config.LayoutConfig;
import com.gyiscas.bbsmp.formlibraryforandroid.R;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableAdapter;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableItemFactory;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.AtomBeans.SpinnerItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.SpinnerBeanTableItem;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bbsmp on 15/12/8.
 */
public class TableItemSpinner implements TableItemFactory {
    private SpinnerBeanTableItem spinnerBean;
    private JSONObject jsonObject;

    public TableItemSpinner(SpinnerBeanTableItem item) {
        this.spinnerBean = item;
    }

    @Override
    public int getLayoutType() {
        return LayoutConfig.SPINNER_TYPE;
    }

    @Override
    public int getLayout() {
        return R.layout.layout_table_item_spinner;
    }

    @Override
    public Object getItem() {
        return spinnerBean;
    }

    @Override
    public void clearnItemValue(TableItemFactory tableItemFactory) {
        ((SpinnerBeanTableItem)(tableItemFactory.getItem())).setValue("");
    }

    @Override
    public Object getNewItem(boolean cleanValue) {
        SpinnerBeanTableItem tempSB = new SpinnerBeanTableItem();
        tempSB.setId(spinnerBean.getId());
        tempSB.setType(spinnerBean.getType());
        tempSB.setName(spinnerBean.getName());
        tempSB.setValues(spinnerBean.getValues());
        if (cleanValue){
            tempSB.setValue(spinnerBean.getValues().get(0).getValue().toString());
        }else {
            tempSB.setValue(spinnerBean.getValue());
        }
        return tempSB;
    }

    @Override
    public View getView(Context mContext, View convertView,ViewGroup parent, LayoutInflater inflater,int position, TableAdapter tableAdapter,JSONObject jsonObj) {
        convertView = inflater.inflate(getLayout(), null);
        jsonObject = jsonObj;
        TextView tv = (TextView) convertView.findViewById(R.id.id_item);
        tv.setText(spinnerBean.getName());
        Spinner spinner = (Spinner) convertView.findViewById(R.id.id_spinner);
        final CommonAdapter<SpinnerItem> adapter = new CommonAdapter<SpinnerItem>(mContext,spinnerBean.getValues(), R.layout.layout_spinner_item) {
            @Override
            public void convert(ViewHolder helper, SpinnerItem item, int position) {
                TextView tt = (TextView) helper.getConvertView().findViewById(R.id.id_spinner_item_text);
                tt.setText(item.getName());
            }
        };
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerItem spinnerItem = adapter.getItem(position);
                spinnerBean.setValue(spinnerItem.getName());

                try {
                    jsonObject.remove(spinnerBean.getSubmitKey());
                    jsonObject.put(spinnerBean.getSubmitKey(), adapter.getItem(position).getId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                try {
                    jsonObject.remove(spinnerBean.getSubmitKey());
                    jsonObject.put(spinnerBean.getSubmitKey(), 0);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return convertView;

    }
}
