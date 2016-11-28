package com.communistpartyconstruction.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.communistpartyconstruction.R;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{
    private RelativeLayout message,passwordChange,clearMemory,about;
    private Button logout,goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initUI();
    }

    private void initUI(){
        message = (RelativeLayout) findViewById(R.id.setting_message);
        passwordChange = (RelativeLayout) findViewById(R.id.setting_password);
        clearMemory = (RelativeLayout) findViewById(R.id.setting_clearMemory);
        about = (RelativeLayout) findViewById(R.id.setting_about);
        logout = (Button) findViewById(R.id.setting_logout);
        goBack = (Button) findViewById(R.id.setting_goBack);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.setting_message:
                break;
            case R.id.setting_password:
                break;
            case R.id.setting_clearMemory:
                break;
            case R.id.setting_about:
                break;
            case R.id.setting_logout:
                break;
            case R.id.setting_goBack:
                break;

            default:
                break;
        }
    }
}
