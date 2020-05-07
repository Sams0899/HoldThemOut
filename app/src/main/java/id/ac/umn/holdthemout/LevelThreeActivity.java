//BUGs: Yang pertama ga msk score

package id.ac.umn.holdthemout;

import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class LevelThreeActivity extends AppCompatActivity {
    private long starttimeinmillis = 6000;
    private TextView commandView, scoreView, TimeLeft, CHEAT, TrafficPercentage, FilterPercentage;
    private static SeekBar Filter, Traffic;
    private Button btnIntercept, btnBand10, btnBand30, btnBand50, btnDrpAll,btnDrpHalf,btnEncrypt,btnTurnOn,btnUpdate,btnFTP,btnGCD, btnSCD,btnConfig,btnPray;

    private CountDownTimer countdowntimer;
    //    private boolean timerrunning;
    private long timeleft = starttimeinmillis;

    public double commandFlag;
    public int correctFlag=0, wrongFlag=0;
    public int totalScore=0;


//    public Timer timer = new Timer();
//    public final Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levelthree);
        TimeLeft = findViewById(R.id.timer);
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
        TrafficPercentage.setText(Traffic.getProgress()+"%");
        FilterPercentage.setText(Filter.getProgress()+"%");
        Traffic.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progressT_value;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        progressT_value = i;
                        TrafficPercentage.setText(i+"%");
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        TrafficPercentage.setText(progressT_value+"%");
                    }
                }
        );
        Filter.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progressF_value;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        progressF_value = i;
                        FilterPercentage.setText(i+"%");
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        FilterPercentage.setText(progressF_value+"%");
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

