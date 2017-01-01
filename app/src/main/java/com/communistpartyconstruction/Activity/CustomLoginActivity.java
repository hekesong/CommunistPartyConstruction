package com.communistpartyconstruction.Activity;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.communistpartyconstruction.Adapter.VideoInformationRecycleViewAdapter;
import com.communistpartyconstruction.Constant.Host;
import com.communistpartyconstruction.R;
import com.communistpartyconstruction.Support.HttpUtils;

import org.json.JSONObject;

public class CustomLoginActivity extends Activity {
    private EditText username,password;
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
        username = (EditText) findViewById(R.id.login_edittext_username);
        password = (EditText) findViewById(R.id.login_edittext_password);
        LoginAsyncTask task = new LoginAsyncTask(this,username.getText().toString(),password.getText().toString());
        task.execute();
    }
    class LoginAsyncTask extends AsyncTask<Void,Void,String> {
        Context context;
        String username,password;
        public LoginAsyncTask(Context context,String username,String password){
            this.context = context;
            this.username = username;
            this.password = password;
        }
        @Override
        protected String doInBackground(Void... voids) {
            String result = "";
            JSONObject jsonParam = new JSONObject();
            try{
                jsonParam.put("account", username);
                jsonParam.put("password", password);
                result = HttpUtils.HttpPost(context, Host.login,jsonParam);

            }catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("00",s);
            finish();

        }
    }
}
