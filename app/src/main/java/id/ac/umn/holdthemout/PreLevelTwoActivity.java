package id.ac.umn.holdthemout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PreLevelTwoActivity extends AppCompatActivity {

    private ImageButton btnSSH, btnIDS, btnARP, btnTurnon, btnInstall,btnActivate, btnBlock;
    private Button btnReady;
    private TextView CHEAT;
    public boolean SSHFlag=false, IDSFlag=false, ARPFlag=false, turnonFlag=false, wrongFlag1=false, wrongFlag2=false, wrongFlag3=false;
    private MediaPlayer bgm;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preleveltwo);

        CHEAT = findViewById(R.id.command);
        btnInstall = findViewById(R.id.installantivirusbtn);
        btnSSH = findViewById(R.id.implementsshbtn);
        btnIDS = findViewById(R.id.implementidsbtn);
        btnARP = findViewById(R.id.implementarpwatchbtn);
        btnTurnon = findViewById(R.id.turnonfirewallbtn);
        btnActivate = findViewById(R.id.activatehoneypotsbtn);
        btnBlock = findViewById(R.id.blockalltrafficbtn);
        btnReady= findViewById(R.id.readybtn);

        btnInstall.setImageResource(R.drawable.install2_off);
        btnSSH.setImageResource(R.drawable.ssh_off);
        btnIDS.setImageResource(R.drawable.ids_off);
        btnARP.setImageResource(R.drawable.arp_off);
        btnTurnon.setImageResource(R.drawable.firewall_off);
        btnActivate.setImageResource(R.drawable.honeypot_off);
        btnBlock.setImageResource(R.drawable.block_off);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        bgm = MediaPlayer.create(PreLevelTwoActivity.this, R.raw.preattacktest);
        bgm.start();
        bgm.setLooping(true);

        final MediaPlayer select = MediaPlayer.create(PreLevelTwoActivity.this, R.raw.select);
        final MediaPlayer wrong = MediaPlayer.create(PreLevelTwoActivity.this, R.raw.wrong);

        CHEAT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CHEATIntent = new Intent(PreLevelTwoActivity.this, LevelTwoActivity.class);
                startActivity(CHEATIntent);
            }
        });

        btnInstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wrongFlag1 == false)
                {
                    wrongFlag1 = true;
                    btnInstall.setImageResource(R.drawable.install2_on);
                    select.start();

                }
                else
                {
                    wrongFlag1 = false;
                    btnInstall.setImageResource(R.drawable.install2_off);
                    select.start();

                }
            }
        });

        btnIDS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IDSFlag == false)
                {
                    IDSFlag = true;
                    btnIDS.setImageResource(R.drawable.ids_on);
                    select.start();

                }
                else
                {
                    IDSFlag = false;
                    btnIDS.setImageResource(R.drawable.ids_off);
                    select.start();
                }
            }
        });

        btnSSH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SSHFlag == false)
                {
                    SSHFlag = true;
                    btnSSH.setImageResource(R.drawable.ssh_on);
                    select.start();
                }
                else
                {
                    SSHFlag  = false;
                    btnSSH.setImageResource(R.drawable.ssh_off);
                    select.start();
                }
            }
        });

        btnARP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ARPFlag == false)
                {
                    ARPFlag = true;
                    btnARP.setImageResource(R.drawable.arp_on);
                    select.start();
                }
                else
                {
                    ARPFlag = false;
                    btnARP.setImageResource(R.drawable.arp_off);
                    select.start();
                }
            }
        });

        btnTurnon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turnonFlag == false)
                {
                    turnonFlag = true;
                    btnTurnon.setImageResource(R.drawable.firewall_on);
                    select.start();
                }
                else
                {
                    turnonFlag = false;
                    btnTurnon.setImageResource(R.drawable.firewall_off);
                    select.start();
                }
            }
        });

        btnActivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wrongFlag2 == false)
                {
                    wrongFlag2 = true;
                    btnActivate.setImageResource(R.drawable.honeypot_on);
                    select.start();
                }
                else
                {
                    wrongFlag2 = false;
                    btnActivate.setImageResource(R.drawable.honeypot_off);
                    select.start();
                }
            }
        });
        btnBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wrongFlag3 == false)
                {
                    wrongFlag3 = true;
                    btnBlock.setImageResource(R.drawable.block_on);
                    select.start();
                }
                else
                {
                    wrongFlag3 = false;
                    btnBlock.setImageResource(R.drawable.block_off);
                    select.start();
                }
            }
        });

        btnReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SSHFlag==true && IDSFlag==true && ARPFlag==true && turnonFlag==true && wrongFlag1==false && wrongFlag2==false && wrongFlag3==false)
                {
                    Intent nextIntent = new Intent(PreLevelTwoActivity.this, LevelTwoActivity.class);
                    startActivity(nextIntent);
                    PreLevelTwoActivity.this.finish();
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
