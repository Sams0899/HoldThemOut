package id.ac.umn.holdthemout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GlobalHighScoreActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference ref;
    Button btnlocalhs;
    ArrayList<String> dataArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_high_score);

        final LinearLayout ll = findViewById(R.id.linearlayout);
        btnlocalhs = findViewById(R.id.LocalHSBtn);

        final MediaPlayer select = MediaPlayer.create(GlobalHighScoreActivity.this, R.raw.select);

         database = FirebaseDatabase.getInstance();
         ref = database.getReference("Users");

         Query query = ref.orderByChild("hscore");
         final User user = new User();

         query.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 if(dataSnapshot.getValue() == null)
                 {
                     Log.d("NULL", "ISI NULLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
                 }
                 else
                 {
                     Log.d("NULL", "NOTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT    NULLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");

                     StringBuffer bufferUsername = new StringBuffer();
                     StringBuffer bufferHighscore = new StringBuffer();

                     dataArr = new ArrayList<>();

                     for(DataSnapshot ds : dataSnapshot.getChildren())
                     {
                         dataArr.add(" " + ds.child("uname").getValue(String.class) + "           " + ds.child("hscore").getValue(Long.class) + "\n\n");
//                       bufferUsername.append(" " + ds.child("uname").getValue(String.class) + "           " + ds.child("hscore").getValue(Long.class) + "\n\n");
                     }

                     Collections.reverse(dataArr);
                     User user = dataSnapshot.getValue(User.class);

                 TextView tv = new TextView(getApplicationContext());
                 LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                         LinearLayout.LayoutParams.WRAP_CONTENT,
                         LinearLayout.LayoutParams.MATCH_PARENT);
                 tv.setTextSize(30);
                 tv.setTextColor(Color.parseColor("#ffffff"));
                 tv.setLayoutParams(lp);

//                 int arraySize = dataArr.size();
//                 for(int i = 0; i < arraySize; i++) {
//                     tv.append(dataArr[i]);
//                 }
                     String listString = "";

                     for (String s : dataArr) {
                         listString += s + " ";
                     }
                     tv.setText(listString);
//                 tv.setText(dataArr.toString());
                 ll.addView(tv);
                 }

             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });

         btnlocalhs.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 select.start();
                 Intent intent = new Intent(GlobalHighScoreActivity.this, HighScoreActivity.class);
                 startActivity(intent);
                 GlobalHighScoreActivity.this.finish();
             }
         });

    }
}
