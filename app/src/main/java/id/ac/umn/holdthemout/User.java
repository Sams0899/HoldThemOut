package id.ac.umn.holdthemout;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

import java.io.Serializable;
@Entity
public class User{
    public String uname;
    public Integer hscore;

    public User() {
    }

    public String getData(){
        return this.uname + this.hscore;
    }

    public User(Integer hscore, String uname) {
        this.uname = uname;
        this.hscore = hscore;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Integer getHscore() {
        return hscore;
    }

    public void setHscore(String Integer) {
        this.hscore = hscore;
    }
}
