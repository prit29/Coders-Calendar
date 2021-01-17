package com.noobsever.codingcontests.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.noobsever.codingcontests.R;
import com.noobsever.codingcontests.Utils.Constants;
import com.noobsever.codingcontests.Utils.Methods;
import com.schibsted.spain.parallaxlayerlayout.ParallaxLayerLayout;
import com.schibsted.spain.parallaxlayerlayout.SensorTranslationUpdater;

public class LayoutTwoActivity extends BaseActivity {

    ParallaxLayerLayout mParallaxLayout,mParallaxLayout1,mParallaxLayout2,mParallaxLayout3;
    SensorTranslationUpdater sensorTranslationUpdater,sensorTranslationUpdater1,sensorTranslationUpdater2,sensorTranslationUpdater3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout content = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_layout_two, content);

        mParallaxLayout = findViewById(R.id.ActivityTwoParallax);
        mParallaxLayout1 = findViewById(R.id.ActivityTwoParallax1);
        mParallaxLayout2 = findViewById(R.id.ActivityTwoParallax2);
        mParallaxLayout3 = findViewById(R.id.ActivityTwoParallax3);
        sensorTranslationUpdater = new SensorTranslationUpdater(this);
        sensorTranslationUpdater1 = new SensorTranslationUpdater(this);
        sensorTranslationUpdater2 = new SensorTranslationUpdater(this);
        sensorTranslationUpdater3 = new SensorTranslationUpdater(this);
        mParallaxLayout.setTranslationUpdater(sensorTranslationUpdater);
        mParallaxLayout1.setTranslationUpdater(sensorTranslationUpdater1);
        mParallaxLayout2.setTranslationUpdater(sensorTranslationUpdater2);
        mParallaxLayout3.setTranslationUpdater(sensorTranslationUpdater3);

        saveActivity();
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
                startActivity(new Intent(LayoutTwoActivity.this, LayoutOneActivity.class));
                finishAffinity();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorTranslationUpdater.registerSensorManager();
        sensorTranslationUpdater1.registerSensorManager();
        sensorTranslationUpdater2.registerSensorManager();
        sensorTranslationUpdater3.registerSensorManager();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorTranslationUpdater.unregisterSensorManager();
        sensorTranslationUpdater1.unregisterSensorManager();
        sensorTranslationUpdater2.unregisterSensorManager();
        sensorTranslationUpdater3.unregisterSensorManager();
    }
}
