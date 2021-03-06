package com.example.jsh.assignment_hh;

import android.content.ClipData;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;
import static java.lang.Math.log;

public class QuizMain extends AppCompatActivity {

    private static final String TAG = "QuizMain";

    //hidden variables
    int qNumber = 2;
    int score = 0;
    ArrayList<Integer> numbersGenerated = new ArrayList<Integer>();

    //variables to count which topics the user answered incorrectly
    int OOP = 0;
    int Abs = 0;
    int Poly = 0;
    int Inh = 0;
    int Enc = 0;


    ArrayList<Question> quizQuestions;
    Question currentQuestion;
    boolean firstAttempt = true;

    //viewables
    TextView MCQuestion;
    RadioButton answerA;
    RadioButton answerB;
    RadioButton answerC;
    RadioButton answerD;

    TextView questionText;

    TextView DnDExplain;
    TextView movable1;
    TextView movable2;
    TextView movable3;
    TextView movable4;
    LinearLayout holderRow;
    LinearLayout row1;

    //create a viewFlipper to switch the included layout between the different questions types
    Button submit;
    Button resetBtn;
    Button skipBtn;
    ViewFlipper myViewFlipper;
    TextView questionNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_main);

        score = 0;

        Log.d(TAG, "onCreate: Score has been reset to: " + score);

        myViewFlipper = (ViewFlipper) findViewById(R.id.myViewFlipper);
        submit = (Button) findViewById(R.id.submit);
        resetBtn = (Button) findViewById(R.id.reset);
        skipBtn = (Button) findViewById(R.id.skip);
        questionNumber = (TextView) findViewById(R.id.questionNumber);

        //instantiating the elements for DnD layout
        DnDExplain = (TextView) findViewById(R.id.DnDExplain);

        movable1 = (TextView) findViewById(R.id.movable1);
        movable1.setOnTouchListener(new QuizMain.MyTouchListener());

        movable2 = (TextView) findViewById(R.id.movable2);
        movable2.setOnTouchListener(new QuizMain.MyTouchListener());

        movable3 = (TextView) findViewById(R.id.movable3);
        movable3.setOnTouchListener(new QuizMain.MyTouchListener());

        movable4 = (TextView) findViewById(R.id.movable4);
        movable4.setOnTouchListener(new QuizMain.MyTouchListener());

        holderRow = (LinearLayout) findViewById(R.id.holderRow);

        row1 = (LinearLayout) findViewById(R.id.row1);
        row1.setOnDragListener(new QuizMain.MyDragListener());

        //instantiating the elements for the checkbox
        MCQuestion = (TextView) findViewById(R.id.question);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.myRadioGroup);
        answerA = (RadioButton) findViewById(R.id.radioButton);
        answerB = (RadioButton) findViewById(R.id.radioButton2);
        answerC = (RadioButton) findViewById(R.id.radioButton3);
        answerD = (RadioButton) findViewById(R.id.radioButton4);

        //instantiating the elements for blanks
        questionText = (TextView) findViewById(R.id.questionFill);
        final EditText answerText = (EditText) findViewById(R.id.answerFill);


        //----------------------------------------------------------------------------------------//
        //layout setup complete

        //create quiz questions
        final Resources r = getResources();

        quizQuestions = new ArrayList<Question>() {{
            /*01*/
            add(new FBQuestion("Inheritance", getString(R.string.FBQ1), getString(R.string.FBQ1A)));
            /*02*/
            add(new FBQuestion("OOP", getString(R.string.FBQ2), getString(R.string.FBQ2A)));
            /*03*/
            add(new FBQuestion("Inheritance", getString(R.string.FBQ3), getString(R.string.FBQ3A)));
            /*04*/
            add(new FBQuestion("Polymorphism", getString(R.string.FBQ4), getString(R.string.FBQ4A)));
            /*05*/
            add(new FBQuestion("Abstraction", getString(R.string.FBQ5), getString(R.string.FBQ5A)));
            /*06*/
            add(new FBQuestion("Encapsulation", getString(R.string.FBQ6), getString(R.string.FBQ6A)));
            /*07*/
            add(new FBQuestion("Polymorphism", getString(R.string.FBQ7), getString(R.string.FBQ7A)));
            /*08*/
            add(new FBQuestion("Inheritance", getString(R.string.FBQ8), getString(R.string.FBQ8A)));
            /*09*/
            add(new FBQuestion("OOP", getString(R.string.FBQ9), getString(R.string.FBQ9A)));
            /*10*/
            add(new MCQuestion("Inheritance", getString(R.string.MCQ1), getString(R.string.MCQ1a), getString(R.string.MCQ1b), getString(R.string.MCQ1c), getString(R.string.MCQ1d), getResources().getInteger(R.integer.MCQ1answer)));
            /*11*/
            add(new MCQuestion("Inheritance", getString(R.string.MCQ2), getString(R.string.MCQ2a), getString(R.string.MCQ2b), getString(R.string.MCQ2c), getString(R.string.MCQ2d), getResources().getInteger(R.integer.MCQ2answer)));
            /*12*/
            add(new MCQuestion("Inheritance", getString(R.string.MCQ3), getString(R.string.MCQ3a), getString(R.string.MCQ3b), getString(R.string.MCQ3c), getString(R.string.MCQ3d), getResources().getInteger(R.integer.MCQ3answer)));
            /*13*/
            add(new MCQuestion("Polymorphism", getString(R.string.MCQ4), getString(R.string.MCQ4a), getString(R.string.MCQ4b), getString(R.string.MCQ4c), getString(R.string.MCQ4d), getResources().getInteger(R.integer.MCQ4answer)));
            /*14*/
            add(new MCQuestion("OOP", getString(R.string.MCQ5), getString(R.string.MCQ5a), getString(R.string.MCQ5b), getString(R.string.MCQ5c), getString(R.string.MCQ5d), getResources().getInteger(R.integer.MCQ5answer)));
            /*15*/
            add(new MCQuestion("Inheritance", getString(R.string.MCQ6), getString(R.string.MCQ6a), getString(R.string.MCQ6b), getString(R.string.MCQ6c), getString(R.string.MCQ6d), getResources().getInteger(R.integer.MCQ6answer)));
            /*16*/
            add(new MCQuestion("Inheritance", getString(R.string.MCQ7), getString(R.string.MCQ7a), getString(R.string.MCQ7b), getString(R.string.MCQ7c), getString(R.string.MCQ7d), getResources().getInteger(R.integer.MCQ7answer)));
            /*17*/
            add(new MCQuestion("OOP", getString(R.string.MCQ8), getString(R.string.MCQ8a), getString(R.string.MCQ8b), getString(R.string.MCQ8c), getString(R.string.MCQ8d), getResources().getInteger(R.integer.MCQ8answer)));
            /*18*/
            add(new MCQuestion("Polymorphism", getString(R.string.MCQ9), getString(R.string.MCQ9a), getString(R.string.MCQ9b), getString(R.string.MCQ9c), getString(R.string.MCQ9d), getResources().getInteger(R.integer.MCQ9answer)));
            /*19*/
            add(new MCQuestion("Abstraction", getString(R.string.MCQ10), getString(R.string.MCQ10a), getString(R.string.MCQ10b), getString(R.string.MCQ10c), getString(R.string.MCQ10d), getResources().getInteger(R.integer.MCQ10answer)));
            /*20*/
            add(new MCQuestion("Inheritance", getString(R.string.MCQ11), getString(R.string.MCQ11a), getString(R.string.MCQ11b), getString(R.string.MCQ11c), getString(R.string.MCQ11d), getResources().getInteger(R.integer.MCQ11answer)));
            /*21*/
            add(new MCQuestion("Polymorphism", getString(R.string.MCQ12), getString(R.string.MCQ12a), getString(R.string.MCQ12b), getString(R.string.MCQ12c), getString(R.string.MCQ12d), getResources().getInteger(R.integer.MCQ12answer)));
            /*22*/
//            add(new DnDQuestion("Inheritance", getString(R.string.DnDQ1), getString(R.string.DnDQ1_1), getString(R.string.DnDQ1_2), getString(R.string.DnDQ1_3), getString(R.string.DnDQ1_4), r.getIntArray(R.array.DNDQ1answer)));
            /*23*/
            add(new DnDQuestion("OOP", getString(R.string.DnDQ2), getString(R.string.DnDQ2_1), getString(R.string.DnDQ2_2), getString(R.string.DnDQ2_3), getString(R.string.DnDQ2_4), r.getIntArray(R.array.DNDQ2answer)));
            /*24*/
            /*25*/

        }};
        Log.d(TAG, "onCreate: You have quiz array has been created successfully, size: " + quizQuestions.size());

        //random number generator to determine first question
        currentQuestion = quizQuestions.get(randomNumber());

        //Set the display
        setDisplay();

        //use the appropriate format
        View.OnClickListener cycle = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //switch case currently set to cycle bewteen the 3 different files
                switch (myViewFlipper.getDisplayedChild()) {

                    case 0: //i.e the question is an MCQ

                        MCQuestion thisquestion1 = (MCQuestion) currentQuestion;

                        if (radioGroup.getCheckedRadioButtonId() == -1) {

                            //user has not selected an options/answer
                            Toast.makeText(QuizMain.this, "Please select an answer", Toast.LENGTH_SHORT).show();

                        } else {
                            //user has selected an answer - move on to check answer

                            Log.d(TAG, "onClick: The correct answer is [" + thisquestion1.getMCQAnswer() + "]");
                            int debugID1 = radioGroup.getCheckedRadioButtonId();
                            Log.d(TAG, "onClick: The answer selected was [" + Integer.toString(debugID1) + "]");

                            switch (thisquestion1.getMCQAnswer()) {

                                case 1: //i.e A is the correct answer
                                    if (radioGroup.getCheckedRadioButtonId() != answerA.getId()) {
                                        //wrong answer
                                        Toast.makeText(getApplicationContext(), "Incorrect - please try again", Toast.LENGTH_SHORT).show();
                                        firstAttempt = false;
                                    } else {
                                        //correct answer
                                        Toast.makeText(QuizMain.this, "Correct!", Toast.LENGTH_SHORT).show();
                                        radioGroup.clearCheck();
                                        incrementOrEnd();
                                    }
                                    break;

                                case 2:
                                    if (radioGroup.getCheckedRadioButtonId() != answerB.getId()) {
                                        //wrong answer
                                        Toast.makeText(getApplicationContext(), "Incorrect - please try again", Toast.LENGTH_SHORT).show();
                                        firstAttempt = false;
                                    } else {
                                        //correct answer
                                        Toast.makeText(QuizMain.this, "Correct!", Toast.LENGTH_SHORT).show();
                                        radioGroup.clearCheck();
                                        incrementOrEnd();
                                    }
                                    break;

                                case 3:
                                    if (radioGroup.getCheckedRadioButtonId() != answerC.getId()) {
                                        //wrong answer
                                        Toast.makeText(getApplicationContext(), "Incorrect - please try again", Toast.LENGTH_SHORT).show();
                                        firstAttempt = false;
                                    } else {
                                        //correct answer
                                        Toast.makeText(QuizMain.this, "Correct!", Toast.LENGTH_SHORT).show();
                                        radioGroup.clearCheck();
                                        incrementOrEnd();
                                    }
                                    break;

                                case 4:
                                    if (radioGroup.getCheckedRadioButtonId() != answerD.getId()) {
                                        //wrong answer
                                        Toast.makeText(getApplicationContext(), "Incorrect - please try again", Toast.LENGTH_SHORT).show();
                                        firstAttempt = false;
                                    } else {
                                        //correct answer
                                        Toast.makeText(QuizMain.this, "Correct!", Toast.LENGTH_SHORT).show();
                                        radioGroup.clearCheck();
                                        incrementOrEnd();
                                    }
                                    break;
                            }
                        }
                        break;

                    case 1: //i.e. the question is a Fill in the blanks question
                        FBQuestion thisquesiton2 = (FBQuestion) currentQuestion;

                        Log.d(TAG, "onClick: The correct answer is [" + thisquesiton2.getFBanswer() + "]");
                        Log.d(TAG, "onClick: The answer selected was [" + answerText.getText() + "]");

                        if (answerText.getText().toString().trim().equals(thisquesiton2.getFBanswer())) {
                            Toast.makeText(QuizMain.this, "Correct!", Toast.LENGTH_SHORT).show();
                            //clear answers
                            answerText.setText("");
                            incrementOrEnd();
                        } else {
                            Toast.makeText(QuizMain.this, "Incorrect - please try again", Toast.LENGTH_SHORT).show();
                            firstAttempt = false;
                        }

                        break;

                    case 2://i.e. the question is a DnD Question
                        DnDQuestion thisquesiton3 = (DnDQuestion) currentQuestion;
                        int[] answerArray = thisquesiton3.getCorrectOrder();
                        TextView[] TVArray = new TextView[]{movable1, movable2, movable3, movable4};

                        int correctCounter = 0;

                        int b = row1.getChildCount();

                        for (int a = 0; a < b; a++) {
                            TextView myTV = (TextView) row1.getChildAt(a);
                            TextView testObject = (TextView) TVArray[(answerArray[a] - 1)];
                            if (myTV.getText() == testObject.getText()) {
                                correctCounter++;
                            }
                        }

                        //if they got all four correct then
                        if (correctCounter == 4) {

                            Toast.makeText(QuizMain.this, "Correct!", Toast.LENGTH_SHORT).show();
                            incrementOrEnd();
                        } else { //otherwise
                            correctCounter = 0;
                            firstAttempt = false;
                            Toast.makeText(getApplicationContext(), "Incorrect - please try again", Toast.LENGTH_SHORT).show();
                        }

                        break;
                }
            }
        };

        //method to reset the DnD drop views
        View.OnClickListener reset = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clear answers
                holderRow.removeAllViews();
                row1.removeAllViews();

                //add the views back in to "reset it"
                holderRow.addView(DnDExplain);
                row1.addView(movable1);
                movable1.setVisibility(View.VISIBLE);

                row1.addView(movable2);
                movable2.setVisibility(View.VISIBLE);

                row1.addView(movable3);
                movable3.setVisibility(View.VISIBLE);

                row1.addView(movable4);
                movable4.setVisibility(View.VISIBLE);

            }
        };

        View.OnClickListener skip = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstAttempt = false;
                incrementOrEnd();
            }
        };

        //set button listeners
        submit.setOnClickListener(cycle);
        resetBtn.setOnClickListener(reset);
        skipBtn.setOnClickListener(skip);


    }

    private final class MyTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        v);
                v.startDrag(data, shadowBuilder, v, 0);
                v.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    //drag and drop functionality inspired by (http://www.vogella.com/tutorials/AndroidDragAndDrop/article.html)
    class MyDragListener implements View.OnDragListener {
        Drawable enterShape = getResources().getDrawable(
                R.drawable.shape_drop_indicator);
        Drawable normalShape = getResources().getDrawable(R.drawable.shape);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackground(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackground(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();
                    ViewGroup owner = (ViewGroup) view.getParent();
                    Log.d(TAG, "onDrag: owner is " + owner.getId());

                    //remove orignial textview
                    owner.removeView(view);

                    //create a layout reference for the drop destination
                    LinearLayout container = (LinearLayout) v;
                    Log.d(TAG, "onDrag: container is " + container.getId());

                    //add the view to the container
                    container.addView(view);

                    //set visible
                    view.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setBackground(normalShape);
                default:
                    break;
            }
            return true;
        }
    }

    //this method generates a random number to determine which question is asked - overlaps have been allowed
    public int randomNumber() {
        //TODO change the max to 25 when the rest of questions have been implemented
        int rn = 0;

        boolean dupe = true;

        while (dupe == true) {
            int holderValue = abs((int) (Math.random() * (0 - 23)));

            if (numbersGenerated.contains(holderValue)) {
                Log.d(TAG, "randomNumber: Number generated was " + holderValue);
                Log.d(TAG, "randomNumber: This number has already been used");
            } else {
                numbersGenerated.add(holderValue);
                dupe = false;
                rn = holderValue;
            }
        }

        Log.d(TAG, "randomNumber: The final number to be used is " + rn);
        return abs(rn);
    }

    //this method increments the question number and ends the activity if 10 questions have been completed
    public void incrementOrEnd() {
        //increment the question number
        if (qNumber <= 10) {
            questionNumber.setText("Q" + qNumber);
            qNumber++;

            //implement a boolean for is this is the first attempt or not to determine if they get a mark or not
            if (firstAttempt == true) {
                score++;
                Log.d(TAG, "incrementOrEnd: The users score is currently " + score);
            } else {
                switch (currentQuestion.getTopic()) {
                    case "OOP":
                        OOP++;
                        break;

                    case "Abstraction":
                        Abs++;
                        break;

                    case "Polymorphism":
                        Poly++;
                        break;

                    case "Inheritance":
                        Inh++;
                        break;

                    case "Encapsulation":
                        Enc++;
                        break;
                }
            }

            firstAttempt = true;
            currentQuestion = quizQuestions.get(randomNumber());
            setDisplay();

        }
        //the quiz ends at 10
        else {
            //do scoring for last question
            if (firstAttempt == true) {
                score++;
            } else {
                switch (currentQuestion.getTopic()) {
                    case "OOP":
                        OOP++;
                        break;

                    case "Abstraction":
                        Abs++;
                        break;

                    case "Polymorphism":
                        Poly++;
                        break;

                    case "Inheritance":
                        Inh++;
                        break;

                    case "Encapsulation":
                        Enc++;
                        break;
                }
            }
            Log.d(TAG, "incrementOrEnd: The users score is currently " + score);

            //determine which of the topics has the most errors
            String wTopic = "-";
            int wMax = -1;
            int[] worst = new int[]{OOP, Abs, Poly, Inh, Enc};
            for (int a = 0; a < 5; a++) {
                if (worst[a] > (wMax + 1)) {
                    wMax = a;
                }
            }
            switch (wMax) {
                case 0:
                    wTopic = "OOP";
                    break;
                case 1:
                    wTopic = "Abstraction";
                    break;
                case 2:
                    wTopic = "Polymorphism";
                    break;
                case 3:
                    wTopic = "Inheritance";
                    break;
                case 4:
                    wTopic = "Encapsulation";
                    break;

            }
            Log.d(TAG, "incrementOrEnd: Worst topic was " + wTopic);

            //new activity is implemented
            Log.d(TAG, "incrementOrEnd: Quiz completed moving to score page");
            Intent finishIntent = new Intent(this, QuizScore.class);
            //send score to new thing before resetting
            finishIntent.putExtra("quizScore", score);
            if (score > 0) {
                finishIntent.putExtra("worstTopic", wTopic);
            }

            startActivity(finishIntent);
            Log.d(TAG, "incrementOrEnd: Starting intent " + finishIntent);
        }
    }

    //this method sets the display based on what question is selected for currentQuestion
    public void setDisplay() {
        //check which type of question it is
        if (currentQuestion instanceof MCQuestion) {
            MCQuestion.setText(currentQuestion.getQuestion());
            answerA.setText(((MCQuestion) currentQuestion).getOptionA());
            answerB.setText(((MCQuestion) currentQuestion).getOptionB());
            answerC.setText(((MCQuestion) currentQuestion).getOptionC());
            answerD.setText(((MCQuestion) currentQuestion).getOptionD());

            myViewFlipper.setDisplayedChild(0);

        } else if (currentQuestion instanceof FBQuestion) {
            questionText.setText(currentQuestion.getQuestion());

            myViewFlipper.setDisplayedChild(1);

        } else if (currentQuestion instanceof DnDQuestion) {

            holderRow.removeAllViews();
            row1.removeAllViews();


            DnDExplain.setText(((DnDQuestion) currentQuestion).getQuestion());
            movable1.setText(((DnDQuestion) currentQuestion).getDnDOptionA());
            movable2.setText(((DnDQuestion) currentQuestion).getDnDOptionB());
            movable3.setText(((DnDQuestion) currentQuestion).getDnDOptionC());
            movable4.setText(((DnDQuestion) currentQuestion).getDnDOptionD());

            holderRow.addView(DnDExplain);
            row1.addView(movable1);
            movable1.setVisibility(View.VISIBLE);

            row1.addView(movable2);
            movable2.setVisibility(View.VISIBLE);

            row1.addView(movable3);
            movable3.setVisibility(View.VISIBLE);

            row1.addView(movable4);
            movable4.setVisibility(View.VISIBLE);


            myViewFlipper.setDisplayedChild(2);
        }
    }

    //make sure they want to leave by implementing a double-click to exit function
    boolean leaveCheck = false;

    public void onBackPressed() {

        //if checked then it will leave and exit to home screen
        if (leaveCheck == true) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        } else { //otherwise, a 1.5 sec window is given to leave
            Toast.makeText(getApplicationContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
            leaveCheck = true;
            //delayed handler takes sets the boolean back to false if nothing is clicked within 1.5 secs
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    leaveCheck = false;
                }
            }, 15 * 100);
        }


    }
}
