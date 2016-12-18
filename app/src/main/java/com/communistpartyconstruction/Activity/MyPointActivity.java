package com.communistpartyconstruction.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.communistpartyconstruction.R;

public class MyPointActivity extends AppCompatActivity {
    private TextView name,totalPoint,growth,done0,done1,done2,done3,done4;
    private ImageView hook0,hook1,hook2,hook3,hook4;
    private Button goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_point);
        initUI();

    }
    private void initUI(){
        name = (TextView) findViewById(R.id.myPoint_name);
        totalPoint = (TextView) findViewById(R.id.myPoint_totalPoint);
        growth = (TextView) findViewById(R.id.myPoint_growth);
        done0 = (TextView) findViewById(R.id.myPoint_done0);
        done1 = (TextView) findViewById(R.id.myPoint_done1);
        done2 = (TextView) findViewById(R.id.myPoint_done2);
        done3 = (TextView) findViewById(R.id.myPoint_done3);
        done4 = (TextView) findViewById(R.id.myPoint_done4);
        hook0 = (ImageView) findViewById(R.id.myPoint_hook0);
        hook1 = (ImageView) findViewById(R.id.myPoint_hook1);
        hook2 = (ImageView) findViewById(R.id.myPoint_hook2);
        hook3 = (ImageView) findViewById(R.id.myPoint_hook3);
        hook4 = (ImageView) findViewById(R.id.myPoint_hook4);
        goBack = (Button) findViewById(R.id.myPoint_goBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
