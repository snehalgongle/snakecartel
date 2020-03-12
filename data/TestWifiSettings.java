package com.altrosmart;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.altrosmart.ui.calibration.Calibration;
import com.altrosmart.ui.wifisettings.WifiSettings;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class TestWifiSettings {
    private static String notActivatedUserName="lokesh@colanonline.co";
    private static String notActivatedPassword="Lokesh@12";
    private static String wrongUserName="lokesh@colline.com";
    private static String wrongPassword="Amiseq@12";
    private static String password="Amiseq@1234";

    @Rule
    public ActivityTestRule<WifiSettings> mActivityRule =
            new ActivityTestRule<WifiSettings>(WifiSettings.class){
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
    public void clickOnSaveSuccess(){
        Log.e("@Test", "clickOnSaveSuccess: " );
        onView((withId(R.id.layout_for_wifi)))
                .perform(click());
        onView(withId(R.id.et_wifipsw))
                .perform(ViewActions.typeText(password));
        closeSoftKeyboard();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView((withId(R.id.tick)))
                .perform(click());
        intended(hasComponent(Calibration.class.getName()));
    }

    @Test
    public void clickOnSavePasswordNotFound(){
        Log.e("@Test", "clickOnSavePasswordNotFound: " );
        onView((withId(R.id.layout_for_wifi)))
                .perform(click());
        onView(withId(R.id.et_wifipsw))
                .perform(ViewActions.typeText(""));
        closeSoftKeyboard();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView((withId(R.id.tick)))
                .perform(click());
        onView(withText("Password is required")).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void clickOnSaveInvalid(){
        Log.e("@Test", "clickOnSaveInvalid: " );
        onView((withId(R.id.layout_for_wifi)))
                .perform(click());
        onView(withId(R.id.et_wifipsw))
                .perform(ViewActions.typeText(wrongPassword));
        closeSoftKeyboard();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView((withId(R.id.tick)))
                .perform(click());

        onView(withText("Invalid credentials."))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )));
    }
}
