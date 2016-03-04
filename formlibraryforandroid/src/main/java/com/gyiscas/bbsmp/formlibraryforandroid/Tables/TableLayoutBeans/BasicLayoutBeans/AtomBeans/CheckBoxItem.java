package com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.AtomBeans;


import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.TableItemBaseBean;

/**
 * Created by bbsmp on 15/12/10
 * 单个checkbox.
 */
public class CheckBoxItem extends TableItemBaseBean {

    public CheckBoxItem(int id, String name, int type, Object value) {
        super(id, name, type, value);
    }

    public CheckBoxItem() {
    }

    public CheckBoxItem(int id, String name, int type) {
        super(id, name, type);
    }

    public CheckBoxItem(int id, String name, int type, Object value, boolean isLastOne) {
        super(id, name, type, value, isLastOne);
    }

    public CheckBoxItem(int id, String name, int type, Object value, String submitKey) {
        super(id, name, type, value, submitKey);
    }
    public CheckBoxItem(int id, String name, int type, int subType, Object value, boolean isLastOne) {
        super(id, name, type, subType, value, isLastOne);
    }

    public CheckBoxItem(int id, String name, int type, int subType, Object value, boolean isLastOne, String submitKey) {
        super(id, name, type, subType, value, isLastOne, submitKey);
    }

    public boolean getTheBooleanValue(){
        boolean b = false;
        if ("true".equals(getValue().toString())){
            b = true;
        }
        return b;
    }

}
