//BUGs: Yang pertama ga msk score

package id.ac.umn.holdthemout;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class LevelOneActivity extends AppCompatActivity {

    private long starttimeinmillis = 6000;
    private TextView commandView, scoreView, TimeLeft, CHEAT;
    private ImageButton btnUpdate, btnDont, btnApprove, btnInform, btnClean;

    private CountDownTimer countdowntimer;
    //    private boolean timerrunning;
    private long timeleft = starttimeinmillis;

    public double commandFlag;
    public boolean updateFlag=false, dontFlag=false, approveFlag=false, informFlag=false, cleanFlag=false, countScoreFlag;
    public int correctFlag=0, wrongFlag=0;
    public long tStart, tEnd;
    public int totalScore=0;
    private MediaPlayer bgm, selectcorrect, selectwrong;
    private Vibrator vibrator;
    String Username;

//    public Timer timer = new Timer();
//    public final Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levelone);

        Intent intent = getIntent();
        Username = intent.getStringExtra("Username");

        CHEAT = findViewById(R.id.command);
        TimeLeft = findViewById(R.id.timer);

        CHEAT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CHEATIntent = new Intent(LevelOneActivity.this, PreLevelTwoActivity.class);
                startActivity(CHEATIntent);
            }
        });

        bgm = MediaPlayer.create(LevelOneActivity.this, R.raw.ingametest);
        bgm.start();
        bgm.setLooping(true);

        selectwrong = MediaPlayer.create(LevelOneActivity.this, R.raw.selectwrong);
        selectcorrect = MediaPlayer.create(LevelOneActivity.this, R.raw.selectcorrect);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        btnUpdate = findViewById(R.id.updateantivirusbtn);
        btnDont = findViewById(R.id.dontopenattachmentbtn);
        btnApprove = findViewById(R.id.approvesafecodesbtn);
        btnInform = findViewById(R.id.informvirusthreatbtn);
        btnClean = findViewById(R.id.cleanregistrybtn);

        btnUpdate.setImageResource(R.drawable.update_on);
        btnDont.setImageResource(R.drawable.attachment_on);
        btnApprove.setImageResource(R.drawable.approve_on);
        btnInform.setImageResource(R.drawable.inform_on);
        btnClean.setImageResource(R.drawable.clean_on);

        startTimer();
        updateTimer();

    }

//    public void startTimer(){
//
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        attackStart();
//                    }
//                });
//            }
//        }, 1000, 5000);
//    }

    private void startTimer(){
        attackStart();
        countdowntimer = new CountDownTimer(starttimeinmillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeleft = millisUntilFinished;
                updateTimer(); //Ubah textview Timer
            }

            @Override
            public void onFinish() {
                resetTimer();
                attackStart();
            }
        }.start();
