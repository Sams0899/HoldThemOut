package id.ac.umn.holdthemout;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnStart, btnSetting, btnHowToPlay, btnProfile, btnHighScore, btnAbout;
    private MediaPlayer bgm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.startbtn);
        btnSetting = findViewById(R.id.startbtn);
        btnHowToPlay = findViewById(R.id.howtoplaybtn);
        btnProfile = findViewById(R.id.profilebtn);
        btnHighScore = findViewById(R.id.highscorebtn);
        btnAbout = findViewById(R.id.aboutbtn);

        bgm = MediaPlayer.create(MainActivity.this, R.raw.bgmmenu);
        bgm.start();
        bgm.setLooping(true);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(MainActivity.this, PreLevelOneActivity.class);
                bgm.pause();
                startActivity(startIntent);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(MainActivity.this, About.class);
                bgm.pause();
                startActivity(startIntent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bgm.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bgm.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bgm.pause();
    }
}
