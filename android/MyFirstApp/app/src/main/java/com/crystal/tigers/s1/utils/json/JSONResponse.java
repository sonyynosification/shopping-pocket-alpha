package com.crystal.tigers.s1.utils.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vtruong8 on 09/03/2017.
 */
public class JSONResponse {
    protected  String response;

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public String getResponse() {
        return response;
    }

    protected JSONObject jsonObject;

    public JSONResponse(String response) throws JSONException{
        this.response = response;
        jsonObject = new JSONObject(response);
    }

    public JSONArray getJSONResponseArray(String arrayName) throws JSONException {
        JSONArray res = jsonObject.getJSONArray(arrayName);
        return res;
    }
}
