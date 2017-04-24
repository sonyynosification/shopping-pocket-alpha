package com.crystal.tigers.s1.webservices.responsehandlers;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.crystal.tigers.s1.R;
import com.crystal.tigers.s1.webservices.WebServiceInvoker;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by vtruong8 on 04/03/2017.
 */
public abstract class S1BaseAsyncHttpResponseHandler extends AsyncHttpResponseHandler {

    ProgressDialog wsInvokerProgressDialog = null;
    WebServiceInvoker wsInvoker;
    List<View> updateOnSuccessViews = new ArrayList<>();

    public S1BaseAsyncHttpResponseHandler(WebServiceInvoker wsInvoker) {
        super();
        this.wsInvokerProgressDialog = wsInvoker.getProgressDialog();
        this.wsInvoker = wsInvoker;
    }

    public S1BaseAsyncHttpResponseHandler(WebServiceInvoker wsInvoker,List<View> updateOnSuccessViews) {
        this(wsInvoker);
        //updateOnSuccessViews.stream().filter() --> Fuck this is not possible yet. Java8 and API min 24
        this.updateOnSuccessViews = updateOnSuccessViews;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        //blank for now
    }

    public void onFinishHandlingResponse() {
        this.wsInvoker.getProgressDialog().hide();
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        wsInvoker.getProgressDialog().hide();
        // When Http response code is '404'
        System.out.println("Status code: " + statusCode);
        if(statusCode == 404){
            Toast.makeText(wsInvoker.getActivity().getApplicationContext(),
                    "Requested resource not found",
                    Toast.LENGTH_LONG).show();
        }
        // When Http response code is '500'
        else if(statusCode == 500){
            Toast.makeText(wsInvoker.getActivity().getApplicationContext(),
                    "Something went wrong at server end",
                    Toast.LENGTH_LONG).show();
        }
        // When Http response code other than 404, 500
        else{
            Toast.makeText(wsInvoker.getActivity().getApplicationContext(),
                    "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]",
                    Toast.LENGTH_LONG).show();
        }
    }
}
