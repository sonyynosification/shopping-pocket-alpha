package com.crystal.tigers.s1.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.crystal.tigers.s1.R;
import com.crystal.tigers.s1.webservices.WebServiceInvoker;
import com.crystal.tigers.s1.webservices.responsehandlers.StoreListAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

public class SearchStoreActivity extends S1BaseActivity {
    String[] categories = {"Clothes","Accessories","Shoes","Jewelry","Household Electronics","Mobile phones"};
    ArrayAdapter<String> categoriesArrayAdapter;
//    SimpleCursorAdapter cursorAdapter;
    ListView searchResultListView;

    String searchQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_store);
        categoriesArrayAdapter = new ArrayAdapter<String>(this,R.layout.list_view_item,categories);



    }

    public void initialize() {
        AutoCompleteTextView autoCompleteCategory = (AutoCompleteTextView) findViewById(R.id.autoComplete_category);
        autoCompleteCategory.setAdapter(categoriesArrayAdapter);

        ListView categoryListView = (ListView) findViewById(R.id.listView_categoryList);
        categoryListView.setAdapter(categoriesArrayAdapter);
    }

    /**
     * Search a store based on specific conditions
     * Send a request to <webservice-url>/store/search
     * @param view
     */
    public void onSearchStore(View view) {
        EditText searchEditText = (EditText) findViewById(R.id.edittext_search_store);
        searchQuery = searchEditText.getText().toString();
        searchResultListView = (ListView) findViewById(R.id.listView_searchResults);
//        ArrayAdapter<String> searchResultArrayAdapter = new ArrayAdapter<String>(this,R.layout.list_view_item);
//
//        String[] searchResults = new String[categories.length];
//        for (String c: categories) {
//            int i = 0;
//            if (c.contains(searchQuery)) {
//                searchResults[i++] = c;
//                searchResultArrayAdapter.add(c);
//            }
//        }
//
//        searchResultListView.setAdapter(searchResultArrayAdapter);
        invokeWebService();
    }

    public void invokeWebService() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("query",searchQuery);
        List<View> updateOnSuccessViews = new ArrayList<View>();
        updateOnSuccessViews.add(searchResultListView);
        String wsStoreListUrl = getResources().getString(R.string.WEBSERVICE_TEST_URL) + "stores/list";
        WebServiceInvoker webServiceInvoker = new WebServiceInvoker(this,wsStoreListUrl);
        webServiceInvoker.invokeWS(requestParams,new StoreListAsyncHttpResponseHandler(webServiceInvoker,updateOnSuccessViews));
    }


}
