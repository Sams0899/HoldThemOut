package id.ac.umn.holdthemout;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class HighScoreActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    Button globalhighscorebtn;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        sqLiteDatabase=openOrCreateDatabase("htodb", Context.MODE_PRIVATE, null);

        LinearLayout ll = findViewById(R.id.linearlayout);
        LinearLayout mainll = findViewById(R.id.mainlinearlayout);
        globalhighscorebtn = findViewById(R.id.GlobalHSBtn);

        Cursor c = sqLiteDatabase.rawQuery("Select * From User Order By Highscore * 1 Desc",null);

        if(c.getCount()==0)
        {
            Toast.makeText(HighScoreActivity.this, "No data", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StringBuffer buffer = new StringBuffer();
            StringBuffer bufferusername = new StringBuffer();
            while(c.moveToNext())
            {
                buffer.append(""+c.getString(2)+"\n\n");
                bufferusername.append(c.getString(1));
            }
            TextView tv = new TextView(getApplicationContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            tv.setTextSize(30);
            tv.setTextColor(Color.parseColor("#817CEE"));
            tv.setLayoutParams(lp);
            tv.setText(buffer.toString());
            ll.addView(tv);

            TextView tvusername = new TextView(getApplicationContext());
            LinearLayout.LayoutParams lpusername = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            tvusername.setTextSize(20);
            tvusername.setTextColor(Color.parseColor("#817CEE"));
            tvusername.setLayoutParams(lpusername);
            tvusername.setText(bufferusername.toString());
            mainll.addView(tvusername);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        HighScoreActivity.this.finish();
    }

}
