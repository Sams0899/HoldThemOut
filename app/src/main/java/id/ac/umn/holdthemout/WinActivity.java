package id.ac.umn.holdthemout;

import android.content.Intent;
import android.media.MediaPlayer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class WinActivity extends AppCompatActivity {

    private MediaPlayer bgm;
    ImageButton btnMenu;
    int totalScore;
    TextView score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        Intent intent = getIntent();
        totalScore = intent.getIntExtra("TotalScore",0);

        btnMenu = findViewById(R.id.mainmenubtn);
        score = findViewById(R.id.score);

        btnMenu.setImageResource(R.drawable.to_main_menu);

        score.setText("Your Final Score: " + totalScore);

        bgm = MediaPlayer.create(WinActivity.this, R.raw.win);
        bgm.start();

        final MediaPlayer select = MediaPlayer.create(WinActivity.this, R.raw.select);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select.start();
                Intent intent = new Intent(WinActivity.this, MainActivity.class);
                startActivity(intent);
                WinActivity.this.finish();
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
