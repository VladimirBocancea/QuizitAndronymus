package com.andronymus.quizit;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class PlayNormal extends ActionBarActivity {
    // TODO: Controlar que al girar la pantalla no se cambien las preguntas
    private EditText editTextQuestion;
    private Button buttonAnswer1;
    private Button buttonAnswer2;
    private Button buttonAnswer3;
    private Button buttonAnswer4;
    private DatabaseManager databaseManager;
    private Question question;
    private boolean isTrue = false;
    private ArrayList<Answer> answers;
    private ArrayList<Button> buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_normal);

        databaseManager = new DatabaseManager(this);

        editTextQuestion = (EditText) findViewById(R.id.editTextQuestion);
        buttonAnswer1 = (Button) findViewById(R.id.buttonAnswer1);
        buttonAnswer2 = (Button) findViewById(R.id.buttonAnswer2);
        buttonAnswer3 = (Button) findViewById(R.id.buttonAnswer3);
        buttonAnswer4 = (Button) findViewById(R.id.buttonAnswer4);

        buttons = new ArrayList<Button>();
        buttons.add(buttonAnswer1);
        buttons.add(buttonAnswer2);
        buttons.add(buttonAnswer3);
        buttons.add(buttonAnswer4);

        loadQuestion();
    }

    /*
        Creates a random number between 1 and the max number of questions in the database.
     */
    public int createRandomNrOfQuestion() {
        int minNumber = 1;
        int maxNumber = databaseManager.getMaxNumQuestion();
        int mRandom = minNumber + (int) (Math.random() * ((maxNumber - minNumber) + 1));
        return mRandom;
    }


    public void loadQuestion() {

        int theNumber = createRandomNrOfQuestion();
        int i = 0;

        question = databaseManager.getQuestion(theNumber);
        String questionText = question.getQuestionText();
        editTextQuestion.setText(questionText);


        answers = databaseManager.getAnswer(theNumber);


        for (Answer answer : answers) {

            buttons.get(i++).setText(answer.getAnswerText());
            System.out.println(answers.size());
            // isTrue = answer.getIsTrue();

            if (answers.size() == 2) {
                buttonAnswer3.setVisibility(View.GONE);
                buttonAnswer4.setVisibility(View.GONE);
            } else {
                buttonAnswer3.setVisibility(View.VISIBLE);
                buttonAnswer4.setVisibility(View.VISIBLE);
            }

        }

    }


    public void onClickAnyAnswer(View view) {
        if (buttonAnswer1 == findViewById(view.getId())) {
            isTrue = answers.get(0).getIsTrue();
        }
        if (buttonAnswer2 == findViewById(view.getId())) {
            isTrue = answers.get(1).getIsTrue();

        }
        if (buttonAnswer3 == findViewById(view.getId())) {
            isTrue = answers.get(2).getIsTrue();

        }
        if (buttonAnswer4 == findViewById(view.getId())) {
            isTrue = answers.get(3).getIsTrue();

        }
        nextQuestion(isTrue);

    }

    /*
        Loads the next question if the contion is true
     */
    public void nextQuestion(boolean condition) {
        if (condition) {
            loadQuestion();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_play_normal, menu);
        return true;
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