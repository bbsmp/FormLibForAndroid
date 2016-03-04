package com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableUtils;

import android.widget.EditText;


import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.AtomBeans.CheckBoxItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.AtomBeans.SpinnerItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.TableItemBaseBean;

import java.util.ArrayList;

/**
 * Created by qintianhao on 15/12/11.
 */
public class TableUtils {
    public static ArrayList<CheckBoxItem> changeTableItemBaseBeanToCheckBoxItems(ArrayList<TableItemBaseBean> tableItemBaseBeans){
        ArrayList<CheckBoxItem> res = new ArrayList<>();
        for (int i=0; i<tableItemBaseBeans.size(); i++){
            TableItemBaseBean t = tableItemBaseBeans.get(i);
            res.add(new CheckBoxItem(t.getId(),t.getName(),t.getType(),t.getValue(),t.getSubmitKey()));
        }

        return res;
    }

    public static ArrayList<SpinnerItem> changeTableItemBaseBeanToSpinnerItems(ArrayList<TableItemBaseBean> tableItemBaseBeans){
        ArrayList<SpinnerItem> res = new ArrayList<>();
        for (int i=0; i<tableItemBaseBeans.size(); i++){
            TableItemBaseBean t = tableItemBaseBeans.get(i);
            res.add(new SpinnerItem(t.getId(),t.getName(),t.getType(),t.getValue()));
        }

        return res;
    }

 }
