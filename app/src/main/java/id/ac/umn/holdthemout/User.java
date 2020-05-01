package id.ac.umn.holdthemout;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

import java.io.Serializable;
@Entity
public class User{
    String fname;
    String lname;
    String uname;
    String pass;

    public User() {
    }

    public User( String fname, String lname, String uname, String pass) {
        this.fname = fname;
        this.lname = lname;
        this.uname = uname;
        this.pass = pass;
    }


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
