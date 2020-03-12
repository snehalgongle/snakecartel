package com.altrosmart;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.altrosmart.ui.license.LicenceAgreementActivity;
import com.altrosmart.ui.login.AltroLoginActvity;
import com.altrosmart.ui.registration.newAccount.AltroNewAccountActitvity;
import com.altrosmart.utils.Singleton;

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
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class TestLogin {
    private static String notActivatedUserName="lokesh@colanonline.co";
    private static String notActivatedPassword="Lokesh@12";
    private static String wrongUserName="lokesh@colline.com";
    private static String wrongPassword="Lokesh@12";
    private static String userName="lokesh@colanonline.com";
    private static String password="Lokesh@123";

    @Rule
    public ActivityTestRule<AltroLoginActvity> mActivityRule =
            new ActivityTestRule<AltroLoginActvity>(AltroLoginActvity.class){
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
    public void setup() {

    }

    @Test
    public void login_success(){
        Log.e("@Test","Performing login success test");
        onView((withId(R.id.createnewemail)))
                .perform(ViewActions.typeText(userName));
        closeSoftKeyboard();
        onView(withId(R.id.et_psw))
                .perform(ViewActions.typeText(password));
        closeSoftKeyboard();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.loginbtn))
                .perform(click());

        intended(hasComponent(LicenceAgreementActivity.class.getName()));
    }


    @Test
    public void login_user_account_does_not_exists(){
        onView((withId(R.id.createnewemail)))
                .perform(ViewActions.typeText(wrongUserName));
        closeSoftKeyboard();
        onView(withId(R.id.et_psw))
                .perform(ViewActions.typeText(wrongPassword));
        closeSoftKeyboard();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.loginbtn))
                .perform(click());

        onView(withText(R.string.AccountNotExist))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )));
    }

    @Test
    public void login_user_activation_required(){
        onView((withId(R.id.createnewemail)))
                .perform(ViewActions.typeText(notActivatedUserName));
        closeSoftKeyboard();

        onView(withId(R.id.et_psw))
                .perform(ViewActions.typeText(notActivatedPassword));
        closeSoftKeyboard();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.loginbtn))
                .perform(click());

        onView(withText("User activation required")).inRoot(isDialog()).check(matches(allOf(withText("User activation required"), isDisplayed())));
        onView(withId(android.R.id.button1)).perform(click());
    }

    @Test
    public void login_email_address_not_found(){
        onView((withId(R.id.createnewemail)))
                .perform(ViewActions.typeText(""));
        closeSoftKeyboard();

        onView(withId(R.id.et_psw))
                .perform(ViewActions.typeText(password));
        closeSoftKeyboard();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.loginbtn))
                .perform(click());

        onView(withText("Please enter your email address")).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void login_password_not_found(){
        onView((withId(R.id.createnewemail)))
                .perform(ViewActions.typeText(userName));
        closeSoftKeyboard();

        onView(withId(R.id.et_psw))
                .perform(ViewActions.typeText(""));
        closeSoftKeyboard();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.loginbtn))
                .perform(click());

        onView(withText("Password is required")).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void login_invalid_credentials(){
        onView((withId(R.id.createnewemail)))
                .perform(ViewActions.typeText(userName));
        closeSoftKeyboard();

        onView(withId(R.id.et_psw))
                .perform(ViewActions.typeText(wrongPassword));
        closeSoftKeyboard();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.loginbtn))
                .perform(click());

        onView(withText("Invalid credentials."))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )));
    }


    @Test
    public void login_navigate_to_create_acc(){
        onView(withId(R.id.createnew))
                .perform(click());
        intended(hasComponent(AltroNewAccountActitvity.class.getName()));
        onView((withId(R.id.firstname)))
                .perform(ViewActions.typeText("fdgfgf"));

        onView(withId(R.id.lastname))
                .perform(ViewActions.typeText("fgf"));

        Singleton.profilefilename="mockimagme.jpg";

        onView(withId(R.id.tick))
                .perform(click());
    }

    @Test
    public void login_navigate_to_forgotpsw(){
        onView(withId(R.id.forgotpsw))
                .perform(click());
        intended(hasComponent(AltroNewAccountActitvity.class.getName()));
    }
}
