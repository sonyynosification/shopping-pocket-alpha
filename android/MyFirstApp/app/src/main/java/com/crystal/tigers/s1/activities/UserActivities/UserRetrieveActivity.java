package com.crystal.tigers.s1.activities.UserActivities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.crystal.tigers.s1.R;
import com.crystal.tigers.s1.webservices.WebServiceInvoker;
import com.crystal.tigers.s1.webservices.responsehandlers.UsersRetrieveAsyncHttpResponseHandler;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

public class UserRetrieveActivity extends AppCompatActivity {
    RequestParams requestParams = new RequestParams();
    WebServiceInvoker wsInvoker;
    List<View> updateOnSuccessViews = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_retrieve);
    }

    public void onSearchUsers(View view) {
        TextView textViewUserResults = (TextView) findViewById(R.id.textView_user_results);
        requestParams.put("username",textViewUserResults.getText());
        String wsUrl = getResources().getString(R.string.WEBSERVICE_BASE_URL);
        wsUrl += "/users/retrieve";
        wsInvoker = new WebServiceInvoker(this,wsUrl);
        UsersRetrieveAsyncHttpResponseHandler usersRetrieveAsyncHttpResponseHandler = new UsersRetrieveAsyncHttpResponseHandler(wsInvoker,updateOnSuccessViews);
        wsInvoker.invokeWS(requestParams,usersRetrieveAsyncHttpResponseHandler);
    }
}
