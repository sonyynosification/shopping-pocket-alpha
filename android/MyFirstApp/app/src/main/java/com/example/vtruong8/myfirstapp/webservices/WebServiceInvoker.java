package com.example.vtruong8.myfirstapp.webservices;

import android.app.Activity;
import android.app.ProgressDialog;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

/**
 * Created by vtruong8 on 16/02/2017.
 */
public class WebServiceInvoker {
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    protected Activity activity;

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    public void setProgressDialog(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
    }

    public String getWsPath() {
        return wsPath;
    }

    public void setWsPath(String wsPath) {
        this.wsPath = wsPath;
    }

    public AsyncHttpClient getClient() {
        return client;
    }

    public void setClient(AsyncHttpClient client) {
        this.client = client;
    }

    protected ProgressDialog progressDialog;
    protected String wsPath;
    protected AsyncHttpClient client;

    public WebServiceInvoker(Activity activity) {
        this.activity = activity;
        progressDialog = new ProgressDialog(activity);
    }

    public WebServiceInvoker(Activity activity, String wsPath) {
        this(activity);
        this.wsPath = wsPath;
    }

    public void invokeWS(RequestParams requestParams, AsyncHttpResponseHandler responseHandler) {
        client = new AsyncHttpClient();
        progressDialog.show();
        client.get(wsPath, requestParams,responseHandler);
    }
}
