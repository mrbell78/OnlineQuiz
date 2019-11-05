package com.example.online_quiz_offline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button btn_a,btn_b,btn_c,btn_d;
    TextView tv_question,tv_time;
    DatabaseReference mdatabase;
    int total=1;
    int correct=0;
    int wrong =0;

    Button btn_next;

    private static final long START_TIME_INMILIS=20000;
    TextView timer;
    Button btn_start,btn_restart;

    private CountDownTimer mcountDownTimer;

    private Boolean mTimerrunning=false;

    private long mTimerinleft_inmils= START_TIME_INMILIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_a=findViewById(R.id.buttona);
        btn_b=findViewById(R.id.buttonb);
        btn_c=findViewById(R.id.buttonc);
        btn_d=findViewById(R.id.buttond);

        btn_next=findViewById(R.id.next);
        tv_question=findViewById(R.id.questionid);
        tv_time=findViewById(R.id.tv_time);
        startCountdown();
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Updatequestion();
                restartCountdown();
                btn_a.setEnabled(true);
                btn_b.setEnabled(true);
                btn_c.setEnabled(true);
                btn_d.setEnabled(true);
            }
        });



    }

    private void Updatequestion() {


        btn_a.setBackgroundColor(Color.parseColor("#F57C00"));
        btn_b.setBackgroundColor(Color.parseColor("#F57C00"));
        btn_c.setBackgroundColor(Color.parseColor("#F57C00"));
        btn_d.setBackgroundColor(Color.parseColor("#F57C00"));


        if(total>4){
            //open result activity
            startActivity(new Intent(getApplicationContext(),ResultActivity.class).putExtra("total",total).putExtra("correctans",correct));
            total=1;
        }else{

            mdatabase= FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(total));
            total++;
            mdatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    final Question question =  dataSnapshot.getValue(Question.class);
                    tv_question.setText(question.getQuestions());
                    btn_a.setText(question.getOption1());
                    btn_b.setText(question.getOption2());
                    btn_c.setText(question.getOption3());
                    btn_d.setText(question.getOption4());

                    btn_a.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            restartCountdown();
                            if(btn_a.getText().toString().equals(question.getAnswer())){
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                            btn_a.setBackgroundColor(Color.parseColor("#03A9F4"));

                                            Updatequestion();

                                    }
                                },1500);
                            }else {
                                Toast.makeText(MainActivity.this, "Incorrect answer ", Toast.LENGTH_SHORT).show();
                                wrong=wrong+1;
                                btn_a.setBackgroundColor(Color.RED);
                                if(btn_b.getText().toString().equals(question.getAnswer())){
                                    btn_b.setBackgroundColor(Color.GREEN);
                                }else if(btn_c.getText().toString().equals(question.getAnswer())){
                                    btn_c.setBackgroundColor(Color.GREEN);
                                }else if(btn_d.getText().toString().equals(question.getAnswer())){
                                    btn_d.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btn_a.setBackgroundColor(Color.parseColor("#F57C00"));
                                        btn_b.setBackgroundColor(Color.parseColor("#F57C00"));
                                        btn_c.setBackgroundColor(Color.parseColor("#F57C00"));
                                        btn_d.setBackgroundColor(Color.parseColor("#F57C00"));


                                        Updatequestion();
                                    }
                                },1500);


                            }
                        }
                    });


                    btn_b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            restartCountdown();
                            if(btn_b.getText().toString().equals(question.getAnswer())){
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                            btn_b.setBackgroundColor(Color.parseColor("#03A9F4"));


                                            Updatequestion();

                                    }
                                },1500);
                            }else {
                                wrong=wrong+1;
                                btn_b.setBackgroundColor(Color.RED);
                                if(btn_a.getText().toString().equals(question.getAnswer())){
                                    btn_a.setBackgroundColor(Color.GREEN);
                                }else if(btn_c.getText().toString().equals(question.getAnswer())){
                                    btn_c.setBackgroundColor(Color.GREEN);
                                }else if(btn_d.getText().toString().equals(question.getAnswer())){
                                    btn_d.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btn_a.setBackgroundColor(Color.parseColor("#F57C00"));
                                        btn_b.setBackgroundColor(Color.parseColor("#F57C00"));
                                        btn_c.setBackgroundColor(Color.parseColor("#F57C00"));
                                        btn_d.setBackgroundColor(Color.parseColor("#F57C00"));


                                        Updatequestion();
                                    }
                                },1500);


                            }
                        }
                    });

                    btn_c.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            restartCountdown();
                            if(btn_c.getText().toString().equals(question.getAnswer())){
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                            btn_c.setBackgroundColor(Color.parseColor("#03A9F4"));


                                            Updatequestion();
                                    }
                                },
                                        100);
                            }else {
                                wrong++;
                                btn_c.setBackgroundColor(Color.RED);
                                if(btn_b.getText().toString().equals(question.getAnswer())){
                                    btn_b.setBackgroundColor(Color.GREEN);
                                }else if(btn_a.getText().toString().equals(question.getAnswer())){
                                    btn_a.setBackgroundColor(Color.GREEN);
                                }else if(btn_d.getText().toString().equals(question.getAnswer())){
                                    btn_d.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btn_a.setBackgroundColor(Color.parseColor("#F57C00"));
                                        btn_b.setBackgroundColor(Color.parseColor("#F57C00"));
                                        btn_c.setBackgroundColor(Color.parseColor("#F57C00"));
                                        btn_d.setBackgroundColor(Color.parseColor("#F57C00"));


                                        Updatequestion();
                                    }

                                },1500);

                            }
                        }
                    });


                    btn_d.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            restartCountdown();
                            if(btn_d.getText().toString().equals(question.getAnswer())){
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                            btn_d.setBackgroundColor(Color.parseColor("#03A9FA"));


                                            Updatequestion();


                                    }
                                },1500);
                            }else {
                                wrong++;
                                btn_d.setBackgroundColor(Color.RED);
                                if(btn_b.getText().toString().equals(question.getAnswer())){
                                    btn_b.setBackgroundColor(Color.GREEN);
                                }else if(btn_c.getText().toString().equals(question.getAnswer())){
                                    btn_c.setBackgroundColor(Color.GREEN);
                                }else if(btn_a.getText().toString().equals(question.getAnswer())){
                                    btn_a.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btn_a.setBackgroundColor(Color.parseColor("#D3D3D3"));
                                        btn_b.setBackgroundColor(Color.parseColor("#D3D3D3"));
                                        btn_c.setBackgroundColor(Color.parseColor("#D3D3D3"));
                                        btn_d.setBackgroundColor(Color.parseColor("#D3D3D3"));


                                        Updatequestion();
                                    }
                                },1500);



                            }
                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    public void startCountdown(){

        mcountDownTimer = new CountDownTimer(mTimerinleft_inmils,100) {
            @Override
            public void onTick(long l) {

                mTimerinleft_inmils=l;
                UpdateCountdowntext();
            }

            @Override
            public void onFinish() {
                btn_a.setEnabled(false);
                btn_b.setEnabled(false);
                btn_c.setEnabled(false);
                btn_d.setEnabled(false);

                btn_a.setBackgroundColor(Color.parseColor("#D3D3D3"));
                btn_b.setBackgroundColor(Color.parseColor("#D3D3D3"));
                btn_c.setBackgroundColor(Color.parseColor("#D3D3D3"));
                btn_d.setBackgroundColor(Color.parseColor("#D3D3D3"));


                mTimerrunning=false;



            }
        }.start();
    }
    public  void restartCountdown(){

        mcountDownTimer.cancel();
        mTimerinleft_inmils=START_TIME_INMILIS;

        mcountDownTimer= new CountDownTimer(mTimerinleft_inmils,100) {
            @Override
            public void onTick(long l) {
                mTimerinleft_inmils=l;
                UpdateCountdowntext();
            }

            @Override
            public void onFinish() {

                btn_a.setEnabled(false);
                btn_b.setEnabled(false);
                btn_c.setEnabled(false);
                btn_d.setEnabled(false);
                mTimerrunning=false;


            }
        }.start();
        mTimerrunning=true;

    }

    private void UpdateCountdowntext() {

        int minitues = (int) ((mTimerinleft_inmils/1000)/60);
        int seconds = (int) ((mTimerinleft_inmils/1000)%60);

        String timerformated= String.format(Locale.getDefault(),"%02d:%02d",minitues,seconds);

        tv_time.setText(timerformated);
    }
}
