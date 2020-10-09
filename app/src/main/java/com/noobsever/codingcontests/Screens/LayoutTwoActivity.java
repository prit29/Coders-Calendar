package com.noobsever.codingcontests.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;

import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.Utils.Methods;

public class LayoutTwoActivity extends BaseActivity {

    boolean doubleBackPressExitOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout content = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_layout_two, content);
    }
    @Override
    public void onBackPressed() {
        if(doubleBackPressExitOnce)
        {
            super.onBackPressed();
            return;
        }
        this.doubleBackPressExitOnce = true;
        Methods.showToast(this,"Press Back Again to Exit");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackPressExitOnce = false;
            }
        },2000);
    }
}
