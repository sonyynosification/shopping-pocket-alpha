package com.crystal.tigers.s1.webservices.responsehandlers;

import android.app.ProgressDialog;
import android.widget.Toast;

import com.crystal.tigers.s1.webservices.WebServiceInvoker;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by vtruong8 on 16/02/2017.
 */
public class AuthRegisterAsyncHttpResponseHandler extends AsyncHttpResponseHandler {
    //TODO: revisit this variable
    ProgressDialog wsInvokerProgressDialog = null;
    WebServiceInvoker wsInvoker;

    public AuthRegisterAsyncHttpResponseHandler(WebServiceInvoker wsInvoker) {
        super();
        this.wsInvokerProgressDialog = wsInvoker.getProgressDialog();
        this.wsInvoker = wsInvoker;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        //Hide progress dialog if any
        if (wsInvokerProgressDialog != null) {
            wsInvokerProgressDialog.hide();
        }
        System.out.println("Response: " + responseBody);

        String responseBodyString = new String(responseBody);
        try {
            JSONObject jsonObject = new JSONObject(responseBodyString);
            Toast.makeText(wsInvoker.getActivity().getApplicationContext(),
                    responseBodyString,
                    Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            //TODO: invalid JSON object
            //Show the JSON string
            Toast.makeText(wsInvoker.getActivity().getApplicationContext(),
                    "Error: " + responseBodyString,
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
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
