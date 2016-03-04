package com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.TableAction;


import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableItemFactory;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.AtomBeans.CheckBoxItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.CheckBoxBeanTableItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.DatePickerBeanTableItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.EditTextBeanTableItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.TableItemBaseBean;

import java.util.ArrayList;

/**
 * Created by bbsmp on 15/12/10.
 */
public class ActionExpandable extends TableItemBaseBean {


    private ArrayList<TableItemFactory> childrens;
    private int expandId;

    public ActionExpandable(int id, String name, int type, Object value, ArrayList<TableItemFactory> childrens, int expandId) {
        super(id, name, type, value);
        this.childrens = childrens;
        this.expandId = expandId;
    }

    public ActionExpandable(ArrayList<TableItemFactory> childrens, int expandId) {
        this.childrens = childrens;
        this.expandId = expandId;
    }

    public ActionExpandable(int id, String name, int type, ArrayList<TableItemFactory> childrens, int expandId) {
        super(id, name, type);
        this.childrens = childrens;
        this.expandId = expandId;
    }

    public ActionExpandable(int id, String name, int type, Object value, boolean isLastOne, ArrayList<TableItemFactory> childrens, int expandId) {
        super(id, name, type, value, isLastOne);
        this.childrens = childrens;
        this.expandId = expandId;
    }

    public ActionExpandable(int id, String name, int type, int subType, Object value, boolean isLastOne, ArrayList<TableItemFactory> childrens, int expandId) {
        super(id, name, type, subType, value, isLastOne);
        this.childrens = childrens;
        this.expandId = expandId;
    }

    public ActionExpandable(int id, String name, int type, int subType, Object value, boolean isLastOne, String submitKey, ArrayList<TableItemFactory> childrens, int expandId) {
        super(id, name, type, subType, value, isLastOne, submitKey);
        this.childrens = childrens;
        this.expandId = expandId;
    }

    public ActionExpandable() {
    }
    public ActionExpandable(int id, String name, int type, Object value, ArrayList<TableItemFactory> childrens) {
        super(id, name, type, value);
        this.childrens = childrens;
    }
    public ActionExpandable(ArrayList<TableItemFactory> childrens) {
        this.childrens = childrens;
    }
    public ActionExpandable(int id, String name, int type, ArrayList<TableItemFactory> childrens) {
        super(id, name, type);
        this.childrens = childrens;
    }
    public ActionExpandable(int id, String name, int type, Object value, boolean isLastOne, ArrayList<TableItemFactory> childrens) {
        super(id, name, type, value, isLastOne);
        this.childrens = childrens;
    }
    /**
     * 将值清空
     */
    public void cleanValue(){
        for (int i=0; i<childrens.size(); i++){
            int type = childrens.get(i).getLayoutType();
            switch (type){
                case 1://编辑文本框类型
                    ((EditTextBeanTableItem)(childrens.get(i).getItem())).setValue("");
                    break;
                case 5://时间选择器类型
                    ((DatePickerBeanTableItem)(childrens.get(i).getItem())).setValue("");
                    break;
                case 7://复选框类型
                    ArrayList<CheckBoxItem> values = ((CheckBoxBeanTableItem)(childrens.get(i).getItem())).getValues();
                    for (int j=0; j<values.size(); j++){
                        values.get(j).setValue(false);
                    }
                    break;
                default:
                    break;
            }
        }
    }
    public ArrayList<TableItemFactory> getChildrens() {
        return childrens;
    }
    public void setChildrens(ArrayList<TableItemFactory> childrens) {
        this.childrens = childrens;
    }


    public int getExpandId() {
        return expandId;
    }

    public void setExpandId(int expandId) {
        this.expandId = expandId;
    }
}
