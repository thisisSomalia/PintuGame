package com.example.ctgu.login_test;



public class UsersInfo {
    public int _id;
    public String name;
    public String password;
    public int age;
    public double score;
    public String info;

    public UsersInfo(){

    }

    public UsersInfo(String name){
        this.name = name;
    }
    public UsersInfo(String name,String password){
        this.name = name;
        this.password = password;
    }

    public UsersInfo(String name,int age,String info,double score){
        this.name = name;
        this.age = age;
        this.info = info;
        this.score = score;
    }
    public UsersInfo(String name,String password,int age,String info){
        this.name = name;
        this.password = password;
        this.age = age;
        this.info = info;
    }

    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
