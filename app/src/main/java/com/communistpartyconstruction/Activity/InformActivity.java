package com.communistpartyconstruction.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.communistpartyconstruction.R;

public class InformActivity extends AppCompatActivity {
    private Button confirmButton;
    private EditText name,studentID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);
        initUI();
    }

    private void initUI(){
        name = (EditText) findViewById(R.id.inform_name);
        studentID = (EditText) findViewById(R.id.inform_studentID);
        confirmButton = (Button) findViewById(R.id.inform_button);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
