package com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutImplements.ComplecImplements;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.gyiscas.bbsmp.formlibraryforandroid.Config.LayoutConfig;
import com.gyiscas.bbsmp.formlibraryforandroid.R;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableAdapter;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableItemFactory;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.EditTextBeanTableItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.BasicLayoutBeans.TextLableBeanTableItem;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutBeans.TableAction.ActionExpandable;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutImplements.BasicImplements.TableItemEditText;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableLayoutImplements.BasicImplements.TableItemLableText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by bbsmp on 15/12/9.
 *  可变的
 */
public class TableItemExpandable implements TableItemFactory {

    private JSONObject jsonObject;
    private JSONArray jsonArray;
    private ActionExpandable actionExpandable;
    private Context mContext;
    public TableItemExpandable(ActionExpandable aeb) {
        this.actionExpandable = aeb;
    }
    @Override
    public int getLayoutType() {
        return LayoutConfig.ACTION_EXPENDABLE;
    }
    @Override
    public int getLayout() {
        return R.layout.layout_table_item_expandable;
    }
    @Override
    public Object getItem() {
        return actionExpandable;
    }
    @Override
    public void clearnItemValue(TableItemFactory tableItemFactory) {
        ArrayList<TableItemFactory> tableItemFactories= ((ActionExpandable) (tableItemFactory.getItem())).getChildrens();
        for (int i=0; i<tableItemFactories.size(); i++){
            tableItemFactories.get(i).clearnItemValue(tableItemFactories.get(i));
        }
    }
    @Override
    public Object getNewItem(boolean cleanValue) {
        return getNewActionExpandable(cleanValue);
    }
    TableAdapter listViewAdapter;
    ListView listview;
    MaterialDialog dialog;
    ListView parentListView;
    private ActionExpandable currentAE;
    @Override
    public View getView(final Context context,  View convertView,ViewGroup parent, LayoutInflater inflater, final int position, final TableAdapter tableAdapter,JSONObject jsonObj ) {
        parentListView = (ListView) parent;
        jsonObject = jsonObj;

        mContext = context;
        convertView = inflater.inflate(getLayout(),null);
        Button textBtn = (Button) convertView.findViewById(R.id.id_dialog_title);
        ImageButton imageButton = (ImageButton) convertView.findViewById(R.id.id_edit_imgbtn);
        textBtn.setText(actionExpandable.getName());
        if (actionExpandable.isLastOne()){
            imageButton.setBackgroundResource(R.mipmap.add);
        }else {
            imageButton.setBackgroundResource(R.mipmap.jian);
        }
        textBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new MaterialDialog.Builder(context)
                        .customView(R.layout.layout_table_item_expandable_dialog,true)
                        .cancelable(false).positiveText("确定")
                        .title("")
                        .negativeText("取消").build();
                currentAE = getNewActionExpandable(false);
                listview = (ListView) dialog.getCustomView().findViewById(R.id.id_dialog_listView);
                listview.setFocusable(true);
                listViewAdapter = new TableAdapter(context,currentAE.getChildrens());
                listview.setAdapter(listViewAdapter);
                listview.setOnScrollListener(new MyScrollListener());
                dialog.show();
                listViewAdapter.notifyDataSetChanged();
                dialog.getBuilder().callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        listViewAdapter.notifyDataSetChanged();
                        actionExpandable = currentAE;
                        tableAdapter.notifyDataSetChanged();
                    }
                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        super.onNegative(dialog);
                        dialog.dismiss();
                        tableAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actionExpandable.isLastOne()) {
                    ActionExpandable tempAE = getNewActionExpandable(true);
                    tableAdapter.getAllItems().add(position + 1, new TableItemExpandable(tempAE));
                    ((ActionExpandable)(tableAdapter.getAllItems().get(position).getItem())).setIsLastOne(false);
                }else {
                    tableAdapter.getAllItems().remove(position);
                }
                tableAdapter.notifyDataSetChanged();
            }
        });
        return convertView;
    }
    public ActionExpandable getNewActionExpandable(boolean ifCleanValues){
        ActionExpandable tempAE = new ActionExpandable();
        tempAE.setId(actionExpandable.getId());
        tempAE.setValue(actionExpandable.getValue());
        tempAE.setType(actionExpandable.getType());
        tempAE.setName(actionExpandable.getName());
        tempAE.setSubmitKey(actionExpandable.getSubmitKey());
        tempAE.setIsLastOne(actionExpandable.isLastOne());
        tempAE.setExpandId(actionExpandable.getExpandId());
        ArrayList<TableItemFactory> tempTIF = new ArrayList<TableItemFactory>();
        for (int i=0; i<actionExpandable.getChildrens().size(); i++){
            TableItemFactory t = actionExpandable.getChildrens().get(i);
            switch (t.getLayoutType()){
                case 1:
                    TableItemEditText tableItemEditText = new TableItemEditText((EditTextBeanTableItem)(t.getNewItem(ifCleanValues)));
                    tempTIF.add(tableItemEditText);
                    break;
                case 2:
                    TableItemLableText tableItemLableText =  new TableItemLableText((TextLableBeanTableItem)(t.getItem()));
                    tempTIF.add(tableItemLableText);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
            }
        }
        tempAE.setChildrens(tempTIF);
        return  tempAE;
    }

    protected class MyScrollListener implements AbsListView.OnScrollListener {

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
            // do nothing
        }

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (SCROLL_STATE_TOUCH_SCROLL == scrollState) {
                View currentFocus = ((AppCompatActivity)(mContext)).getCurrentFocus();
                if (currentFocus != null) {
                    currentFocus.clearFocus();
                }
            }
        }

    }
}
