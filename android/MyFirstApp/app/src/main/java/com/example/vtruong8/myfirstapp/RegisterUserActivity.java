package com.example.vtruong8.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vtruong8.myfirstapp.webservices.WebServiceInvoker;
import com.example.vtruong8.myfirstapp.webservices.responsehandlers.AuthRegisterAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class RegisterUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        //Toast.makeText(getApplicationContext(),)
    }

    public void onRegiserUser(View view) {
        EditText txtUsername = (EditText) findViewById(R.id.edittext_register_username);
        EditText txtPassword = (EditText) findViewById(R.id.edittext_register_password);

        RequestParams params = new RequestParams();
        params.put("username",txtUsername.getText().toString());
        params.put("password",txtPassword.getText().toString());

        //WebServiceInvoker wsInvoker = new WebServiceInvoker(this,"http://20.203.156.184:8088/shopws/register/doregister");
        WebServiceInvoker wsInvoker = new WebServiceInvoker(this,"http://192.168.214.238:8088/shopws/register/doregister");

        AuthRegisterAsyncHttpResponseHandler authRegisterAsyncHttpResponseHandler = new AuthRegisterAsyncHttpResponseHandler(wsInvoker);
        try {
            wsInvoker.invokeWS(params, authRegisterAsyncHttpResponseHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //new WebServiceInvoker(this,"http://localhost:8088/shopws/register/doregister",params,)
    }
}
