package id.ac.umn.holdthemout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class HighScoreActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    Button globalhighscorebtn;
    String username;
    int highscore;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        sqLiteDatabase=openOrCreateDatabase("htodb", Context.MODE_PRIVATE, null);

        LinearLayout ll = findViewById(R.id.linearlayout);
        //ini gw matiin - wisnu
//        LinearLayout mainll = findViewById(R.id.mainlinearlayout);
        TextView uname = findViewById(R.id.username);
        globalhighscorebtn = findViewById(R.id.GlobalHSBtn);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");

        final MediaPlayer select = MediaPlayer.create(HighScoreActivity.this, R.raw.select);

        Cursor c = sqLiteDatabase.rawQuery("Select * From User Order By Highscore * 1 Desc",null);
        Cursor cursorusername = sqLiteDatabase.rawQuery("Select Username From User",null);
        Cursor cursorhighscore = sqLiteDatabase.rawQuery("Select Highscore From User Order By Highscore * 1 Desc",null);

        if(c.getCount()==0)
        {
            Toast.makeText(HighScoreActivity.this, "No data", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StringBuffer buffer = new StringBuffer();

            cursorusername.moveToFirst();
            username = cursorusername.getString(cursorusername.getColumnIndex("Username"));

            cursorhighscore.moveToFirst();
            highscore = cursorhighscore.getInt(cursorhighscore.getColumnIndex("Highscore"));

            while(c.moveToNext())
            {
                buffer.append(""+c.getString(2)+"\n\n");
            }
            TextView tv = new TextView(getApplicationContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            tv.setTextSize(30);
            tv.setTextColor(Color.parseColor("#ffffff"));
            tv.setLayoutParams(lp);
            tv.setText(buffer.toString());
            ll.addView(tv);

            TextView tvusername = new TextView(getApplicationContext());
            LinearLayout.LayoutParams lpusername = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            tvusername.setTextSize(20);
            tvusername.setTextColor(Color.parseColor("#ffffff"));
            tvusername.setLayoutParams(lpusername);
            tvusername.setText(username);

            //ini gw matiin - wisnu
//            mainll.addView(tvusername);
            //gw ganti ini
            uname.setText(username);

            User user = new User(highscore, username);
            myRef.child(username).child("hscore").setValue(highscore);
            myRef.child(username).child("uname").setValue(username);
        }

        globalhighscorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select.start();
                Intent nextIntent = new Intent(HighScoreActivity.this, GlobalHighScoreActivity.class);
                startActivity(nextIntent);
                HighScoreActivity.this.finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        HighScoreActivity.this.finish();
    }

}
