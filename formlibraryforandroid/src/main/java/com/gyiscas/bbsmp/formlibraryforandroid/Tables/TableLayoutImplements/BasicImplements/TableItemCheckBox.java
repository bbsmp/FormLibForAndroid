package com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutImplements.BasicImplements;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.TextView;

import com.gyiscas.bbsmp.formlibraryforandroid.Adapter.CommonAdapter;
import com.gyiscas.bbsmp.formlibraryforandroid.Adapter.ViewHolder;
import com.gyiscas.bbsmp.formlibraryforandroid.Config.LayoutConfig;
import com.gyiscas.bbsmp.formlibraryforandroid.R;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableAdapter;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableItemFactory;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.AtomBeans.CheckBoxItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.CheckBoxBeanTableItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bbsmp on 15/12/8.
 */
public class TableItemCheckBox implements TableItemFactory {

    private CheckBoxBeanTableItem itemCheckBox;
    private JSONObject jsonObject;

    public TableItemCheckBox(CheckBoxBeanTableItem item) {
        this.itemCheckBox = item;
    }

    @Override
    public int getLayoutType() {
        return LayoutConfig.CHECKBOX_TYPE;
    }

    @Override
    public int getLayout() {
        return R.layout.layout_table_item_checkbox;
    }

    @Override
    public Object getItem() {
        return itemCheckBox;
    }

    @Override
    public void clearnItemValue(TableItemFactory tableItemCheckBox) {
        ArrayList<CheckBoxItem> values = ((CheckBoxBeanTableItem)(tableItemCheckBox.getItem())).getValues();
        for (int j=0; j<values.size(); j++){
            values.get(j).setValue(false);
        }
    }

    @Override
    public Object getNewItem(boolean cleanValue) {
        CheckBoxBeanTableItem tempCB= new CheckBoxBeanTableItem();
        tempCB.setId(itemCheckBox.getId());
        tempCB.setType(itemCheckBox.getType());
        tempCB.setName(itemCheckBox.getName());
        tempCB.setSubmitKey(itemCheckBox.getSubmitKey());

        ArrayList<CheckBoxItem> checkBoxItems = new ArrayList<>();
        if (cleanValue){
            tempCB.setValue("");
            for (int i=0; i<itemCheckBox.getValues().size(); i++){
                CheckBoxItem checkBoxItem = itemCheckBox.getValues().get(i);
                checkBoxItem.setValue("false");
                checkBoxItem.setSubmitKey(itemCheckBox.getValues().get(i).getSubmitKey());
                checkBoxItems.add(checkBoxItem);
            }
        }else {
            tempCB.setValue(itemCheckBox.getValue());
            checkBoxItems.addAll(itemCheckBox.getValues());
        }

        tempCB.setValues(checkBoxItems);
        return tempCB;
    }

    CommonAdapter<CheckBoxItem> gridAdapter;
    @Override
    public View getView(Context context, View convertView, ViewGroup parent,LayoutInflater inflater,int position, TableAdapter tableAdapter, final JSONObject jsonObj) {
        convertView = inflater.inflate(getLayout(), null);
        jsonObject = jsonObj;
        TextView title = (TextView) convertView.findViewById(R.id.id_checkboxes_title);
        GridView gridView = (GridView) convertView.findViewById(R.id.id_checkboxs_gridview);
        title.setText(itemCheckBox.getName());
        String[] valueSelected = ((String)(itemCheckBox.getName())).split(",");
        for (int i=0; i<itemCheckBox.getValues().size(); i++){
            for (int j=0; j<valueSelected.length; j++){
                if (itemCheckBox.getValues().get(i).equals(valueSelected[j])){
                    itemCheckBox.getValues().get(i).setValue(true);
                }
            }
        }

         gridAdapter = new CommonAdapter<CheckBoxItem>(context, itemCheckBox.getValues(), R.layout.layout_grid_chkb_item) {
            @Override
            public void convert(ViewHolder helper, final CheckBoxItem item, final int position) {
                CheckBox checkBox = (CheckBox) helper.getConvertView().findViewById(R.id.id_checkbox);
                checkBox.setText(item.getName());
                checkBox.setChecked(((CheckBoxItem)item).getTheBooleanValue());

                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        itemCheckBox.getValues().get(position).setValue(isChecked);
                        notifyDataSetChanged();
                        itemCheckBox.setValue(new String(""));

                        try {
                            jsonObject.remove(item.getSubmitKey());
                            jsonObject.put(item.getSubmitKey(), item.getName());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i=0; i<gridAdapter.getCount(); i++){
                            CheckBoxItem c = itemCheckBox.getValues().get(i);
                            if (c.getTheBooleanValue()) {
                                String value = (String)itemCheckBox.getValue();
                                if ("".equals(value)) {
                                    itemCheckBox.setValue(c.getName());
                                }else {
                                    itemCheckBox.setValue(itemCheckBox.getValue() + "," + c.getName());
                                }
                            }
                        }

                    }
                });
            }
        };

        gridView.setAdapter(gridAdapter);
        gridAdapter.notifyDataSetChanged();
        ViewGroup.LayoutParams lp = gridView.getLayoutParams();
        lp.height = Utils.getTotalHeightofListView(gridView);



        return convertView;
    }
}
