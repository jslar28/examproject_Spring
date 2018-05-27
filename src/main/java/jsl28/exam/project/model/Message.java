package jsl28.exam.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Message implements Serializable {

    @Id @GeneratedValue
    private int id;
    private String text;
    private String senderName;
    private String senderEmail;
    private String senderPhone;
    //private Date date;

    public Message(String text, String senderName, String senderEmail, String senderPhone/*, Date date*/) {
        this.text = text;
        this.senderName = senderName;
        this.senderEmail = senderEmail;
        this.senderPhone = senderPhone;
        //this.date = date;
    }

    public Message() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    /*public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
*/
    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", senderName='" + senderName + '\'' +
                ", senderEmail='" + senderEmail + '\'' +
                ", senderPhone='" + senderPhone + '\'' +
                //", date=" + date +
                '}';
    }
}
