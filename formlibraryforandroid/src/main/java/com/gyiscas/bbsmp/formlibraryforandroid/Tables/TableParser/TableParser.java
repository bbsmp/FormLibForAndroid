package com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableParser;

import android.content.Context;


import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableItemFactory;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.ACTextBeanTableItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.AtomBeans.CheckBoxItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.AtomBeans.SpinnerItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.CheckBoxBeanTableItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.DatePickerBeanTableItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.EditTextBeanTableItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.SpinnerBeanTableItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.SwitchBeanTableItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.TextLableBeanTableItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.TableAction.ActionExpandable;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.TableAction.ActionExpandableItemBean;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.TableItemBaseBean;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutImplements.BasicImplements.TableItemCheckBox;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutImplements.BasicImplements.TableItemDatePicker;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutImplements.BasicImplements.TableItemEditText;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutImplements.BasicImplements.TableItemLableText;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutImplements.BasicImplements.TableItemSpinner;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutImplements.BasicImplements.TableItemSwitch;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutImplements.ComplecImplements.TableItemExpandable;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableUtils.TableUtils;
import com.gyiscas.bbsmp.formlibraryforandroid.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bbsmp on 15/12/11.
 */
public class TableParser {


    /**
     *
     * @param context
     * @param datas
     * @return
     * @throws Exception
     */
    public ArrayList<TableItemFactory> parseTable(Context context,String  datas) throws Exception{
        ArrayList<TableItemFactory> res = new ArrayList<>();
        JSONObject tableJson = new JSONObject(datas);
//        JSONArray jsonArray = tableJson.getJSONArray("datas");
        res = parseJsonObjectToTableItemFactory(tableJson);
//        res = parseJasonArryToTableItemFactory(jsonArray);
        return res;
    }

    /**
     *
     * @param jsonObject
     * @return
     * @throws Exception
     */
    private ArrayList<TableItemFactory> parseJsonObjectToTableItemFactory(JSONObject jsonObject) throws Exception{
        ArrayList<TableItemFactory> res = new ArrayList<>();
        int type = jsonObject.optInt("type");
        int id = jsonObject.getInt("id");
        String name = jsonObject.optString("name");
        switch (type){
            case 1://简单文本框类型
                EditTextBeanTableItem e = new EditTextBeanTableItem();
                e.setId(id);
                e.setType(type);
                e.setName(name);
                e.setValue(jsonObject.optString("value"));
                e.setSubType(jsonObject.optInt("subType", 0));
                e.setSubmitKey(jsonObject.optString("submitKey"));
                e.setIsRequired(jsonObject.optInt("isRequired", 0));
                e.setValiType(jsonObject.optInt("validationType", 6));
                TableItemEditText t = new TableItemEditText(e);
                res.add(t);
                break;

            case 11://自动补全文本框类型
                ACTextBeanTableItem acbt = new ACTextBeanTableItem();
                acbt.setId(id);
                acbt.setType(type);
                acbt.setName(name);
                acbt.setValue(jsonObject.optString("value"));
                acbt.setSubType(jsonObject.optInt("subType", 0));
                acbt.setSubmitKey(jsonObject.optString("submitKey"));
                acbt.setIsRequired(jsonObject.optInt("isRequired", 0));
                acbt.setValiType(jsonObject.optInt("validationType", 6));
//                TableItemACEditText tact = new TableItemACEditText(acbt);
//                res.add(tact);
                break;
            case 2://基本文本类型
                TextLableBeanTableItem tl = new TextLableBeanTableItem();
                tl.setId(id);
                tl.setType(type);
                tl.setName(name);
                TableItemLableText tt = new TableItemLableText(tl);
                res.add(tt);
                JSONArray jArray = jsonObject.getJSONArray("datas");
                res.addAll(parseJasonArryToTableItemFactory(jArray));
                break;
            case 3://下拉列表
                SpinnerBeanTableItem s = new SpinnerBeanTableItem();
                s.setId(id);
                s.setType(type);
                s.setName(name);
                s.setValue(jsonObject.optString("value"));
                s.setSubmitKey(jsonObject.optString("submitKey"));
                JSONArray jsonArray = jsonObject.getJSONArray("values");
                ArrayList<TableItemBaseBean> tableItemBaseBeans1 = parseJsonArrayToAtomBeans(jsonArray);

                ArrayList<SpinnerItem> spinnerItems = TableUtils.changeTableItemBaseBeanToSpinnerItems(tableItemBaseBeans1);
                s.setValues(spinnerItems);
                TableItemSpinner spinner = new TableItemSpinner(s);
                res.add(spinner);

                break;
            case 4://switch
                SwitchBeanTableItem switchBeanTableItem = new SwitchBeanTableItem();
                switchBeanTableItem.setId(id);
                switchBeanTableItem.setType(type);
                switchBeanTableItem.setName(name);
                switchBeanTableItem.setSubmitKey(jsonObject.optString("submitKey"));
                String v = (String) Utils.changeBooleanToObject(jsonObject.optBoolean("value"));
                switchBeanTableItem.setValue(v);

                TableItemSwitch tableItemSwitch = new TableItemSwitch(switchBeanTableItem);
                res.add(tableItemSwitch);

                break;
            case 5://基本时间选择器
                DatePickerBeanTableItem datePickerBeanTableItem = new DatePickerBeanTableItem();
                datePickerBeanTableItem.setId(id);
                datePickerBeanTableItem.setType(type);
                datePickerBeanTableItem.setName(name);
                datePickerBeanTableItem.setValue(jsonObject.optString("value"));
                datePickerBeanTableItem.setSubmitKey(jsonObject.optString("submitKey"));
                TableItemDatePicker tableItemDatePicker = new TableItemDatePicker(datePickerBeanTableItem);
                res.add(tableItemDatePicker);

                break;
            case 6:
                break;
            case 7://基本复选框
                CheckBoxBeanTableItem checkBoxBeanTableItem = new CheckBoxBeanTableItem();
                checkBoxBeanTableItem.setId(id);
                checkBoxBeanTableItem.setType(type);
                checkBoxBeanTableItem.setName(name);
                checkBoxBeanTableItem.setValue(jsonObject.optBoolean("value"));
                checkBoxBeanTableItem.setSubmitKey(jsonObject.optString("submitKey"));
                JSONArray jsonArrayCheckBoxes = jsonObject.getJSONArray("values");
                parseJasonArryToTableItemFactory(jsonArrayCheckBoxes);

                ArrayList<TableItemBaseBean> tableItemBaseBeans = parseJsonArrayToAtomBeans(jsonArrayCheckBoxes);
                ArrayList<CheckBoxItem> checkBoxItems = TableUtils.changeTableItemBaseBeanToCheckBoxItems(tableItemBaseBeans);
                checkBoxBeanTableItem.setValues(checkBoxItems);
                TableItemCheckBox tableItemCheckBox = new TableItemCheckBox(checkBoxBeanTableItem);
                res.add(tableItemCheckBox);

                break;
            case 8:
                /**
                LocationBeanTableItem locationBeanTableItem = new LocationBeanTableItem();
                locationBeanTableItem.setId(id);
                locationBeanTableItem.setType(type);
                locationBeanTableItem.setName(name);
                locationBeanTableItem.setValue(jsonObject.optString("value"));
                locationBeanTableItem.setSubmitKey(jsonObject.optString("submitKey"));
                locationBeanTableItem.setIsRequired(jsonObject.optInt("isRequired", 0));
                locationBeanTableItem.setValiType(jsonObject.optInt("validationType", 6));
                TableItemLocation tableItemLocation = new TableItemLocation(locationBeanTableItem);
                res.add(tableItemLocation);
                 **/
                break;

            case 100:

                ActionExpandable actionExpandable = new ActionExpandable();
                actionExpandable.setId(id);
                actionExpandable.setName(name);
                actionExpandable.setIsLastOne(jsonObject.getBoolean("isLastOne"));
                actionExpandable.setSubmitKey(jsonObject.optString("submitKey"));
                actionExpandable.setExpandId(jsonObject.optInt("expandId"));
                JSONArray jsonArray1 = jsonObject.getJSONArray("datas");
                ArrayList<TableItemFactory> tableItemFactories =  parseJasonArryToTableItemFactory(jsonArray1);
                actionExpandable.setChildrens(tableItemFactories);
                ArrayList<TableItemFactory> dd = new ArrayList<>();
                dd.add(new TableItemExpandable(actionExpandable));
                res.addAll(dd);

                break;
            default:
                break;
        }

        return res;
    }

