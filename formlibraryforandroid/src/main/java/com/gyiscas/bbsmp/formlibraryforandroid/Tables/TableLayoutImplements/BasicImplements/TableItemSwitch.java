package com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutImplements.BasicImplements;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.gyiscas.bbsmp.formlibraryforandroid.Config.LayoutConfig;
import com.gyiscas.bbsmp.formlibraryforandroid.R;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableAdapter;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableItemFactory;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.SwitchBeanTableItem;
import com.kyleduo.switchbutton.SwitchButton;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bbsmp on 15/12/8.
 */
public class TableItemSwitch implements TableItemFactory {
    private SwitchBeanTableItem item;
    private JSONObject jsonObject;

    public TableItemSwitch(SwitchBeanTableItem item) {
        this.item = item;
    }

    @Override
    public int getLayoutType() {
        return LayoutConfig.SWITCH_TYPE;
    }

    @Override
    public int getLayout() {
        return R.layout.layout_table_item_switch;
    }

    @Override
    public Object getItem() {
        return item;
    }

    @Override
    public void clearnItemValue(TableItemFactory tableItemFactory) {
        ((SwitchBeanTableItem)(tableItemFactory.getItem())).setValue(false);
    }

    @Override
    public Object getNewItem(boolean cleanValue) {
        SwitchBeanTableItem tempSBT = new SwitchBeanTableItem();
        tempSBT.setId(item.getId());
        tempSBT.setType(item.getType());
        tempSBT.setName(item.getName());
        if (cleanValue){
            tempSBT.setValue("false");
        }else {
            tempSBT.setValue(item.getValue());
        }
        return tempSBT;
    }

    @Override
    public View getView(Context context, View convertView,ViewGroup parent, LayoutInflater inflater,int position,TableAdapter tableAdapter, JSONObject jsonObj) {
        convertView = inflater.inflate(getLayout(), null);
        jsonObject = jsonObj;
        TextView tv = (TextView) convertView.findViewById(R.id.id_switch_name);
        SwitchButton s = (SwitchButton) convertView.findViewById(R.id.id_switch);

        tv.setText(item.getName());
        s.setChecked(item.getTheBooleanValue());
        try {
            jsonObject.remove(item.getSubmitKey());
            if (s.isChecked()){
                jsonObject.put(item.getSubmitKey(),1);
            }else {
                jsonObject.put(item.getSubmitKey(),0);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                item.setValue(isChecked);
                try {
                    jsonObject.remove(item.getSubmitKey());
                    if (isChecked){
                        jsonObject.put(item.getSubmitKey(),1);
                    }else {
                        jsonObject.put(item.getSubmitKey(),0);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return convertView;
    }
}
