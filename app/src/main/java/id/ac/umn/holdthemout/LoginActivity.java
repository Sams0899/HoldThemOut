package id.ac.umn.holdthemout;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity{

    SQLiteDatabase sqLiteDatabase;
    private EditText username, password;
    String uname, pass;
    private Button loginButton, registerButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sqLiteDatabase = openOrCreateDatabase("htodb", Context.MODE_PRIVATE, null);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.Btnlogin);
        registerButton = findViewById(R.id.Btnregister);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname = username.getText().toString().trim();
                pass = password.getText().toString().trim();

                Cursor c = sqLiteDatabase.rawQuery("Select * From User Where Username='" + uname + "' AND Password='" + pass + "'", null);

                if (uname.equals("") || pass.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Fields can not be empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (c.moveToFirst())
                    {
                        Intent nextIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(nextIntent);
                        LoginActivity.this.finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Username or Password Incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
                LoginActivity.this.finish();
            }
        });

    }
}
