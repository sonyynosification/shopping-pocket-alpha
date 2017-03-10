package com.crystal.tigers.s1.webservices.responsehandlers;

import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.crystal.tigers.s1.R;
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
public class UsersListAsyncHttpResponseHandler extends S1BaseAsyncHttpResponseHandler {
    public UsersListAsyncHttpResponseHandler(WebServiceInvoker wsInvoker) {
        super(wsInvoker);
    }

    public UsersListAsyncHttpResponseHandler(WebServiceInvoker wsInvoker, List<View> updateOnSuccessViews) {
        super(wsInvoker,updateOnSuccessViews);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        for (View v : updateOnSuccessViews) {
            if (v instanceof TextView) {
                ((TextView) v).setText(responseBody.toString());
            }

            if (v instanceof ListView) {
                List<Map<String, String>> adapterData = new ArrayList<>();


                ArrayList<UserModel> userModels = getResponseList(new String(responseBody));
                String[] res = new String[userModels.size()];
                for (int i = 0; i < userModels.size(); i++) {
                    res[i] = userModels.get(i).getUserName();
                    Map<String, String> dataMap = new HashMap<>();
                    dataMap.put("userName", userModels.get(i).getUserName());
                    dataMap.put("email", userModels.get(i).getEmail());
                    dataMap.put("address", userModels.get(i).getAddress());
                    adapterData.add(dataMap);
                }
                String[] fromColumns = {"userName", "email", "address"};
                int[] toColumns = {R.id.textView_res_user_name,
                        R.id.textView_res_user_email,
                        R.id.textView_res_user_address};
                SimpleAdapter simpleAdapter = new SimpleAdapter(
                        wsInvoker.getActivity().getApplicationContext(),
                        adapterData,
                        R.layout.listview_user_item,
                        fromColumns,
                        toColumns
                );

                // update to ListView
                ((ListView) v).setAdapter(simpleAdapter);
            }
        }

        onFinishHandlingResponse();
    }

    protected ArrayList<UserModel> getResponseList(String responseList) {
        ArrayList<UserModel> userModels = new ArrayList<UserModel>();
        try {
            JSONResponse jsonResponse = new JSONResponse(responseList);
            JSONArray jsonArray = jsonResponse.getJSONResponseArray("users");
            for (int i = 0 ; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                UserModel userModel = new UserModel();
                userModel.setUserName(obj.getString("userName"));
                userModel.setEmail(obj.getString("email"));
                userModel.setAddress(obj.getString("address"));
                userModels.add(userModel);
            }}
        catch (JSONException e) {
            Toast.makeText(wsInvoker.getActivity().getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return userModels;

    }
}
