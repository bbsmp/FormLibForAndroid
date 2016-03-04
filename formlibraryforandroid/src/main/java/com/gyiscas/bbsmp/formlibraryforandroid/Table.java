package com.gyiscas.bbsmp.formlibraryforandroid;

import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.gyiscas.bbsmp.formlibraryforandroid.AsycTasks.CreateListViewTask;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableAdapter;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableItemFactory;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.EditTextBeanTableItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.TableAction.ActionExpandable;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutImplements.ComplecImplements.TableItemExpandable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by qintianhao on 16/3/3.
 */
public class Table {

    private TableAdapter adapter;
    private String datas = null;
    private ListView listView;
    private MaterialDialog.Builder dialog;
    private AppCompatActivity appCompatActivity;


    List<TableItemFactory> allDatas;
    JSONObject jsonObject = new JSONObject();
    JSONArray jsonArray = null;

    public Table(AppCompatActivity activity, ListView listView,String tableJson) {
        this.listView = listView;
        this.appCompatActivity = activity;
        this.dialog = new MaterialDialog.Builder(activity)
                .content("请稍后...")
                .widgetColorRes(R.color.colorPrimary)
                .cancelable(false)
                .negativeColor(activity.getResources().getColor(R.color.colorPrimary))
                .progress(true, 100)
                .progressIndeterminateStyle(false);
        datas = tableJson;
    }

//    new CreateListViewTask(dialog,TableOneActivity.this,listView,adapter).execute(datas);

    public void drawTable(){
        new CreateListViewTask(dialog,appCompatActivity,listView,adapter).execute(datas);
    }


    public String getJSONStringOfTableValue(){

        jsonObject = ((TableAdapter)(listView.getAdapter())).getJsonObject();
       return jsonObject.toString();
    }


    public String getJSONArrStringOfTableValue(){
        allDatas = ((TableAdapter)(listView.getAdapter())).getAllItems();

        jsonArray = new JSONArray();
        for (int i=0; i<allDatas.size(); i++){
            int type =  ((TableItemFactory)(allDatas.get(i))).getLayoutType();
            boolean isJasonArrEmpty = true;
            if (type==100){
                TableItemExpandable tableItemExpandable = (TableItemExpandable)(allDatas.get(i));
                ActionExpandable ace = (ActionExpandable)tableItemExpandable.getItem();
                List<TableItemFactory> items = ((ActionExpandable) (((TableItemExpandable) (allDatas.get(i))).getItem())).getChildrens();
                JSONObject jsonObj = new JSONObject();
                for (int j=0; j<items.size(); j++){
                    EditTextBeanTableItem item = ((EditTextBeanTableItem) (((TableItemFactory) (items.get(j))).getItem()));
                    if (null != item.getValue() && !"".equals(item.getValue())){
                        isJasonArrEmpty = false;
                    }
                    try {
                        if (!isJasonArrEmpty) {
                            jsonObj.put(item.getSubmitKey(), item.getValue());
                        }
                        jsonObj.put("type",ace.getExpandId());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (!isJasonArrEmpty) {
                    jsonArray.put(jsonObj);
                }
            }


        }


        return jsonArray.toString();
    }
}
