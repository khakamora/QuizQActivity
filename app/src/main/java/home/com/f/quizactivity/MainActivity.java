package home.com.f.quizactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private static final String TAG = "QuizActivity";

    private static final String KEY_INDEX = "index";
    private static final String KEY_CHEATER = "cheater";
    private static final String KEY_ARRAY_CHEATER = "array";

    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private TextView questionTextView;
    private TextView nextQuestion;
    private ImageButton prevButton;
    private Button cheatButton;
    private Boolean isCheater;
    private TextView api_level;

    private int cheaterIndex = 0;

 //   ArrayList bankOfCheater = new ArrayList();
    int[] bankOfCheater = new int [5];

    private TrueFalse[] questionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true),
    };

    private int currentIndex = 0;

    @Override
    protected void onActivityResult(int RequestCode, int ResultCode, Intent data) {
        if (data == null) {
            return;
        }
        isCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
        cheaterIndex = data.getIntExtra(CheatActivity.KEY_INDEX_ARRAY_CHEATER, 0);
        bankOfCheater[cheaterIndex] = 1;
    }

    private void updateQuestion() {
        int question = questionBank[currentIndex].getQuestion();
        questionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {

        boolean answerIsTrue = questionBank[currentIndex].isTrueQuestion();

        int messageResId = 0;

        if (bankOfCheater[currentIndex] == 1 | isCheater) {
            messageResId = R.string.judgment_toast;
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isCheater = false;

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            isCheater = savedInstanceState.getBoolean(KEY_CHEATER, isCheater);
            bankOfCheater = savedInstanceState.getIntArray(KEY_ARRAY_CHEATER);
        }

        api_level = (TextView) findViewById(R.id.api_level);
        int ap = Build.VERSION.SDK_INT;
        api_level.setText(ap);

        questionTextView = (TextView) findViewById(R.id.question_text_view);

        trueButton = (Button) findViewById(R.id.true_button);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        falseButton = (Button) findViewById(R.id.false_button);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        nextButton = (ImageButton) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex == questionBank.length - 1) {
                    currentIndex = 0;
                } else {
                    currentIndex = currentIndex + 1;
                }
                isCheater = false;
                updateQuestion();
            }
        });

        prevButton = (ImageButton) findViewById(R.id.prev_button);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex == 0) {
                    currentIndex = questionBank.length - 1;
                } else {
                    currentIndex = currentIndex - 1;
                }
                isCheater = false;
                updateQuestion();
            }
        });

        nextQuestion = (TextView) findViewById(R.id.question_text_view);
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex == questionBank.length - 1) {
                    currentIndex = 0;
                } else {
                    currentIndex = currentIndex + 1;
                }
                isCheater = false;
                updateQuestion();
            }
        });

        cheatButton = (Button) findViewById(R.id.cheat_button);
        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CheatActivity.class);
                boolean answerIsTrue = questionBank[currentIndex].isTrueQuestion();
                i.putExtra(CheatActivity.KEY_INDEX_ARRAY_CHEATER, currentIndex);
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE, answerIsTrue);
                startActivityForResult(i, 0);
            }
        });

        updateQuestion();

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX, currentIndex);
        savedInstanceState.putBoolean(KEY_CHEATER, isCheater);
        savedInstanceState.putIntArray(KEY_ARRAY_CHEATER, bankOfCheater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
