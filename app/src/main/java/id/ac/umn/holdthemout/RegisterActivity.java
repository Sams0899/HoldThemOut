package id.ac.umn.holdthemout;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private UserRoomDatabase userDatabase;
    private EditText firstname, lastname, username, password;
    private Button registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userDatabase = Room.databaseBuilder(getApplicationContext(),
                UserRoomDatabase.class, "userdatabase").build();

        new DatabaseAsync().execute();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(registerIntent);
                RegisterActivity.this.finish();
            }
        });
    }

    private class DatabaseAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //Perform pre-adding operation here.
        }

        @Override
        protected Void doInBackground(Void... voids) {

            User user = new User();
            user.setUsername("admin");
            user.setFirstname("Admin");
            user.setLastname("Admin");
            user.setPassword("admin");
            user.setHighscore(1000);

            userDatabase.daoUser().insert(user);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            //To after addition operation here.
        }
    }
}
