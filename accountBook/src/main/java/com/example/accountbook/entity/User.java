package com.example.accountbook.entity;

public class User {
    private String username;
    private String password;
    private String update_time;
    private String update_id;
    private String create_time;
    private String create_id;
    private int delete_flg;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(String update_id) {
        this.update_id = update_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCreate_id() {
        return create_id;
    }

    public void setCreate_id(String create_id) {
        this.create_id = create_id;
    }

    public int getDelete_flg() {
        return delete_flg;
    }

    public void setDelete_flg(int delete_flg) {
        this.delete_flg = delete_flg;
    }

    public String getUsername() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
