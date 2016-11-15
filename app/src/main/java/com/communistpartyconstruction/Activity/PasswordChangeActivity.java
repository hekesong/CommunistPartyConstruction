package com.communistpartyconstruction.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.communistpartyconstruction.R;

public class PasswordChangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);
        Button goBack = (Button)findViewById(R.id.passwordChange_goBack);
        Button confirm = (Button)findViewById(R.id.passwordChange_confirmButton);
        final EditText originPassword = (EditText)findViewById(R.id.passwordChange_originPassWord);
        final EditText newPassWord = (EditText)findViewById(R.id.passwordChange_newPassWord);
        final EditText repeatPassword = (EditText)findViewById(R.id.passwordChange_repeatPassWord);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String originString = originPassword.getText().toString();
                String newString = newPassWord.getText().toString();
                String repeatString = repeatPassword.getText().toString();
                
            }
        });
    }
}
