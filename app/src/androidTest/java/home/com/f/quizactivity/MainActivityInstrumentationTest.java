package home.com.f.quizactivity;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import home.com.f.quizactivity.espressoreadyproject.MainActivity;


import static android.test.ViewAsserts.assertOnScreen;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;


/**
 * Created by F on 06.10.2015.
 */
public class MainActivityInstrumentationTest extends ActivityInstrumentationTestCase2<MainActivity>{
    private MainActivity mMainActivity;
    private TextView mHelloWorldTextView;

    @SuppressWarnings( "deprecation" )
    public MainActivityInstrumentationTest() {
        super("com.dropsport.espressoreadyproject", MainActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();

        mMainActivity = getActivity();
        mHelloWorldTextView = (TextView) mMainActivity.findViewById(R.id.hello_world_text);
    }

    public void testTextView() {
        assertOnScreen(mMainActivity.getWindow().getDecorView(), mHelloWorldTextView);
    }

    public void testLabel() {
        onView(withId(R.id.hello_world_text)).check(matches(withText("Hello world!")));
    }

    public void testFalseLabel() {
        onView(withId(R.id.hello_world_text)).check(matches(withText("What a label!")));
    }
}
