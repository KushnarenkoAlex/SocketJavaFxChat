package server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DataHolder {

    @Override
    public String toString() {
        return "DataHolder{" +
                "users=" + users +
                ", messages=" + messages +
                '}';
    }

    public DataHolder() {
        users = new HashMap<>();
        messages = new ArrayList<>();
    }

    HashMap<String, Socket> users;
    List<Message> messages;

}

class Message {
    String user;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    String text;
    Date date;
    String sopa;

    public Message(String user, String text, Date date, String sopa) {
        this.user = user;
        this.text = text;
        this.date = date;
        this.sopa = sopa;
    }

    public String getSopa() {

        return sopa;
    }

    public void setSopa(String sopa) {
        this.sopa = sopa;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Message(String user, String text, Date date) {

        this.user = user;
        this.text = text;
        this.date = date;
    }
}
