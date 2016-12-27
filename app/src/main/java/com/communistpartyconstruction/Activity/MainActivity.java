package com.communistpartyconstruction.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.communistpartyconstruction.Fragment.HomeFragment;
import com.communistpartyconstruction.Fragment.InteractiveFragment;
import com.communistpartyconstruction.Fragment.MeFragment;
import com.communistpartyconstruction.R;

public class MainActivity extends Activity {
    private RadioButton rb_home,rb_interactive,rb_me;
    private Mylisten mylisten;
    private HomeFragment homeFragment ;
    private InteractiveFragment interactiveFragment;
    private MeFragment meFragment;
    private android.app.FragmentTransaction begintTransaction;
    private android.app.FragmentManager fragmentManager;
    private long time = System.currentTimeMillis();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mylisten = new Mylisten();
        homeFragment = new HomeFragment();
        interactiveFragment = new InteractiveFragment();
        meFragment = new MeFragment();
        fragmentManager = getFragmentManager();
        begintTransaction = fragmentManager.beginTransaction();
        begintTransaction.replace(R.id.mainactivity_fragment,homeFragment);
        begintTransaction.commit();
        initView();
    }

    private void initView() {
        rb_home = (RadioButton) findViewById(R.id.mainactivity_rb_home);
        rb_home.setOnClickListener(mylisten);
        rb_interactive = (RadioButton) findViewById(R.id.mainactivity_rb_interactive);
        rb_interactive.setOnClickListener(mylisten);
        rb_me = (RadioButton) findViewById(R.id.mainactivity_rb_me);
        rb_me.setOnClickListener(mylisten);
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - time > 2000){
            Toast.makeText(MainActivity.this,R.string.exit_program,Toast.LENGTH_SHORT).show();
            time = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }

    }

    private class Mylisten implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.mainactivity_rb_home:
                    getFragmentManager().beginTransaction().replace(R.id.mainactivity_fragment,homeFragment).commit();
                    break;
                case R.id.mainactivity_rb_interactive:
                    getFragmentManager().beginTransaction().replace(R.id.mainactivity_fragment,interactiveFragment).commit();
                    break;
                case R.id.mainactivity_rb_me:
                    getFragmentManager().beginTransaction().replace(R.id.mainactivity_fragment,meFragment).commit();
                    break;
                default:
                    break;
            }
        }
    }
}
