package edu.hitsz.application;

import java.text.SimpleDateFormat;
import java.util.Date;

public class scoreRecord implements Comparable<scoreRecord>{
    private int score;

    private Date date;

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm");

    public int rank ;

    private String UserName = "testUserName";

    public void setUserName(String UserName){
        this.UserName = UserName;
    }

    public String getUserName(){
        return this.UserName;
    }
    public scoreRecord(int score){
        this.score = score;
        this.date = new Date();
    }

    public scoreRecord(int score, String UserName){
        this.UserName = UserName;
        this.score = score;
        this.date = new Date();
    }

    public scoreRecord(int score, Date date, String UserName){
        this.UserName = UserName;
        this.score = score;
        this.date = date;
    }

    public scoreRecord(int score, Date date){
        this.score = score;
        this.date = date;
    }

    public scoreRecord(String data) throws Exception{
        String[] data_split = data.split(",");
        this.UserName = data_split[0];
        this.score = Integer.parseInt(data_split[1]);
        this.date = formatter.parse(data_split[2]);
    }

    public void print(){
        System.out.println("第" + rank + "名：" + UserName + ", " + score + ", " + formatter.format(date) + '\n');

    }

    public String toString(){
        return UserName + "," + score + "," + formatter.format(date) + '\n' ;
    }

    public int compareTo(scoreRecord o){
        if(this.score == o.score) {
            return this.date.compareTo(o.date);
        }
        return  o.score - this.score;
    }
}
