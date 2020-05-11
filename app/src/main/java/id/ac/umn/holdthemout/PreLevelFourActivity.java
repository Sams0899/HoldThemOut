package id.ac.umn.holdthemout;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PreLevelFourActivity extends AppCompatActivity {
    private ImageButton btnProvide, btnOrder, btnDefine, btnHire, btnAnalyze, btnDisable, btnReady, btnStart;
    private TextView CHEAT;
    private MediaPlayer bgm;
    private Vibrator vibrator;
    public boolean provideFlag=false, orderFlag=false, defineFlag=false, wrongFlag1=false, wrongFlag2=false, wrongFlag3=false;
    int totalScore;
    String Username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prelevelfour);

        Intent intent = getIntent();
        totalScore = intent.getIntExtra("TotalScore",0);
        Username = intent.getStringExtra("Username");

        btnProvide = findViewById(R.id.provide);
        btnOrder = findViewById(R.id.order);
        btnDefine = findViewById(R.id.definerules);
        btnHire = findViewById(R.id.hirestaff);
        btnAnalyze = findViewById(R.id.analyzesignal);
        btnDisable = findViewById(R.id.disableservices);
        btnReady = findViewById(R.id.readybtn);
        btnStart = findViewById(R.id.startbtn);
        btnStart.setClickable(false);

        btnProvide.setImageResource(R.drawable.provide_training_for_staff_off);
        btnOrder.setImageResource(R.drawable.order_staff_to_sign_statement_off);
        btnDefine.setImageResource(R.drawable.define_rules_and_consequences_off);
        btnHire.setImageResource(R.drawable.hire_more_staff_off);
        btnAnalyze.setImageResource(R.drawable.analize_incoming_signal_off);
        btnDisable.setImageResource(R.drawable.disable_all_services_off);
        btnReady.setImageResource(R.drawable.ready_on);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        bgm = MediaPlayer.create(PreLevelFourActivity.this, R.raw.preattacktest);
        bgm.start();
        bgm.setLooping(true);

        final MediaPlayer select = MediaPlayer.create(PreLevelFourActivity.this, R.raw.select);
        final MediaPlayer wrong = MediaPlayer.create(PreLevelFourActivity.this, R.raw.wrong);
        final MediaPlayer selectcorrect = MediaPlayer.create(PreLevelFourActivity.this, R.raw.selectcorrect);

        CHEAT = findViewById(R.id.command);

        CHEAT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CHEATIntent = new Intent(PreLevelFourActivity.this, LevelThreeActivity.class);
                startActivity(CHEATIntent);
            }
        });

        btnHire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wrongFlag1 == false)
                {
                    select.start();
                    wrongFlag1 = true;
                    btnHire.setImageResource(R.drawable.hire_more_staff_on);
                }
                else
                {
                    select.start();
                    wrongFlag1 = false;
                    btnHire.setImageResource(R.drawable.hire_more_staff_off);
                }
            }
        });

        btnProvide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (provideFlag == false)
                {
                    select.start();
                    provideFlag = true;
                    btnProvide.setImageResource(R.drawable.provide_training_for_staff_on);
                }
                else
                {
                    select.start();
                    provideFlag = false;
                    btnProvide.setImageResource(R.drawable.provide_training_for_staff_off);
                }
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (orderFlag == false)
                {
                    select.start();
                    orderFlag = true;
                    btnOrder.setImageResource(R.drawable.order_staff_to_sign_statement_on);
                }
                else
                {
                    select.start();
                    orderFlag  = false;
                    btnOrder.setImageResource(R.drawable.order_staff_to_sign_statement_off);
                }
            }
        });

        btnDefine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (defineFlag == false)
                {
                    select.start();
                    defineFlag = true;
                    btnDefine.setImageResource(R.drawable.define_rules_and_consequences_on);
                }
                else
                {
                    select.start();
                    defineFlag = false;
                    btnDefine.setImageResource(R.drawable.define_rules_and_consequences_off);
                }
            }
        });

        btnAnalyze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wrongFlag2 == false)
                {
                    select.start();
                    wrongFlag2 = true;
                    btnAnalyze.setImageResource(R.drawable.analize_incoming_signal_on);
                }
                else
                {
                    select.start();
                    wrongFlag2 = false;
                    btnAnalyze.setImageResource(R.drawable.analize_incoming_signal_off);
                }
            }
        });
        btnDisable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wrongFlag3 == false)
                {
                    select.start();
                    wrongFlag3 = true;
                    btnDisable.setImageResource(R.drawable.disable_all_services_on);
                }
                else
                {
                    select.start();
                    wrongFlag3 = false;
                    btnDisable.setImageResource(R.drawable.disable_all_services_off);
            }
            }
        });

        btnReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (provideFlag==true && orderFlag==true && defineFlag==true && wrongFlag1==false && wrongFlag2==false && wrongFlag3==false)
                {
                    selectcorrect.start();
                    btnStart.setImageResource(R.drawable.start_on);
                    btnStart.setClickable(true);
                    btnProvide.setClickable(false);
                    btnOrder.setClickable(false);
                    btnDefine.setClickable(false);
                    btnHire.setClickable(false);
                    btnAnalyze.setClickable(false);
                    btnDisable.setClickable(false);
                    btnReady.setClickable(false);
                }
                else
                {
                    wrong.start();
                    vibrator.vibrate(500);
                }
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextIntent = new Intent(PreLevelFourActivity.this, LevelFourActivity.class);
                nextIntent.putExtra("TotalScore", totalScore);
                nextIntent.putExtra("Username", Username);
                startActivity(nextIntent);
                PreLevelFourActivity.this.finish();
            }
        });
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
