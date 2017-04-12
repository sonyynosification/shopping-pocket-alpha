package com.crystal.tigers.s1.activities.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sonyynoss on 4/11/17.
 */

public class StoreOnItemClickListener implements OnItemClickListener {

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    protected Intent intent;
    protected ListView listView;
    private Context context;

    public StoreOnItemClickListener(Context context) {
        this.context = context;
    }

    public StoreOnItemClickListener(Context context, ListView listView) {
        this(context);
        this.listView = listView;
    }

    public StoreOnItemClickListener(Context context, ListView listView, Class activityClass ) {
        this(context, listView);
        this.intent = new Intent(context, activityClass);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Map<String,String> item = (HashMap<String,String>) adapterView.getItemAtPosition(i);
        intent.putExtra("storeId",item.get("store_id"));
        context.startActivity(intent);
    }
}
