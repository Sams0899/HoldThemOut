package id.ac.umn.holdthemout;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HowToPlayActivity extends AppCompatActivity {
    public int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
        final ImageView imageView = findViewById(R.id.Image1);
        Button btnImageChanger = findViewById(R.id.nextbtn);
        final TextView desc = findViewById(R.id.description);
        btnImageChanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id++;
                if(id==1) {
                    imageView.setImageResource(R.drawable.command);
                    desc.setText("During the Attack Stage, press the right button related with the command");
                }else if(id==2){
                    imageView.setImageResource(R.drawable.timer);
                    desc.setText("Look out for the Timer. The command will change after the time is up");
                }else if(id==3){
                    imageView.setImageResource(R.drawable.score);
                    desc.setText("Try to finish each command as fast as possible for a better score");
                }else if(id==4){
                    Intent startIntent = new Intent(HowToPlayActivity.this, MainActivity.class);
                    startActivity(startIntent);
                    HowToPlayActivity.this.finish();
                }
            }
        });
    }
}
