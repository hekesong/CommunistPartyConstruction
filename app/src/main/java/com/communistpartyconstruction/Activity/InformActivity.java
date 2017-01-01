package com.communistpartyconstruction.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.communistpartyconstruction.Adapter.InteractiveRecycleViewAdapter;
import com.communistpartyconstruction.Constant.Host;
import com.communistpartyconstruction.JavaBean.SubmitApplicationJavaBean;
import com.communistpartyconstruction.R;
import com.communistpartyconstruction.Support.HttpUtils;

import org.json.JSONObject;

public class InformActivity extends AppCompatActivity {
    private Button confirmButton,goBack;
    private EditText name,studentID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);
        initUI(this);

    }

    private void initUI(final Context context){
        name = (EditText) findViewById(R.id.inform_name);
        studentID = (EditText) findViewById(R.id.inform_studentID);
        confirmButton = (Button) findViewById(R.id.inform_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InformAsyncTask task = new InformAsyncTask(context,name.getText().toString(),studentID.getText().toString());
                task.execute();
            }
        });
        goBack = (Button) findViewById(R.id.inform_goBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    class InformAsyncTask extends AsyncTask<Void,Void,String> {
        Context context;
        String Sname,Sid;
        public InformAsyncTask(Context context,String Sname,String Sid){
            this.context = context;
            this.Sname = Sname;
            this.Sid = Sid;
        }
        @Override
        protected String doInBackground(Void... voids) {
            String result = "";
            JSONObject jsonParam = new JSONObject();
            try{
                jsonParam.put("name", Sname);
                jsonParam.put("studentId", Sid);
                result = HttpUtils.HttpPost(context, Host.getInfo,jsonParam);
            }catch (Exception e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("00",s);
            Intent intent = new Intent();
            SubmitApplicationJavaBean bean = HttpUtils.getInformJavaBean(s,context);
            intent.putExtra("SubmitApplicationJavaBean", bean);
            intent.setClass(context, SubmitApplicationActivity.class);
            context.startActivity(intent);
        }
    }
}
