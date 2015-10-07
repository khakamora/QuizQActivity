package home.com.f.quizactivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.app.Activity;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.TextView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


/**
 * Created by F on 06.10.2015.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityInstrumentationTest{

    public static final String STRING_TO_BE_EXPECTED = "The Suez Canal connects the Red Sea and the Indian Ocean.";
    public static final String INCORRECT_TOAST_TO_BE_EXPECTED = "Incorrect!";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void checkIncorrectAnswer() {
        //Press the button.
        onView(withId(R.id.true_button)).perform(click());

        // Check that the text was appeared.
        onView(withId(R.string.incorrect_toast)).check(matches(withText(INCORRECT_TOAST_TO_BE_EXPECTED)));
        //onView(withId(R.string.incorrect_toast)).check(isDisplayed().matches(withText(INCORRECT_TOAST_TO_BE_EXPECTED)));
   //     onView(withText(R.string.incorrect_toast)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void checkNextQuestion() {
        //Press the button.
        onView(withId(R.id.next_button)).perform(click());

        // Check that the text was appeared.
        onView(withId(R.id.question_text_view)).check(matches(withText(STRING_TO_BE_EXPECTED)));
    }
}
