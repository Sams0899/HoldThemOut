package id.ac.umn.holdthemout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class GlobalHighScoreActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference ref;
    String username, value;
    int highscore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_high_score);

        final LinearLayout ll = findViewById(R.id.linearlayout);

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

                     for(DataSnapshot ds : dataSnapshot.getChildren())
                     {
                         bufferUsername.append(" " + ds.child("uname").getValue(String.class) + "           " + ds.child("hscore").getValue(Long.class) + "\n\n");
//                         bufferHighscore.append(" " + ds.child("hscore").getValue(Long.class));
                     }

//                 highscore = (Integer) dataSnapshot.child("hscore").getValue();


//                 bufferUsername.append(dataSnapshot.getValue().toString());
                     User user = dataSnapshot.getValue(User.class);
//                     username = user.getUname();


//                 bufferUsername.append(dataSnapshot.child(""));
//                 bufferHighscore.append(highscore);

                 TextView tv = new TextView(getApplicationContext());
                 LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                         LinearLayout.LayoutParams.WRAP_CONTENT,
                         LinearLayout.LayoutParams.MATCH_PARENT);
                 tv.setTextSize(30);
                 tv.setTextColor(Color.parseColor("#ffffff"));
                 tv.setLayoutParams(lp);
                 tv.setText(bufferUsername.toString());
                 ll.addView(tv);

//                 TextView tv2 = new TextView(getApplicationContext());
//                 tv2.setTextSize(30);
//                 tv2.setTextColor(Color.parseColor("#ffffff"));
//                 tv2.setLayoutParams(lp);
//                 tv2.setText(bufferHighscore.toString());
//                 ll.addView(tv2);
                 }

             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });

    }
}
