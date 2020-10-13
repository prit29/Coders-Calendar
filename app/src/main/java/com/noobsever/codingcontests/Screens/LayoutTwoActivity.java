package com.noobsever.codingcontests.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.FrameLayout;
import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.Utils.Constants;
import com.noobsever.codingcontests.Utils.Methods;

public class LayoutTwoActivity extends BaseActivity {

    boolean doubleBackPressExitOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout content = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_layout_two, content);

        saveActivity();
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

    //  Function to remember current activity.
    public void saveActivity() {
        Methods.setPreferences(this, Constants.LAYOUT_SWITCH_KEY, Constants.CURRENT_ACTIVITY, 2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.menu_layout:
                startActivity(new Intent(LayoutTwoActivity.this,LayoutOneActivity.class));
                finishAffinity();
                break;
            case R.id.menu_search:
                // add action
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
