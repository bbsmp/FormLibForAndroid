package com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.TableAction;


import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableItemFactory;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.AtomBeans.CheckBoxItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.CheckBoxBeanTableItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.DatePickerBeanTableItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.EditTextBeanTableItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.TableItemBaseBean;

import java.util.ArrayList;

/**
 * Created by qintianhao on 15/12/16.
 */
public class ActionExpandableItemBean extends TableItemBaseBean {
    private ArrayList<TableItemFactory> children;

    public ActionExpandableItemBean(int id, String name, int type, Object value, ArrayList<TableItemFactory> children) {
        super(id, name, type, value);
        this.children = children;
    }

    public ActionExpandableItemBean(ArrayList<TableItemFactory> children) {
        this.children = children;
    }

    public ActionExpandableItemBean(int id, String name, int type, ArrayList<TableItemFactory> children) {
        super(id, name, type);
        this.children = children;
    }

    public ActionExpandableItemBean(int id, String name, int type, Object value, boolean isLastOne, ArrayList<TableItemFactory> children) {
        super(id, name, type, value, isLastOne);
        this.children = children;
    }

    public ActionExpandableItemBean(int id, String name, int type, int subType, Object value, boolean isLastOne, ArrayList<TableItemFactory> children) {
        super(id, name, type, subType, value, isLastOne);
        this.children = children;
    }

    public ActionExpandableItemBean(int id, String name, int type, int subType, Object value, boolean isLastOne, String submitKey, ArrayList<TableItemFactory> children) {
        super(id, name, type, subType, value, isLastOne, submitKey);
        this.children = children;
    }

    public ActionExpandableItemBean() {
    }

    public ArrayList<TableItemFactory> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<TableItemFactory> children) {
        this.children = children;
    }

    /**
     * 将值清空
     */
    public void cleanValue(){
        for (int i=0; i<children.size(); i++){
            int type = children.get(i).getLayoutType();
            switch (type){
                case 1://编辑文本框类型
                    ((EditTextBeanTableItem)(children.get(i).getItem())).setValue("");
                    break;
                case 5://时间选择器类型
                    ((DatePickerBeanTableItem)(children.get(i).getItem())).setValue("");
                    break;
                case 7://复选框类型
                    ArrayList<CheckBoxItem> values = ((CheckBoxBeanTableItem)(children.get(i).getItem())).getValues();
                    for (int j=0; j<values.size(); j++){
                        values.get(j).setValue(false);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
