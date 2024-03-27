package model;

import repositories.DiaryRepository;

import java.util.List;

public class Diary{
    private String username;
    private String password;
    private int id;
    private boolean isLocked;

    public boolean isLocked(){
        return  isLocked;
    }
    public void lockDiary(){
        isLocked = true;
    }
    public void unLockDiary() {
        isLocked = false;

    }
    public Diary(String username, String password) {
        this.username = username;
        this.password =password;
    }

    public Diary() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }
}
