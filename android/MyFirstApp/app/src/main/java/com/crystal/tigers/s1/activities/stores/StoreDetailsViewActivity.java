package com.crystal.tigers.s1.activities.stores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.crystal.tigers.s1.R;
import com.crystal.tigers.s1.webservices.WebServiceInvoker;
import com.crystal.tigers.s1.webservices.responsehandlers.StoreDetailsViewAsyncHttpResponseHandler;
import com.crystal.tigers.s1.webservices.responsehandlers.StoreListNewAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;


public class StoreDetailsViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details_view);
        //TODO: get the store_id passed from clickListener, call approriate webservice
        Intent intent = getIntent();
        String storeId = intent.getStringExtra("storeId");

        //TODO: for now we just call the hardcoded url
        invokeWebService(storeId);

    }

    public void invokeWebService(String query) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("query",query);
        List<View> updateOnSuccessViews = new ArrayList<View>();
        //updateOnSuccessViews.add(searchResultListView);
        String wsStoreListUrl = getResources().getString(R.string.WEBSERVICE_TEST_URL) + "stores/list";
        WebServiceInvoker webServiceInvoker = new WebServiceInvoker(this,wsStoreListUrl);
        webServiceInvoker.invokeWS(requestParams,new StoreDetailsViewAsyncHttpResponseHandler(webServiceInvoker,updateOnSuccessViews));
    }

}
