package com.example.vtruong8.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.vtruong8.myfirstapp.utils.constants.SimpleConstants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this,DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(SimpleConstants.EXTRA_MESSAGE,message);
        startActivity(intent);
    }

    /**
     * Open the UserRegisterActivity
     * @param view
     */
    public void openUserRegisterView(View view) {
        Intent intent = new Intent(this,RegisterUserActivity.class);
        startActivity(intent);
    }
}
