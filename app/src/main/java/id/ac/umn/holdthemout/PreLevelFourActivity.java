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
                    wrongFlag1 = true;
                    btnHire.setBackgroundResource(R.drawable.hire_more_staff_on);
                    select.start();
                }
                else
                {
                    wrongFlag1 = false;
                    btnHire.setBackgroundResource(R.drawable.hire_more_staff_off);
                    select.start();
                }
            }
        });

        btnProvide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (provideFlag == false)
                {
                    provideFlag = true;
                    btnProvide.setBackgroundResource(R.drawable.provide_training_for_staff_on);
                    select.start();
                }
                else
                {
                    provideFlag = false;
                    btnProvide.setBackgroundResource(R.drawable.provide_training_for_staff_off);
                    select.start();
                }
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (orderFlag == false)
                {
                    orderFlag = true;
                    btnOrder.setBackgroundResource(R.drawable.order_staff_to_sign_statement_on);
                    select.start();
                }
                else
                {
                    orderFlag  = false;
                    btnOrder.setBackgroundResource(R.drawable.order_staff_to_sign_statement_off);
                    select.start();
                }
            }
        });

        btnDefine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (defineFlag == false)
                {
                    defineFlag = true;
                    btnDefine.setBackgroundResource(R.drawable.define_rules_and_consequences_on);
                    select.start();
                }
                else
                {
                    defineFlag = false;
                    btnDefine.setBackgroundResource(R.drawable.define_rules_and_consequences_off);
                    select.start();
                }
            }
        });

        btnAnalyze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wrongFlag2 == false)
                {
                    wrongFlag2 = true;
                    btnAnalyze.setBackgroundResource(R.drawable.analize_incoming_signal_on);
                    select.start();
                }
                else
                {
                    wrongFlag2 = false;
                    btnAnalyze.setBackgroundResource(R.drawable.analize_incoming_signal_off);
                    select.start();
                }
            }
        });
        btnDisable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wrongFlag3 == false)
                {
                    wrongFlag3 = true;
                    btnDisable.setBackgroundResource(R.drawable.disable_all_services_on);
                    select.start();
                }
                else
                {
                    wrongFlag3 = false;
                    btnDisable.setBackgroundResource(R.drawable.disable_all_services_off);
                    select.start();
                }
            }
        });

        btnReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (provideFlag==true && orderFlag==true && defineFlag==true && wrongFlag1==false && wrongFlag2==false && wrongFlag3==false)
                {
                    btnStart.setImageResource(R.drawable.start_on);
                    Intent nextIntent = new Intent(PreLevelFourActivity.this, LevelFourActivity.class);
                    nextIntent.putExtra("TotalScore", totalScore);
                    nextIntent.putExtra("Username", Username);
                    startActivity(nextIntent);
                    PreLevelFourActivity.this.finish();
                }
                else
                {
                    wrong.start();
                    vibrator.vibrate(500);
                }
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
