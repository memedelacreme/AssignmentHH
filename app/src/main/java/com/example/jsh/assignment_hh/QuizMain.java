package com.example.jsh.assignment_hh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class QuizMain extends AppCompatActivity {

    int qNumber = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_main);

        final ViewFlipper myViewFlipper = (ViewFlipper) findViewById(R.id.myViewFlipper);
        Button submit = (Button) findViewById(R.id.submit);
        final TextView questionNumber = (TextView) findViewById(R.id.questionNumber);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //TODO create the way to save the selected answer (perhaps an Array)

                switch (myViewFlipper.getDisplayedChild()){
                    case 0:
                        myViewFlipper.setDisplayedChild(1);
                        break;
                    case 1:
                        myViewFlipper.setDisplayedChild(0);
                        break;
                    case 2: break;
                }


                //TODO add aditional xml files to the ViewFlipper in activity_main.xml
                //myViewFlipper.setDisplayedChild(1);

                //increment the question number
                questionNumber.setText("Q" + qNumber);
                qNumber++;
            }
        });
    }
}
