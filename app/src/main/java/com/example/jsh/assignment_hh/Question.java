package com.example.jsh.assignment_hh;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class Question extends AppCompatActivity {
    private String question;

    public void setQuestion(String question){
        this.question = question;
    }

    public String getQuestion(){
        return this.question;
    }

    public Question (String question){
        this.question = question;
    }

    //TODO insert questions (note this may be placed in the Questions class as i cannot reference in a static context)
//    public final ArrayList<Question> quizQuestions = new ArrayList<Question>() {{
//        /*01*/add(new FBQuestion(getString(R.string.FBQ1), getString(R.string.FBQ1A)));
//        /*02*/add(new FBQuestion(getString(R.string.FBQ2), getString(R.string.FBQ2A)));
//        /*03*/add(new FBQuestion(getString(R.string.FBQ3), getString(R.string.FBQ3A)));
//        /*04*/add(new FBQuestion(getString(R.string.FBQ4), getString(R.string.FBQ4A)));
//        /*05*/add(new FBQuestion(getString(R.string.FBQ5), getString(R.string.FBQ5A)));
//        /*06*/add(new FBQuestion(getString(R.string.FBQ6), getString(R.string.FBQ6A)));
//        /*07*/add(new FBQuestion(getString(R.string.FBQ7), getString(R.string.FBQ7A)));
//        /*08*/add(new FBQuestion(getString(R.string.FBQ8), getString(R.string.FBQ8A)));
//        /*09*/add(new FBQuestion(getString(R.string.FBQ9), getString(R.string.FBQ9A)));
//        /*10*/add(new MCQuestion(getString(R.string.MCQ1), getString(R.string.MCQ1a), getString(R.string.MCQ1b), getString(R.string.MCQ1c), getString(R.string.MCQ1d), getResources().getInteger(R.integer.MCQ1answer)));
//        /*11*/add(new MCQuestion(getString(R.string.MCQ2), getString(R.string.MCQ2a), getString(R.string.MCQ2b), getString(R.string.MCQ2c), getString(R.string.MCQ2d), getResources().getInteger(R.integer.MCQ2answer)));
//        /*12*/add(new MCQuestion(getString(R.string.MCQ3), getString(R.string.MCQ3a), getString(R.string.MCQ3b), getString(R.string.MCQ3c), getString(R.string.MCQ3d), getResources().getInteger(R.integer.MCQ3answer)));
//        /*13*/add(new MCQuestion(getString(R.string.MCQ4), getString(R.string.MCQ4a), getString(R.string.MCQ4b), getString(R.string.MCQ4c), getString(R.string.MCQ4d), getResources().getInteger(R.integer.MCQ4answer)));
//        /*14*/add(new MCQuestion(getString(R.string.MCQ5), getString(R.string.MCQ5a), getString(R.string.MCQ5b), getString(R.string.MCQ5c), getString(R.string.MCQ5d), getResources().getInteger(R.integer.MCQ5answer)));
//        /*15*/add(new MCQuestion(getString(R.string.MCQ6), getString(R.string.MCQ6a), getString(R.string.MCQ6b), getString(R.string.MCQ6c), getString(R.string.MCQ6d), getResources().getInteger(R.integer.MCQ6answer)));
//        /*16*/add(new MCQuestion(getString(R.string.MCQ7), getString(R.string.MCQ7a), getString(R.string.MCQ7b), getString(R.string.MCQ7c), getString(R.string.MCQ7d), getResources().getInteger(R.integer.MCQ7answer)));
//        /*17*/add(new MCQuestion(getString(R.string.MCQ8), getString(R.string.MCQ8a), getString(R.string.MCQ8b), getString(R.string.MCQ8c), getString(R.string.MCQ8d), getResources().getInteger(R.integer.MCQ8answer)));
//        /*18*/add(new MCQuestion(getString(R.string.MCQ9), getString(R.string.MCQ9a), getString(R.string.MCQ9b), getString(R.string.MCQ9c), getString(R.string.MCQ9d), getResources().getInteger(R.integer.MCQ9answer)));
//        /*19*/add(new MCQuestion(getString(R.string.MCQ10), getString(R.string.MCQ10a), getString(R.string.MCQ10b), getString(R.string.MCQ10c), getString(R.string.MCQ10d), getResources().getInteger(R.integer.MCQ10answer)));
//        /*20*/add(new MCQuestion(getString(R.string.MCQ11), getString(R.string.MCQ11a), getString(R.string.MCQ11b), getString(R.string.MCQ11c), getString(R.string.MCQ11d), getResources().getInteger(R.integer.MCQ11answer)));
//        /*21*/add(new MCQuestion(getString(R.string.MCQ12), getString(R.string.MCQ12a), getString(R.string.MCQ12b), getString(R.string.MCQ12c), getString(R.string.MCQ12d), getResources().getInteger(R.integer.MCQ12answer)));
//        /*22*/
//        /*23*/
//        /*24*/
//        /*25*/
//
//    }};
//
//    public ArrayList<Question> getQuizQuestions() {
//        return quizQuestions;
//    }
}
