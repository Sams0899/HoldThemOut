package id.ac.umn.holdthemout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteTransactionListener;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class LevelTwoActivity extends AppCompatActivity {

    private static final long starttimeinmillis = 8000;
    private TextView commandView, scoreView,TimeLeft, CHEAT;
    private ImageButton btnHTTPS, btnLogout, btnSID256, btnSID512, btnSID3512, btnVPN, btnEncrypt3224, btnEncrypt3256, btnEncrypt3512, btnUpdate;

    private CountDownTimer countdowntimer;
    private long timeleft = starttimeinmillis;

    public double commandFlag;
    public boolean countScoreFlag;
    public int correctFlag=0, wrongFlag=0;
    public int totalScore=0;
    private MediaPlayer bgm, selectcorrect, selectwrong;
    private Vibrator vibrator;

    public String Username;

    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leveltwo);

        sqLiteDatabase=openOrCreateDatabase("htodb", Context.MODE_PRIVATE, null);

        Intent intent = getIntent();
        totalScore = intent.getIntExtra("TotalScore",0);
        Username = intent.getStringExtra("Username");

        TimeLeft = findViewById(R.id.timer);

        CHEAT = findViewById(R.id.command);

        CHEAT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CHEATIntent = new Intent(LevelTwoActivity.this, PreLevelThreeActivity.class);
                startActivity(CHEATIntent);
            }
        });

        bgm = MediaPlayer.create(LevelTwoActivity.this, R.raw.ingametest);
        bgm.start();
        bgm.setLooping(true);

        selectwrong = MediaPlayer.create(LevelTwoActivity.this, R.raw.selectwrong);
        selectcorrect = MediaPlayer.create(LevelTwoActivity.this, R.raw.selectcorrect);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

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
        intentNext.putExtra("TotalScore", totalScore);
        intentNext.putExtra("Username", Username);
        startActivity(intentNext);
        LevelTwoActivity.this.finish();
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

        String insertTotalScore = String.valueOf(totalScore);

        sqLiteDatabase.execSQL("Insert Into User(Username, Highscore)VALUES('" + Username + "','" + insertTotalScore + "')");

        wrongFlag++; //EXCEPTION HANDLING

        Intent intentNext = new Intent(LevelTwoActivity.this, GameOverActivity.class);
        intentNext.putExtra("TotalScore",totalScore);
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

        btnUpdate.setImageResource(R.drawable.update_username_password_on);
        btnHTTPS.setImageResource(R.drawable.https_on);
        btnLogout.setImageResource(R.drawable.log_out_on);
        btnSID256.setImageResource(R.drawable.sha256_on);
        btnSID512.setImageResource(R.drawable.sha512_on);
        btnSID3512.setImageResource(R.drawable.sha3512_on);
        btnVPN.setImageResource(R.drawable.vpn_on);
        btnEncrypt3256.setImageResource(R.drawable.sha3256_on);
        btnEncrypt3224.setImageResource(R.drawable.sha3224_on);
        btnEncrypt3512.setImageResource(R.drawable.sha3512_on_1);
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
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }
            });
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnVPN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3224.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3512.setOnClickListener(new View.OnClickListener() {
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
        else if (commandFlag==2)
        {
            Log.d("IN", "1");
            btnHTTPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Clicked", "1");
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }
            });
            btnSID256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnVPN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3224.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3512.setOnClickListener(new View.OnClickListener() {
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
        else if (commandFlag==3)
        {
            Log.d("IN", "1");
            btnHTTPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Clicked", "1");
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }
            });
            btnSID512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
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
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3512.setOnClickListener(new View.OnClickListener() {
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
        else if (commandFlag==4)
        {
            Log.d("IN", "1");
            btnHTTPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Clicked", "1");
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }
            });
            btnSID3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnVPN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3224.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3512.setOnClickListener(new View.OnClickListener() {
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
        else if (commandFlag==5)
        {
            Log.d("IN", "1");
            btnHTTPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Clicked", "1");
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }
            });
            btnVPN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3224.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3512.setOnClickListener(new View.OnClickListener() {
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
        else if (commandFlag==6)
        {
            Log.d("IN", "1");
            btnHTTPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();

                }
            });
            btnVPN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Clicked", "1");
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }
            });
            btnEncrypt3224.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3512.setOnClickListener(new View.OnClickListener() {
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
        else if (commandFlag==7)
        {
            Log.d("IN", "1");
            btnHTTPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();

                }
            });
            btnVPN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3224.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Clicked", "1");
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }
            });
            btnEncrypt3256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3512.setOnClickListener(new View.OnClickListener() {
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
        else if (commandFlag==8)
        {
            Log.d("IN", "1");
            btnHTTPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();

                }
            });
            btnVPN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3224.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Clicked", "1");
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }
            });
            btnEncrypt3512.setOnClickListener(new View.OnClickListener() {
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
        else if (commandFlag==9)
        {
            Log.d("IN", "1");
            btnHTTPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();

                }
            });
            btnVPN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3224.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Clicked", "1");
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
        }
        else if (commandFlag==10)
        {
            Log.d("IN", "1");
            btnHTTPS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnSID3512.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();

                }
            });
            btnVPN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3224.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3256.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            });
            btnEncrypt3512.setOnClickListener(new View.OnClickListener() {
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
                    Log.d("Clicked", "1");
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }
            });
        }
        if(correctFlag==12){
            Log.d("IN WIN", "");
            commandView.setText("");
            gameOverWin();
        }
        if(wrongFlag==3){
            Log.d("IN LOSE", "");
            commandView.setText("");
            gameOverLose();
        }
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
