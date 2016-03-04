package com.gyiscas.bbsmp.formlibraryforandroid.AsycTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableAdapter;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableItemFactory;
import com.gyiscas.bbsmp.formlibraryforandroid.Tables.TableParser.TableParser;

import java.util.ArrayList;

/**
 * Created by bbsmp on 15/12/12.
 */
public class CreateListViewTask extends AsyncTask<String, Void, ArrayList<TableItemFactory>> {

    private MaterialDialog progress;
    private Context context;
    private ListView listView;
    private TableAdapter tableAdapter;

    public CreateListViewTask(MaterialDialog.Builder dialog, Context context, ListView listView, TableAdapter tableAdapter) {
        this.progress = dialog.show();
        this.context = context;
        this.listView = listView;
        this.tableAdapter = tableAdapter;
    }


    @Override
    protected ArrayList<TableItemFactory> doInBackground(String... params) {
        ArrayList<TableItemFactory> tableItemFactories = new ArrayList<>();
        try {
            Log.d("TAG", "带解析！！==>" + params[0]);
            tableItemFactories = new TableParser().parseTable(context, params[0]);

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("TAG","解析出错！！");
        }
        return tableItemFactories;
    }

    @Override
    protected void onPostExecute(ArrayList<TableItemFactory> tableItemFactories) {
        super.onPostExecute(tableItemFactories);
        tableAdapter = new TableAdapter(context, tableItemFactories);
        listView.setAdapter(tableAdapter);
        if (progress != null) progress.dismiss();

    }

}
