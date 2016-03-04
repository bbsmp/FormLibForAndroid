package com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans;


import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.AtomBeans.SpinnerItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.TableItemBaseBean;

import java.util.ArrayList;

/**
 * Created by qintianhao on 15/12/10.
 */
public class SpinnerBeanTableItem extends TableItemBaseBean {
    private ArrayList<SpinnerItem> values;

    public SpinnerBeanTableItem(int id, String name, int type, Object value, ArrayList<SpinnerItem> values) {
        super(id, name, type, value);
        this.values = values;
    }

    public SpinnerBeanTableItem(ArrayList<SpinnerItem> values) {
        this.values = values;
    }

    public SpinnerBeanTableItem(int id, String name, int type, ArrayList<SpinnerItem> values) {
        super(id, name, type);
        this.values = values;
    }

    public SpinnerBeanTableItem() {
    }


    public ArrayList<SpinnerItem> getValues() {
        return values;
    }

    public void setValues(ArrayList<SpinnerItem> values) {
        this.values = values;
    }
}
