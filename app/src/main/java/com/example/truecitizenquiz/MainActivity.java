package com.example.truecitizenquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button falseButton;
    private Button trueButton;
    private ImageButton nextButton;

    private TextView questionTextView;
    private int currentQuestionIndex = 0;


    private Question[] questionBank = new Question[] {
            new Question(R.string.question_amendments, false),
            new Question(R.string.question_constitution, true),
            new Question(R.string.question_declaration, true),
            new Question(R.string.question_independence_rights, true),
            new Question(R.string.question_religion, true),
            new Question(R.string.question_government, false),
            new Question(R.string.question_government_feds, false),
            new Question(R.string.question_government_senators, true),

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.true_button);
        questionTextView = findViewById(R.id.answer_text_view);
        nextButton = findViewById(R.id.next_button);


        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.false_button:
                checkAnswer(false);
                break;

            case R.id.true_button:
                checkAnswer(true);
                break;

            case R.id.next_button:
                currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.length;
                updateQuestion();

        }
    }

    private void updateQuestion() {
        Log.d("Content", "onClick: " + currentQuestionIndex);
        questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());
    }

    private void checkAnswer(boolean userChooseCorrect) {
        boolean answerIsTrue = questionBank[currentQuestionIndex].isAnswerTrue();
        int toastMessageId = 0;

        if (userChooseCorrect == answerIsTrue) {
            toastMessageId = R.string.correct_answer;

        } else {
            toastMessageId = R.string.wrong_answer;
        }

        Toast.makeText(MainActivity.this, toastMessageId, Toast.LENGTH_SHORT).show();
    }
}
