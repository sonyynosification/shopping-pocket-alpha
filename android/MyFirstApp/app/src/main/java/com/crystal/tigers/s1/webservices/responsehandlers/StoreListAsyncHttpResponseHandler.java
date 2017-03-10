package com.crystal.tigers.s1.webservices.responsehandlers;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.crystal.tigers.s1.R;
import com.crystal.tigers.s1.model.StoreModel;
import com.crystal.tigers.s1.model.UserModel;
import com.crystal.tigers.s1.utils.json.JSONResponse;
import com.crystal.tigers.s1.webservices.WebServiceInvoker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by vtruong8 on 04/03/2017.
 */
public class StoreListAsyncHttpResponseHandler extends S1BaseAsyncHttpResponseHandler {
    public StoreListAsyncHttpResponseHandler(WebServiceInvoker wsInvoker) {
        super(wsInvoker);
    }

    public StoreListAsyncHttpResponseHandler(WebServiceInvoker wsInvoker, List<View> updateOnSuccessViews) {
        super(wsInvoker,updateOnSuccessViews);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)  {
        for (View v: updateOnSuccessViews) {
            if (v instanceof TextView) {
                ((TextView) v).setText(responseBody.toString());
            }

            if (v instanceof ListView) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(wsInvoker.getActivity(), R.layout.list_view_item);
                List<Map<String,String>> adapterData = new ArrayList<>();


                ArrayList<StoreModel> storeModels = getResponseList(new String(responseBody));
                String[] res = new String[storeModels.size()];
                for (int i = 0 ;i < storeModels.size(); i++) {
                    res[i] = storeModels.get(i).getStoreName();
                    Map<String,String> dataMap = new HashMap<>();
                    dataMap.put("storeName",storeModels.get(i).getStoreName());
                    dataMap.put("email",storeModels.get(i).getEmail());
                    dataMap.put("address",storeModels.get(i).getAddress());
                    adapterData.add(dataMap);
                }
                String[] fromColumns = {"storeName","email","address"};
                int[] toColumns = {R.id.textView_res_store_name,
                                    R.id.textView_res_store_email,
                                    R.id.textView_res_store_address};
                SimpleAdapter simpleAdapter = new SimpleAdapter(
                        wsInvoker.getActivity().getApplicationContext(),
                        adapterData,
                        R.layout.listview_store_item,
                        fromColumns,
                        toColumns
                );

                // update to ListView
                adapter.addAll(res);
                ((ListView) v).setAdapter(simpleAdapter);
            }
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