//        timerrunning = true;
    }

    private void resetTimer() {
        timeleft = starttimeinmillis;
        countdowntimer.cancel();
        startTimer();
    }

    // Ubah textview Timer
    public void updateTimer(){
        int minutes =(int) timeleft/60000;
        int seconds =(int) timeleft%60000/1000;

        String timeLeftText;

        timeLeftText = ""+minutes;
        timeLeftText += ":";
        if(seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;
        TimeLeft.setText(timeLeftText);
    }

    private void gameOverWin(){
        countdowntimer.cancel();
        btnUpdate.setClickable(false);
        btnDont.setClickable(false);
        btnApprove.setClickable(false);
        btnInform.setClickable(false);
        btnClean.setClickable(false);

//        if(wrongFlag==0) totalScore = totalScore + 30;
//        else if(wrongFlag==1) totalScore = totalScore +20;
//        else if(wrongFlag==2) totalScore = totalScore +10;

        Intent intentNext = new Intent(LevelOneActivity.this, PreLevelTwoActivity.class);
        correctFlag++; //EXCEPTION HANDLING
        intentNext.putExtra("TotalScore",totalScore);
        intentNext.putExtra("Username", Username);
        startActivity(intentNext);
        LevelOneActivity.this.finish();
    }

    private void gameOverLose(){
        countdowntimer.cancel();
        btnUpdate.setClickable(false);
        btnDont.setClickable(false);
        btnApprove.setClickable(false);
        btnInform.setClickable(false);
        btnClean.setClickable(false);

        Intent intentGameOver = new Intent(LevelOneActivity.this, GameOverActivity.class);
        startActivity(intentGameOver);
        LevelOneActivity.this.finish();

    }

    public void attackStart() {

        commandView = findViewById(R.id.command);
        scoreView = findViewById(R.id.score);

//        btnUpdate.setClickable(false);
//        btnDont.setClickable(false);
//        btnApprove.setClickable(false);
//        btnInform.setClickable(false);
//        btnClean.setClickable(false);
        updateFlag = false;
        dontFlag = false;
        approveFlag = false;
        informFlag = false;
        cleanFlag = false;

        scoreView.setText("Score : " + totalScore);

        Log.d("CORRECT", "correctFlag= " + correctFlag);
        Log.d("WRONG", "wrongFlag= " + wrongFlag);
        commandFlag = Math.floor(Math.random() * (6 - 1)) + 1;
        if(commandFlag==1){
            commandView.setText("Antivirus is out of date!");
        }
        else if(commandFlag==2){
            commandView.setText("Unknown Email with suspicious attachment is received");
        }
        else if(commandFlag==3){
            commandView.setText("Codes need your approval!");
        }
        else if(commandFlag==4){
            commandView.setText("We are being attacked by some Viruses!");
        }
        else if(commandFlag==5){
            commandView.setText("Registry need to be cleaned!");
        }
        Log.d("COMMAND", "commandFlag = " + commandFlag);
        if (commandFlag==1)
        {
            Log.d("IN", "1");
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Clicked", "1");
                    updateFlag = true;
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }
            });
            btnDont.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();

                }
            });
            btnApprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();

                }
            });
            btnInform.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();

                }
            });
            btnClean.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();

                }
            });
        }
        else if (commandFlag==2)
        {
            Log.d("IN", "2");
            btnDont.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Clicked", "2");
                    dontFlag = true;
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }
            });
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnInform.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnApprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnClean.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
        }
        else if (commandFlag==3)
        {
            Log.d("IN", "3");
            btnApprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Clicked", "3");
                    approveFlag = true;
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }
            });
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnDont.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnInform.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnClean.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
        }
        else if (commandFlag==4)
        {
            Log.d("IN", "4");
            btnInform.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Clicked", "4");
                    informFlag = true;
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }
            });
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnDont.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnApprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnClean.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
        }
        else if (commandFlag==5)
        {
            Log.d("IN", "5");
            btnClean.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Log.d("Clicked", "5");
                    cleanFlag = true;
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }
            });
            btnDont.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnApprove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnInform.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
        }

        if(correctFlag==7){
            Log.d("IN WIN", "");
            commandView.setText("YOU WIN!!!");
            gameOverWin();
        }
        if(wrongFlag==3){
            Log.d("IN LOSE", "");
            commandView.setText("YOU LOSE!!!");
         /*   gameOverLose();*/
        }
    }
    public void countscore() {
        int scoreTemp;
        String tempTime = TimeLeft.getText().toString();
        if (tempTime.equals("0:05")) {
            scoreTemp = 5;
        } else if (tempTime.equals("0:04")) {
            scoreTemp = 4;
        } else if (tempTime.equals("0:03")) {
            scoreTemp = 3;
        } else if (tempTime.equals("0:02")) {
            scoreTemp = 2;
        } else if (tempTime.equals("0:01")) {
            scoreTemp = 1;
        } else {
            scoreTemp = 0;
        }
        totalScore += scoreTemp;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bgm.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bgm.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bgm.start();
    }
}
