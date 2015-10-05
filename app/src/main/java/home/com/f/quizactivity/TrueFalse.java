package home.com.f.quizactivity;

/**
 * Created by F on 04.10.2015.
 */
public class TrueFalse {
    private int question;

    private boolean trueQuestion;

    public TrueFalse(int question, boolean trueQuestion){
        this.question = question;
        this.trueQuestion = trueQuestion;
    }

    public int getQuestion(){
        return this.question;
    }

    public void setQuestion(int question){
        this.question = question;
    }

    public boolean isTrueQuestion(){
        return this.trueQuestion;
    }

    public void setTrueQuestion(boolean trueQuestion){
        this.trueQuestion = trueQuestion;
    }
}
