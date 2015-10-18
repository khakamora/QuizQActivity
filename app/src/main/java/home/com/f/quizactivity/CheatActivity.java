package home.com.f.quizactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by F on 17.10.2015.
 */
public class CheatActivity extends Activity {

    public static final String EXTRA_ANSWER_IS_TRUE = "home.com.f.quizactivity.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "home.com.f.quizactivity.answer_shown";
    private static final String KEY_INDEX = "index";

    private Boolean answerIsTrue;
    private TextView answerTextView;
    private Button showAnswer;

    private void setAnswerShownResult(Boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

  /*      if (savedInstanceState != null){
            setAnswerShownResult(true);
        }*/


        if (savedInstanceState != null){
            setAnswerShownResult(answerIsTrue);
        }

            setAnswerShownResult(false);
            answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        answerTextView = (TextView) findViewById(R.id.answerTextView);

        showAnswer = (Button) findViewById(R.id.showAnswerButton);
        showAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (answerIsTrue) {
                    answerTextView.setText(R.string.true_button);
                } else {
                    answerTextView.setText(R.string.false_button);
                }
                answerIsTrue = true;
                setAnswerShownResult(answerIsTrue);

            }

        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(KEY_INDEX, answerIsTrue);
        //       savedInstanceState.putInt(KEY_INDEX, currentIndex);
    }
}
