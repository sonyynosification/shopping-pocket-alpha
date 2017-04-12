package com.crystal.tigers.s1.activities.stores;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.crystal.tigers.s1.R;
import com.crystal.tigers.s1.activities.listener.StoreOnItemClickListener;
import com.crystal.tigers.s1.webservices.WebServiceInvoker;
import com.crystal.tigers.s1.webservices.responsehandlers.StoreListAsyncHttpResponseHandler;
import com.crystal.tigers.s1.webservices.responsehandlers.StoreListNewAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class SearchStoreNew extends AppCompatActivity {
    protected  String searchQuery;
    protected ListView searchResultListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_store_new);
    }


    public void onSearchStore(View view) {
        EditText searchText = (EditText) findViewById(R.id.edittext_search_store);
        String searchQuery = searchText.getText().toString();

        searchResultListView = (ListView) findViewById(R.id.listView_searchResults);
        StoreOnItemClickListener itemClickListener = new StoreOnItemClickListener(this,searchResultListView,
                StoreDetailsViewActivity.class);
        searchResultListView.setOnItemClickListener(itemClickListener);

//        Uri imgUri = Uri.parse("http://www.jrtstudio.com/sites/default/files/ico_android.png");
//        ImageView v = (ImageView) findViewById(R.id.testImg);
//        Picasso.with(this)
//                .load(imgUri)
//                .into(v);
//        v.setImageURI(null);
//        v.setImageURI(imgUri);

        invokeWebService();
    }

    public void invokeWebService() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("query",searchQuery);
        List<View> updateOnSuccessViews = new ArrayList<View>();
        updateOnSuccessViews.add(searchResultListView);
        String wsStoreListUrl = getResources().getString(R.string.WEBSERVICE_TEST_URL) + "stores/list";
        WebServiceInvoker webServiceInvoker = new WebServiceInvoker(this,wsStoreListUrl);
        webServiceInvoker.invokeWS(requestParams,new StoreListNewAsyncHttpResponseHandler(webServiceInvoker,updateOnSuccessViews));
    }
}