    /**
     *
     * @param jsonObject
     * @return
     * @throws Exception
     */
    private ArrayList<TableItemBaseBean> parseJsonObjectToAtomBean(JSONObject jsonObject) throws Exception {
        ArrayList<TableItemBaseBean> res = new ArrayList<>();
        int type = jsonObject.getInt("type");
        int id = jsonObject.getInt("id");
        String name = jsonObject.optString("name");
        switch (type){
            case 9://
                String submitKey = jsonObject.optString("submitKey");
                CheckBoxItem c = new CheckBoxItem();
                c.setId(id);
                c.setType(type);
                c.setName(name);
                c.setSubmitKey(submitKey);
                String v = (String) Utils.changeBooleanToObject(jsonObject.optBoolean("value"));
                c.setValue(v);
                res.add(c);
                break;
            case 10://spinner
                String submitKey1 = jsonObject.optString("submitKey");
                SpinnerItem s = new SpinnerItem();
                s.setId(id);
                s.setType(type);
                s.setName(name);
                s.setSubmitKey(submitKey1);
                s.setValue(jsonObject.optString("value"));
                res.add(s);
                break;
            default:
                break;
        }

        return res;
    }


    private ArrayList<TableItemBaseBean> parseJsonArrayToAtomBeans(JSONArray jsonArray) throws Exception{
        ArrayList<TableItemBaseBean> res = new ArrayList<>();
        for (int i=0; i<jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            ArrayList<TableItemBaseBean> temp = parseJsonObjectToAtomBean(jsonObject);
            res.addAll(temp);
        }

        return res;
    }

    private ArrayList<ActionExpandableItemBean> parseJsonArrayToAEPB(JSONArray jsonArray) throws Exception{
        ArrayList<ActionExpandableItemBean> res = new ArrayList<>();
        for (int i=0; i<jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int type = jsonObject.getInt("type");
            int id = jsonObject.getInt("id");
            String name = jsonObject.optString("name");
            ActionExpandableItemBean ab = new ActionExpandableItemBean();
            ArrayList<TableItemFactory> tt = new ArrayList<>();

            ab.setId(id);
            ab.setName(name);
            ab.setType(type);

            tt = parseJasonArryToTableItemFactory(jsonObject.getJSONArray("children"));
            ab.setChildren(tt);
            res.add(ab);
        }

        return res;
    }

    private ArrayList<TableItemFactory> parseJasonArryToTableItemFactory(JSONArray jsonArray) throws Exception{
        ArrayList<TableItemFactory> res = new ArrayList<>();
        for (int i=0; i<jsonArray.length(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            ArrayList<TableItemFactory> temp = new ArrayList<>();
            temp = parseJsonObjectToTableItemFactory(jsonObject);
            res.addAll(temp);
        }
        return res;
    }


}
