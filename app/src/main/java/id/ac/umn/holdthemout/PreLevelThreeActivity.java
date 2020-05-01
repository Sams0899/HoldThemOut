package id.ac.umn.holdthemout;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PreLevelThreeActivity extends AppCompatActivity {
    private Button btnProfile, btnSignal, btnHoneypots, btnTurnoff, btnVPN,btnAllConn, btnReady;
    private TextView CHEAT;
    public boolean profileFlag=false, signalFlag=false, honeypotsFlag=false, wrongFlag1=false, wrongFlag2=false, wrongFlag3=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prelevelthree);


        btnProfile = findViewById(R.id.profileweb);
        btnSignal = findViewById(R.id.analyzesign);
        btnHoneypots = findViewById(R.id.honeypotsactive);
        btnTurnoff = findViewById(R.id.turnofffirewallbtn);
        btnVPN = findViewById(R.id.implementvpn);
        btnAllConn = findViewById(R.id.acceptconnect);
        btnReady= findViewById(R.id.readybtn);

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
                }
                else
                {
                    wrongFlag1 = false;
                }
            }
        });

        btnSignal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (signalFlag == false)
                {
                    signalFlag = true;
                }
                else
                {
                    signalFlag = false;
                }
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (profileFlag == false)
                {
                    profileFlag = true;
                }
                else
                {
                    profileFlag  = false;
                }
            }
        });

        btnHoneypots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (honeypotsFlag == false)
                {
                    honeypotsFlag = true;
                }
                else
                {
                    honeypotsFlag = false;
                }
            }
        });

        btnAllConn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wrongFlag2 == false)
                {
                    wrongFlag2 = true;
                }
                else
                {
                    wrongFlag2 = false;
                }
            }
        });
        btnTurnoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (wrongFlag3 == false)
                {
                    wrongFlag3 = true;
                }
                else
                {
                    wrongFlag3 = false;
                }
            }
        });

        btnReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (profileFlag==true && signalFlag==true && honeypotsFlag==true && wrongFlag1==false && wrongFlag2==false && wrongFlag3==false)
                {
                    Intent nextIntent = new Intent(PreLevelThreeActivity.this, LevelThreeActivity.class);
                    startActivity(nextIntent);
                    PreLevelThreeActivity.this.finish();
                }
            }
        });
    }
}
