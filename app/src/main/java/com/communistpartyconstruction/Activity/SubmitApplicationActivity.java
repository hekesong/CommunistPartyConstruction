package com.communistpartyconstruction.Activity;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.communistpartyconstruction.Constant.Host;
import com.communistpartyconstruction.R;
import com.communistpartyconstruction.Support.HttpUtils;
import com.communistpartyconstruction.JavaBean.SubmitApplicationJavaBean;

import org.json.JSONObject;

public class SubmitApplicationActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText name,gender,racial,birthday,birthPlace,nativePlace,education,work,school,idCard,presentPlace,special;
    private Button goBack,submit;
    private SubmitApplicationJavaBean submitApplicationJavaBean;

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
                finish();
                break;
            case R.id.submitApplication_submitButton:
                submitApplicationJavaBean = new SubmitApplicationJavaBean();
                submitApplicationJavaBean.setName(name.getText().toString());
                submitApplicationJavaBean.setGender(gender.getText().toString());
                submitApplicationJavaBean.setRacial(racial.getText().toString());
                submitApplicationJavaBean.setBirthday(birthday.getText().toString());
                submitApplicationJavaBean.setBirthPlace(birthPlace.getText().toString());
                submitApplicationJavaBean.setNativePlace(nativePlace.getText().toString());
                submitApplicationJavaBean.setEducation(education.getText().toString());
                submitApplicationJavaBean.setWork(work.getText().toString());
                submitApplicationJavaBean.setSchool(school.getText().toString());
                submitApplicationJavaBean.setIdCard(idCard.getText().toString());
                submitApplicationJavaBean.setPresentPlace(presentPlace.getText().toString());
                submitApplicationJavaBean.setSpecial(special.getText().toString());
                SubmitAsyncTask task = new SubmitAsyncTask(this,submitApplicationJavaBean);
                task.execute();
                break;

            default:
                break;
        }
    }

    class SubmitAsyncTask extends AsyncTask<Void,Void,String> {
        Context context;
        SubmitApplicationJavaBean javaBean;
        public SubmitAsyncTask(Context context,SubmitApplicationJavaBean javaBean){
            this.context = context;
            this.javaBean = javaBean;
        }
        @Override
        protected String doInBackground(Void... voids) {
            String result = "";
            JSONObject jsonParam = new JSONObject();
            try{
                jsonParam.put("name", javaBean.getName());
                jsonParam.put("sex", javaBean.getGender());
                jsonParam.put("nation", javaBean.getBirthPlace());
                jsonParam.put("dateOfBirth", System.currentTimeMillis());
                jsonParam.put("nativePlace", javaBean.getNativePlace());
                jsonParam.put("placeOfBirth", System.currentTimeMillis());
                jsonParam.put("educationBackground", javaBean.getEducation());
                jsonParam.put("schoolOrTitle", javaBean.getSchool());
                jsonParam.put("workCondition", javaBean.getWork());
                jsonParam.put("livingPlace", javaBean.getPresentPlace());
                jsonParam.put("idNumber", javaBean.getIdCard());
                jsonParam.put("specialSkill", javaBean.getSpecial());

                result = HttpUtils.HttpPost(context, Host.submitApplication,jsonParam);
            }catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.e("00",s);

        }
    }
}
