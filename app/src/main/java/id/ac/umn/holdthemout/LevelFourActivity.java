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
import android.widget.SeekBar;
import android.widget.TextView;

public class LevelFourActivity extends AppCompatActivity {
    private long starttimeinmillis = 8000;
    private TextView commandView, scoreView, TimeLeft, CHEAT, PaymentPercentage;
    private static SeekBar payment;
    private ImageButton btnPayment,btnChangePass, btnBlock, btnHoldTongue, btnThrowbbq, btnFireStaff, btnHoldCampaign, btnHearStaff;
    public String TempPercentage;
    private CountDownTimer countdowntimer;
    private long timeleft = starttimeinmillis;

    public double commandFlag;
    public int correctFlag=0, wrongFlag=0;
    public int totalScore=0, seekcase;

    private MediaPlayer bgm, selectcorrect, selectwrong;
    private Vibrator vibrator;

    public String Username;

    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levelfour);

        sqLiteDatabase=openOrCreateDatabase("htodb", Context.MODE_PRIVATE, null);

        Intent intent = getIntent();
        totalScore = intent.getIntExtra("TotalScore",0);
        Username = intent.getStringExtra("Username");

        TimeLeft = findViewById(R.id.timer);
        Seebbar();

        bgm = MediaPlayer.create(LevelFourActivity.this, R.raw.ingametest);
        bgm.start();
        bgm.setLooping(true);

        selectwrong = MediaPlayer.create(LevelFourActivity.this, R.raw.selectwrong);
        selectcorrect = MediaPlayer.create(LevelFourActivity.this, R.raw.selectcorrect);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        startTimer();
        updateTimer();

    }

    public void Seebbar(){
        payment = (SeekBar)findViewById(R.id.payment);
        payment.incrementProgressBy(10);
        payment.setProgress(0);
        PaymentPercentage= (TextView)findViewById(R.id.percentage);
        PaymentPercentage.setText(payment.getProgress()+"%");
        payment.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progressF_value;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        i/=10;
                        i*=10;
                        progressF_value = i;
                        PaymentPercentage.setText(i+"%");
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        PaymentPercentage.setText(progressF_value+"%");
                        TempPercentage = PaymentPercentage.getText().toString();
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
            }
        }.start();
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

    private void gameOverWin(){
        if(countdowntimer != null){
            countdowntimer.cancel();
        }
        btnChangePass.setClickable(false);
        btnBlock.setClickable(false);
        btnHoldTongue.setClickable(false);
        btnThrowbbq.setClickable(false);
        btnFireStaff.setClickable(false);
        btnHoldCampaign.setClickable(false);
        btnHearStaff.setClickable(false);

        /////////////COBA HIGHSCORE
        Log.d("USERNAMEEEEEEEEEEEEEEE", "Username :" + Username);

//        Cursor c = sqLiteDatabase.rawQuery("Select * From User",null);
//        String username = c.getString(1);
        String insertTotalScore = String.valueOf(totalScore);

        sqLiteDatabase.execSQL("Insert Into User(Username, Highscore)VALUES('" + Username + "','" + insertTotalScore + "')");

        //////////////////

        correctFlag++; //EXCEPTION HANDLING

        Intent intentNext = new Intent(LevelFourActivity.this, WinActivity.class);
        intentNext.putExtra("TotalScore",totalScore);
        startActivity(intentNext);
        LevelFourActivity.this.finish();
    }

    private void gameOverLose(){
        if(countdowntimer != null){
            countdowntimer.cancel();
        }
        btnChangePass.setClickable(false);
        btnBlock.setClickable(false);
        btnHoldTongue.setClickable(false);
        btnThrowbbq.setClickable(false);
        btnFireStaff.setClickable(false);
        btnHoldCampaign.setClickable(false);
        btnHearStaff.setClickable(false);

        /////////////COBA HIGHSCORE
        Log.d("USERNAMEEEEEEEEEEEEEEE", "Username :" + Username);

//        Cursor c = sqLiteDatabase.rawQuery("Select * From User",null);
//        String username = c.getString(1);
        String insertTotalScore = String.valueOf(totalScore);

        sqLiteDatabase.execSQL("Insert Into User(Username, Highscore)VALUES('" + Username + "','" + insertTotalScore + "')");

        //////////////////

        wrongFlag++; //EXCEPTION HANDLING

        Intent intentNext = new Intent(LevelFourActivity.this, GameOverActivity.class);
        intentNext.putExtra("TotalScore",totalScore);
        startActivity(intentNext);
        LevelFourActivity.this.finish();
    }

    public void attackStart() {

        commandView = findViewById(R.id.command);
        scoreView = findViewById(R.id.score);
        btnChangePass = findViewById(R.id.changepass);
        btnBlock = findViewById(R.id.blockacc);
        btnHoldTongue = findViewById(R.id.holdtongue);
        btnThrowbbq = findViewById(R.id.bbqparty);
        btnFireStaff = findViewById(R.id.firestaff);
        btnHoldCampaign = findViewById(R.id.holdcampaign);
        btnHearStaff = findViewById(R.id.hearstaff);
        btnPayment = findViewById(R.id.adjpayment);

        btnChangePass.setImageResource(R.drawable.change_all_passwords_on);
        btnBlock.setImageResource(R.drawable.block_accounts_on);
        btnHoldTongue.setImageResource(R.drawable.hold_their_tongues_on);
        btnThrowbbq.setImageResource(R.drawable.throw_a_bbq_party_on);
        btnFireStaff.setImageResource(R.drawable.fire_a_staff_on);
        btnHoldCampaign.setImageResource(R.drawable.hold_a_campaign_on);
        btnHearStaff.setImageResource(R.drawable.hear_staff_s_counsel_on);
        btnPayment.setImageResource(R.drawable.confirm_payment);


        scoreView.setText("Score : " + totalScore);

        Log.d("CORRECT", "correctFlag= " + correctFlag);
        Log.d("WRONG", "wrongFlag= " + wrongFlag);
        commandFlag = Math.floor(Math.random() * (9 - 1)) + 1;
        if(commandFlag==1){
            commandView.setText("All Passwords Need To Be Changed");
        }
        else if(commandFlag==2){
            commandView.setText("Old Accounts Have To Be Blocked");
        }
        else if(commandFlag==3){
            commandView.setText("You Need To Tell Them To Hold Their Tongues");
        }
        else if(commandFlag==4){
            commandView.setText("We Have To Throw A Party For Everyone");
        }
        else if(commandFlag==5){
            commandView.setText("Fire A Staff");
        }
        else if(commandFlag==6){
            commandView.setText("We Have To Tell Employees What They Need To Know");
        }
        else if(commandFlag==7){
            commandView.setText("We Need To Hear What The Employees Have To Say");
        }
        //command flag 8 khusus seek bar
        else if(commandFlag==8){
            if(payment.getProgress()<20){
                double i = Math.floor(Math.random() * (4-1))+1;
                if(i==1){
                    commandView.setText("Increase The Payment to 10%");
                    seekcase = 1;
                }else if(i==2){
                    commandView.setText("Increase The Payment to 20%");
                    seekcase = 2;
                }else{
                    commandView.setText("Increase The Payment to 50%");
                    seekcase = 3;
                }
            }else if(payment.getProgress()>=30) {
                double x = Math.floor(Math.random()* (4-1))+1;
                if(x==1){
                    commandView.setText("Decrease The Payment to 10%");
                    seekcase = 4;
                }else if(x==2){
                    commandView.setText("Decrease The Payment to 20%");
                    seekcase = 5;
                }else{
                    commandView.setText("No Payment for Them");
                    seekcase = 6;
                }
            }
        }
        Log.d("COMMAND", "commandFlag = " + commandFlag);
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(seekcase==1 && commandFlag==8){
                    if(TempPercentage.equals("10%")){
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
                }else if(seekcase==2  && commandFlag==8){
                    if(TempPercentage.equals("20%")){
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
                }else if(seekcase==3  && commandFlag==8){
                    if(TempPercentage.equals("50%")){
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
                }else if(seekcase==4  && commandFlag==8){
                    if(TempPercentage.equals("10%")){
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
                }else if(seekcase==5  && commandFlag==8){
                    if (TempPercentage.equals("20%")) {
                        correctFlag++;
                        selectcorrect.start();
                        countscore();
                        resetTimer();
                    }else{
                        wrongFlag++;
                        resetTimer();
                    }
                }else if(seekcase==6  && commandFlag==8){
                    if(TempPercentage.equals("0%")){
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

        btnChangePass.setOnClickListener(new View.OnClickListener() {
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

        btnBlock.setOnClickListener(new View.OnClickListener() {
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
        btnHoldTongue.setOnClickListener(new View.OnClickListener() {
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
        btnThrowbbq.setOnClickListener(new View.OnClickListener() {
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
        btnFireStaff.setOnClickListener(new View.OnClickListener() {
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
        btnHoldCampaign.setOnClickListener(new View.OnClickListener() {
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
        btnHearStaff.setOnClickListener(new View.OnClickListener() {
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
        if(correctFlag==15){
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
    public void countscore() {
        int scoreTemp;
        String tempTime = TimeLeft.getText().toString();
        if (tempTime.equals("0:08")) {
            scoreTemp = 8;
        }else if(tempTime.equals("0:07")){
            scoreTemp = 7;
        }else if(tempTime.equals("0:06")){
            scoreTemp = 6;
        }else if(tempTime.equals("0:05")){
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
