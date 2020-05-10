package id.ac.umn.holdthemout;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

public class About extends AppCompatActivity {
    private TextView nama1, nama2, nama3, nama4, heading;
    private static int splashTimeOut=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        nama1 = findViewById(R.id.nama1);
        nama2 = findViewById(R.id.nama2);
        nama3 = findViewById(R.id.nama3);
        nama4 = findViewById(R.id.nama4);
        heading = findViewById(R.id.heading);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(About.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, splashTimeOut);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.splash);
        nama1.startAnimation(myanim);
        nama2.startAnimation(myanim);
        nama3.startAnimation(myanim);
        nama4.startAnimation(myanim);
        heading.startAnimation(myanim);
    }
}
