package com.senturk.fatih.chat03;

import java.util.Date;

/**
 * Created by fatih on 13.6.2015.
 */
public class Conversation {
    public static final int STATUS_SENDING = 0;

    /** The Constant STATUS_SENT. */
    public static final int STATUS_SENT = 1;

    /** The Constant STATUS_FAILED. */
    public static final int STATUS_FAILED = 2;
    /** The msg. */
    private String msg;

    /** The status. */
    private int status = STATUS_SENT;

    /** The date. */
    private Date date;

    /** The sender. */
    private String sender;


    public Conversation(String msg,Date date,String sender){
        this.msg=msg;
        this.date=date;
        this.sender=sender;
    }

    public Conversation(){

    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg=msg;
    }

    public boolean isSent(){
        return UserList.user.getUsername().equals(sender);
    }


    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date=date;
    }


    public String getSender(){
        return sender;
    }

    public void setSender(String sender){
        this.sender=sender;
    }

    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status=status;
    }



















}
