package id.ac.umn.holdthemout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity{

    SQLiteDatabase sqLiteDatabase;
    private EditText username;
    String uname;
    private ImageButton loginButton;
    SharedPreferences loginPref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sqLiteDatabase=openOrCreateDatabase("htodb", Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS User(Id INTEGER PRIMARY KEY AUTOINCREMENT, Username VARCHAR(50), Highscore VARCHAR(10))");


        username = findViewById(R.id.username);
        loginButton = findViewById(R.id.Btnlogin);
        loginButton.setImageResource(R.drawable.play);
        loginPref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isLogin = loginPref.getBoolean("isLogin", false);

        if(isLogin)
        {
            loginPref.edit().putBoolean("isLogin", true).commit();
            Intent nextIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(nextIntent);
            LoginActivity.this.finish();
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname = username.getText().toString().trim();
                if(uname.equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Username can not be empty",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    sqLiteDatabase.execSQL("Insert Into User(Username, Highscore)VALUES('" + uname + "',0)") ;
                    Toast.makeText(LoginActivity.this, "Profile Saved",Toast.LENGTH_SHORT).show();
                    Intent nextIntent = new Intent(LoginActivity.this, MainActivity.class);
                    nextIntent.putExtra("Username",uname);
                    loginPref.edit().putBoolean("isLogin", true).apply();
                    startActivity(nextIntent);
                    LoginActivity.this.finish();
                }
            }
        });

    }
}
