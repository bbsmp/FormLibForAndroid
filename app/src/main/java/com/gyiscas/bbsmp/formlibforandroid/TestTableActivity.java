package com.gyiscas.bbsmp.formlibforandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;

import com.gyiscas.bbsmp.formlibraryforandroid.Table;
import com.gyiscas.bbsmp.formlibraryforandroid.Utils.Utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by qintianhao on 16/3/3.
 */
public class TestTableActivity extends AppCompatActivity {

    private ListView listView;
    Table tablel;
    String tableJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_table);

        listView = (ListView) findViewById(R.id.id_listview);

        try {

            InputStream in = getResources()
                    .getAssets().open("table1.json");
            tableJson = Utils.getStreamString(in);
            Log.d("TAG" , "TABLEjSON " +tableJson);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (null != tableJson){
            listView.setOnScrollListener(new MyScrollListener());
            tablel = new Table(TestTableActivity.this,listView,tableJson);
            tablel.drawTable();
        }


        ((Button)findViewById(R.id.btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.d("TAG", "jsonResult" + tablel.getJSONStringOfTableValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Log.d("TAG", "jsonArrResult" + tablel.getJSONArrStringOfTableValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



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
                View currentFocus = getCurrentFocus();
                if (currentFocus != null) {
                    currentFocus.clearFocus();
                }
            }
        }

    }
}
