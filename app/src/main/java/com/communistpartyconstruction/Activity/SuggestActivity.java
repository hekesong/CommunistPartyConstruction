package com.communistpartyconstruction.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.communistpartyconstruction.R;

public class SuggestActivity extends AppCompatActivity implements View.OnClickListener{
    Button goBack,comfirm;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
        goBack = (Button) findViewById(R.id.suggest_goBack);
        comfirm = (Button) findViewById(R.id.suggest_button);
        editText = (EditText) findViewById(R.id.suggest_edittext);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.suggest_goBack:
                break;
            case R.id.suggest_button:
                break;
            case R.id.suggest_edittext:
                break;

            default:
                break;
        }
    }
}
