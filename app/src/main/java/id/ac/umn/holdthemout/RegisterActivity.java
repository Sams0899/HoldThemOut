package id.ac.umn.holdthemout;

import androidx.annotation.NonNull;
import androidx.room.Insert;
import androidx.room.Room;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    FirebaseDatabase database;
    FirebaseAuth fAuth;
    DatabaseReference myRef;
    FirebaseFirestore fStore;
    SQLiteDatabase sqLiteDatabase;
    private EditText firstname, lastname, username, password;
    String fname, lname, uname, pass;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        sqLiteDatabase=openOrCreateDatabase("htodb", Context.MODE_PRIVATE, null);
//        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS User(UserID INTEGER PRIMARY KEY AUTOINCREMENT, Username VARCHAR(50), FirstName VARCHAR(50), LastName VARCHAR(50), Password VARCHAR(50), Highscore INTEGER );");

        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        username = findViewById(R.id.email);
        password = findViewById(R.id.password);
        registerBtn = findViewById(R.id.Btnregister);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        fAuth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fname = firstname.getText().toString().trim();
                lname = lastname.getText().toString().trim();
                uname = username.getText().toString().trim();
                pass = password.getText().toString().trim();


                if (fname.equals("") || lname.equals("") || uname.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields can not be empty", Toast.LENGTH_SHORT).show();
                }
//                else {
//                    User user = new User(fname, lname, uname, pass);
//                    myRef.push().setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Toast.makeText(RegisterActivity.this, "Register Successfull", Toast.LENGTH_SHORT).show();
//                            Intent nextIntent = new Intent(RegisterActivity.this, LoginActivity.class);
//                            startActivity(nextIntent);
//                            RegisterActivity.this.finish();
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(RegisterActivity.this, "Register Unsuccessfull", Toast.LENGTH_SHORT).show();
//                        }
//                    });
////                }
//                }
            }
        });
    }
}

