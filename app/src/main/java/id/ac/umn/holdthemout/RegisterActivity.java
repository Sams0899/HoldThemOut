package id.ac.umn.holdthemout;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    private EditText firstname, lastname, username, password;
    String fname, lname, uname, pass;
    private Button registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sqLiteDatabase=openOrCreateDatabase("htodb", Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS User(UserID INTEGER PRIMARY KEY AUTOINCREMENT, Username VARCHAR(50), FirstName VARCHAR(50), LastName VARCHAR(50), Password VARCHAR(50), Highscore INTEGER );");

        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        registerBtn = findViewById(R.id.Btnregister);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fname = firstname.getText().toString().trim();
                lname = lastname.getText().toString().trim();
                uname = username.getText().toString().trim();
                pass = password.getText().toString().trim();
                if(fname.equals("") || lname.equals("") || uname.equals("") || password.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Fields can not be empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    sqLiteDatabase.execSQL("Insert Into htodb(Username, FirstName, LastName, Password) VALUES('" + uname + "','" + fname + "','" + lname +"','" + pass + "');");
                    Toast.makeText(getApplicationContext(), "Record Saved", Toast.LENGTH_SHORT).show();

                    Intent nextIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(nextIntent);
                    RegisterActivity.this.finish();
                }
            }
        });
    }
}

