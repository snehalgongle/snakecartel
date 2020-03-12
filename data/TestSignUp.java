package com.altrosmart;

import android.app.Activity;
import android.app.Fragment;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ImageView;
import com.theartofdev.edmodo.cropper.CropImage;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.matchers.TypeSafeMatcher;
import org.junit.runner.RunWith;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.altrosmart.ui.registration.newAccount.AltroNewAccountActitvity;
import com.altrosmart.ui.registration.newAccount.frgment.AltroAccountCreationContinueFragment;
import com.altrosmart.utils.Singleton;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.theartofdev.edmodo.cropper.CropImage.CROP_IMAGE_EXTRA_RESULT;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class TestSignUp {

    public static String firsName = "Testing";
    public static String lastName = "Testing";
    public static String profilePic = "Testing.jpg";

    private static final Intent MY_ACTIVITY_INTENT = new Intent(InstrumentationRegistry.getTargetContext(), AltroNewAccountActitvity.class);

/*    @Rule
    public ActivityTestRule<AltroNewAccountActitvity> mActivityRule =
            new ActivityTestRule<AltroNewAccountActitvity>(AltroNewAccountActitvity.class) {
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
            };*/

    @Rule
    public IntentsTestRule<AltroNewAccountActitvity> mActivityRule =
            new IntentsTestRule<>(AltroNewAccountActitvity.class);


    public void attachFragment() {
        AltroAccountCreationContinueFragment fragment = new AltroAccountCreationContinueFragment();
        mActivityRule.getActivity().getSupportFragmentManager().beginTransaction().add(R.id.frame_container, fragment).commit();
    }

    @Before
    public void setUp() {
        closeSoftKeyboard();
        savePickedImage();
        intending(hasAction(Intent.ACTION_CHOOSER)).respondWith(getImageResult());
    }

    @Test
    public void checkTextDisplayedInDynamicallyCreatedFragment() {
        attachFragment();
    }


    @Test
    public void default_ImageHasNoDrawable(){
        onView(withId(R.id.profile_image)).check(matches(not(hasDrawable())));
    }

    @Test
    public void pickImage_ImagePicked(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //onView(withId(R.id.cameraimg)).perform(click());
        onView(withId(R.id.profile_image)).check(matches(hasDrawable()));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void savePickedImage() {
        Bitmap bm = BitmapFactory.decodeResource(mActivityRule.getActivity().getResources(), R.drawable.avatar);
        assertTrue(bm != null);
        File dir = mActivityRule.getActivity().getExternalCacheDir();
        File file = new File(dir.getPath(), "pickImageResult.jpeg");
        System.out.println(file.getAbsolutePath());
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Instrumentation.ActivityResult getImageResult() {
        Intent resultData = new Intent();
        File dir = mActivityRule.getActivity().getExternalCacheDir();
        File file = new File(dir.getPath(), "pickImageResult.jpeg");
        Uri uri = Uri.fromFile(file);
        resultData.putExtra(CROP_IMAGE_EXTRA_RESULT, new CropImage.ActivityResult(null, uri, null, null, null, 0, 1));
        return new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
    }

    public static Matcher<View> hasDrawable() {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {}

            @Override
            public boolean matchesSafely(View item) {
                CircleImageView imageView = (CircleImageView) item;
                return imageView.getDrawable() != null;
            }
        };
    }

    @Test
    public void click_save_empty_firstname() {
        onView((withId(R.id.firstname)))
                .perform(ViewActions.typeText(""));

        onView(withId(R.id.lastname))
                .perform(ViewActions.typeText(lastName));

        onView(withId(R.id.tick))
                .perform(click());
        closeSoftKeyboard();

        onView(withText("Please Enter First Name"))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )));
    }

    @Test
    public void click_save_empty_lastname() {
        onView((withId(R.id.firstname)))
                .perform(ViewActions.typeText(firsName));
        onView(withId(R.id.lastname))
                .perform(ViewActions.typeText(""));
        closeSoftKeyboard();
        onView(withText("Please Enter Last Name"))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )));
    }

    @Test
    public void click_save_choose_pic() {
        onView((withId(R.id.firstname)))
                .perform(ViewActions.typeText(firsName));

        onView(withId(R.id.lastname))
                .perform(ViewActions.typeText(lastName));

        onView(withId(R.id.tick))
                .perform(click());
        closeSoftKeyboard();

        onView(withText("Please Choose Photo"))
                .check(matches(withEffectiveVisibility(
                        ViewMatchers.Visibility.VISIBLE
                )));
    }

    @Test
    public void click_save_success() {
        onView((withId(R.id.firstname)))
                .perform(ViewActions.typeText(firsName));

        onView(withId(R.id.lastname))
                .perform(ViewActions.typeText(lastName));

        Singleton.profilefilename="mockimagme.jpg";

        onView(withId(R.id.tick))
                .perform(click());

        closeSoftKeyboard();

        //intended(hasComponent(AltroNewAccountActitvity.class.getName()));
    }
}
