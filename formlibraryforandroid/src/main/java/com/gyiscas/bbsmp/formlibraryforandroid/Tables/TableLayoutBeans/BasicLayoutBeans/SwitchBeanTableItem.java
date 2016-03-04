package com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans;


import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.TableItemBaseBean;

/**
 * Created by bbsmp on 15/12/10.
 *
 */
public class SwitchBeanTableItem extends TableItemBaseBean {
    public SwitchBeanTableItem(int id, String name, int type, Object value) {
        super(id, name, type, value);
    }

    public SwitchBeanTableItem() {
    }

    public SwitchBeanTableItem(int id, String name, int type) {
        super(id, name, type);
    }

    public boolean getTheBooleanValue(){
        boolean b = false;
        if ("true".equals(getValue().toString())){
            b = true;
        }
        return b;
    }
}
