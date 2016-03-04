package com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans;


import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.AtomBeans.CheckBoxItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.TableItemBaseBean;

import java.util.ArrayList;

/**
 * Created by bbsmp on 15/12/10.
 */
public class CheckBoxBeanTableItem extends TableItemBaseBean {
    private ArrayList<CheckBoxItem> values;

    public CheckBoxBeanTableItem(int id, String name, int type, Object value, ArrayList<CheckBoxItem> values) {
        super(id, name, type, value);
        this.values = values;
    }

    public CheckBoxBeanTableItem(ArrayList<CheckBoxItem> values) {
        this.values = values;
    }

    public CheckBoxBeanTableItem(int id, String name, int type, ArrayList<CheckBoxItem> values) {
        super(id, name, type);
        this.values = values;
    }

    public ArrayList<CheckBoxItem> getValues() {
        return values;
    }

    public void setValues(ArrayList<CheckBoxItem> values) {
        this.values = values;
    }

    public CheckBoxBeanTableItem() {
    }

    public boolean getTheBooleanValue(){
        boolean b = false;
        if ("true".equals(getValue().toString())){
            b = true;
        }
        return b;
    }

}
