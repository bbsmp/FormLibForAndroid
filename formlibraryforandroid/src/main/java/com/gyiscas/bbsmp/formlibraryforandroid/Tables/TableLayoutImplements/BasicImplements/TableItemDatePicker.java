package com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutImplements.BasicImplements;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.gyiscas.bbsmp.formlibraryforandroid.Config.LayoutConfig;
import com.gyiscas.bbsmp.formlibraryforandroid.R;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableAdapter;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableItemFactory;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.DatePickerBeanTableItem;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;


/**
 * Created by bbsmp on 15/12/10.
 */
public class TableItemDatePicker implements TableItemFactory {
    private DatePickerBeanTableItem datePickerBean;
    private JSONObject jsonObject;

    public TableItemDatePicker(DatePickerBeanTableItem datePickerBean) {
        this.datePickerBean = datePickerBean;
    }

    @Override
    public int getLayoutType() {
        return LayoutConfig.DATE_PICKER_TYPE;
    }

    @Override
    public int getLayout() {
        return R.layout.layout_table_item_datepicker;
    }

    @Override
    public Object getItem() {
        return datePickerBean;
    }

    @Override
    public void clearnItemValue(TableItemFactory tableItemFactory) {
        ((DatePickerBeanTableItem)(tableItemFactory.getItem())).setValue("");
    }

    @Override
    public Object getNewItem(boolean cleanValue) {
        DatePickerBeanTableItem tempDP = new DatePickerBeanTableItem();
        tempDP.setId(datePickerBean.getId());
        tempDP.setType(datePickerBean.getType());
        tempDP.setName(datePickerBean.getName());
        if (cleanValue){
            tempDP.setValue("");
        }else {
            tempDP.setValue(datePickerBean.getValue());
        }
        return tempDP;

    }



    @Override
    public View getView(final Context context, View convertView, ViewGroup parent,LayoutInflater inflater, int position, final TableAdapter tableAdapter,JSONObject jsonObj) {
        convertView = inflater.inflate(getLayout(), null);
        jsonObject = jsonObj;
        TextView tv = (TextView) convertView.findViewById(R.id.id_item_name);
        EditText etv = (EditText) convertView.findViewById(R.id.id_item_value_et);
        ImageButton imageButton = (ImageButton)convertView.findViewById(R.id.id_button_date);
        tv.setText(datePickerBean.getName());
        if (!"".equals(datePickerBean.getValue())){
            etv.setText(datePickerBean.getValue().toString());
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
                datePickerBean.setValue(s.toString());
                try {
                    jsonObject.remove(datePickerBean.getSubmitKey());
                    jsonObject.put(datePickerBean.getSubmitKey(),s.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MaterialDialog dialog = new MaterialDialog.Builder(context).build();
                dialog.show();
                DatePicker picker = new DatePicker(context);
                Calendar c = Calendar.getInstance();
                picker.setDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH));
                picker.setMode(DPMode.SINGLE);
                picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
                    @Override
                    public void onDatePicked(String date) {
                        datePickerBean.setValue(date);
                        try {
                            jsonObject.remove(datePickerBean.getSubmitKey());
                            jsonObject.put(datePickerBean.getSubmitKey(),date);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        tableAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setContentView(picker, params);
                dialog.getWindow().setGravity(Gravity.CENTER);
                dialog.getBuilder().positiveColor(context.getResources().getColor(R.color.colorPrimary)).callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                    }
                });

            }
        });


        return convertView;
    }
}