//        Intent intentNext = new Intent(LevelThreeActivity.this, PreLevelTwoActivity.class);
//        startActivity(intentNext);
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
            commandView.setText("Drop All of Connection Requests");
        }
        else if(commandFlag==7){
            commandView.setText("Drop Half of Connection Requests");
        }
        else if(commandFlag==8){
            commandView.setText("Data Transmit Have To Be Encrypted");
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
            commandView.setText("You Need To Praise The Lord Harder");
        }
        //command flag 15++ khusus seek bar
        else if(commandFlag==15){
            commandView.setText("Filter 25% of The Incoming Packets");
            String tempTime = TimeLeft.getText().toString();
            if (tempTime.equals("0:05")) {
                String PercentageTraffic = FilterPercentage.getText().toString();
                if(PercentageTraffic.equals("25%")){
                    totalScore+=5;
                    resetTimer();
                }
            }else if(tempTime.equals("0:04")){
                String PercentageTraffic = FilterPercentage.getText().toString();
                if(PercentageTraffic.equals("25%")){
                    totalScore+=4;
                    resetTimer();
                }
            }else if(tempTime.equals("0:03")){
                String PercentageTraffic = FilterPercentage.getText().toString();
                if(PercentageTraffic.equals("25%")){
                    totalScore+=3;
                    resetTimer();
                }
            }else if(tempTime.equals("0:02")){
                String PercentageTraffic = FilterPercentage.getText().toString();
                if(PercentageTraffic.equals("25%")){
                    totalScore+=2;
                    resetTimer();
                }
            }else if(tempTime.equals("0:01")){
                String PercentageTraffic = FilterPercentage.getText().toString();
                if(PercentageTraffic.equals("25%")){
                    totalScore+=1;
                    resetTimer();
                }
            }else{
                wrongFlag++;
                resetTimer();
            }
        }
        else if(commandFlag==16){
            commandView.setText("Filter 50% of The Incoming Packets");
            String tempTime = TimeLeft.getText().toString();
            if (tempTime.equals("0:05")) {
                String PercentageTraffic = FilterPercentage.getText().toString();
                if(PercentageTraffic.equals("50%")){
                    totalScore+=5;
                    resetTimer();
                }
            }else if(tempTime.equals("0:04")){
                String PercentageTraffic = FilterPercentage.getText().toString();
                if(PercentageTraffic.equals("50%")){
                    totalScore+=4;
                    resetTimer();
                }
            }else if(tempTime.equals("0:03")){
                String PercentageTraffic = FilterPercentage.getText().toString();
                if(PercentageTraffic.equals("50%")){
                    totalScore+=3;
                    resetTimer();
                }
            }else if(tempTime.equals("0:02")){
                String PercentageTraffic = FilterPercentage.getText().toString();
                if(PercentageTraffic.equals("50%")){
                    totalScore+=2;
                    resetTimer();
                }
            }else if(tempTime.equals("0:01")){
                String PercentageTraffic = FilterPercentage.getText().toString();
                if(PercentageTraffic.equals("50%")){
                    totalScore+=1;
                    resetTimer();
                }
            }else{
                wrongFlag++;
                resetTimer();
            }
        }
        else if(commandFlag==17){
            commandView.setText("Filter 75% of The Incoming Packets");
            String tempTime = TimeLeft.getText().toString();
            if (tempTime.equals("0:05")) {
                String PercentageTraffic = FilterPercentage.getText().toString();
                if(PercentageTraffic.equals("75%")){
                    totalScore+=5;
                    resetTimer();
                }
            }else if(tempTime.equals("0:04")){
                String PercentageTraffic = FilterPercentage.getText().toString();
                if(PercentageTraffic.equals("75%")){
                    totalScore+=4;
                    resetTimer();
                }
            }else if(tempTime.equals("0:03")){
                String PercentageTraffic = FilterPercentage.getText().toString();
                if(PercentageTraffic.equals("75%")){
                    totalScore+=3;
                    resetTimer();
                }
            }else if(tempTime.equals("0:02")){
                String PercentageTraffic = FilterPercentage.getText().toString();
                if(PercentageTraffic.equals("75%")){
                    totalScore+=2;
                    resetTimer();
                }
            }else if(tempTime.equals("0:01")){
                String PercentageTraffic = FilterPercentage.getText().toString();
                if(PercentageTraffic.equals("75%")){
                    totalScore+=1;
                    resetTimer();
                }
            }else{
                wrongFlag++;
                resetTimer();
            }
        }
        else if(commandFlag==18){
            commandView.setText("Filter 90% of The Incoming Packets");
            String tempTime = TimeLeft.getText().toString();
            if (tempTime.equals("0:05")) {
                String PercentageTraffic = FilterPercentage.getText().toString();
                if(PercentageTraffic.equals("90%")){
                    totalScore+=5;
                    resetTimer();
                }
            }else if(tempTime.equals("0:04")){
                String PercentageTraffic = FilterPercentage.getText().toString();
                if(PercentageTraffic.equals("90%")){
                    totalScore+=4;
                    resetTimer();
                }
            }else if(tempTime.equals("0:03")){
                String PercentageTraffic = FilterPercentage.getText().toString();
                if(PercentageTraffic.equals("90%")){
                    totalScore+=3;
                    resetTimer();
                }
            }else if(tempTime.equals("0:02")){
                String PercentageTraffic = FilterPercentage.getText().toString();
                if(PercentageTraffic.equals("90%")){
                    totalScore+=2;
                    resetTimer();
                }
            }else if(tempTime.equals("0:01")){
                String PercentageTraffic = FilterPercentage.getText().toString();
                if(PercentageTraffic.equals("90%")){
                    totalScore+=1;
                    resetTimer();
                }
            }else{
                wrongFlag++;
                resetTimer();
            }
        }
        else if(commandFlag==19){
            commandView.setText("Throttle Down 10% of The Traffic");
            String tempTime = TimeLeft.getText().toString();
            if (tempTime.equals("0:05")) {
                String PercentageTraffic = TrafficPercentage.getText().toString();
                if(PercentageTraffic.equals("10%")){
                    totalScore+=5;
                    resetTimer();
                }
            }else if(tempTime.equals("0:04")){
                String PercentageTraffic = TrafficPercentage.getText().toString();
                if(PercentageTraffic.equals("10%")){
                    totalScore+=4;
                    resetTimer();
                }
            }else if(tempTime.equals("0:03")){
                String PercentageTraffic = TrafficPercentage.getText().toString();
                if(PercentageTraffic.equals("10%")){
                    totalScore+=3;
                    resetTimer();
                }
            }else if(tempTime.equals("0:02")){
                String PercentageTraffic = TrafficPercentage.getText().toString();
                if(PercentageTraffic.equals("10%")){
                    totalScore+=2;
                    resetTimer();
                }
            }else if(tempTime.equals("0:01")){
                String PercentageTraffic = TrafficPercentage.getText().toString();
                if(PercentageTraffic.equals("10%")){
                    totalScore+=1;
                    resetTimer();
                }
            }else{
                wrongFlag++;
                resetTimer();
            }
        }
        else if(commandFlag==20){
            commandView.setText("Throttle Down 20% of The Traffic");
            String tempTime = TimeLeft.getText().toString();
            if (tempTime.equals("0:05")) {
                String PercentageTraffic = TrafficPercentage.getText().toString();
                if(PercentageTraffic.equals("30%")){
                    totalScore+=5;
                    resetTimer();
                }
            }else if(tempTime.equals("0:04")){
                String PercentageTraffic = TrafficPercentage.getText().toString();
                if(PercentageTraffic.equals("30%")){
                    totalScore+=4;
                    resetTimer();
                }
            }else if(tempTime.equals("0:03")){
                String PercentageTraffic = TrafficPercentage.getText().toString();
                if(PercentageTraffic.equals("30%")){
                    totalScore+=3;
                    resetTimer();
                }
            }else if(tempTime.equals("0:02")){
                String PercentageTraffic = TrafficPercentage.getText().toString();
                if(PercentageTraffic.equals("30%")){
                    totalScore+=2;
                    resetTimer();
                }
            }else if(tempTime.equals("0:01")){
                String PercentageTraffic = TrafficPercentage.getText().toString();
                if(PercentageTraffic.equals("30%")){
                    totalScore+=1;
                    resetTimer();
                }
            }else{
                wrongFlag++;
                resetTimer();
            }
        }
        else if(commandFlag==21){
            commandView.setText("Throttle Down 50% of The Traffic");
            String tempTime = TimeLeft.getText().toString();
            if (tempTime.equals("0:05")) {
                String PercentageTraffic = TrafficPercentage.getText().toString();
                if(PercentageTraffic.equals("50%")){
                    totalScore+=5;
                    resetTimer();
                }
            }else if(tempTime.equals("0:04")){
                String PercentageTraffic = TrafficPercentage.getText().toString();
                if(PercentageTraffic.equals("50%")){
                    totalScore+=4;
                    resetTimer();
                }
            }else if(tempTime.equals("0:03")){
                String PercentageTraffic = TrafficPercentage.getText().toString();
                if(PercentageTraffic.equals("50%")){
                    totalScore+=3;
                    resetTimer();
                }
            }else if(tempTime.equals("0:02")){
                String PercentageTraffic = TrafficPercentage.getText().toString();
                if(PercentageTraffic.equals("50%")){
                    totalScore+=2;
                    resetTimer();
                }
            }else if(tempTime.equals("0:01")){
                String PercentageTraffic = TrafficPercentage.getText().toString();
                if(PercentageTraffic.equals("50%")){
                    totalScore+=1;
                    resetTimer();
                }
            }else{
                wrongFlag++;
                resetTimer();
            }
        }
        Log.d("COMMAND", "commandFlag = " + commandFlag);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==1) {
                    correctFlag++;
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    resetTimer();
                }
            }
        });

        btnIntercept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==2){
                    correctFlag++;
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    resetTimer();
                }
            }
        });
        btnBand10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==3){
                    correctFlag++;
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    resetTimer();
                }
            }
        });
        btnBand30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==4){
                    correctFlag++;
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    resetTimer();
                }
            }
        });
        btnBand50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==5){
                    correctFlag++;
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    resetTimer();
                }
            }
        });
        btnDrpAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==6){
                    correctFlag++;
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    resetTimer();
                }
            }
        });
        btnDrpHalf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==7){
                    correctFlag++;
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    resetTimer();
                }
            }
        });
        btnEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==8){
                    correctFlag++;
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    resetTimer();
                }
            }
        });
        btnTurnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==9){
                    correctFlag++;
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    resetTimer();
                }
            }
        });
        btnFTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==10){
                    correctFlag++;
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    resetTimer();
                }
            }
        });
        btnGCD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==11){
                    correctFlag++;
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    resetTimer();
                }
            }
        });
        btnSCD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==12){
                    correctFlag++;
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    resetTimer();
                }
            }
        });
        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==13){
                    correctFlag++;
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
                    resetTimer();
                }
            }
        });
        btnPray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(commandFlag==14){
                    correctFlag++;
                    countscore();
                    resetTimer();
                }else{
                    wrongFlag++;
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
}
