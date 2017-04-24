package com.crystal.tigers.s1.webservices.responsehandlers;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.crystal.tigers.s1.R;
import com.crystal.tigers.s1.model.StoreModel;
import com.crystal.tigers.s1.utils.json.JSONResponse;
import com.crystal.tigers.s1.webservices.WebServiceInvoker;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by sonyynoss on 4/12/17.
 */

public class StoreDetailsViewAsyncHttpResponseHandler extends S1BaseAsyncHttpResponseHandler {
    public StoreDetailsViewAsyncHttpResponseHandler(WebServiceInvoker wsInvoker) {
        super(wsInvoker);
    }

    public StoreDetailsViewAsyncHttpResponseHandler(WebServiceInvoker wsInvoker, List<View> updateOnSuccessViews) {
        super(wsInvoker, updateOnSuccessViews);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        //TODO: maybe we need to auto-populate the views automatically based on returned attributes
        Activity currentActivity = wsInvoker.getActivity();
        //find the necessary views
        ImageView storeIconView = (ImageView) currentActivity.findViewById(R.id.imgView_store_icon);
        TextView storeNameView = (TextView) currentActivity.findViewById(R.id.textView_store_detail_name);
        TextView storeAddressView = (TextView) currentActivity.findViewById(R.id.textView_store_detail_address);
        TextView storeEmailView = (TextView) currentActivity.findViewById(R.id.textView_store_detail_email);

        ArrayList<StoreModel> storeModels = getResponseList(new String(responseBody));
        if (storeModels.size() >0) {
            StoreModel storeModel = storeModels.get(0);
            //set Icon
            //TODO: add image url
            Picasso.with(currentActivity.getApplicationContext())
                    .load("http://www.jrtstudio.com/sites/default/files/ico_android.png")
                    .into(storeIconView);

            //set textView
            storeNameView.setText(storeModel.getStoreName());
            storeAddressView.setText(storeModel.getAddress());
            storeEmailView.setText(storeModel.getEmail());

        } else {
            //TODO: throw error when no object is returned
        }
        onFinishHandlingResponse();
    }

    protected ArrayList<StoreModel> getResponseList(String responseList) {
        ArrayList<StoreModel> storeModels = new ArrayList<StoreModel>();
        try {
            JSONResponse jsonResponse = new JSONResponse(responseList);
            JSONArray jsonArray = jsonResponse.getJSONResponseArray("stores");
            for (int i = 0 ; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                StoreModel storeModel = new StoreModel();
                storeModel.setStoreName(obj.getString("storeName"));
                storeModel.setEmail(obj.getString("email"));
                storeModel.setAddress(obj.getString("address"));
                storeModels.add(storeModel);
            }}
        catch (JSONException e) {
            Toast.makeText(wsInvoker.getActivity().getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return storeModels;

    }
}
