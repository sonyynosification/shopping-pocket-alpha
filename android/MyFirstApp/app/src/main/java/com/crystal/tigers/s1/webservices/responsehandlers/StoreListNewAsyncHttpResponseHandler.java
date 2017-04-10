package com.crystal.tigers.s1.webservices.responsehandlers;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.crystal.tigers.s1.R;
import com.crystal.tigers.s1.model.StoreModel;
import com.crystal.tigers.s1.utils.adapters.ListItemAdapter;
import com.crystal.tigers.s1.webservices.WebServiceInvoker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by sonyynoss on 4/1/17.
 */

public class StoreListNewAsyncHttpResponseHandler extends StoreListAsyncHttpResponseHandler {

    public StoreListNewAsyncHttpResponseHandler(WebServiceInvoker wsInvoker) {
        super(wsInvoker);
    }

    public StoreListNewAsyncHttpResponseHandler(WebServiceInvoker wsInvoker, List<View> updateOnSuccessViews) {
        super(wsInvoker, updateOnSuccessViews);
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)  {
        for (View v: updateOnSuccessViews) {
            if (v instanceof TextView) {
                ((TextView) v).setText(responseBody.toString());
            }

            if (v instanceof ListView) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(wsInvoker.getActivity(), R.layout.listview_store_item2);
                List<Map<String,String>> adapterData = new ArrayList<>();


                ArrayList<StoreModel> storeModels = getResponseList(new String(responseBody));
                String[] res = new String[storeModels.size()];
                for (int i = 0 ;i < storeModels.size(); i++) {
                    res[i] = storeModels.get(i).getStoreName();
                    Map<String,String> dataMap = new HashMap<>();
                    dataMap.put("storeName",storeModels.get(i).getStoreName());
                    //dataMap.put("email",storeModels.get(i).getEmail());
                    dataMap.put("address",storeModels.get(i).getAddress());
                    dataMap.put("icon","http://www.jrtstudio.com/sites/default/files/ico_android.png");
                    adapterData.add(dataMap);
                }
                String[] fromColumns = {"icon","storeName","address"};
                int[] toColumns = {
                        R.id.imgView_store_icon,
                        R.id.textView_res_store_name,
                        R.id.textView_res_store_address};
//                SimpleAdapter simpleAdapter = new SimpleAdapter(
//                        wsInvoker.getActivity().getApplicationContext(),
//                        adapterData,
//                        R.layout.listview_store_item2,
//                        fromColumns,
//                        toColumns
//                );
                ListItemAdapter simpleAdapter = new ListItemAdapter(
                        wsInvoker.getActivity().getApplicationContext(),
                        adapterData,
                        R.layout.listview_store_item2,
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
}
