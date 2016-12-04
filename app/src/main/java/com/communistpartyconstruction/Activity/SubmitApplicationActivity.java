package com.communistpartyconstruction.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.communistpartyconstruction.R;

public class SubmitApplicationActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText name,gender,racial,birthday,birthPlace,nativePlace,education,work,school,idCard,presentPlace,special;
    private Button goBack,submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_application);
        initUI();
    }

    private void initUI(){
        name = (EditText) findViewById(R.id.submitApplication_name);
        gender = (EditText) findViewById(R.id.submitApplication_gender);
        racial = (EditText) findViewById(R.id.submitApplication_racial);
        birthday = (EditText) findViewById(R.id.submitApplication_birthday);
        birthPlace = (EditText) findViewById(R.id.submitApplication_birthPlace);
        nativePlace = (EditText) findViewById(R.id.submitApplication_nativePlace);
        education = (EditText) findViewById(R.id.submitApplication_education);
        work = (EditText) findViewById(R.id.submitApplication_work);
        school = (EditText) findViewById(R.id.submitApplication_school);
        idCard = (EditText) findViewById(R.id.submitApplication_id);
        presentPlace = (EditText) findViewById(R.id.submitApplication_presentPlace);
        special = (EditText) findViewById(R.id.submitApplication_special);

        goBack = (Button) findViewById(R.id.submitApplication_goBack);
        goBack.setOnClickListener(this);
        submit = (Button) findViewById(R.id.submitApplication_submitButton);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.submitApplication_goBack:
                break;
            case R.id.submitApplication_submitButton:
                break;

            default:
                break;
        }
    }
}
