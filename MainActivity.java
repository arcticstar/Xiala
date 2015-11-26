package com.auxgroup.xiala;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private SwipeRefreshLayout swipeRefreshLayout;
    private ListView lv;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        lv= (ListView) findViewById(R.id.lv);
        arrayList=new ArrayList<>();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new MyAsyncTask().execute();
            }
        });
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        lv.setAdapter(adapter);
    }

    private class MyAsyncTask extends AsyncTask{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected Object doInBackground(Object[] params) {
            return count++;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            arrayList.add(0,"刷新"+o+"次");
            adapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
