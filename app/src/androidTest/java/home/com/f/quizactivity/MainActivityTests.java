package home.com.f.quizactivity;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

/**
 * Created by F on 04.10.2015.
 */
public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTests(){
        super(MainActivity.class);
    }

    public void testActivityExisted(){
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }

    public void testTrueButtonExisted(){
        MainActivity activity = getActivity();
        Button trueButton = (Button)activity.findViewById(R.id.true_button);
        assertNotNull(trueButton);
    }

    public void testFalseButtonExisted(){
        MainActivity activity = getActivity();
        Button falseButton = (Button)activity.findViewById(R.id.false_button);
        assertNotNull(falseButton);
    }
}
