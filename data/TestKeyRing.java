package com.altrosmart;

import android.content.Intent;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.web.sugar.Web;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.altrosmart.ui.keyRing.KeyRingActivity;
import com.altrosmart.ui.keyRing.appSettings.AppSettingsActivity;
import com.altrosmart.ui.keyRing.updatePassword.UpdatePasswordActivity;
import com.altrosmart.ui.license.LicenceAgreementActivity;
import com.altrosmart.ui.login.AltroLoginActvity;
import com.altrosmart.ui.registration.newAccount.AltroNewAccountActitvity;
import com.altrosmart.utils.AppConstants;
import com.altrosmart.utils.Singleton;
import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static android.support.test.espresso.web.model.Atoms.getCurrentUrl;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class TestKeyRing  {

    @Rule
    public ActivityTestRule<KeyRingActivity> mActivityRule =
            new ActivityTestRule<KeyRingActivity>(KeyRingActivity.class){
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
    public void setUp() {
       // Web.onWebView().forceJavascriptEnabled();
    }

    @Test
    public void clickOnNavigationMenuKeyRing() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Open Drawer to click on navigation.
        onView(withContentDescription(R.string.navigation_drawer_open))
                .perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.menu_key_ring))
                .perform(click());

        intended(hasComponent(KeyRingActivity.class.getName()));
    }

    @Test
    public void clickCheckNotification() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Open Drawer to click on navigation.
        onView(withContentDescription(R.string.navigation_drawer_open))
                .perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.menu_notification_switch)).check(matches(isChecked())).perform(scrollTo(), click());
    }

    @Test
    public void clickUnCheckNotification() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Open Drawer to click on navigation.
        onView(withContentDescription(R.string.navigation_drawer_open))
                .perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.menu_notification_switch)).check(matches(isNotChecked())).perform(scrollTo(), click());
    }

    @Test
    public void clickOnProfile() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Open Drawer to click on navigation.
        onView(withContentDescription(R.string.navigation_drawer_open))
                .perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.menu_profile))
                .perform(click());

        intended(hasComponent(AltroNewAccountActitvity.class.getName()));
        onView((withId(R.id.firstname)))
                .perform(ViewActions.typeText("Lokesh"));

        closeSoftKeyboard();

        onView(withId(R.id.lastname))
                .perform(ViewActions.typeText("Lokesh"));

        closeSoftKeyboard();
        Singleton.profilefilename="cropped-859132214.jpg";

        onView(withId(R.id.tick))
                .perform(click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        intended(hasComponent(KeyRingActivity.class.getName()));
    }


    @Test
    public void clickOnhelpSupport() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Open Drawer to click on navigation.
        onView(withContentDescription(R.string.navigation_drawer_open))
                .perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.menu_support))
                .perform(click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.tv_faqs))
                .perform(click());

        Web.onWebView()
                // We're in the main frame.
                .check(webMatches(getCurrentUrl(), containsString(AppConstants.SUPPORT_URL)));
    }

    @Test
    public void clickOnhelpContactUs() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Open Drawer to click on navigation.
        onView(withContentDescription(R.string.navigation_drawer_open))
                .perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.menu_support))
                .perform(click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.tv_contact_us))
                .perform(click());

        Web.onWebView()
                // We're in the main frame.
                .check(webMatches(getCurrentUrl(), containsString(AppConstants.CONTACT_US_URL)));
    }

    @Test
    public void clickOnhelpAboutUs() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Open Drawer to click on navigation.
        onView(withContentDescription(R.string.navigation_drawer_open))
                .perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.menu_support))
                .perform(click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.tv_about_us))
                .perform(click());

        Web.onWebView()
                // We're in the main frame.
                .check(webMatches(getCurrentUrl(), containsString(AppConstants.ABOUT_US_URL)));
    }

    @Test
    public void clickOnhelpLicense() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Open Drawer to click on navigation.
        onView(withContentDescription(R.string.navigation_drawer_open))
                .perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.menu_support))
                .perform(click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.tv_application_license))
                .perform(click());

        intended(hasComponent(LicenceAgreementActivity.class.getName()));
    }

    @Test
    public void clickOnhelpPrivacyPolicy() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Open Drawer to click on navigation.
        onView(withContentDescription(R.string.navigation_drawer_open))
                .perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.menu_support))
                .perform(click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.tv_privacy_policy))
                .perform(click());

        Web.onWebView()
                // We're in the main frame.
                .check(webMatches(getCurrentUrl(), containsString(AppConstants.PRIVACY_POLICY_URL)));
    }

    @Test
    public void clickOnhelpTermsOfService() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Open Drawer to click on navigation.
        onView(withContentDescription(R.string.navigation_drawer_open))
                .perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.menu_support))
                .perform(click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.tv_terms_of_use))
                .perform(click());

        Web.onWebView()
                // We're in the main frame.
                .check(webMatches(getCurrentUrl(), containsString(AppConstants.TERMS_OF_SERVICE)));
    }


    @Test
    public void clickOnSignout() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Open Drawer to click on navigation.
        onView(withContentDescription(R.string.navigation_drawer_open))
                .perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("Signout")).perform(ViewActions.scrollTo()).check(ViewAssertions.matches(isDisplayed())).perform(click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("Confirmation")).check(matches(isDisplayed()));
        onView(withId(android.R.id.button1)).perform(click());
        intended(hasComponent(AltroLoginActvity.class.getName()));
    }


    @Test
    public void clickOnSetting() {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Open Drawer to click on navigation.
        onView(withContentDescription(R.string.navigation_drawer_open))
                .perform(click());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        intended(hasComponent(AppSettingsActivity.class.getName()));
        onView(withId(R.id.update_pw_layout))
                .perform(click());
        intended(hasComponent(UpdatePasswordActivity.class.getName()));
    }
}
