package id.ac.umn.holdthemout;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "User")
public class User implements Serializable{
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "Username")
    private String username;

    @ColumnInfo(name = "First_Name")
    private String firstname;

    @ColumnInfo(name = "Last_Name")
    private String lastname;

    @ColumnInfo(name = "Password")
    private String password;

    @ColumnInfo(name = "High_Score")
    private Integer highscore;

//    public User(String username, String firstname, String lastname,
//                     String password, Integer highscore){
//        this.username = username;
//        this.firstname = firstname;
//        this.lastname = lastname;
//        this.password = password;
//        this.highscore = highscore;
//    }

    public void setUsername(String username) {this.username = username; }
    public void setFirstname(String firstname) {this.firstname = firstname; }
    public void setLastname(String lastname) {this.lastname = lastname;}
    public void setPassword(String password){this.password = password; }
    public void setHighscore(Integer highscore) {this.highscore = highscore; }

    public String getUsername() { return this.username; }
    public String getFirstname() { return this.firstname; }
    public String getLastname() { return this.lastname;}
    public String getPassword() { return this.password;}
    public Integer getHighscore() { return this.highscore;}

}
