package id.ac.umn.holdthemout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PreLevelOneActivity extends AppCompatActivity {

    private ImageButton btnInstall, btnDistribute, btnBackup, btnImplement, btnActivate;
    private Button btnReady;
    private TextView CHEAT;
    public boolean installFlag=false, distributeFlag=false, backupFlag=false, wrongFlag1=false, wrongFlag2=false;
    private LinearLayout.LayoutParams params;
    private MediaPlayer bgm;
    private Vibrator vibrator;
    String Username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prelevelone);

        Intent intent = getIntent();
        Username = intent.getStringExtra("Username");

        CHEAT = findViewById(R.id.command);
        btnInstall = findViewById(R.id.installantivirusbtn);
        btnDistribute = findViewById(R.id.distributepolicybtn);
        btnBackup = findViewById(R.id.backupdatabtn);
        btnImplement = findViewById(R.id.implementvpnbtn);
        btnActivate = findViewById(R.id.activatehoneypotsbtn);
        btnReady= findViewById(R.id.readybtn);

        btnInstall.setImageResource(R.drawable.install_off);
        btnDistribute.setImageResource(R.drawable.distribute_off);
        btnImplement.setImageResource(R.drawable.implement_off);
        btnActivate.setImageResource(R.drawable.activate_off);
        btnBackup.setImageResource(R.drawable.backup_off);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        bgm = MediaPlayer.create(PreLevelOneActivity.this, R.raw.preattacktest);
        bgm.start();
        bgm.setLooping(true);

        final MediaPlayer mediaPlayer = MediaPlayer.create(PreLevelOneActivity.this, R.raw.select);
        final MediaPlayer wrong = MediaPlayer.create(PreLevelOneActivity.this, R.raw.wrong);

//        params = (LinearLayout.LayoutParams) btnInstall.getLayoutParams();
//        params.height = 75;
//        params.width = 150;
//        btnInstall.setLayoutParams(params);
//        btnInstall.setScaleType(ImageView.ScaleType.FIT_XY);
//        btnDistribute

        CHEAT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent CHEATIntent = new Intent(PreLevelOneActivity.this, LevelOneActivity.class);
                bgm.stop();
                startActivity(CHEATIntent);
            }
        });

        btnInstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (installFlag == false)
                {
                    mediaPlayer.start();
                    installFlag = true;
                    btnInstall.setImageResource(R.drawable.install_on);

                }
                else
                {
                    mediaPlayer.start();
                    installFlag = false;
                    btnInstall.setImageResource(R.drawable.install_off);

                }
            }
        });

        btnDistribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (distributeFlag == false)
                {
                    mediaPlayer.start();
                    distributeFlag = true;
                    btnDistribute.setImageResource(R.drawable.distribute_on);
                }
                else
                {
                    mediaPlayer.start();
                    distributeFlag = false;
                    btnDistribute.setImageResource(R.drawable.distribute_off);
                }
            }
        });

        btnBackup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (backupFlag == false)
                {
                    mediaPlayer.start();
                    backupFlag = true;
                    btnBackup.setImageResource(R.drawable.backup_on);
                }
                else
                {
                    mediaPlayer.start();
                    backupFlag = false;
                    btnBackup.setImageResource(R.drawable.backup_off);
                }
            }
        });

        btnImplement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wrongFlag1 == false)
                {
                    mediaPlayer.start();
                    wrongFlag1 = true;
                    btnImplement.setImageResource(R.drawable.implement_on);
                }
                else
                {
                    mediaPlayer.start();
                    wrongFlag1 = false;
                    btnImplement.setImageResource(R.drawable.implement_off);
                }
            }
        });

        btnActivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wrongFlag2 == false)
                {
                    mediaPlayer.start();
                    wrongFlag2 = true;
                    btnActivate.setImageResource(R.drawable.activate_on);
                }
                else
                {
                    mediaPlayer.start();
                    wrongFlag2 = false;
                    btnActivate.setImageResource(R.drawable.activate_off);
                }
            }
        });

        btnReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (installFlag==true && distributeFlag==true && backupFlag==true && wrongFlag1==false && wrongFlag2==false)
                {
                    Intent nextIntent = new Intent(PreLevelOneActivity.this, LevelOneActivity.class);
                    bgm.stop();
                    nextIntent.putExtra("Username",Username);
                    startActivity(nextIntent);
                    PreLevelOneActivity.this.finish();
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

