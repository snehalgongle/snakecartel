package com.altrosmart;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.altrosmart.ui.blescanning.BleScanning;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class TestBleScanning {


    @Rule
    public ActivityTestRule<BleScanning> mActivityRule =
            new ActivityTestRule<BleScanning>(BleScanning.class){
                @Override
                protected void afterActivityLaunched() {
                    Intents.init();
                    super.afterActivityLaunched();
                }

                @Override
                protected void afterActivityFinished() {
                    super.afterActivityFinished();
                    Intents.release();
                }
            };

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void clickOnScan(){
        Log.e("@Test", "clickOnScan: ");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView((withId(R.id.altroscanninganimation)))
                .perform(click());

    }

}
