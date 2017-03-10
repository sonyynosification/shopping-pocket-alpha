package com.crystal.tigers.s1.activities.UserActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.crystal.tigers.s1.R;
import com.crystal.tigers.s1.webservices.WebServiceInvoker;
import com.crystal.tigers.s1.webservices.responsehandlers.UsersListAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

public class UserRetrieveActivity extends AppCompatActivity {
    RequestParams requestParams = new RequestParams();
    WebServiceInvoker wsInvoker;
    List<View> updateOnSuccessViews = new ArrayList<View>();
    String searchQuery;
    ListView searchResultListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_retrieve);
        searchResultListView = (ListView) findViewById(R.id.listView_user_searchResults);
    }

    public void onSearchUsers(View view) {
        //TextView textViewUserResults = (TextView) findViewById(R.id.textView_user_results);
        //requestParams.put("username",textViewUserResults.getText());
        EditText searchEditText = (EditText) findViewById(R.id.edittext_user_filter);
        searchQuery = searchEditText.getText().toString();
//        String wsUrl = getResources().getString(R.string.WEBSERVICE_BASE_URL);
//        wsUrl += "users/list";
//        wsInvoker = new WebServiceInvoker(this,wsUrl);
//        UsersListAsyncHttpResponseHandler usersRetrieveAsyncHttpResponseHandler = new UsersListAsyncHttpResponseHandler(wsInvoker,updateOnSuccessViews);
        invokeWebService();
    }

    public void invokeWebService() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("query",searchQuery);
        List<View> updateOnSuccessViews = new ArrayList<View>();
        updateOnSuccessViews.add(searchResultListView);
        String wsUserListUrl = getResources().getString(R.string.WEBSERVICE_TEST_URL) + "users/list";
        WebServiceInvoker webServiceInvoker = new WebServiceInvoker(this,wsUserListUrl);
        webServiceInvoker.invokeWS(requestParams,new UsersListAsyncHttpResponseHandler(webServiceInvoker,updateOnSuccessViews));
    }
}
