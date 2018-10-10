package com.example.jsh.assignment_hh;

import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import org.w3c.dom.Text;

public class QuizMain extends AppCompatActivity {

    int qNumber = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_main);

        TextView movable1 = (TextView) findViewById(R.id.movable1);
        movable1.setOnTouchListener(new QuizMain.MyTouchListener());

        TextView movable2 = (TextView) findViewById(R.id.movable2);
        movable2.setOnTouchListener(new QuizMain.MyTouchListener());


        TextView movable3 = (TextView)findViewById(R.id.movable3);
        movable3.setOnTouchListener(new QuizMain.MyTouchListener());


        TextView movable4 = (TextView)findViewById(R.id.movable4);
        movable4.setOnTouchListener(new QuizMain.MyTouchListener());


        findViewById(R.id.holderRow).setOnDragListener(new QuizMain.MyDragListener());
        findViewById(R.id.row1).setOnDragListener(new QuizMain.MyDragListener());
        findViewById(R.id.row2).setOnDragListener(new QuizMain.MyDragListener());
        findViewById(R.id.row3).setOnDragListener(new QuizMain.MyDragListener());


        //TODO decide if we want to use a single row for answers or have 3 rows like currently

        //create a viewFlipper to switch the included layout between the different questions types
        final ViewFlipper myViewFlipper = (ViewFlipper) findViewById(R.id.myViewFlipper);
        Button submit = (Button) findViewById(R.id.submit);
        final TextView questionNumber = (TextView) findViewById(R.id.questionNumber);


        View.OnClickListener cycle = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //TODO take in the question and choose the appropriate .xml file to house the question.

                //TODO create the way to save the selected answer (perhaps an Array)

                //switch case currently set to cycle bewteen the 3 different files
                switch (myViewFlipper.getDisplayedChild()) {
                    case 0:
                        myViewFlipper.setDisplayedChild(1);
                        break;
                    case 1:
                        myViewFlipper.setDisplayedChild(2);
                        break;
                    case 2:
                        myViewFlipper.setDisplayedChild(0);
                        break;
                }


                //increment the question number
                if(qNumber <= 10) {
                    questionNumber.setText("Q" + qNumber);
                    qNumber++;
                }
                //end the quiz at 10
                else {
                    //TODO implement new end quiz activity here

                    //reset the numbers for next time.
                    questionNumber.setText("Q1");
                    qNumber = 2;
                }
            }
        };

        submit.setOnClickListener(cycle);

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
                    owner.removeView(view);
                    LinearLayout container = (LinearLayout) v;
                    container.addView(view);
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
}
