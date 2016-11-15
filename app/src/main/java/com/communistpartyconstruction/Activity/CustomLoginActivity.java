package com.communistpartyconstruction.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.communistpartyconstruction.R;

public class CustomLoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_login);
        Button loginButton = (Button)findViewById(R.id.login_loginButton);
        TextView forgetPassword = (TextView)findViewById(R.id.login_forgetPassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "登录",
                        Toast.LENGTH_SHORT).show();
            }
        });
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "忘记密码",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}
