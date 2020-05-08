//BUGs: Yang pertama ga msk score

package id.ac.umn.holdthemout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Vibrator;
import android.sax.StartElementListener;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class LevelThreeActivity extends AppCompatActivity {
    private long starttimeinmillis = 10000;
    private TextView commandView, scoreView, TimeLeft, CHEAT, TrafficPercentage, FilterPercentage;
    private static SeekBar Filter, Traffic;
    private ImageButton btnFilter, btnThrottle,btnIntercept, btnBand10, btnBand30, btnBand50, btnDrpAll,btnDrpHalf,btnEncrypt,btnTurnOn,btnUpdate,btnFTP,btnGCD, btnSCD,btnConfig,btnPray;

    private CountDownTimer countdowntimer;
    //    private boolean timerrunning;
    private long timeleft = starttimeinmillis;
    public String tempPercentage, tempPercentage2;
    public double commandFlag;
    public int correctFlag=0, wrongFlag=0;
    public int totalScore=0;

    private MediaPlayer bgm, selectcorrect, selectwrong;
    private Vibrator vibrator;

    public String Username;

//    public Timer timer = new Timer();
//    public final Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levelthree);
        TimeLeft = findViewById(R.id.timer);

        Intent intent = getIntent();
        totalScore = intent.getIntExtra("TotalScore",0);
        Username = intent.getStringExtra("Username");

        bgm = MediaPlayer.create(LevelThreeActivity.this, R.raw.ingametest);
        bgm.start();
        bgm.setLooping(true);

        selectwrong = MediaPlayer.create(LevelThreeActivity.this, R.raw.selectwrong);
        selectcorrect = MediaPlayer.create(LevelThreeActivity.this, R.raw.selectcorrect);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        Seebbar();
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
    public void Seebbar(){
        Filter = (SeekBar)findViewById(R.id.seekfilter);
        Traffic = (SeekBar)findViewById(R.id.seektraffic);
        TrafficPercentage = (TextView)findViewById(R.id.traffic);
        FilterPercentage = (TextView)findViewById(R.id.filter);
        Filter.incrementProgressBy(5);
        Traffic.incrementProgressBy(10);
        Filter.setProgress(0);
        Traffic.setProgress(0);
        TrafficPercentage.setText(Traffic.getProgress()+"%");
        FilterPercentage.setText(Filter.getProgress()+"%");
        Traffic.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progressT_value;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        i/=10;
                        i*=10;
                        progressT_value = i;
                        TrafficPercentage.setText(i+"%");
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        TrafficPercentage.setText(progressT_value+"%");
                        tempPercentage = TrafficPercentage.getText().toString();
                    }
                }
        );
        Filter.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progressF_value;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        i/=5;
                        i*=5;
                        progressF_value = i;
                        FilterPercentage.setText(i+"%");
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        FilterPercentage.setText(progressF_value+"%");
                        tempPercentage2 = FilterPercentage.getText().toString();
                    }
                }
        );

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
        if(countdowntimer != null){
            countdowntimer.cancel();
        }
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

    private void gameOver(){
        if(countdowntimer != null){
            countdowntimer.cancel();
        }
        btnUpdate.setClickable(false);
        btnIntercept.setClickable(false);
        btnBand10.setClickable(false);
        btnBand30.setClickable(false);
        btnBand50.setClickable(false);
        btnDrpHalf.setClickable(false);
        btnDrpAll.setClickable(false);
        btnEncrypt.setClickable(false);
        btnTurnOn.setClickable(false);
        btnFTP.setClickable(false);
        btnGCD.setClickable(false);
        btnSCD.setClickable(false);
        btnConfig.setClickable(false);
        btnPray.setClickable(false);

        correctFlag++; //EXCEPTION HANDLING
        Intent intentNext = new Intent(LevelThreeActivity.this, PreLevelFourActivity.class);
        intentNext.putExtra("TotalScore", totalScore);
        intentNext.putExtra("Username", Username);
        startActivity(intentNext);
        LevelThreeActivity.this.finish();
    }

    public void attackStart() {

        commandView = findViewById(R.id.command);
        scoreView = findViewById(R.id.score);
        btnUpdate = findViewById(R.id.kernelbtn);
        btnIntercept = findViewById(R.id.interceptbtn);
        btnBand10 = findViewById(R.id.B10btn);
        btnBand30 = findViewById(R.id.B30btn);
        btnBand50 = findViewById(R.id.B50btn);
        btnDrpAll = findViewById(R.id.Allbtn);
        btnDrpHalf = findViewById(R.id.Halfbtn);
        btnEncrypt = findViewById(R.id.encryptbtn);
        btnTurnOn = findViewById(R.id.firewallbtn);
        btnFTP = findViewById(R.id.FTPbtn);
        btnGCD = findViewById(R.id.GCDbtn);
        btnSCD = findViewById(R.id.SCDbtn);
        btnConfig = findViewById(R.id.configurebtn);
        btnPray = findViewById(R.id.praybtn);
        btnFilter = findViewById(R.id.filterbtn);
        btnThrottle = findViewById(R.id.throttle);

        btnUpdate.setBackgroundResource(R.drawable.update_kernel_on);
        btnIntercept.setBackgroundResource(R.drawable.intercept_tcp_packets_on);
        btnBand10.setBackgroundResource(R.drawable.ten__on);
        btnBand30.setBackgroundResource(R.drawable.thirty__on);
        btnBand50.setBackgroundResource(R.drawable.fifty__on);
        btnDrpAll.setBackgroundResource(R.drawable.all_on);
        btnDrpHalf.setBackgroundResource(R.drawable.half_on);
        btnEncrypt.setBackgroundResource(R.drawable.encrypt_data_transmission_on);
        btnTurnOn.setBackgroundResource(R.drawable.turn_on_firewall_on);
        btnFTP.setBackgroundResource(R.drawable.ftp_on);
        btnGCD.setBackgroundResource(R.drawable.gcd_on);
        btnSCD.setBackgroundResource(R.drawable.scd_on);
        btnConfig.setBackgroundResource(R.drawable.configure_firewall_on);
        btnPray.setBackgroundResource(R.drawable.pray_harder_on);


        scoreView.setText("Score : " + totalScore);

        Log.d("CORRECT", "correctFlag= " + correctFlag);
        Log.d("WRONG", "wrongFlag= " + wrongFlag);
        commandFlag = Math.floor(Math.random() * (22 - 1)) + 1;
        if(commandFlag==1){
            commandView.setText("Kernel Need To Be Updated");
        }
        else if(commandFlag==2){
            commandView.setText("TCP Packets Need To Be Intercepted");
        }
        else if(commandFlag==3){
            commandView.setText("Increase Bandwidth 10%");
        }
        else if(commandFlag==4){
            commandView.setText("Increase Bandwidth 30%");
        }
        else if(commandFlag==5){
            commandView.setText("Increase Bandwidth 50%");
        }
        else if(commandFlag==6){
            commandView.setText("Drop All of The Connection Request");
        }
        else if(commandFlag==7){
            commandView.setText("Drop Half of The Connection Request");
        }
        else if(commandFlag==8){
            commandView.setText("Data Transmission Need To Be Encrypted");
        }
        else if(commandFlag==9){
            commandView.setText("Firewall Need To Be Turned On");
        }
        else if(commandFlag==10){
            commandView.setText("Disable FTP Service");
        }
        else if(commandFlag==11){
            commandView.setText("Disable GCD Service");
        }
        else if(commandFlag==12){
            commandView.setText("Disable SCD Service");
        }
        else if(commandFlag==13){
            commandView.setText("Firewall Need To Be Reconfigured");
        }
        else if(commandFlag==14){
            commandView.setText("You Need To Praise The Lord");
        }
        //command flag 15++ khusus seek bar
        else if(commandFlag==15){
            commandView.setText("Filter 25% of Incoming Packets");
        }
        else if(commandFlag==16){
            commandView.setText("Filter 50% of Incoming Packets");
        }
        else if(commandFlag==17){
            commandView.setText("Filter 75% of Incoming Packets");
        }
        else if(commandFlag==18){
            commandView.setText("Filter 90% of Incoming Packets");
        }
        else if(commandFlag==19){
            commandView.setText("Throttle Traffic to 10%");
        }
        else if(commandFlag==20){
            commandView.setText("Throttle Traffic to 30%");
        }
        else if(commandFlag==21){
            commandView.setText("Throttle Traffic to 50%");
        }
        Log.d("COMMAND", "commandFlag = " + commandFlag);

        btnThrottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==19){
                    if(tempPercentage.equals("10%")){
                        correctFlag++;
                        selectcorrect.start();
                        countscore();
                        resetTimer();
                    } else{
                        wrongFlag++;
                        vibrator.vibrate(100);
                        selectwrong.start();
                        resetTimer();
                    }
                }else if(commandFlag==20){
                    if(tempPercentage.equals("30%")){
                        correctFlag++;
                        selectcorrect.start();
                        countscore();
                        resetTimer();
                    }else{
                        wrongFlag++;
                        vibrator.vibrate(100);
                        selectwrong.start();
                        resetTimer();
                    }
                }else if(commandFlag==21){
                    if(tempPercentage.equals("50%")){
                        correctFlag++;
                        selectcorrect.start();
                        countscore();
                        resetTimer();
                    }else{
                        wrongFlag++;
                        vibrator.vibrate(100);
                        selectwrong.start();
                        resetTimer();
                    }
                }
                else{
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==15){
                    if(tempPercentage2.equals("25%")){
                        correctFlag++;
                        selectcorrect.start();
                        countscore();
                        resetTimer();
                    }else{
                        wrongFlag++;
                        vibrator.vibrate(100);
                        selectwrong.start();
                        resetTimer();
                    }
                }else if(commandFlag==16){
                    if(tempPercentage2.equals("50%")){
                        correctFlag++;
                        selectcorrect.start();
                        countscore();
                        resetTimer();
                    }else{
                        wrongFlag++;
                        vibrator.vibrate(100);
                        selectwrong.start();
                        resetTimer();
                    }
                }else if(commandFlag==17){
                    if(tempPercentage2.equals("75%")){
                        correctFlag++;
                        selectcorrect.start();
                        countscore();
                        resetTimer();
                    }else{
                        wrongFlag++;
                        vibrator.vibrate(100);
                        selectwrong.start();
                        resetTimer();
                    }
                }else if(commandFlag==18){
                    if(tempPercentage2.equals("90%")){
                        correctFlag++;
                        selectcorrect.start();
                        countscore();
                        resetTimer();
                    }else{
                        wrongFlag++;
                        vibrator.vibrate(100);
                        selectwrong.start();
                        resetTimer();
                    }
                }
                else{
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==1) {
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            }
        });

        btnIntercept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==2){
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            }
        });
        btnBand10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==3){
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            }
        });
        btnBand30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==4){
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            }
        });
        btnBand50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==5){
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            }
        });
        btnDrpAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==6){
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            }
        });
        btnDrpHalf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==7){
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            }
        });
        btnEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==8){
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            }
        });
        btnTurnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==9){
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            }
        });
        btnFTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==10){
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            }
        });
        btnGCD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==11){
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            }
        });
        btnSCD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==12){
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            }
        });
        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==13){
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            }
        });
        btnPray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==14){
                    correctFlag++;
                    selectcorrect.start();
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    vibrator.vibrate(100);
                    selectwrong.start();
                    resetTimer();
                }
            }
        });
        if(correctFlag==15){
            Log.d("IN WIN", "");
            commandView.setText("YOU WIN!!!");
            gameOver();
        }
        if(wrongFlag==3){
            Log.d("IN LOSE", "");
            commandView.setText("YOU LOSE!!!");
            gameOver();
        }
    }
    public void countscore() {
        int scoreTemp;
        String tempTime = TimeLeft.getText().toString();
        if(tempTime.equals("0:10")){
            scoreTemp = 10;
        }
        else if(tempTime.equals("0:09")){
            scoreTemp = 9;
        }
        else if(tempTime.equals("0:08")){
            scoreTemp = 8;
        }
        else if(tempTime.equals("0:07")){
            scoreTemp = 7;
        }else if(tempTime.equals("0:06")){
            scoreTemp = 6;
        }
        else if(tempTime.equals("0:05")) {
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
