package id.ac.umn.holdthemout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.sax.StartElementListener;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class LevelTwoActivity extends AppCompatActivity {

    private static final long starttimeinmillis = 8000;
    private TextView commandView, scoreView,TimeLeft, CHEAT;
    private Button btnHTTPS, btnLogout, btnSID256, btnSID512, btnSID3512, btnVPN, btnEncrypt3224, btnEncrypt3256, btnEncrypt3512, btnUpdate;

    private CountDownTimer countdowntimer;
    private long timeleft = starttimeinmillis;

    public double commandFlag;
    public boolean countScoreFlag;
    public int correctFlag=0, wrongFlag=0;
    public int totalScore=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leveltwo);
        TimeLeft = findViewById(R.id.timer);

        CHEAT = findViewById(R.id.command);

        CHEAT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CHEATIntent = new Intent(LevelTwoActivity.this, PreLevelThreeActivity.class);
                startActivity(CHEATIntent);
            }
        });

        startTimer();
        updateTimer();
    }

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
                //KALO GA DI PENCET SELAMA 30 DETIK ISI DISINIIIIII
                //KALO GA DI PENCET SELAMA 5 DETIK BELOM DI EXCEPT HANDLE
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
        btnHTTPS.setClickable(false);
        btnLogout.setClickable(false);
        btnSID256.setClickable(false);
        btnSID512.setClickable(false);
        btnSID3512.setClickable(false);
        btnVPN.setClickable(false);
        btnEncrypt3256.setClickable(false);
        btnEncrypt3224.setClickable(false);
        btnEncrypt3512.setClickable(false);

        Intent intentNext = new Intent(LevelTwoActivity.this, PreLevelThreeActivity.class);
        correctFlag++; //EXCEPTION HANDLING
        LevelTwoActivity.this.finish();
        startActivity(intentNext);
    }

    private void gameOverLose(){
        countdowntimer.cancel();
        btnUpdate.setClickable(false);
        btnHTTPS.setClickable(false);
        btnLogout.setClickable(false);
        btnSID256.setClickable(false);
        btnSID512.setClickable(false);
        btnSID3512.setClickable(false);
        btnVPN.setClickable(false);
        btnEncrypt3256.setClickable(false);
        btnEncrypt3224.setClickable(false);
        btnEncrypt3512.setClickable(false);

        Intent intentNext = new Intent(LevelTwoActivity.this, GameOverActivity.class);
        LevelTwoActivity.this.finish();
        startActivity(intentNext);
    }

    public void countscore() {
        int scoreTemp;
        String tempTime = TimeLeft.getText().toString();
        if (tempTime.equals("0:07")) {
            scoreTemp = 7;
        } else if (tempTime.equals("0:06")) {
            scoreTemp = 6;
        } else if (tempTime.equals("0:05")) {
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

    public void attackStart() {

        commandView = findViewById(R.id.command);
        scoreView = findViewById(R.id.score);
        btnUpdate = findViewById(R.id.updatecredentialsbtn);
        btnHTTPS = findViewById(R.id.usehttpsbtn);
        btnLogout = findViewById(R.id.logoutbtn);
        btnSID256 = findViewById(R.id.sha256btn);
        btnSID512 = findViewById(R.id.sha512btn);
        btnSID3512 = findViewById(R.id.sha3512btn);
        btnVPN = findViewById(R.id.usevpnbtn);
        btnEncrypt3256 = findViewById(R.id.sha3256btn);
        btnEncrypt3224 = findViewById(R.id.sha3224btn);
        btnEncrypt3512 = findViewById(R.id.sha3512encryptbtn);
        int scoreTemp;
        int  subScore;

        scoreView.setText("Score : " + totalScore);

        Log.d("CORRECT", "correctFlag= " + correctFlag);
        Log.d("WRONG", "wrongFlag= " + wrongFlag);
        commandFlag = Math.floor(Math.random() * (11 - 1)) + 1;
        if(commandFlag==1){
            commandView.setText("HTTPS Connection is Needed!");
        }
        else if(commandFlag==2){
            commandView.setText("User Forgot to Log Out!");
        }
        else if(commandFlag==3){
            commandView.setText("Secure SID With SHA256 Encryption is Required!");
        }
        else if(commandFlag==4){
            commandView.setText("Secure SID With SHA512 Encryption is Required!");
        }
        else if(commandFlag==5){
            commandView.setText("Secure SID With SHA3512 Encryption is Required!");
        }
        else if(commandFlag==6){
            commandView.setText("Secure VPN Connection is Needed!");
        }
        else if(commandFlag==7){
            commandView.setText("Data Transmit Using SHA3224 Encryption is Required!");
        }
        else if(commandFlag==8){
            commandView.setText("Data Transmit Using SHA3256 Encryption is Required!");
        }
        else if(commandFlag==9){
            commandView.setText("Data Transmit Using SHA3512 Encryption is Required!");
        }
        else if(commandFlag==10){
            commandView.setText("Username and Password Need to be Updated");
        }

        Log.d("COMMAND", "commandFlag = " + commandFlag);
        if (commandFlag==1)
        {
            Log.d("IN", "1");
            btnHTTPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Clicked", "1");
                    countScoreFlag = true;
                    correctFlag++;
                    countscore();
                    resetTimer();
                }
            });
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnVPN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3224.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
        }
        else if (commandFlag==2)
        {
            Log.d("IN", "1");
            btnHTTPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Clicked", "1");
                    correctFlag++;
                    countscore();
                    resetTimer();
                }
            });
            btnSID256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnVPN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3224.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
        }
        else if (commandFlag==3)
        {
            Log.d("IN", "1");
            btnHTTPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Clicked", "1");
                    correctFlag++;
                    countscore();
                    resetTimer();
                }
            });
            btnSID512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnVPN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3224.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
        }
        else if (commandFlag==4)
        {
            Log.d("IN", "1");
            btnHTTPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Clicked", "1");
                    correctFlag++;
                    countscore();
                    resetTimer();
                }
            });
            btnSID3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnVPN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3224.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
        }
        else if (commandFlag==5)
        {
            Log.d("IN", "1");
            btnHTTPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Clicked", "1");
                    correctFlag++;
                    countscore();
                    resetTimer();
                }
            });
            btnVPN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3224.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
        }
        else if (commandFlag==6)
        {
            Log.d("IN", "1");
            btnHTTPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();

                }
            });
            btnVPN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Clicked", "1");
                    correctFlag++;
                    countscore();
                    resetTimer();
                }
            });
            btnEncrypt3224.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
        }
        else if (commandFlag==7)
        {
            Log.d("IN", "1");
            btnHTTPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();

                }
            });
            btnVPN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3224.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Clicked", "1");
                    correctFlag++;
                    countscore();
                    resetTimer();
                }
            });
            btnEncrypt3256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
        }
        else if (commandFlag==8)
        {
            Log.d("IN", "1");
            btnHTTPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();

                }
            });
            btnVPN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3224.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Clicked", "1");
                    correctFlag++;
                    countscore();
                    resetTimer();
                }
            });
            btnEncrypt3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
        }
        else if (commandFlag==9)
        {
            Log.d("IN", "1");
            btnHTTPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();

                }
            });
            btnVPN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3224.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Clicked", "1");
                    correctFlag++;
                    countscore();
                    resetTimer();
                }
            });
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
        }
        else if (commandFlag==10)
        {
            Log.d("IN", "1");
            btnHTTPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnSID3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();

                }
            });
            btnVPN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3224.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnEncrypt3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    resetTimer();
                }
            });
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Clicked", "1");
                    correctFlag++;
                    countscore();
                    resetTimer();
                }
            });
        }
        if(correctFlag==12){
            Log.d("IN WIN", "");
            commandView.setText("YOU WIN!!!");
            gameOverWin();
        }
        if(wrongFlag==3){
            Log.d("IN LOSE", "");
            commandView.setText("YOU LOSE!!!");
           /* gameOverLose();*/
        }
    }
}
