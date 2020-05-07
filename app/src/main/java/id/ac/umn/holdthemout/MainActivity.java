package id.ac.umn.holdthemout;

import android.content.Intent;
import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnStart, btnSetting, btnHowToPlay, btnProfile, btnHighScore, btnAbout;
    private MediaPlayer bgm;
    String Username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Username = intent.getStringExtra("Username");

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
                startIntent.putExtra("Username", Username);
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

        btnHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(MainActivity.this, HighScoreActivity.class);
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
