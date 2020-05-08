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

public class PreLevelThreeActivity extends AppCompatActivity {
    private ImageButton btnProfile, btnSignal, btnHoneypots, btnTurnoff, btnVPN, btnAllConn;
    private Button btnReady;
    private TextView CHEAT;
    private MediaPlayer bgm;
    private Vibrator vibrator;
    public boolean profileFlag=false, signalFlag=false, honeypotsFlag=false, wrongFlag1=false, wrongFlag2=false, wrongFlag3=false;
    int totalScore;
    String Username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prelevelthree);

        Intent intent = getIntent();
        totalScore = intent.getIntExtra("TotalScore",0);
        Username = intent.getStringExtra("Username");

        btnProfile = findViewById(R.id.profileweb);
        btnSignal = findViewById(R.id.analyzesign);
        btnHoneypots = findViewById(R.id.honeypotsactive);
        btnTurnoff = findViewById(R.id.turnofffirewallbtn);
        btnVPN = findViewById(R.id.implementvpn);
        btnAllConn = findViewById(R.id.acceptconnect);
        btnReady= findViewById(R.id.readybtn);

        btnProfile.setImageResource(R.drawable.profile_web_activity_off);
        btnSignal.setImageResource(R.drawable.analize_incomming_signal_off);
        btnHoneypots.setImageResource(R.drawable.activate_honeypots_off);
        btnTurnoff.setImageResource(R.drawable.turn_off_firewall_off);
        btnVPN.setImageResource(R.drawable.implement_vpn_off);
        btnAllConn.setImageResource(R.drawable.accept_all_connection_off);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        bgm = MediaPlayer.create(PreLevelThreeActivity.this, R.raw.preattacktest);
        bgm.start();
        bgm.setLooping(true);

        final MediaPlayer select = MediaPlayer.create(PreLevelThreeActivity.this, R.raw.select);
        final MediaPlayer wrong = MediaPlayer.create(PreLevelThreeActivity.this, R.raw.wrong);

        CHEAT = findViewById(R.id.command);

        CHEAT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CHEATIntent = new Intent(PreLevelThreeActivity.this, LevelThreeActivity.class);
                startActivity(CHEATIntent);
            }
        });

        btnVPN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wrongFlag1 == false)
                {
                    wrongFlag1 = true;
                    btnVPN.setImageResource(R.drawable.implement_vpn_on);
                    select.start();
                }
                else
                {
                    wrongFlag1 = false;
                    btnVPN.setImageResource(R.drawable.implement_vpn_off);
                    select.start();
                }
            }
        });

        btnSignal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (signalFlag == false)
                {
                    signalFlag = true;
                    btnSignal.setImageResource(R.drawable.analize_incomming_signal_on);
                    select.start();
                }
                else
                {
                    signalFlag = false;
                    btnSignal.setImageResource(R.drawable.analize_incomming_signal_off);
                    select.start();
                }
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (profileFlag == false)
                {
                    profileFlag = true;
                    btnProfile.setImageResource(R.drawable.profile_web_activity_on);
                    select.start();
                }
                else
                {
                    profileFlag  = false;
                    btnProfile.setImageResource(R.drawable.profile_web_activity_off);
                    select.start();
                }
            }
        });

        btnHoneypots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (honeypotsFlag == false)
                {
                    honeypotsFlag = true;
                    btnHoneypots.setImageResource(R.drawable.activate_honeypots_on);
                    select.start();
                }
                else
                {
                    honeypotsFlag = false;
                    btnHoneypots.setImageResource(R.drawable.activate_honeypots_off);
                    select.start();
                }
            }
        });

        btnAllConn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wrongFlag2 == false)
                {
                    wrongFlag2 = true;
                    btnAllConn.setImageResource(R.drawable.accept_all_connection_on);
                    select.start();
                }
                else
                {
                    wrongFlag2 = false;
                    btnAllConn.setImageResource(R.drawable.accept_all_connection_off);
                    select.start();
                }
            }
        });
        btnTurnoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wrongFlag3 == false)
                {
                    wrongFlag3 = true;
                    btnTurnoff.setImageResource(R.drawable.turn_off_firewall_on);
                    select.start();
                }
                else
                {
                    wrongFlag3 = false;
                    btnTurnoff.setImageResource(R.drawable.turn_off_firewall_off);
                    select.start();
                }
            }
        });

        btnReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (profileFlag==true && signalFlag==true && honeypotsFlag==true && wrongFlag1==false && wrongFlag2==false && wrongFlag3==false)
                {
                    Intent nextIntent = new Intent(PreLevelThreeActivity.this, LevelThreeActivity.class);
                    nextIntent.putExtra("TotalScore", totalScore);
                    nextIntent.putExtra("Username", Username);
                    startActivity(nextIntent);
                    PreLevelThreeActivity.this.finish();
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
