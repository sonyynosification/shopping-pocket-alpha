package com.crystal.tigers.s1.webservices.responsehandlers;

import android.view.View;
import android.widget.TextView;

import com.crystal.tigers.s1.webservices.WebServiceInvoker;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by vtruong8 on 04/03/2017.
 */
public class UsersRetrieveAsyncHttpResponseHandler extends S1BaseAsyncHttpResponseHandler {
    public UsersRetrieveAsyncHttpResponseHandler(WebServiceInvoker wsInvoker) {
        super(wsInvoker);
    }

    public UsersRetrieveAsyncHttpResponseHandler(WebServiceInvoker wsInvoker,List<View> updateOnSuccessViews) {
        super(wsInvoker,updateOnSuccessViews);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        for (View v: updateOnSuccessViews) {
            if (v instanceof TextView) {
                ((TextView) v).setText(responseBody.toString());
            }
        }
    }
}
