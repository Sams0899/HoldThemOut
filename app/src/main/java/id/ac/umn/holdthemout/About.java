package id.ac.umn.holdthemout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class About extends AppCompatActivity {
    private ImageButton btnNeven, btnSamuel, btnWisnu, btnNataya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btnNeven = findViewById(R.id.btnNeven);
        btnSamuel = findViewById(R.id.btnSamuel);
        btnWisnu = findViewById(R.id.btnWisnu);
        btnNataya = findViewById(R.id.btnNataya);

        btnNeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(About.this, Profil_Neven.class);
                startActivity(startIntent);
            }
        });
        btnSamuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(About.this, Profil_Samuel.class);
                startActivity(startIntent);
            }
        });
        btnWisnu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(About.this, Profil_Wisnu.class);
                startActivity(startIntent);
            }
        });
        btnNataya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(About.this, Profil_Nataya.class);
                startActivity(startIntent);
            }
        });
    }
}
