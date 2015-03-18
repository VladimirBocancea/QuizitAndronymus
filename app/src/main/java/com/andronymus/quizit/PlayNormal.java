package com.andronymus.quizit;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class PlayNormal extends ActionBarActivity {

    public static final int INCREMENT_SCORE = 100;
    public static final int DECREMENT_SCORE = 50;
    public static final int NUMBER_QUESTIONS = 10;
    // TODO: Controlar que al girar la pantalla no se cambien las preguntas
    private TextView textViewQuestion;
    private TextView gameOver;
    private Button buttonAnswer1;
    private Button buttonAnswer2;
    private Button buttonAnswer3;
    private Button buttonAnswer4;
    private DatabaseManager databaseManager;
    private Question question;
    private boolean isTrue = false;
    private ArrayList<Answer> answers;
    private ArrayList<Button> buttons;
    private int count = 1;
    private int score = 0;
    Sonidos sonidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_normal);

        databaseManager = new DatabaseManager(this);

        textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
        buttonAnswer1 = (Button) findViewById(R.id.buttonAnswer1);
        buttonAnswer2 = (Button) findViewById(R.id.buttonAnswer2);
        buttonAnswer3 = (Button) findViewById(R.id.buttonAnswer3);
        buttonAnswer4 = (Button) findViewById(R.id.buttonAnswer4);
        gameOver = (TextView) findViewById(R.id.gameOver);

        buttons = new ArrayList<Button>();
        buttons.add(buttonAnswer1);
        buttons.add(buttonAnswer2);
        buttons.add(buttonAnswer3);
        buttons.add(buttonAnswer4);
sonidos = new Sonidos();
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
        textViewQuestion.setText(questionText);


        answers = databaseManager.getAnswer(theNumber);


        for (Answer answer : answers) {

            buttons.get(i++).setText(answer.getAnswerText());
            //System.out.println(answers.size());
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
sonidos.sonidoClick(this);
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
        Loads the next question if the contion is true and increments the number of answered questions and the score.
     */
    public void nextQuestion(boolean condition) {
        if (count <= NUMBER_QUESTIONS) {
            if (condition) {
                loadQuestion();
                count++;
                score += INCREMENT_SCORE;

            } else {
                score -= DECREMENT_SCORE;
                if (score <= 0){
                    score = 0;
                }
            }
            gameOver.setText("Score: " +Integer.toString(score));

        } else {
            textViewQuestion.setVisibility(View.GONE);
            for (int i = 0; i < buttons.size(); i++){
                buttons.get(i).setVisibility(View.GONE);
            }
            gameOver.setText("Score: " +Integer.toString(score));
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
