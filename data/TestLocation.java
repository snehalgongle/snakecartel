package com.altrosmart;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.altrosmart.ui.location.LocationActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class TestLocation {


    @Rule
    public ActivityTestRule<LocationActivity> mActivityRule =
            new ActivityTestRule<LocationActivity>(LocationActivity.class){
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
    public void clickOnCurrentLocation(){
        Log.e("@Test", "clickOnCurrentLocation: ");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView((withId(R.id.altrocurrentlocation)))
                .perform(click());

    }

    @Test
    public void clickOnLockMyLocation(){
        Log.e("@Test", "clickOnLockMyLocation: " );
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView((withId(R.id.lockmylocation)))
                .perform(click());
    }

    @Test
    public void clickOnSave(){
        Log.e("@Test", "clickOnSave: " );
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView((withId(R.id.tick)))
                .perform(click());
    }
}
